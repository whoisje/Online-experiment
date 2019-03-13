package com.jack.aidao.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jack.aidao.dao.StuStepsDao;
import com.jack.aidao.entity.StuStep;
import com.jack.aidao.mapper.StuStepsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wangwj
 * @data 2019/3/11
 */
@Repository
public class StuStepsDaoImpl implements StuStepsDao {
    @Autowired
    private StuStepsMapper mapper;

    @Override
    public List<StuStep> selectSteps(String studentId, List<String> stepIds) {

        QueryWrapper<StuStep> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(StuStep::getStudentId, studentId).in(StuStep::getStepId, stepIds);
        return mapper.selectList(queryWrapper);
    }

    @Override
    public void saveSteps(List<StuStep> list, String studentId) {
        QueryWrapper<StuStep> wrapper = new QueryWrapper<>();
        wrapper.lambda().in(StuStep::getStepId,
                list.stream().map(StuStep::getStepId).collect(Collectors.toList()))
                .eq(StuStep::getStudentId, studentId);
        if (mapper.selectList(wrapper).size() == 0) {
            list.forEach((e) -> {
                        e.setStudentId(studentId);
                        mapper.insert(e);
                    }
            );
        } else {
            list.forEach(e -> {
                QueryWrapper<StuStep> wr= new QueryWrapper<>();
                wr.lambda().eq(StuStep::getStepId,e.getStepId()).eq(StuStep::getStudentId,studentId);
                e.setStudentId(studentId);
                mapper.update(e, wr);
            });
        }

    }
}
