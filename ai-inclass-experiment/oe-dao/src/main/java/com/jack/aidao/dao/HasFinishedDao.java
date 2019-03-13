package com.jack.aidao.dao;

import com.jack.aidao.entity.HasFinishedEntity;

import java.util.List;

/**
 * @author wangwj
 * @data 2019/3/6
 */
public interface HasFinishedDao {

    /**
     * 获得学生已完成的内容
     * @param studentId
     * @param experimentId
     * @return
     */
    HasFinishedEntity selectFinishedById(String studentId, String experimentId);

    int save(HasFinishedEntity entity);

    List<HasFinishedEntity> selectStatusList(List<String> experimentIds,String studentId);

}
