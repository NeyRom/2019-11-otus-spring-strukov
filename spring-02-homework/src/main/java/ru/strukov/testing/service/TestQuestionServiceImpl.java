package ru.strukov.testing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.strukov.testing.dao.TestQuestionDao;
import ru.strukov.testing.domain.Student;
import ru.strukov.testing.domain.TestQuestion;

import java.util.List;
import java.util.Locale;

/**
 * Created by Roman Strukov on 30.11.2019.
 */

@Service
@Primary
public class TestQuestionServiceImpl implements TestQuestionService {
    private final TestQuestionDao testQuestionDao;
    private Student student;
    private final StudentService studentService;
    private final IOService ioService;
    private Locale locale;
    private final LocaleService localeService;
    private final MessageSource messageSource;

    @Autowired
    public TestQuestionServiceImpl(TestQuestionDao testQuestionDao,
                                   StudentService studentService,
                                   IOService ioService,
                                   LocaleService localeService,
                                   MessageSource messageSource) {
        this.testQuestionDao = testQuestionDao;
        this.studentService = studentService;
        this.ioService = ioService;
        this.localeService = localeService;
        this.messageSource = messageSource;
    }

    @Override
    public void conductTesting() {
        setLocale(localeService.getUserLocale());
        setStudent();
        List<TestQuestion> questions = testQuestionDao.getQuestions(localeService.getPathComponent(locale));
        int questionsQuantity = questions.size();
        int questionNumber = 1;
        int rightAnswers = 0;
        ioService.printMessage(messageSource.getMessage("Questions.number", new  Object[] {questionsQuantity}, locale));
        for (TestQuestion question : questions) {
            rightAnswers += processQuestion(question, questionNumber);
            questionNumber++;
        }
        printTestResult(rightAnswers, questionsQuantity);
    }

    @Override
    public void setStudent() {
        student = studentService.setName(locale);
    }

    @Override
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public int processQuestion(TestQuestion question, int questionNumber) {
        ioService.printMessage(messageSource.getMessage("Question.caption", null, locale)
                + questionNumber + ": " + question.getQuestion());
        int answerNum = 1;
        int rightAnswer = 1;
        for (String answer : question.getAnswers()) {
            System.out.println(answerNum + ". " + answer);
            if (answer.equals(question.getRightAnswer())) {
                rightAnswer = answerNum;
            }
            answerNum++;
        }
        int givenAnswer = ioService.getInt();
        return givenAnswer == rightAnswer ? 1 : 0;
    }

    public void printTestResult(int rightAnswers, int questionsQuantity) {
        ioService.printMessage(messageSource.getMessage("Testing.resultCaption",
                new Object[] {studentService.getFullName(student)}, locale));
        ioService.printMessage(messageSource.getMessage("Testing.rightAnswersCaption",
                new Object[] {rightAnswers, questionsQuantity}, locale));
        ioService.printMessage(rightAnswers > questionsQuantity / 2 ? messageSource.getMessage("Testing.passed", null
                , locale) : messageSource.getMessage("Testing.failed", null, locale));
    }
}
