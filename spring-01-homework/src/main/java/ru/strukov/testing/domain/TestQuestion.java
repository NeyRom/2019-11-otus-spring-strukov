package ru.strukov.testing.domain;

import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Roman Strukov on 27.11.2019.
 */

public class TestQuestion {
    @CsvBindByName
    private String question;
    @CsvBindAndSplitByName(elementType = String.class, collectionType = ArrayList.class, splitOn = "\\|")
    private List<String> answers;
    @CsvBindByName
    private String rightAnswer;

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswers() {
        Collections.shuffle(answers);
        return answers;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    @Override
    public String toString() {
        return "Вопрос тестирования: " + question +
                ", ответы=" + answers +
                ", правильный ответ=" + rightAnswer;
    }
}
