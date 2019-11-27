package ru.strukov.testing.dao;

import ru.strukov.testing.domain.Student;

/**
 * Created by Roman Strukov on 27.11.2019.
 */

public class StudentDaoImpl implements StudentDao {

    @Override
    public Student createStudent(String name) {
        return new Student(name);
    }
}
