package cn.com.pg.mpt.toolkit.auth.service.impl;

import cn.com.pg.mpt.toolkit.auth.dao.AuthClassDao;
import cn.com.pg.mpt.toolkit.auth.dao.AuthClassElementDao;
import cn.com.pg.mpt.toolkit.auth.entity.AuthClass;
import cn.com.pg.mpt.toolkit.auth.entity.AuthClassElement;
import cn.com.pg.mpt.toolkit.auth.entity.enums.AuthType;
import cn.com.pg.mpt.toolkit.auth.model.AuthJsonElement;
import cn.com.pg.mpt.toolkit.auth.service.AuthClassService;
import cn.com.pg.mpt.toolkit.auth.service.AuthFileService;
import com.alibaba.fastjson.JSON;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthClassServiceImpl implements AuthClassService {

    @Autowired
    private AuthClassDao authClassDao;

    @Autowired
    private AuthClassElementDao authClassElementDao;


    @Override
    public List<AuthClass> getServiceList() {

        return authClassDao.getServiceList();
    }

    @Override
    public List<AuthClass> getAuthClassList(AuthClass condition) {

        return authClassDao.getAuthClassList(condition);
    }
}
