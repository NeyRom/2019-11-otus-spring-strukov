package ru.strukov.testing;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.strukov.testing.dao.TestQuestionDao;
import ru.strukov.testing.domain.Student;
import ru.strukov.testing.service.StudentService;

/**
 * Created by Roman Strukov on 27.11.2019.
 */

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new
                ClassPathXmlApplicationContext("/spring-context.xml");
        TestQuestionDao questionDao = context.getBean(TestQuestionDao.class);
        StudentService studentService = context.getBean(StudentService.class);
        Student student = studentService.setName();
        questionDao.prepareResource(context.getResource(questionDao.getResource()));
        System.out.println("Результаты тестирования студента " + student.getName() + ":");
    }
}
