package ru.strukov.testing;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.strukov.testing.dao.TestQuestionDao;
import ru.strukov.testing.service.TestQuestionService;

/**
 * Created by Roman Strukov on 27.11.2019.
 */

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new
                AnnotationConfigApplicationContext(Main.class);

        TestQuestionService questionsService = context.getBean(TestQuestionService.class);
        TestQuestionDao questionDao = context.getBean(TestQuestionDao.class);
        questionsService.setStudent();
        questionsService.conductTesting(context.getResource(questionDao.getResource()));
    }

}
