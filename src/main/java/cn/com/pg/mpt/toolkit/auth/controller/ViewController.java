package cn.com.pg.mpt.toolkit.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/view")
public class ViewController
{

    @RequestMapping(value = "common/head", method = RequestMethod.GET)
    String head() {
        return "common/head";
    }

    @RequestMapping(value = "common/left", method = RequestMethod.GET)
    String left() {
        return "common/left";
    }

    @RequestMapping(value = "auth/create", method = RequestMethod.GET)
    String authCreate() {
        return "auth/create";
    }

    @RequestMapping(value = "auth/service", method = RequestMethod.GET)
    String authService() {
        return "auth/service";
    }

}
