package com.biscuits.b;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan("com.biscuits.core")
public class BApplication {

    public static void main(String[] args) {
        B.getInstance().initBySelf();
        B.getInstance().runBySelf(SpringApplication.run(BApplication.class, args));
    }
}
