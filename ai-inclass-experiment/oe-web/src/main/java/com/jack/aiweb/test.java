package com.jack.aiweb;

/**
 * @author wangwj
 * @data 2018/12/28
 */
public class test {
    public static void main(String[] args){
        System.out.println(test.class.getClassLoader().getResourceAsStream("application.properties"));
    }
}
