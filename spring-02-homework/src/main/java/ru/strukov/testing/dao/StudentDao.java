package ru.strukov.testing.dao;

import org.springframework.stereotype.Service;
import ru.strukov.testing.domain.Student;

/**
 * Created by Roman Strukov on 27.11.2019.
 */

@Service
public interface StudentDao {
    Student createStudent(String firstName, String lastName);
}
