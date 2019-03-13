package com.jack.aidao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author wangwj
 * @data 2019/3/11
 */
@Data
@TableName("student_step")
public class StuStep implements BaseEntity{

    private String stepId;

    private String studentId;

    private String stepCode;
}
