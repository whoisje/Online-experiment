package com.jack.aidao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author wangwj
 * @data 2018/12/26
 */
@Data
@TableName("experiment")
public class ExpEntity implements BaseEntity {

    private String experimentId;

    private String experimentTitle;

    private String courseId;

    private String experimentEnvironment;

    private String experimentQuestion;

    private String expectResult;

    private String experimentPurpose;

    private String code;

    private String tip;
}
