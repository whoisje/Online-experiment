package com.jack.aiservice.service;

import java.util.List;
import java.util.Map;

/**
 * @author wangwj
 * @data 2018/12/28
 */
@FunctionalInterface
public interface PythonExecuter {
    /**
     * @param list      步骤代码列表
     * @param studentId 学生id
     * @return
     */
    Map<String, String> executePython(List<Map>  list, String studentId, String experimentId, String realPath, String args);
}
