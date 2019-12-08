package ru.strukov.testing.service;

import ru.strukov.testing.dao.StudentDao;
import ru.strukov.testing.domain.Student;

/**
 * Created by Roman Strukov on 27.11.2019.
 */

public class StudentServiceImpl implements StudentService {
    private final StudentDao studentDao;
    private final ConsoleService consoleService;

    public StudentServiceImpl(StudentDao studentDao,
                              ConsoleService consoleService) {
        this.studentDao = studentDao;
        this.consoleService = consoleService;
    }

    @Override
    public Student setName() {
        String firstName = consoleService.getStringWithMessage("Пожалуйста, введите имя:");
        String lastName = consoleService.getStringWithMessage("Пожалуйста, введите фамилию:");
        return studentDao.createStudent(firstName, lastName);
    }

    @Override
    public String getFullName(Student student) {
        return student.getFirstName() + " " + student.getLastName();
    }
}
