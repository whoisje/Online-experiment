package com.jack.aicore.utils;

import org.springframework.util.StringUtils;

/**
 * @author wangwj
 * @data 2019/3/7
 */
public class CheckParams {

    public static boolean check(Object ...objects){
        for (Object o:objects){
            if (StringUtils.isEmpty(o)){
                return false;
            }
        }
        return true;
    }
}
