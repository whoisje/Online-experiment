package com.jack.aicore.utils;

import com.jack.aicore.beans.ApiResponse;
import com.jack.aicore.constants.Status;

/**
 * @author wangwj
 * @data 2018/12/17
 */
public class ApiResponseBuilder {

    private final static int ERROR_CODE = 0;
    private final static int SUCCESS_CODE = 1;

    public static ApiResponse success(Object t) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setBody(t);
        apiResponse.setMessage("success");
        apiResponse.setRet(Status.SUCCESS_CODE);
        return apiResponse;
    }

    public static ApiResponse failed(String msg) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(msg);
        apiResponse.setRet(Status.FAILED_CODE);
        return apiResponse;
    }
}
