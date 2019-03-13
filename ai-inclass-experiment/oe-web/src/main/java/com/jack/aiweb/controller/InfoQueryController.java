package com.jack.aiweb.controller;

import com.jack.aicore.beans.ApiResponse;
import com.jack.aicore.beans.BaseResult;
import com.jack.aicore.constants.Status;
import com.jack.aicore.utils.ApiResponseBuilder;
import com.jack.aicore.utils.CheckParams;
import com.jack.aidao.entity.HasFinishedEntity;
import com.jack.aidao.entity.StuStep;
import com.jack.aiservice.impl.facade.InfoQueryFacadeImpl;
import com.jack.aiservice.impl.facade.PythonExecuterFacadeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author wangwj
 * @data 2018/12/25
 */
@RestController
@RequestMapping("/oe")
public class InfoQueryController {
    @Autowired
    private InfoQueryFacadeImpl infoQueryFacade;
    @Autowired
    private PythonExecuterFacadeImpl executerFacade;

    @RequestMapping("/getExperimentList")
    public ApiResponse getClassInfo(String courseId, HttpServletRequest request) {
        String studentId = getAttrFromSession(request);
        if (!CheckParams.check(studentId, courseId)) {
            return ApiResponseBuilder.failed("获取数据错误");
        }
        BaseResult result = infoQueryFacade.selectExpList(courseId);
        if (result.getCode() == Status.FAILED_CODE) {
            return ApiResponseBuilder.failed("获取数据错误");
        }
        return ApiResponseBuilder.success(result.getResult());
    }

    @RequestMapping("/selectHasFinished")
    public ApiResponse selectHasFinished(String experimentId, HttpServletRequest request) {
        String studentId = getAttrFromSession(request);
        if (!CheckParams.check(studentId, experimentId)) {
            return ApiResponseBuilder.failed("获取数据错误");
        }
        BaseResult result = infoQueryFacade.selectHasFinished(studentId, experimentId);
        if (result.getCode() == Status.FAILED_CODE) {
            return ApiResponseBuilder.failed("获取数据错误");
        }
        return ApiResponseBuilder.success(result.getResult());
    }

    @RequestMapping(value = "/selectStatusList", method = RequestMethod.POST)
    public ApiResponse selectStatusList(@RequestBody Map<String, List<String>> map, HttpServletRequest request) {
        String studentId = getAttrFromSession(request);
        if (!CheckParams.check(studentId, map)) {
            return ApiResponseBuilder.failed("获取数据错误");
        }
        List<String> experimentIds = map.get("experimentIdList");
        BaseResult result = infoQueryFacade.selectStatusList(experimentIds, studentId);
        if (result.getCode() == Status.FAILED_CODE) {
            return ApiResponseBuilder.failed("获取数据错误");
        }
        return ApiResponseBuilder.success(result.getResult());
    }

    @RequestMapping("/getCourseList")
    public ApiResponse getCourseList(HttpServletRequest request) {
        String studentId = getAttrFromSession(request);
        if (!CheckParams.check(studentId)) {
            return ApiResponseBuilder.failed("获取数据错误");
        }
        BaseResult result = infoQueryFacade.getCourseList(studentId);
        if (result.getCode() == Status.FAILED_CODE) {
            return ApiResponseBuilder.failed("获取数据错误");
        }
        return ApiResponseBuilder.success(result.getResult());
    }

    @RequestMapping("/getExperimentDetail")
    public ApiResponse getExpDetail(@RequestParam("experimentId") String experimentId, HttpServletRequest request) {
        String studentId = getAttrFromSession(request);
        if (!CheckParams.check(studentId, experimentId)) {
            return ApiResponseBuilder.failed("获取数据错误");
        }
        BaseResult result = infoQueryFacade.selectExpDetail(experimentId);
        if (result.getCode() == Status.FAILED_CODE) {
            return ApiResponseBuilder.failed("获取数据错误");
        }
        return ApiResponseBuilder.success(result.getResult());
    }

    @RequestMapping("/save")
    public ApiResponse save(@RequestBody HasFinishedEntity hasFinishedEntity, HttpServletRequest request) {
        String studentId = getAttrFromSession(request);
        if (!CheckParams.check(studentId, hasFinishedEntity)) {
            return ApiResponseBuilder.failed("参数错误");
        }
        hasFinishedEntity.setStudentId(studentId);
        BaseResult result = infoQueryFacade.save(hasFinishedEntity);
        if (result.getCode() == Status.FAILED_CODE) {
            return ApiResponseBuilder.failed("保存失败");
        }
        return ApiResponseBuilder.success(result.getResult());
    }

    @RequestMapping(value = "/executeCode", method = RequestMethod.POST)
    public ApiResponse execute(@RequestBody Map<String, Object> params, HttpServletRequest request) {
        String studentId = getAttrFromSession(request);
        String experimentId = (String)params.get("experimentId");
        List<Map> list = (List)params.get("list");
        String args = (String)params.get("args");
        if (!CheckParams.check(list, studentId,experimentId)) {
            return ApiResponseBuilder.failed("执行错误");
        }
        String realPath = request.getServletContext().getRealPath(File.separator);
        BaseResult result = executerFacade.executePython(list,studentId,experimentId,realPath,args);
        if (result.getCode() == Status.FAILED_CODE) {
            return ApiResponseBuilder.failed("执行错误");
        }
        return ApiResponseBuilder.success(result.getResult());
    }

    @RequestMapping(value = "/selectSteps")
    public ApiResponse selectSteps(String experimentId, HttpServletRequest request) {
        String studentId = getAttrFromSession(request);
        if (!CheckParams.check(experimentId, studentId)) {
            return ApiResponseBuilder.failed("error");
        }

        BaseResult result = infoQueryFacade.selectSteps(experimentId);
        if (result.getCode() == Status.FAILED_CODE) {
            return ApiResponseBuilder.failed("error");
        }
        return ApiResponseBuilder.success(result.getResult());
    }

    @RequestMapping(value = "/selectStuSteps", method = RequestMethod.POST)
    public ApiResponse selectStuSteps(@RequestBody Map<String, List<String>> stepIds, HttpServletRequest request) {
        List<String> ids = stepIds.get("stepIds");
        String studentId = getAttrFromSession(request);
        if (!CheckParams.check(ids, studentId)) {
            return ApiResponseBuilder.failed("错误");
        }

        BaseResult result = infoQueryFacade.selectStuSteps(studentId, ids);
        if (result.getCode() == Status.FAILED_CODE) {
            return ApiResponseBuilder.failed("错误");
        }
        return ApiResponseBuilder.success(result.getResult());
    }

    @RequestMapping(value = "/saveSteps", method = RequestMethod.POST)
    public ApiResponse saveSteps(@RequestBody Map<String,List<StuStep>> map, HttpServletRequest request) {
        String studentId = getAttrFromSession(request);
        List<StuStep> list = map.get("list");
        if (!CheckParams.check(list, studentId)) {
            return ApiResponseBuilder.failed("错误");
        }

        BaseResult result = infoQueryFacade.saveSteps(list, studentId);
        if (result.getCode() == Status.FAILED_CODE) {
            return ApiResponseBuilder.failed("错误");
        }
        return ApiResponseBuilder.success(result.getResult());
    }

    private String getAttrFromSession(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("JSESSIONID".equals(cookie.getName())) {
                if ("".equals(cookie.getValue()) || cookie.getValue() != null) {
                    return cookie.getValue();
                }

            }
        }
        return null;
    }
}
