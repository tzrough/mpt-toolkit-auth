package cn.com.pg.mpt.toolkit.auth.controller;

import cn.com.pg.mpt.toolkit.auth.entity.AuthClass;
import cn.com.pg.mpt.toolkit.auth.entity.AuthClassElement;
import cn.com.pg.mpt.toolkit.auth.service.AuthClassElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/auth-class")
public class AuthClassController {

    @Autowired
    private AuthClassElementService authClassElementService;

    /**
     * 获取权限模块下权限点
     **/
    @PostMapping(value = "/element")
    public ResponseEntity<List<AuthClassElement>> getAuthClassList(@RequestBody AuthClass condition) {

        List<AuthClassElement> authClassElementList = authClassElementService.getAuthClassElmenetList(condition);

        return new ResponseEntity<>(authClassElementList, new HttpHeaders(), HttpStatus.OK);
    }

    /** 更新、存储权限模块下权限点 **/
    @PostMapping(value = "/save")
    public ResponseEntity<AuthClass> saveAuthClassList(@RequestBody List<AuthClassElement> authClassElementList) {
        if(CollectionUtils.isEmpty(authClassElementList)) {
            return new ResponseEntity(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        AuthClass authClass = authClassElementService.saveAuthClassList(authClassElementList);
        return new ResponseEntity(authClass, new HttpHeaders(), HttpStatus.OK);
    }
}
