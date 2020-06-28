package ru.strukov.springauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author Roman Strukov
 */

@SpringBootApplication
@EnableMongoRepositories
public class SpringAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringAuthApplication.class, args);
    }
}
