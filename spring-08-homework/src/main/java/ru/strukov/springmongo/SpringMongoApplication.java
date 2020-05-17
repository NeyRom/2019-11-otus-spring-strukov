package ru.strukov.springmongo;

/* Created by Roman Strukov in 11.05.2020 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication
public class SpringMongoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringMongoApplication.class, args);
    }

}
