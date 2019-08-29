package cn.com.pg.mpt.toolkit.auth.controller;

import cn.com.pg.mpt.toolkit.auth.service.RbacJsonService;
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
@RequestMapping(value = "/rbac-json")
public class RbacJsonController {

    @Autowired
    private RbacJsonService rbacJsonService;

    @GetMapping(value = "excel")
    public void downloadRbacJson(@RequestParam(name = "filePath", defaultValue = "") String filePath, HttpServletResponse response) throws IOException {

        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(filePath);

        String resultJson = rbacJsonService.convertExcelToJson(xssfWorkbook);

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
