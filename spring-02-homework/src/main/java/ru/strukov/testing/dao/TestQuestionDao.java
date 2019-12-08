package ru.strukov.testing.dao;

import org.springframework.core.io.Resource;
import ru.strukov.testing.domain.TestQuestion;

import java.util.List;

/**
 * Created by Roman Strukov on 29.11.2019.
 */

public interface TestQuestionDao {
    List<TestQuestion> getQuestions(Resource resource);
    String getResource();
}
