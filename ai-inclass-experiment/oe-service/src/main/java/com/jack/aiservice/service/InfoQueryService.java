package com.jack.aiservice.service;

import com.jack.aidao.entity.CourseEntity;
import com.jack.aidao.entity.ExpEntity;
import com.jack.aidao.entity.HasFinishedEntity;
import com.jack.aidao.entity.StepEntity;
import com.jack.aidao.entity.StuStep;

import java.util.List;

/**
 * @author wangwj
 * @data 2018/12/25
 */
public interface InfoQueryService {




    HasFinishedEntity selectHasFinished(String studentId, String experimentId);

    boolean save(HasFinishedEntity entity);

    List<HasFinishedEntity> selectStatusList(List<String> experimentIds,String studentId);

    List<CourseEntity> getCourseList(String studentId);

    List<ExpEntity> selectExpList(String courseId);

    ExpEntity selectExpDetail(String experimentId);

    List<StepEntity> selectSteps(String experimentId);

    List<StuStep> selectStuSteps(String studentId, List<String> stepIds);

    void saveSteps(List<StuStep> list,String studentId);
}
