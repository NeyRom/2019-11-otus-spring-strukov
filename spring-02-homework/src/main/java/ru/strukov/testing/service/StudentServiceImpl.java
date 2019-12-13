package ru.strukov.testing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.strukov.testing.dao.StudentDao;
import ru.strukov.testing.domain.Student;

import java.util.Locale;

/**
 * Created by Roman Strukov on 27.11.2019.
 */

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentDao studentDao;
    private final IOService IOService;
    private final MessageSource messageSource;

    @Autowired
    public StudentServiceImpl(StudentDao studentDao, IOService IOService, MessageSource messageSource) {
        this.studentDao = studentDao;
        this.IOService = IOService;
        this.messageSource = messageSource;
    }

    @Override
    public Student setName(Locale locale) {
        String firstName = IOService.getStringWithMessage(messageSource.getMessage("Student.firstName", null, locale));
        String lastName = IOService.getStringWithMessage(messageSource.getMessage("Student.lastName", null, locale));
        return studentDao.createStudent(firstName, lastName);
    }

    @Override
    public String getFullName(Student student) {
        return student.getFirstName() + " " + student.getLastName();
    }
}
