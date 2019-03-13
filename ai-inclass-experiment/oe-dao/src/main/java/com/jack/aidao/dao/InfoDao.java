package com.jack.aidao.dao;

import com.jack.aidao.entity.UserInfoEntity;

/**
 * @author wangwj
 * @data 2019/3/6
 */
public interface InfoDao {

    UserInfoEntity getUserInfo(String studentId);
}
