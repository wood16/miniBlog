package com.bookshop.poc.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class MiniBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(MiniBlogApplication.class, args);
    }

}
