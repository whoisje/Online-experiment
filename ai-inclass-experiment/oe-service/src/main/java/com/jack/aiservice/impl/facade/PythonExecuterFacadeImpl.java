package com.jack.aiservice.impl.facade;

import com.jack.aiapi.facade.PythonExecuterFacade;
import com.jack.aicore.beans.BaseResult;
import com.jack.aiservice.impl.service.PythonExecuterImpl;
import com.jack.aiservice.impl.service.ServiceExcuterImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author wangwj
 * @data 2018/12/28
 */
@Component
public class PythonExecuterFacadeImpl implements PythonExecuterFacade {
    @Autowired
    private ServiceExcuterImpl serviceExcuter;

    @Autowired
    private PythonExecuterImpl pythonExecuter;
    @Override
    public BaseResult executePython(List<Map> list, String studentId, String experimentId, String realPath, String args) {
        return serviceExcuter.excute("运行python代码", (baseResult) -> {
            Map<String,String> result = pythonExecuter.executePython(list,studentId,experimentId,realPath,args);
            if (result != null) {
                baseResult.setCode(1);
                baseResult.setResult(result);
            }
        });
    }
}

