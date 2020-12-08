package com.atuigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Hobo
 * @create 2020-12-07 19:33
 */
@EnableEurekaServer
@SpringBootApplication
public class Application7001 {
    public static void main(String[] args) {
        SpringApplication.run(Application7001.class, args);
    }
}
