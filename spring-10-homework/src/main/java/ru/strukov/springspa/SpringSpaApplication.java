package ru.strukov.springspa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author Roman Strukov
 */

@SpringBootApplication
@EnableMongoRepositories
public class SpringSpaApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringSpaApplication.class, args);
    }
}
