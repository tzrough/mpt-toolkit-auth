package cn.com.pg.mpt.toolkit.auth.service;

import cn.com.pg.mpt.toolkit.auth.entity.AuthClass;

import java.util.List;

public interface AuthClassService
{
    /** 获取服务列表 **/
    List<AuthClass> getServiceList();

    /** 获取权限模块列表 **/
    List<AuthClass> getAuthClassList(AuthClass condition);
}
