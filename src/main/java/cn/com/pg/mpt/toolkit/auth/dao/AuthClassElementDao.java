package cn.com.pg.mpt.toolkit.auth.dao;

import cn.com.pg.mpt.toolkit.auth.entity.AuthClassElement;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthClassElementDao {

    int deleteByPrimaryKey(Long id);

    int insertSelective(AuthClassElement record);

    AuthClassElement selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AuthClassElement record);

    int updateByPrimaryKey(AuthClassElement record);

    /**
     *  检查权限点表是否存在
     **/
    boolean tableExisted(@Param("dbName") String dbName, @Param("tableName") String tableName);

    /**
     *  创建权限点表
     **/
    boolean createTable(@Param("tableName") String tableName);
}