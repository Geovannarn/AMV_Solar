package com.amvsolar.amvsolar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.amvsolar")
public class AmvSolarApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmvSolarApplication.class, args);
    }

}