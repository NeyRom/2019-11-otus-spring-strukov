package ru.strukov.testing.domain;

import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Roman Strukov on 27.11.2019.
 */

public class TestQuestion {
    @CsvBindByName
    private String question;
    @CsvBindAndSplitByName(elementType = String.class, collectionType = HashSet.class, splitOn = "\\|")
    private Set<String> answers;
    @CsvBindByName
    private String rightAnswer;

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswers(Set<String> answers) {
        this.answers = answers;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public Set<String> getAnswers() {
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
