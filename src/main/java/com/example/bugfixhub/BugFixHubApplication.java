package com.example.bugfixhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BugFixHubApplication {

    public static void main(String[] args) {
        SpringApplication.run(BugFixHubApplication.class, args);
    }

}
