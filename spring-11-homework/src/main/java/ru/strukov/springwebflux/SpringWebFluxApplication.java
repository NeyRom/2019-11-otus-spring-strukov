package ru.strukov.springwebflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

/**
 * @author Roman Strukov
 */

@SpringBootApplication
@EnableReactiveMongoRepositories
public class SpringWebFluxApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringWebFluxApplication.class);
    }
}
