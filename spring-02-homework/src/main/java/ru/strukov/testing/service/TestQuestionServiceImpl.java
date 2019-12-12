package ru.strukov.testing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import ru.strukov.testing.dao.TestQuestionDao;
import ru.strukov.testing.domain.Student;
import ru.strukov.testing.domain.TestQuestion;

import java.util.List;

/**
 * Created by Roman Strukov on 30.11.2019.
 */

@Service
@Primary
public class TestQuestionServiceImpl implements TestQuestionService {
    private final TestQuestionDao testQuestionDao;
    private Student student;
    private final StudentService studentService;
    private final IOService IOService;

    @Autowired
    public TestQuestionServiceImpl(TestQuestionDao testQuestionDao, StudentService studentService,
                                   IOService IOService) {
        this.testQuestionDao = testQuestionDao;
        this.studentService = studentService;
        this.IOService = IOService;
    }

    @Override
    public void conductTesting(Resource resource) {
        List<TestQuestion> questions = testQuestionDao.getQuestions(resource);
        int questionsQuantity = questions.size();
        int questionNumber = 1;
        int rightAnswers = 0;
        IOService.printMessage("Всего вопросов будет " + questionsQuantity);
        for (TestQuestion question : questions) {
            rightAnswers += processQuestion(question, questionNumber);
            questionNumber++;
        }
        printTestResult(rightAnswers, questionsQuantity);
    }

    @Override
    public void setStudent() {
        student = studentService.setName();
    }

    public int processQuestion(TestQuestion question, int questionNumber) {
        IOService.printMessage("Вопрос #" + questionNumber + ": " + question.getQuestion());
        int answerNum = 1;
        int rightAnswer = 1;
        for (String answer : question.getAnswers()) {
            System.out.println(answerNum + ". " + answer);
            if (answer.equals(question.getRightAnswer())) {
                rightAnswer = answerNum;
            }
            answerNum++;
        }
        int givenAnswer = IOService.getInt();
        return givenAnswer == rightAnswer ? 1 : 0;
    }

    public void printTestResult(int rightAnswers, int questionsQuantity) {
        IOService
                .printMessage("Результаты тестирования студента " + studentService.getFullName(student) + ":");
        IOService.printMessage("Правильно отвечено на " + rightAnswers + " из " +
                questionsQuantity + " вопросов");
        IOService.printMessage(rightAnswers > questionsQuantity / 2 ? "Тест сдан" : "Тест не сдан");
    }
}
