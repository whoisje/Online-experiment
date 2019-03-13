package com.jack.aiservice.impl.service;

import com.jack.aidao.dao.impl.InfoDaoImpl;
import com.jack.aidao.dao.impl.LoginDaoImpl;
import com.jack.aidao.entity.UserEntity;
import com.jack.aidao.entity.UserInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

/**
 * @author wangwj
 * @data 2018/12/17
 */
@Service
@PropertySource("classpath:wxconfig.properties")
public class LoginServiceImpl implements com.jack.aiservice.service.LoginService {
    @Autowired
    private LoginDaoImpl loginDao;
    @Autowired
    private InfoDaoImpl infoDao;
    @Override
    public UserInfoEntity login(String studentId, String password) {
        UserEntity entity = loginDao.login(studentId);
        if (entity == null) {
            return null;
        }
        if (entity.getPassword().equals(password)){
            return infoDao.getUserInfo(studentId);
        }
        return null;
    }
}
