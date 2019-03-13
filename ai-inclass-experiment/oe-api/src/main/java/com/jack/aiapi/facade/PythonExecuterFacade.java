package com.jack.aiapi.facade;

import com.jack.aicore.beans.BaseResult;

import java.util.List;
import java.util.Map;

/**
 * @author wangwj
 * @data 2018/12/28
 */
public interface PythonExecuterFacade {

    /**
     *
     * @param list
     * @param studentId
     * @param experimentId
     * @param realPath
     * @param args
     * @return
     */
    BaseResult executePython(List<Map> list, String studentId, String experimentId, String realPath, String args);
}
