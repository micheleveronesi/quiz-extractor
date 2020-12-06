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
    private final Category category;
    private final List<String> answers;
    private final int correct;

    public Question(String question, Category category, List<String> answers, int correct) {
        this.question = question;
        this.category = category;
        this.answers = answers;
        this.correct = correct;
    }

    public String getQuestion() { return question; }
    public Category getCategory() { return category; }
    public List<String> getAnswers() { return answers; }
    public String getCorrectAnswer() { return answers.get(correct); }

}
