package cn.com.pg.mpt.toolkit.auth.service.impl;

import cn.com.pg.mpt.toolkit.auth.dao.AuthClassDao;
import cn.com.pg.mpt.toolkit.auth.dao.AuthClassElementDao;
import cn.com.pg.mpt.toolkit.auth.entity.AuthClass;
import cn.com.pg.mpt.toolkit.auth.entity.AuthClassElement;
import cn.com.pg.mpt.toolkit.auth.model.AuthJsonElement;
import cn.com.pg.mpt.toolkit.auth.entity.enums.AuthType;
import cn.com.pg.mpt.toolkit.auth.service.AuthFileService;
import com.alibaba.fastjson.JSON;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthFileServiceImpl implements AuthFileService {

    @Autowired
    private AuthClassDao authClassDao;

    @Autowired
    private AuthClassElementDao authClassElementDao;


    @Override
    public void saveExcel2DB(XSSFWorkbook xssfWorkbook) {

        XSSFSheet authMetaSheet = xssfWorkbook.getSheetAt(0);
        XSSFSheet authDataSheet = xssfWorkbook.getSheetAt(1);

        // 解析权限元数据
        AuthClass authClass = this.extractAuthMeta(authMetaSheet);

        // 更新版本号并入库
        int version = authClassDao.getMaxVersion(authClass) + 1;
        authClass.setVersion(version);
        authClassDao.insertAuthClass(authClass);

        // 解析权限点数据
        List<AuthClassElement> authClassElements = this.extractAuthList(authDataSheet);

        String dbName = "mpt_toolkit_auth";
        String tableName = authClass.getAuthTableName();

        // 1. 若权限点表不存在, 则先创建权限点表
        if(!authClassElementDao.tableExisted(dbName, tableName)) {
            authClassElementDao.createTable(tableName);
        }

        // 2. 插入权限点数据
        for (AuthClassElement authClassElement : authClassElements) {
            // 生成 JSON KEY 前缀  format: serviceName.type.range.*
            String keyPrefix = generateRbacKeyPrefix(authClass);
            authClassElement.setTableName(tableName);
            authClassElement.setAuthClassId(authClass.getId());
            authClassElement.setRbacKey(keyPrefix + authClassElement.getAuthElement());
            authClassElementDao.insertSelective(authClassElement);
        }
    }

    @Override
    public String convertExcel2Json(XSSFWorkbook xssfWorkbook) {

        String resultJson;

        XSSFSheet authMetaSheet = xssfWorkbook.getSheetAt(0);
        XSSFSheet authDataSheet = xssfWorkbook.getSheetAt(1);

        // 解析权限元数据
        AuthClass authClass = this.extractAuthMeta(authMetaSheet);

        // 解析权限点数据
        List<AuthClassElement> authClassElements = this.extractAuthList(authDataSheet);

        resultJson = convert2json(authClassElements);
        return resultJson;
    }

    @Override
    public String getJsonAll(String serviceName) {

        String resultJson;
        List<AuthClassElement> combineList = new ArrayList<>();

        AuthClass condition = new AuthClass();
        condition.setAuthServiceName(serviceName);
        condition.setIsDeleted(0);

        List<AuthClass> authClassList = authClassDao.getAuthClassList(condition);

        if(!CollectionUtils.isEmpty(authClassList)) {
            for (AuthClass authClass : authClassList) {

                List<AuthClassElement> authClassElmenetList = authClassElementDao.getAuthClassElmenetList(authClass);

                if(!CollectionUtils.isEmpty(authClassElmenetList)) {
                    combineList.addAll(authClassElmenetList);
                }
            }
        }

        resultJson = convert2json(combineList);
        return resultJson;
    }


    /** 解析权限元数据 **/
    private AuthClass extractAuthMeta(XSSFSheet authMetaSheet){

        AuthClass rbacAuth = new AuthClass();

        XSSFRow authMeta = authMetaSheet.getRow(1);

        String serviceName = authMeta.getCell(0).getStringCellValue();
        String authType = authMeta.getCell(1).getStringCellValue();
        String authRange = authMeta.getCell(2).getStringCellValue();
        String authChName = authMeta.getCell(3).getStringCellValue();
        // serviceName + type, e.g ept_data
        String authTableName = (serviceName + "_" + authType).toLowerCase();

        Integer authTypeCode = -1;

        switch (authType) {
            case "function":
                authTypeCode = AuthType.FUNC.getCode();
                break;
            case "data":
                authTypeCode = AuthType.DATA.getCode();
                break;
            default:
        }

        rbacAuth.setAuthServiceName(serviceName);
        rbacAuth.setAuthType(authTypeCode);
        rbacAuth.setAuthRange(authRange);
        rbacAuth.setAuthChName(authChName);
        rbacAuth.setAuthTableName(authTableName);

        return rbacAuth;
    }

    /** 解析权限点数据 **/
    private List<AuthClassElement> extractAuthList(XSSFSheet authDataSheet){

        List<AuthClassElement> authClassElements = new ArrayList<>();

        // 从第二行开始解析
        for (int rowIndex = 1; rowIndex < authDataSheet.getPhysicalNumberOfRows(); rowIndex++) {

            XSSFRow authEleRow = authDataSheet.getRow(rowIndex);

            if (authEleRow == null) {
                continue;
            }

            AuthClassElement authClassEle = new AuthClassElement();

            String authEleName = authEleRow.getCell(0).getStringCellValue();
            String authEle = authEleRow.getCell(1).getStringCellValue();

            authClassEle.setAuthElementName(authEleName);
            authClassEle.setAuthElement(authEle);

            authClassElements.add(authClassEle);
        }

        return authClassElements;
    }

    /** 获取 rbac key 前缀 **/
    private String generateRbacKeyPrefix(AuthClass authClass) {

        StringBuffer keyPrefix = new StringBuffer();
        String authTypeKey = "";

        switch (authClass.getAuthType()) {
            case 1:
                authTypeKey = AuthType.FUNC.getKey();
                break;
            case 2:
                authTypeKey = AuthType.DATA.getKey();
                break;
            default:
        }

        keyPrefix.append(authClass.getAuthServiceName());
        keyPrefix.append(".");
        keyPrefix.append(authTypeKey);
        keyPrefix.append(".");
        keyPrefix.append(authClass.getAuthRange());
        keyPrefix.append(".");

        return keyPrefix.toString();
    }


    /** 生成 rbac json **/
    private String convert2json(List<AuthClassElement> authClassElements){

        List<AuthJsonElement> authJsonElementList = new ArrayList<>();

        if(!CollectionUtils.isEmpty(authClassElements)) {
            for (AuthClassElement ele : authClassElements) {
                AuthJsonElement authJsonElement = new AuthJsonElement();
                authJsonElement.setKey(ele.getRbacKey());
                authJsonElement.setName(ele.getAuthElementName());
                authJsonElementList.add(authJsonElement);
            }
        }
       return JSON.toJSONString(authJsonElementList, true);
    }

}
