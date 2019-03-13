package com.jack.aiservice.impl.service;

import com.jack.aidao.dao.impl.CourseDaoImpl;
import com.jack.aidao.dao.impl.ExperimentDaoImpl;
import com.jack.aidao.dao.impl.HasFinishedDaoImpl;
import com.jack.aidao.dao.impl.StepsDaoImpl;
import com.jack.aidao.dao.impl.StuStepsDaoImpl;
import com.jack.aidao.entity.CourseEntity;
import com.jack.aidao.entity.ExpEntity;
import com.jack.aidao.entity.HasFinishedEntity;
import com.jack.aidao.entity.StepEntity;
import com.jack.aidao.entity.StuStep;
import com.jack.aiservice.service.InfoQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangwj
 * @data 2018/12/25
 */
@Service
public class InfoQueryServiceImpl implements InfoQueryService {
    @Autowired
    private ExperimentDaoImpl experimentMapper;
    @Autowired
    private HasFinishedDaoImpl hasFinishedDao;
    @Autowired
    private CourseDaoImpl courseDao;
    @Autowired
    private StepsDaoImpl stepsDao;
    @Autowired
    private StuStepsDaoImpl stuStepsDao;

    @Override
    public HasFinishedEntity selectHasFinished(String studentId, String experimentId) {

        return hasFinishedDao.selectFinishedById(studentId,experimentId);
    }

    @Override
    public boolean save(HasFinishedEntity entity) {
        int result = hasFinishedDao.save(entity);
        if (result>0){
            return true;
        }
        return false;
    }

    @Override
    public List<HasFinishedEntity> selectStatusList(List<String> experimentIds, String studentId) {

        return hasFinishedDao.selectStatusList(experimentIds,studentId);
    }

    @Override
    public List<CourseEntity> getCourseList(String studentId) {
        return courseDao.getCourseList(studentId);
    }

    @Override
    public List<ExpEntity> selectExpList(String courseId) {
        List<ExpEntity> expList = new ArrayList<>();
        List<ExpEntity> list = experimentMapper.selectExpList(courseId);
        list.forEach(e->{
            ExpEntity entity = new ExpEntity();
            entity.setExperimentId(e.getExperimentId());
            entity.setExperimentTitle(e.getExperimentTitle());
            expList.add(entity);
        });
        return expList;
    }

    @Override
    public ExpEntity selectExpDetail(String experimentId) {
        return experimentMapper.selectExpDetail(experimentId);
    }

    @Override
    public List<StepEntity> selectSteps(String experimentId) {
        return stepsDao.selectSteps(experimentId);
    }

    @Override
    public List<StuStep> selectStuSteps(String studentId, List<String> stepIds) {
        return stuStepsDao.selectSteps(studentId,stepIds);
    }

    @Override
    public void saveSteps(List<StuStep> list, String studentId) {
        stuStepsDao.saveSteps(list,studentId);
    }
}
