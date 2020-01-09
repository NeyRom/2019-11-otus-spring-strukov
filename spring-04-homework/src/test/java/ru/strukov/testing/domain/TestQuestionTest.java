package ru.strukov.testing.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Roman Strukov on 18.12.2019.
 */
@DisplayName("Проверка класса TestQuestion")
class TestQuestionTest {
    private TestQuestion testQuestion;

    @BeforeEach
    void setUp() {
        testQuestion = new TestQuestion();
        testQuestion.setQuestion("Год основания Москвы");
        String[] answers = new String[] {"1147", "1941", "1812", "1649"};
        testQuestion.setAnswers(new ArrayList<>(Arrays.asList(answers)));
        testQuestion.setRightAnswer("1147");
    }

    @DisplayName("Корректно возвращает вопрос")
    @Test
    void shouldGetQuestion() {
        assertEquals("Год основания Москвы", testQuestion.getQuestion());
    }

    @DisplayName("Корректно возвращает варианты ответов")
    @Test
    void shouldGetAnswers() {
        List<String> answers = testQuestion.getAnswers();
        assertThat(answers).isNotEmpty().hasSize(4).contains("1812");
    }

    @DisplayName("Корректно возвращает правильный ответ")
    @Test
    void shouldGetRightAnswer() {
        assertEquals("1147", testQuestion.getRightAnswer());
    }
}