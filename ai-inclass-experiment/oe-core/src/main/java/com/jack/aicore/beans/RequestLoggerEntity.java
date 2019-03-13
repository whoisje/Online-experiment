package com.jack.aicore.beans;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author wangwj
 * @data 2019/3/5
 */
@Data
public class RequestLoggerEntity implements Serializable {

    private Long id;
    /**
     * 客户端请求ip
     */
    private String clientIp;
    private String uri;
    private String type;
    private String method;
    private String paramData;
    private String sessionId;
    private Timestamp time;
    /**
     * 接口返回时间
     */
    private String returnTime;
    /**
     * 接口返回数据json
     */
    private String returnData;
    private String httpStatusCode;
    /**
     * 请求耗时秒单位
     */
    private int timeConsuming;

    @Override
    public String toString() {
        return "ali_id:" + id + "&" +
                "ali_client_ip:" + clientIp + "&" +
                "ali_uri:" + uri + "&" +
                "ali_type:" + type + "&" +
                "ali_method:" + method + "&" +
                "ali_param_data:" + paramData + "&" +
                "ali_session_id:" + sessionId + "&" +
                "ali_return_data:" + returnData + "&" +
                "ali_http_status_code:" + httpStatusCode + "&" +
                "ali_time_consuming:" + timeConsuming;

    }
}