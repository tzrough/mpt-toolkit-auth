package cn.com.pg.mpt.toolkit.auth.controller;

import cn.com.pg.mpt.toolkit.auth.entity.AuthClass;
import cn.com.pg.mpt.toolkit.auth.service.AuthClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/auth-service")
public class AuthServiceController {

    @Autowired
    private AuthClassService authClassService;


    /**
     * 获取权限 service 列表
     **/
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AuthClass>> getServiceList () {

        List<AuthClass> authClassList = authClassService.getServiceList();

        return new ResponseEntity<>(authClassList, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * 获取权限 service 下权限模块列表
     **/
    @PostMapping(value = "/auth-class")
    public ResponseEntity<List<AuthClass>> getAuthClassList(@RequestBody AuthClass condition) {

        List<AuthClass> authClassList = authClassService.getAuthClassList(condition);

        return new ResponseEntity<>(authClassList, new HttpHeaders(), HttpStatus.OK);
    }



}
