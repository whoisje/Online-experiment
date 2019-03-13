package com.jack.aiservice.service;

import com.jack.aidao.entity.UserInfoEntity;

/**
 * @author wangwj
 * @data 2018/12/17
 */
public interface LoginService {
    UserInfoEntity login(String studentId,String password);


}
