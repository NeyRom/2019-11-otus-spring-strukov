package ru.strukov.testing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.strukov.testing.service.TestQuestionService;

@SpringBootApplication
public class TestingApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(TestingApplication.class, args);
		TestQuestionService questionService = context.getBean(TestQuestionService.class);
		questionService.conductTesting();
	}

}
