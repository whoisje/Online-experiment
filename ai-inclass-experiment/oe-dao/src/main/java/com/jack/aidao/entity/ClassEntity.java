package com.jack.aidao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author wangwj
 * @data 2018/12/25
 */
@Data
@TableName("class")
public class ClassEntity implements BaseEntity {
    @TableField("class_id")
    private int classId;
    @TableField("class_name")
    private String className;
}
