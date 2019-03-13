package com.jack.aidao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author wangwj
 * @data 2018/12/22
 */
@Data
@TableName("student")
public class UserInfoEntity implements BaseEntity {
    @TableField("student_id")
    private int studentId;
    @TableField("student_name")
    private String studentName;
    @TableField("class_id")
    private int classId;

}
