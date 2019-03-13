package com.jack.aidao.dao;

import com.jack.aidao.entity.CourseEntity;

import java.util.List;

/**
 * @author wangwj
 * @data 2019/3/7
 */
public interface CourseDao {

    List<CourseEntity> getCourseList(String studentId);
}
