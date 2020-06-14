package ru.strukov.springauth.mongock;

import com.github.cloudyrock.mongock.SpringBootMongock;
import com.github.cloudyrock.mongock.SpringBootMongockBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.strukov.springauth.mongock.changelog.DbChangeLog;

/**
 * @author Roman Strukov
 */

@Configuration
public class MongockConfig {

    @Bean
    public SpringBootMongock mongock(MongoTemplate mongoTemplate, ApplicationContext springContext) {
        return new SpringBootMongockBuilder(mongoTemplate, DbChangeLog.class.getPackageName())
                .setApplicationContext(springContext)
                .setLockQuickConfig()
                .build();
    }
}
