package cn.com.pg.mpt.toolkit.auth.controller;

import cn.com.pg.mpt.toolkit.auth.service.AuthFileService;
import cn.com.pg.mpt.toolkit.auth.utils.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
@RequestMapping(value = "/auth-file")
public class AuthFileController {

    @Autowired
    private AuthFileService authFileService;


    @GetMapping(value = "excel-upload")
    public void excelUpload(@RequestParam(name = "filePath", defaultValue = "") String filePath, HttpServletResponse response) throws IOException {
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(filePath);
        authFileService.saveExcel2DB(xssfWorkbook);
    }


    @GetMapping(value = "excel-json")
    public void excel2Json(@RequestParam(name = "filePath", defaultValue = "") String filePath, HttpServletResponse response) throws IOException {
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(filePath);
        String resultJson = authFileService.convertExcel2Json(xssfWorkbook);
        IOUtils.downloadFile(response, "rbac.json", resultJson);
    }


    @GetMapping(value = "all-json/{serviceName}")
    public void exportJsonAll(@PathVariable(name = "serviceName") String serviceName, HttpServletResponse response) throws IOException {
        String resultJson = authFileService.getJsonAll(serviceName);
        IOUtils.downloadFile(response, "rbac.json", resultJson);
    }
}
