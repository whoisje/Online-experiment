package com.jack.aicore.beans;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wangwj
 * @data 2018/12/17
 */
@Data
public class BaseResult implements Serializable {
    private static final long serialVersionUID = 4914155437783119073L;
    private int code = -1;
    private Object result;
    private String msg;
}
