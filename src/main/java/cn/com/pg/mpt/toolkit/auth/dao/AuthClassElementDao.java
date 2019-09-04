package cn.com.pg.mpt.toolkit.auth.dao;

import cn.com.pg.mpt.toolkit.auth.entity.AuthClass;
import cn.com.pg.mpt.toolkit.auth.entity.AuthClassElement;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthClassElementDao {

    /*********************** insert begin ***********************/

    int insertSelective(AuthClassElement record);

    /**
     *  创建权限点表
     **/
    boolean createTable(@Param("tableName") String tableName);

    /*********************** update begin ***********************/

    int updateByPrimaryKeySelective(AuthClassElement record);

    int updateByPrimaryKey(AuthClassElement record);

    /*********************** query begin ***********************/

    /**
     *  检查权限点表是否存在
     **/

    AuthClassElement selectByPrimaryKey(Long id);

    boolean tableExisted(@Param("dbName") String dbName, @Param("tableName") String tableName);

    /**
     * 获取权限模块下权限点
     **/
    List<AuthClassElement> getAuthClassElmenetList(AuthClass condition);

}