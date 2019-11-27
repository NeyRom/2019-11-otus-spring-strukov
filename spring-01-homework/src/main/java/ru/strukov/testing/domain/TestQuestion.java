package ru.strukov.testing.domain;

import java.util.Set;

/**
 * Created by Roman Strukov on 27.11.2019.
 */

public class TestQuestion {
    private final String question;
    private final Set<String> answerVariants;
    private final String answer;

    public TestQuestion(String question, Set<String> answerVariants, String answer) {
        this.question = question;
        this.answerVariants = answerVariants;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public Set<String> getAnswerVariants() {
        return answerVariants;
    }

    public String getAnswer() {
        return answer;
    }
}
