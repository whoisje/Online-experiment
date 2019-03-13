package com.jack.aiweb.controller;

import com.jack.aicore.beans.ApiResponse;
import com.jack.aicore.beans.BaseResult;
import com.jack.aicore.constants.Status;
import com.jack.aicore.utils.ApiResponseBuilder;
import com.jack.aidao.entity.UserEntity;
import com.jack.aiservice.impl.facade.LoginFacadeImpl;
import com.jack.aiservice.impl.service.PythonExecuterImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author wangwj
 * @data 2018/12/18
 */
@RestController
@RequestMapping("/oe")
public class LoginController {

    @Autowired
    private LoginFacadeImpl loginFacade;

    @Autowired
    private PythonExecuterImpl pythonExecuter;

    @RequestMapping("/login")
    public ApiResponse login(String userId, String password) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userId);
        userEntity.setPassword(password);
        BaseResult result = loginFacade.login(userEntity);
        if (result.getCode() == Status.FAILED_CODE) {
            return ApiResponseBuilder.failed("登录失败");
        }
        return ApiResponseBuilder.success(result.getResult());
    }

    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public Map<String, String> test(@RequestBody Map<String,Object> map, HttpServletRequest request) {
        String realPath = request.getServletContext().getRealPath(File.separator);
        Map<String,String> result = pythonExecuter.executePython((List)map.get("list"),(String)map.get("studentId"),(String)map.get("experimentId"),realPath,(String)map.get("args"));
        return result;
    }
}
