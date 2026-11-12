package com.fruit.serverwebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ServerWebAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerWebAppApplication.class, args);
    }

}
