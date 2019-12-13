package ru.strukov.testing.service;

import ru.strukov.testing.domain.Student;

import java.util.Locale;

/**
 * Created by Roman Strukov on 27.11.2019.
 */

public interface StudentService {
    Student setName(Locale locale);
    String getFullName(Student student);
}
