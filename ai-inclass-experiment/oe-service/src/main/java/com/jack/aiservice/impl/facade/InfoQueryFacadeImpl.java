package com.jack.aiservice.impl.facade;

import com.jack.aiapi.exception.BaseException;
import com.jack.aiapi.facade.InfoQueryFacade;
import com.jack.aicore.beans.BaseResult;
import com.jack.aicore.constants.Status;
import com.jack.aidao.entity.CourseEntity;
import com.jack.aidao.entity.ExpEntity;
import com.jack.aidao.entity.HasFinishedEntity;
import com.jack.aidao.entity.StepEntity;
import com.jack.aidao.entity.StuStep;
import com.jack.aiservice.impl.service.InfoQueryServiceImpl;
import com.jack.aiservice.impl.service.ServiceExcuterImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wangwj
 * @data 2018/12/25
 */
@Component
public class InfoQueryFacadeImpl implements InfoQueryFacade {

    @Autowired
    private ServiceExcuterImpl serviceExcuter;
    @Autowired
    private InfoQueryServiceImpl infoQueryService;

    @Override
    public BaseResult selectHasFinished(String studentId, String experimentId) {
        return serviceExcuter.excute("获取已完成内容", (baseResult) -> {
            HasFinishedEntity entity = infoQueryService.selectHasFinished(studentId, experimentId);
            if (entity == null) {
                throw new BaseException(-1,"获取已完成内容失败！");
            }
            baseResult.setResult(entity);
            baseResult.setCode(Status.SUCCESS_CODE);
        });
    }

    @Override
    public BaseResult save(HasFinishedEntity entity) {
        return serviceExcuter.excute("保存已完成内容", (baseResult) -> {
            boolean flag = infoQueryService.save(entity);
            if (!flag) {
                throw new BaseException(-1,"保存已完成内容失败！");
            }
            baseResult.setResult(null);
            baseResult.setCode(Status.SUCCESS_CODE);
        });
    }

    @Override
    public BaseResult selectStatusList(List<String> experimentIds, String studentId) {
        return serviceExcuter.excute("获取实验状态列表", (baseResult) -> {
            List<HasFinishedEntity> entity = infoQueryService.selectStatusList(experimentIds, studentId);
            if (entity == null) {
                throw new BaseException(-1,"获取实验状态列表失败！");
            }
            baseResult.setResult(entity);
            baseResult.setCode(Status.SUCCESS_CODE);
        });
    }

    @Override
    public BaseResult getCourseList(String studentId) {
        return serviceExcuter.excute("获取课程列表", (baseResult) -> {
            List<CourseEntity> entity = infoQueryService.getCourseList(studentId);
            if (entity == null) {
                throw new BaseException(-1,"获取课程列表失败！");
            }
            baseResult.setResult(entity);
            baseResult.setCode(Status.SUCCESS_CODE);
        });
    }

    @Override
    public BaseResult selectExpList(String courseId) {

        return serviceExcuter.excute("获取实验列表", (baseResult) -> {
            List<ExpEntity> entity = infoQueryService.selectExpList(courseId);
            if (entity == null) {
                throw new BaseException(Status.FAILED_CODE, "获取实验列表失败");
            }
            baseResult.setCode(Status.SUCCESS_CODE);
            baseResult.setResult(entity);
        });
    }

    @Override
    public BaseResult selectExpDetail(String experimentId) {
        return serviceExcuter.excute("获取实验信息", (baseResult) -> {
            ExpEntity queryResult = infoQueryService.selectExpDetail(experimentId);
            if (queryResult != null) {
                baseResult.setResult(queryResult);
                baseResult.setCode(Status.SUCCESS_CODE);
            } else {
                throw new BaseException(Status.FAILED_CODE, "获取实验信息失败");
            }
        });
    }

    @Override
    public BaseResult selectSteps(String experimentId) {
        return serviceExcuter.excute("获取步骤信息", (baseResult) -> {
            List<StepEntity> queryResult = infoQueryService.selectSteps(experimentId);
            if (queryResult != null) {
                baseResult.setResult(queryResult);
                baseResult.setCode(Status.SUCCESS_CODE);
            } else {
                throw new BaseException(Status.FAILED_CODE, "获取步骤信息失败");
            }
        });
    }

    @Override
    public BaseResult selectStuSteps(String studentId, List<String> stepIds) {
        return serviceExcuter.excute("获取学生已完成步骤信息", (baseResult) -> {
            List<StuStep> queryResult = infoQueryService.selectStuSteps(studentId,stepIds);
            if (queryResult != null) {
                baseResult.setResult(queryResult);
                baseResult.setCode(Status.SUCCESS_CODE);
            } else {
                throw new BaseException(Status.FAILED_CODE, "获取学生已完成步骤信息失败");
            }
        });
    }

    @Override
    public BaseResult saveSteps(List<StuStep> list, String studentId) {
        return serviceExcuter.excute("存储已完成步骤信息", (baseResult) -> {
            infoQueryService.saveSteps(list,studentId);
                baseResult.setResult(null);
                baseResult.setCode(Status.SUCCESS_CODE);
        });
    }

}
