package cn.com.pg.mpt.toolkit.auth.controller;

import cn.com.pg.mpt.toolkit.auth.service.RbacJsonService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequestMapping(value = "/rbac-json")
public class RbacJsonController {

    @Autowired
    private RbacJsonService rbacJsonService;

    @GetMapping(value = "excel")
    public String downloadRbacJson(@RequestParam(name = "filePath", defaultValue = "") String filePath) throws IOException {

        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(filePath);

        String resultJson = rbacJsonService.convertExcelToJson(xssfWorkbook);

        return resultJson;
    }
}
