package com.jack.aidao.dao;

import com.jack.aidao.entity.UserEntity;

/**
 * @author wangwj
 * @data 2018/12/17
 */
public interface LoginDao {
    UserEntity login(String studentId);

}
