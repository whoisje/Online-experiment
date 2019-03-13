package com.jack.aidao.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jack.aidao.dao.StepsDao;
import com.jack.aidao.entity.StepEntity;
import com.jack.aidao.mapper.StepsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wangwj
 * @data 2019/3/11
 */
@Repository
public class StepsDaoImpl implements StepsDao {

    @Autowired
    private StepsMapper mapper;
    @Override
    public List<StepEntity> selectSteps(String experimentId) {

        QueryWrapper<StepEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(StepEntity::getExperimentId,experimentId);

        return mapper.selectList(wrapper);
    }
}
