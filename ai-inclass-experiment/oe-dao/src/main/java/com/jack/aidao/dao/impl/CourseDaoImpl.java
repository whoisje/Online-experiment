package com.jack.aidao.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jack.aidao.dao.CourseDao;
import com.jack.aidao.entity.CourseEntity;
import com.jack.aidao.entity.StuCourseEntity;
import com.jack.aidao.mapper.CourseMapper;
import com.jack.aidao.mapper.SCourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wangwj
 * @data 2019/3/7
 */
@Repository
public class CourseDaoImpl implements CourseDao {
    @Autowired
    private SCourseMapper mapper;
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<CourseEntity> getCourseList(String studentId) {

        QueryWrapper<StuCourseEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(StuCourseEntity::getStudentId, studentId);
        List<StuCourseEntity> entities = mapper.selectList(wrapper);
        QueryWrapper<CourseEntity> wrapper1 = new QueryWrapper<>();
        wrapper1
                .lambda()
                .in(CourseEntity::getCourseId, entities
                        .stream()
                        .map(StuCourseEntity::getCourseId)
                        .collect(Collectors.toList()));
        return courseMapper.selectList(wrapper1);
    }
}
