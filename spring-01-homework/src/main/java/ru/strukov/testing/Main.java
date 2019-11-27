package ru.strukov.testing;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import ru.strukov.testing.domain.Student;
import ru.strukov.testing.service.StudentService;

/**
 * Created by Roman Strukov on 27.11.2019.
 */

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new
                ClassPathXmlApplicationContext("/spring-context.xml");
        StudentService studentService = context.getBean(StudentService.class);
        Student student = studentService.setName();
        System.out.println("Результаты тестирования студента " + student.getName() + ":");
        //Resource resource = context.getResource("/qna.csv");
    }
}
