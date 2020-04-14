package ru.strukov.testing.service;

import org.springframework.core.io.Resource;

/**
 * Created by Roman Strukov on 30.11.2019.
 */

public interface TestQuestionService {
    void conductTesting(Resource resource);
    void setStudent();
}
