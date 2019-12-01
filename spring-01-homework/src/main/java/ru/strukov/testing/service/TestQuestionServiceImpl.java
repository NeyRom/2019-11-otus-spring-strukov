package ru.strukov.testing.service;

import org.springframework.core.io.Resource;
import ru.strukov.testing.dao.TestQuestionDao;
import ru.strukov.testing.domain.Student;
import ru.strukov.testing.domain.TestQuestion;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Roman Strukov on 30.11.2019.
 */

public class TestQuestionServiceImpl implements TestQuestionService {
    private TestQuestionDao testQuestionDao;
    private Student student;
    private StudentService studentService;

    public TestQuestionServiceImpl(TestQuestionDao testQuestionDao, StudentService studentService) {
        this.testQuestionDao = testQuestionDao;
        this.studentService = studentService;
    }

    @Override
    public void conductTesting(Resource resource) {
        List<TestQuestion> questions = testQuestionDao.getQuestions(resource);
        Collections.shuffle(questions);
        int questionsQuantity = questions.size();
        int questionNumber = 1;
        int rightAnswers = 0;
        System.out.println("Всего вопросов будет " + questionsQuantity);
        for (TestQuestion question : questions) {
            rightAnswers += processQuestion(question, questionNumber);
            questionNumber++;
        }
        System.out.println("Результаты тестирования студента " + studentService.getFullName(student) + ":");
        System.out.println("Правильно отвечено на " + rightAnswers + " из " +
                questionsQuantity + " вопросов");
        System.out.println(rightAnswers > questionsQuantity / 2 ? "Тест сдан" : "Тест не сдан");
    }

    @Override
    public void setStudent() {
        student = studentService.setName();
    }

    public int processQuestion(TestQuestion question, int questionNumber) {
        System.out.println("Вопрос #" + questionNumber + ": " + question.getQuestion());
        int answerNum = 1;
        int rightAnswer = 1;
        for (String answer : question.getAnswers()) {
            System.out.println(answerNum + ". " + answer);
            if (answer.equals(question.getRightAnswer())) {
                rightAnswer = answerNum;
            }
            answerNum++;
        }
        int givenAnswer = processInput();
        return givenAnswer == rightAnswer ? 1 : 0;
    }

    @Override
    public String resource() {
        return testQuestionDao.getResource();
    }

    private int processInput() {
        Scanner scanner = new Scanner(System.in);
        int givenAnswer;
        while (true) {
            try {
                givenAnswer = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Для ответа, введите его номер");
            }
        }
        return givenAnswer;
    }
}
