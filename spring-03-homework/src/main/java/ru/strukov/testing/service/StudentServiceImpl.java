package ru.strukov.testing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.strukov.testing.dao.StudentDao;
import ru.strukov.testing.domain.Student;

import java.util.Locale;

/**
 * Created by Roman Strukov on 27.11.2019.
 */

@Component
public class StudentServiceImpl implements StudentService {
    private final StudentDao studentDao;
    private final IOService ioService;
    private final MessageSource messageSource;

    @Autowired
    public StudentServiceImpl(StudentDao studentDao, IOService ioService, MessageSource messageSource) {
        this.studentDao = studentDao;
        this.ioService = ioService;
        this.messageSource = messageSource;
    }

    @Override
    public Student setName(Locale locale) {
        String firstName = ioService
                .getStringWithMessage(messageSource.getMessage("Student.firstName", null, locale));
        String lastName = ioService
                .getStringWithMessage(messageSource.getMessage("Student.lastName", null, locale));
        return studentDao.createStudent(firstName, lastName);
    }

    @Override
    public String getFullName(Student student) {
        return student.getFirstName() + " " + student.getLastName();
    }
}
