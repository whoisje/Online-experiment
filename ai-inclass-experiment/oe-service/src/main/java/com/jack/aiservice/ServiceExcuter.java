package com.jack.aiservice;


import com.jack.aicore.beans.BaseResult;

/**
 * @author wangwj
 * @data 2018/12/17
 */

public interface ServiceExcuter {


    BaseResult excute(String msg, Command command);

}
