package com.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MainClass {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(MainClass.class, args);
        Send send = context.getBean(Send.class);
        try {
            send.sendData();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
