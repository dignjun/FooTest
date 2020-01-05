package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by DINGJUN on 2019.12.15.
 */
@SpringBootApplication
@EnableTransactionManagement
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
