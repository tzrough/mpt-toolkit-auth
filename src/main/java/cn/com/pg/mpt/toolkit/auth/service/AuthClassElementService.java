package cn.com.pg.mpt.toolkit.auth.service;

import cn.com.pg.mpt.toolkit.auth.entity.AuthClass;
import cn.com.pg.mpt.toolkit.auth.entity.AuthClassElement;

import java.util.List;

public interface AuthClassElementService
{
    /**
     * 获取权限模块下权限点
     **/
    List<AuthClassElement> getAuthClassElmenetList(AuthClass condition);
}
