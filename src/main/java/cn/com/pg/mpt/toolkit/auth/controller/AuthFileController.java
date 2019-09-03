package cn.com.pg.mpt.toolkit.auth.controller;

import cn.com.pg.mpt.toolkit.auth.service.AuthFileService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;


@Controller
@RequestMapping(value = "/auth-file")
public class AuthFileController {

    @Autowired
    private AuthFileService rbacFileService;


    @GetMapping(value = "excel-upload")
    public void excelUpload(@RequestParam(name = "filePath", defaultValue = "") String filePath, HttpServletResponse response) throws IOException {

        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(filePath);

        rbacFileService.saveExcel2DB(xssfWorkbook);
    }


    @GetMapping(value = "excel-json")
    public void excel2Json(@RequestParam(name = "filePath", defaultValue = "") String filePath, HttpServletResponse response) throws IOException {

        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(filePath);

        String resultJson = rbacFileService.convertExcel2Json(xssfWorkbook);

        response.setContentType("application/x-download");
        response.addHeader("Content-Disposition", "attachment;filename=rbac.json");

        OutputStream out = response.getOutputStream();;

        try{
            out.write(resultJson.getBytes());
            out.flush();
        }catch (Exception e) {

        }finally {
            out.close();
        }
    }
}
