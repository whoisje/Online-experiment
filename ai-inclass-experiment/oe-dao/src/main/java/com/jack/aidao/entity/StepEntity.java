package com.jack.aidao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author wangwj
 * @data 2019/3/11
 */
@TableName("steps")
@Data
public class StepEntity implements BaseEntity {

    private String experimentId;

    private String stepId;

    private String stepContent;

    private String stepCode;

    private int stepNum;

    private String text;
}
