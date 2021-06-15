package com.example.medontes5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class Medontes5Application {

    public static void main(String[] args) {
        SpringApplication.run(Medontes5Application.class, args);
    }

}
