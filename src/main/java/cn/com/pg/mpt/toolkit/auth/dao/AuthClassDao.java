package cn.com.pg.mpt.toolkit.auth.dao;

import cn.com.pg.mpt.toolkit.auth.entity.AuthClass;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthClassDao {

    /*********************** insert begin ***********************/

    /**
     * 插入或更新权限模块
     **/
    int insertAuthClass(AuthClass record);

    /*********************** update begin ***********************/

    int updateByPrimaryKeySelective(AuthClass record);

    int updateByPrimaryKey(AuthClass record);

    /*********************** query begin ***********************/

    AuthClass selectByPrimaryKey(Long id);

    /**
     * 查询权限模块最大的版本号
     **/
    Integer getMaxVersion(AuthClass record);

    /**
     * 获取服务列表
     **/
    List<AuthClass> getServiceList();

    /** 获取权限模块列表 **/
    List<AuthClass> getAuthClassList(AuthClass condition);


}