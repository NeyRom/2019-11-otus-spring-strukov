package ru.strukov.testing.dao;

import org.springframework.core.io.Resource;
import ru.strukov.testing.domain.TestQuestion;

/**
 * Created by Roman Strukov on 29.11.2019.
 */

public interface TestQuestionDao {
    TestQuestion getQuestion();
    void prepareResource(Resource resource);
    String getResource();
}
