package ru.strukov.testing.service;

import ru.strukov.testing.dao.StudentDao;
import ru.strukov.testing.domain.Student;

import java.util.Scanner;

/**
 * Created by Roman Strukov on 27.11.2019.
 */

public class StudentServiceImpl implements StudentService {
    private final StudentDao studentDao;

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public Student setName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Пожалуйста, введите имя:");
        return studentDao.createStudent(scanner.nextLine());
    }
}
