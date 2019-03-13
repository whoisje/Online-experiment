package com.jack.aidao.dao;

import com.jack.aidao.entity.ExpEntity;

import java.util.List;

/**
 * @author wangwj
 * @data 2018/12/26
 */
public interface ExperimentDao {

    List<ExpEntity> selectExpList(String courseId);

    ExpEntity selectExpDetail(String experimentId);

}
