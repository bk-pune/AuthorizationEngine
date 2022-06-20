package com.bk.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 08/04/22
 */
@ComponentScan(basePackages = "com.bk")
@EnableAutoConfiguration
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}
