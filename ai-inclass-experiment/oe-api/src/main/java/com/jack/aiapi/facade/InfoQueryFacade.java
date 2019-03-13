package com.jack.aiapi.facade;

import com.jack.aicore.beans.BaseResult;
import com.jack.aidao.entity.HasFinishedEntity;
import com.jack.aidao.entity.StuStep;

import java.util.List;

/**
 * @author wangwj
 * @data 2018/12/25
 */
public interface InfoQueryFacade {

    BaseResult selectHasFinished(String studentId, String experimentId);

    BaseResult save(HasFinishedEntity entity);

    BaseResult selectStatusList(List<String> experimentIds, String studentId);

    BaseResult getCourseList(String studentId);

    BaseResult selectExpList(String courseId);

    BaseResult selectExpDetail(String experimentId);

    BaseResult selectSteps(String experimentId);

    BaseResult selectStuSteps(String studentId, List<String> stepIds);

    BaseResult saveSteps(List<StuStep> list, String studentId);
}
