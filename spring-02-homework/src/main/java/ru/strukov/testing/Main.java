package ru.strukov.testing;

import org.springframework.context.annotation.*;
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
        questionsService.conductTesting();
    }

}
