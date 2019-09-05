package cn.com.pg.mpt.toolkit.auth.service;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public interface AuthFileService {

    void saveExcel2DB(XSSFWorkbook xssfWorkbook);

    String convertExcel2Json(XSSFWorkbook xssfWorkbook);

}