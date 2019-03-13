package com.jack.aiservice;


import com.jack.aicore.beans.BaseResult;

/**
 * @author wangwj
 * @data 2018/12/17
 */
public interface Command {


    /**
     * 创建结果对象
     *
     * @return
     */
    default BaseResult creatResult() {
        return new BaseResult();
    }

    /**
     * 执行命令内容
     *
     * @param t
     */
    void excute(BaseResult t) throws Exception;
}
