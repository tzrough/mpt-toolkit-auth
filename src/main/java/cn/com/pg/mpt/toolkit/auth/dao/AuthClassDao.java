package cn.com.pg.mpt.toolkit.auth.dao;

import cn.com.pg.mpt.toolkit.auth.entity.AuthClass;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthClassDao {

    int updateByPrimaryKeySelective(AuthClass record);

    int updateByPrimaryKey(AuthClass record);

    AuthClass selectByPrimaryKey(Long id);

    /**
     * 查询权限模块最大的版本号
     **/
    Integer getMaxVersion(AuthClass record);

    /**
     * 插入或更新权限模块
     **/
    int insertAuthClass(AuthClass record);

    int deleteByPrimaryKey(Long id);
}