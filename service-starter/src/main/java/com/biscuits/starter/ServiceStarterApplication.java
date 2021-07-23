package com.biscuits.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.biscuits.core")
public class ServiceStarterApplication {

    public static void main(String[] args) {
        Starter.getInstance().initBySelf();
        Starter.getInstance().runBySelf(SpringApplication.run(ServiceStarterApplication.class, args));
    }
}
