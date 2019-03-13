package com.jack.aiapi.exception;

import lombok.Data;

/**
 * @author wangwj
 * @data 2018/12/17
 */
@Data
public class BaseException extends Exception {
    private static final long serialVersionUID = -6487822420975568332L;
    private int code;
    private String msg;

    public BaseException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
