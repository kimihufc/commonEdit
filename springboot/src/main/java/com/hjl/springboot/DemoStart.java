package com.hjl.springboot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author: HJL
 * @create: 2018-12-18 17:03
 */
@SpringBootApplication
public class DemoStart {

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder();
        builder
            .sources(DemoStart.class)
            .build()
            .run(args);
//        SpringApplication.run(DemoStart.class,args);
    }

}
