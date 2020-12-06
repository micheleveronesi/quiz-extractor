package controller;

import model.CSVReader;

import java.util.List;

/**
 * CONTROLLER CLASS:
 * This class receive a data structure from the reader (model)
 * elaborate to make a quiz with a selected subset of questions
 * elaborate the answer sheet for the generated quiz
 * */
public class Controller {
    private final CSVReader csvFile;
    private final List<Question> questions;

    public Controller(String filePath) {
        csvFile = CSVReader.open(filePath);
        questions = csvFile.read();
    }

    public List<Question> getSheet() {

        return null;
    }

}
