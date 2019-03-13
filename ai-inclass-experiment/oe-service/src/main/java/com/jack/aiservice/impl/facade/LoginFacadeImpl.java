package com.jack.aiservice.impl.facade;

import com.jack.aiapi.exception.BaseException;
import com.jack.aiapi.facade.LoginFacade;
import com.jack.aicore.beans.BaseResult;
import com.jack.aidao.entity.UserEntity;
import com.jack.aidao.entity.UserInfoEntity;
import com.jack.aiservice.impl.service.LoginServiceImpl;
import com.jack.aiservice.impl.service.ServiceExcuterImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * @author wangwj
 * @data 2018/12/17
 */
@Component
public class LoginFacadeImpl implements LoginFacade {

    @Autowired
    private ServiceExcuterImpl serviceExcuter;
    @Autowired
    private LoginServiceImpl loginService;

    @Override
    public BaseResult login(UserEntity userEntity) {

        return serviceExcuter.excute("登录", (baseResult) -> {
            if (StringUtils.isEmpty(userEntity)) {
                throw new BaseException(-1, "用户名或者密码错误");
            }
            Optional<UserInfoEntity> optional = Optional.ofNullable(loginService.login(userEntity.getUserId(),userEntity.getPassword()));
            optional.ifPresent((entity) -> {
                baseResult.setResult(entity);
                baseResult.setCode(1);
            });
        });
    }
}
