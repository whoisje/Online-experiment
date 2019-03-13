package com.jack.aiservice.impl.service;

import com.jack.aicore.beans.BaseResult;
import com.jack.aiservice.Command;
import com.jack.aiservice.ServiceExcuter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author wangwj
 * @data 2018/12/17
 */
@Service
public class ServiceExcuterImpl implements ServiceExcuter {
    private final static Logger logger = LoggerFactory.getLogger(ServiceExcuterImpl.class);
    @Override
    public BaseResult excute(String msg, Command command) {
        BaseResult t = command.creatResult();
        try {
            command.excute(t);
        } catch (Exception e) {

            t.setCode(-1);
            t.setMsg(e.getMessage());
            logger.error(e.getMessage());
        }

        return t;
    }
}
