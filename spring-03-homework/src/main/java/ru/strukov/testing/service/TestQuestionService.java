package ru.strukov.testing.service;

import java.util.Locale;

/**
 * Created by Roman Strukov on 30.11.2019.
 */

public interface TestQuestionService {
    void conductTesting();
    void setStudent();
    void setLocale(Locale locale);
}
