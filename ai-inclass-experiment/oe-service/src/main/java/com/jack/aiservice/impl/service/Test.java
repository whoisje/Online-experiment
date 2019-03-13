package com.jack.aiservice.impl.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;

/**
 * @author wangwj
 * @data 2018/12/20
 */
public class Test {
    public static void main(String[] args) {


        String cmd = "python D:\\work\\ai-inclass-experiment\\oe-service\\src\\main\\java\\com\\jack\\aiservice\\impl\\service\\test.py \"hello\" \"world\"";
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            InputStream in = process.getInputStream();
            InputStreamReader reader = new InputStreamReader(in,"GBK");
            BufferedReader bufferedReader = new BufferedReader(reader);
            Optional<String> result = bufferedReader.lines().reduce((a, b)->a+b);
            result.ifPresent(System.out::println);
            BufferedReader reader1 =new BufferedReader(new InputStreamReader(process.getErrorStream()));
            System.out.println(reader1.lines().reduce((a,b)->a+b));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
