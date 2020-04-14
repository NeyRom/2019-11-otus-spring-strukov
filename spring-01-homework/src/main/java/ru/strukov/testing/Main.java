package ru.strukov.testing;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.strukov.testing.dao.TestQuestionDao;
import ru.strukov.testing.service.TestQuestionService;

/**
 * Created by Roman Strukov on 27.11.2019.
 */

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new
                ClassPathXmlApplicationContext("/spring-context.xml");
        TestQuestionService questionsService = context.getBean(TestQuestionService.class);
        TestQuestionDao questionDao = context.getBean(TestQuestionDao.class);
        questionsService.setStudent();
        questionsService.conductTesting(context.getResource(questionDao.getResource()));
    }

}
