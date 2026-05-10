package com.prakar.jira;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class JiraApplication {

    public static void main(String[] args) {
        SpringApplication.run(JiraApplication.class, args);
    }

}
