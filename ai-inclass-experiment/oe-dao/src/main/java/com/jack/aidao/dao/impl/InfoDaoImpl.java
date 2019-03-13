package com.jack.aidao.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jack.aidao.dao.InfoDao;
import com.jack.aidao.entity.UserInfoEntity;
import com.jack.aidao.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author wangwj
 * @data 2019/3/6
 */
@Repository
public class InfoDaoImpl implements InfoDao {
    @Autowired
    private UserInfoMapper mapper;

    @Override
    public UserInfoEntity getUserInfo(String studentId) {
        QueryWrapper<UserInfoEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UserInfoEntity::getStudentId,studentId);
        return mapper.selectOne(wrapper);
    }
}
