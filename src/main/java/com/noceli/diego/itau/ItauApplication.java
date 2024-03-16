package com.noceli.diego.itau;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ItauApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItauApplication.class, args);
    }

}
