package com.jack.aiapi.facade;

import com.jack.aicore.beans.BaseResult;
import com.jack.aidao.entity.UserEntity;

/**
 * @author wangwj
 * @data 2018/12/17
 */
public interface LoginFacade {
    /**
     * 登录接口
     * @return 登录结果
     */
    BaseResult login(UserEntity userEntity);

}
