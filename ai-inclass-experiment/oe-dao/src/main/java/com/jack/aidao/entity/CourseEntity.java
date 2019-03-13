package com.jack.aidao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author wangwj
 * @data 2019/3/7
 */
@Data
@TableName("course")
public class CourseEntity implements BaseEntity {

    private String courseId;

    private String courseName;

    private String courseClassification;
}
