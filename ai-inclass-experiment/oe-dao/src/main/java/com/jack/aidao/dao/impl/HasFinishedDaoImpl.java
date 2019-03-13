package com.jack.aidao.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jack.aidao.dao.HasFinishedDao;
import com.jack.aidao.entity.HasFinishedEntity;
import com.jack.aidao.mapper.HasfinishedMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wangwj
 * @data 2019/3/6
 */
@Repository
public class HasFinishedDaoImpl implements HasFinishedDao {
    @Autowired
    private HasfinishedMapper mapper;

    @Override
    public HasFinishedEntity selectFinishedById(String studentId, String experimentId) {

        QueryWrapper<HasFinishedEntity> wrapper = new QueryWrapper<>();
        wrapper
                .lambda()
                .eq(HasFinishedEntity::getStudentId, studentId)
                .eq(HasFinishedEntity::getExperimentId, experimentId);

        return mapper.selectOne(wrapper);
    }

    @Override
    public int save(HasFinishedEntity entity) {
        QueryWrapper<HasFinishedEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(HasFinishedEntity::getExperimentId,entity.getExperimentId())
                .eq(HasFinishedEntity::getStudentId,entity.getStudentId());
        if(mapper.selectOne(queryWrapper)==null){
            return mapper.insert(entity);
        }
        return mapper.update(entity,queryWrapper);
    }

    @Override
    public List<HasFinishedEntity> selectStatusList(List<String> experimentIds, String studentId) {
        QueryWrapper<HasFinishedEntity> wrapper = new QueryWrapper<>();
        wrapper
                .lambda()
                .eq(HasFinishedEntity::getStudentId, studentId)
                .in(HasFinishedEntity::getExperimentId, experimentIds)
                .select(HasFinishedEntity::getStatus,HasFinishedEntity::getExperimentId);
        return mapper.selectList(wrapper).stream().map(HasFinishedEntity::mapToStatus).collect(Collectors.toList());
    }

}
