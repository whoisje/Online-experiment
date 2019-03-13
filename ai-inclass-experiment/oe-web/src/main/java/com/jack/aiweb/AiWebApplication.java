package com.jack.aiweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wangwj
 */
@MapperScan("com.jack.aidao.mapper")
@SpringBootApplication(scanBasePackages = "com.jack")
public class AiWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiWebApplication.class, args);
    }

}

