package ru.strukov.testing.service;

import ru.strukov.testing.domain.Student;

import java.util.Locale;

/**
 * Created by Roman Strukov on 30.11.2019.
 */

public interface TestQuestionService {
    void conductTesting(Student student, Locale locale);
}
