package cn.com.pg.mpt.toolkit.auth.service;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public interface RbacJsonService {

    String convertExcelToJson(XSSFWorkbook xssfWorkbook);

}
