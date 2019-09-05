package cn.com.pg.mpt.toolkit.auth.service;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public interface AuthFileService {

    /** 解析 excel 存储数据库 **/
    void saveExcel2DB(XSSFWorkbook xssfWorkbook);

    /** 转换 excel 为 json **/
    String convertExcel2Json(XSSFWorkbook xssfWorkbook);

    /** 获取全部权限模块的 json **/
    String getJsonAll(String serviceName);

}
