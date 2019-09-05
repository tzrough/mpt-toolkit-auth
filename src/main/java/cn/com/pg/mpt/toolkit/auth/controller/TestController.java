package cn.com.pg.mpt.toolkit.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TestController {

    @RequestMapping(value = "test")
    public String test(HttpServletRequest request) {

        request.setAttribute("user", "zhangsan");

        return "index1";
    }
}
