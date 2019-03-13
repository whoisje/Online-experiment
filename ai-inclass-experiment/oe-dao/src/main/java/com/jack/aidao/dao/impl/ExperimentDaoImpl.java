package com.jack.aidao.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jack.aidao.dao.ExperimentDao;
import com.jack.aidao.entity.ExpEntity;
import com.jack.aidao.mapper.ExperimentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wangwj
 * @data 2018/12/26
 */
@Repository
public class ExperimentDaoImpl implements ExperimentDao {
    @Autowired
    private ExperimentMapper experimentMapper;

    @Override
    public List<ExpEntity> selectExpList(String courseId) {
        QueryWrapper<ExpEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .select(ExpEntity::getExperimentTitle, ExpEntity::getExperimentId)
                .eq(ExpEntity::getCourseId, courseId);
        return experimentMapper.selectList(queryWrapper);
    }

    @Override
    public ExpEntity selectExpDetail(String experimentId) {

        QueryWrapper<ExpEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ExpEntity::getExperimentId, experimentId);

        return experimentMapper.selectOne(queryWrapper);
    }
}
