package controller;

import java.util.List;

/**
 * This class represents a single question
 * */

public class Question {
    /**
     * @field question: represents the text of the question
     * @field answers: represent a List containing String,
     *                 each one represents an anwser
     * @field correct: represent the index in the list of the
     *                 correct answer to that question
     * */
    private final String question;
    private final List<String> answers;
    private final int correct;

    public Question(String question, List<String> answers, int correct) {
        this.question = question;
        this.answers = answers;
        this.correct = correct;
    }

}
