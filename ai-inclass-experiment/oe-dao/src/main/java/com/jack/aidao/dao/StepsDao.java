package com.jack.aidao.dao;

import com.jack.aidao.entity.StepEntity;

import java.util.List;

/**
 * @author wangwj
 * @data 2019/3/11
 */
public interface StepsDao {

    List<StepEntity> selectSteps(String experimentId);
}
