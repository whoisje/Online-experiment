package com.jack.aidao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author wangwj
 * @data 2019/3/5
 */
@Data
@TableName("user")
public class UserEntity implements BaseEntity{

    private String userId;


    private char sex;

    private String password;

    private String email;

    private boolean activated;

    @TableField("resetpasswordkey")
    private String resetPasswordKey;
}
