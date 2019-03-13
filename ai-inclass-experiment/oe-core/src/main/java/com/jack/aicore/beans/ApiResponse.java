package com.jack.aicore.beans;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wangwj
 * @data 2018/12/17
 */
@Data
public class ApiResponse implements Serializable {

    private static final long serialVersionUID = 8762304273682208033L;
    private int ret;
    private String message;
    private Object body;

}
