package com.jack.aidao.dao;

import com.jack.aidao.entity.StuStep;

import java.util.List;

/**
 * @author wangwj
 * @data 2019/3/11
 */
public interface StuStepsDao {
    /**
     *
     * @param studentId 学生id
     * @param stepIds 步骤id列表
     * @return
     */
    List<StuStep> selectSteps(String studentId, List<String> stepIds);

    void saveSteps(List<StuStep> list,String studentId);
}
