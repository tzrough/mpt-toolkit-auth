package cn.com.pg.mpt.toolkit.auth.service.impl;

import cn.com.pg.mpt.toolkit.auth.entity.RbacAuthElement;
import cn.com.pg.mpt.toolkit.auth.service.RbacJsonService;
import com.alibaba.fastjson.JSON;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RbacJsonServiceImpl implements RbacJsonService {

    @Override
    public String convertExcelToJson(XSSFWorkbook xssfWorkbook) {

        String resultJson;
        List<RbacAuthElement> rbacAuthElementList = new ArrayList<>();

        XSSFSheet authMetaSheet = xssfWorkbook.getSheetAt(0);
        XSSFSheet authDataSheet = xssfWorkbook.getSheetAt(1);

        /** 解析权限元数据 begin **/
        XSSFRow authMeta = authMetaSheet.getRow(1);

        String serviceName = authMeta.getCell(0).getStringCellValue();
        String authType = authMeta.getCell(1).getStringCellValue();
        String authField = authMeta.getCell(2).getStringCellValue();

        String keyPrefix = serviceName + "." + authType + "." + authField + ".";
        /** 解析权限元数据 end **/


        /** 解析权限点数据 begin **/

        // 从第二行开始解析
        for (int rowIndex = 1; rowIndex < authDataSheet.getPhysicalNumberOfRows(); rowIndex++) {

            XSSFRow authEleRow = authDataSheet.getRow(rowIndex);

            if (authEleRow == null) {
                continue;
            }

            RbacAuthElement authEle = new RbacAuthElement();

            String authEleName = authEleRow.getCell(0).getStringCellValue();
            String authEleKey = authEleRow.getCell(1).getStringCellValue();

            authEle.setName(authEleName);
            authEle.setKey(keyPrefix + authEleKey);

            rbacAuthElementList.add(authEle);
        }
        /** 解析权限点数据 end **/

        resultJson = JSON.toJSONString(rbacAuthElementList);

        return resultJson;
    }
}
