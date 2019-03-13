package com.jack.aidao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author wangwj
 * @data 2019/3/7
 */
@Data
@TableName("student_course")
public class StuCourseEntity implements BaseEntity {

    private String studentId;
    private String courseId;
}
