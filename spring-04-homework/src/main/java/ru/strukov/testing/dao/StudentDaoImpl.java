package ru.strukov.testing.dao;

import org.springframework.stereotype.Component;
import ru.strukov.testing.domain.Student;

/**
 * Created by Roman Strukov on 27.11.2019.
 */

@Component
public class StudentDaoImpl implements StudentDao {

    @Override
    public Student createStudent(String firstName, String lastName) {
        return new Student(firstName, lastName);
    }
}
