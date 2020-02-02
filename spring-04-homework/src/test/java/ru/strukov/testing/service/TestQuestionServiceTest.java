package ru.strukov.testing.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.MessageSource;
import ru.strukov.testing.dao.TestQuestionDao;
import ru.strukov.testing.domain.TestQuestion;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("Проверка сервиса TestQuestionService")
class TestQuestionServiceTest {
    private TestQuestionServiceImpl testQuestionService;
    private TestQuestionDao testQuestionDao;
    private StudentService studentService;
    private IOService ioService;
    private LocaleService localeService;
    private MessageSource messageSource;
    private TestQuestion testQuestion;

    @BeforeEach
    void setUp() {
        testQuestionDao = mock(TestQuestionDao.class);
        studentService = mock(StudentService.class);
        ioService = mock(IOService.class);
        localeService = mock(LocaleService.class);
        messageSource = mock(MessageSource.class);
        testQuestionService = new TestQuestionServiceImpl(testQuestionDao,
                studentService,
                ioService,
                localeService,
                messageSource);
        testQuestion = new TestQuestion();
        testQuestion.setQuestion("Год основания Москвы");
        String[] answers = new String[] {"1147", "1941", "1812", "1649"};
        testQuestion.setAnswers(new ArrayList<>(Arrays.asList(answers)));
        testQuestion.setRightAnswer("1147");
    }

    @DisplayName("Корректно обрабатывается вопрос тестирования")
    @Test
    void shouldProcessQuestion() {
        when(ioService.getInt()).thenReturn(2);
        int answer = testQuestionService.processQuestion(testQuestion, 3);
        assertThat(answer).isBetween(-1, 2);
    }
}