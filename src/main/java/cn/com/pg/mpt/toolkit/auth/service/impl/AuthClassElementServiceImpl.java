package cn.com.pg.mpt.toolkit.auth.service.impl;

import cn.com.pg.mpt.toolkit.auth.dao.AuthClassElementDao;
import cn.com.pg.mpt.toolkit.auth.entity.AuthClass;
import cn.com.pg.mpt.toolkit.auth.entity.AuthClassElement;
import cn.com.pg.mpt.toolkit.auth.service.AuthClassElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthClassElementServiceImpl implements AuthClassElementService {

    @Autowired
    private AuthClassElementDao authClassElementDao;

    @Override
    public List<AuthClassElement> getAuthClassElmenetList(AuthClass condition) {

        return authClassElementDao.getAuthClassElmenetList(condition);
    }
}
