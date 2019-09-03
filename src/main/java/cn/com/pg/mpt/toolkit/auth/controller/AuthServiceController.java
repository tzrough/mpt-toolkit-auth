package cn.com.pg.mpt.toolkit.auth.controller;

import cn.com.pg.mpt.toolkit.auth.entity.AuthClass;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/auth-service")
public class AuthServiceController {

    /**
     * 获取权限 service 列表
     **/
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AuthClass>> getServiceList () {

        List<AuthClass> authClassList = new ArrayList<>();

        AuthClass a = new AuthClass();
        a.setAuthServiceName("ept");

        AuthClass b = new AuthClass();
        b.setAuthServiceName("eot");

        AuthClass c = new AuthClass();
        c.setAuthServiceName("dot");

        authClassList.add(a);
        authClassList.add(b);
        authClassList.add(c);

        return new ResponseEntity<>(authClassList, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * 获取权限 service 下权限模块列表
     **/
    @GetMapping(value = "/{serviceName}/{authType}")
    public ResponseEntity<List<AuthClass>> getAuthClassList(@PathVariable(name = "serviceName") @NotNull String serviceName,
                                                            @PathVariable(name = "authType") @NotNull int authType) {

        List<AuthClass> authClassList = new ArrayList<>();

        AuthClass a = new AuthClass();
        a.setId(1L);
        a.setAuthServiceName("ept");
        a.setAuthChName("品牌权限");
        a.setAuthType(1);

        AuthClass b = new AuthClass();
        b.setId(2L);
        b.setAuthServiceName("ept");
        b.setAuthChName("财年权限");
        b.setAuthType(1);


        AuthClass c = new AuthClass();
        c.setId(3L);
        c.setAuthServiceName("ept");
        c.setAuthChName("分类权限");
        c.setAuthType(1);

        authClassList.add(a);
        authClassList.add(b);
        authClassList.add(c);

        return new ResponseEntity<>(authClassList, new HttpHeaders(), HttpStatus.OK);
    }



}
