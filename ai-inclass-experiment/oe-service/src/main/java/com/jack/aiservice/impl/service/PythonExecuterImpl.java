package com.jack.aiservice.impl.service;

import com.jack.aidao.dao.impl.ExperimentDaoImpl;
import com.jack.aiservice.service.PythonExecuter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author wangwj
 * @data 2019/3/12
 */
@Service
public class PythonExecuterImpl implements PythonExecuter {
    @Autowired
    private ExperimentDaoImpl experimentDao;

    @Override
    public Map<String, String> executePython(List<Map> list, String studentId, String experimentId, String realPath, String args) {
        String common = experimentDao.selectExpDetail(experimentId).getCode();
        final String path = realPath + File.separator + studentId
                + experimentId + ".py";
        StringBuffer studentCode = new StringBuffer();
        studentCode.append("import sys\n");
        list.forEach(e ->
                studentCode.append(e.get("stepCode") + "\n")
        );
        String fCode = studentCode + common;
        writeToFile(fCode, path);
        InputStream error = null;
        InputStream in = null;
        String cmd = "python3 " + path + " " + args;
        String result = null;
        String errMsg = null;
        BufferedReader reader = null;
        BufferedReader reader2 = null;
        Map<String, String> map = null;
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            error = process.getErrorStream();
            reader = new BufferedReader(new InputStreamReader(error, "UTF-8"));
            map = new HashMap<>(2);
            Optional<String> o = reader.lines().reduce((a, b) -> a + b);
            if (o.isPresent()) {
                errMsg = o.get();
                map.put("error", errMsg);

            }
            in = process.getInputStream();
            reader2 = new BufferedReader(new InputStreamReader(in,"UTF-8"));
            Optional<String> o2 = reader2.lines().reduce((a, b) -> a + b);
            if (o2.isPresent()) {
                result = o2.get();
                map.put("result", result);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader2 != null) {
                try {
                    reader2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (in != null) {
                try {
                    error.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if (error != null) {
                try {
                    error.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return map;
    }

    private void writeToFile(String code, String path) {
        File file = new File(path);
        FileOutputStream out = null;
        PrintWriter writer = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            out = new FileOutputStream(file);
            writer = new PrintWriter(out);
            writer.write(code.toCharArray());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (writer != null) {
                writer.close();
            }
        }

    }
}
