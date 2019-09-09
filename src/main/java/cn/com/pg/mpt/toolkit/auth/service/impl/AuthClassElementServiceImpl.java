package cn.com.pg.mpt.toolkit.auth.service.impl;

import cn.com.pg.mpt.toolkit.auth.dao.AuthClassDao;
import cn.com.pg.mpt.toolkit.auth.dao.AuthClassElementDao;
import cn.com.pg.mpt.toolkit.auth.entity.AuthClass;
import cn.com.pg.mpt.toolkit.auth.entity.AuthClassElement;
import cn.com.pg.mpt.toolkit.auth.service.AuthClassElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthClassElementServiceImpl implements AuthClassElementService {

    @Autowired
    private AuthClassDao authClassDao;

    @Autowired
    private AuthClassElementDao authClassElementDao;

    @Override
    public List<AuthClassElement> getAuthClassElmenetList(AuthClass condition) {

        return authClassElementDao.getAuthClassElmenetList(condition);
    }

    @Transactional
    @Override
    public AuthClass saveAuthClassList(List<AuthClassElement> authClassElementList) {
        Long authClassId = authClassElementList.get(0).getAuthClassId();
        // 获取权限模块
        AuthClass authClass = authClassDao.selectByPrimaryKey(authClassId);

        // 更新版本号并入库
        int version = authClassDao.getMaxVersion(authClass) + 1;
        authClass.setVersion(version);
        authClassDao.insertAuthClass(authClass);

        for (AuthClassElement authClassElement : authClassElementList) {
            authClassElement.setTableName(authClass.getAuthTableName());
            authClassElement.setAuthClassId(authClass.getId());
            authClassElementDao.insertSelective(authClassElement);
        }

        return authClass;
    }
}
