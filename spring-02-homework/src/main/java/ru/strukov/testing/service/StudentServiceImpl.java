package ru.strukov.testing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.strukov.testing.dao.StudentDao;
import ru.strukov.testing.domain.Student;

/**
 * Created by Roman Strukov on 27.11.2019.
 */

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentDao studentDao;
    private final IOService IOService;

    @Autowired
    public StudentServiceImpl(StudentDao studentDao, IOService IOService) {
        this.studentDao = studentDao;
        this.IOService = IOService;
    }

    @Override
    public Student setName() {
        String firstName = IOService.getStringWithMessage("Пожалуйста, введите имя:");
        String lastName = IOService.getStringWithMessage("Пожалуйста, введите фамилию:");
        return studentDao.createStudent(firstName, lastName);
    }

    @Override
    public String getFullName(Student student) {
        return student.getFirstName() + " " + student.getLastName();
    }
}
