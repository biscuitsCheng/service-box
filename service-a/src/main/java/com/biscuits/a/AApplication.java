package com.biscuits.a;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = "com.biscuits.core")
public class AApplication {

    public static void main(String[] args) {
        A.getInstance().initBySelf();
        A.getInstance().runBySelf(SpringApplication.run(AApplication.class, args));
    }
}
