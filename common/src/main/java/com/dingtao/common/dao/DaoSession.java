package com.dingtao.common.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.dingtao.common.bean.homepage.LsjlBean;
import com.dingtao.common.bean.login.LoginBean;

import com.dingtao.common.dao.LsjlBeanDao;
import com.dingtao.common.dao.LoginBeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig lsjlBeanDaoConfig;
    private final DaoConfig loginBeanDaoConfig;

    private final LsjlBeanDao lsjlBeanDao;
    private final LoginBeanDao loginBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        lsjlBeanDaoConfig = daoConfigMap.get(LsjlBeanDao.class).clone();
        lsjlBeanDaoConfig.initIdentityScope(type);

        loginBeanDaoConfig = daoConfigMap.get(LoginBeanDao.class).clone();
        loginBeanDaoConfig.initIdentityScope(type);

        lsjlBeanDao = new LsjlBeanDao(lsjlBeanDaoConfig, this);
        loginBeanDao = new LoginBeanDao(loginBeanDaoConfig, this);

        registerDao(LsjlBean.class, lsjlBeanDao);
        registerDao(LoginBean.class, loginBeanDao);
    }
    
    public void clear() {
        lsjlBeanDaoConfig.clearIdentityScope();
        loginBeanDaoConfig.clearIdentityScope();
    }

    public LsjlBeanDao getLsjlBeanDao() {
        return lsjlBeanDao;
    }

    public LoginBeanDao getLoginBeanDao() {
        return loginBeanDao;
    }

}
