package ru.strukov.testing.dao;

import ru.strukov.testing.domain.TestQuestion;

import java.util.List;

/**
 * Created by Roman Strukov on 29.11.2019.
 */

public interface TestQuestionDao {
    List<TestQuestion> getQuestions(String pathComponent);
}
