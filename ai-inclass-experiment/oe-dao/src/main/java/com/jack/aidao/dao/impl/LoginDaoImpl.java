package com.jack.aidao.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jack.aidao.dao.LoginDao;
import com.jack.aidao.entity.UserEntity;
import com.jack.aidao.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author wangwj
 * @data 2018/12/17
 */
@Repository
public class LoginDaoImpl implements LoginDao {

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public UserEntity login(String studentId) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserEntity::getUserId,studentId).select(UserEntity::getPassword);
        return loginMapper.selectOne(queryWrapper);
    }

}
