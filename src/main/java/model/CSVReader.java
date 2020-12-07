package model;
import controller.Category;
import controller.Question;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * MODEL CLASS
 * this class have to access data stored in a CSV files
 * and provide them to the controller in a List data structure
 * */
public class CSVReader {
    private final BufferedReader reader;

    private CSVReader(BufferedReader reader) {
        this.reader = reader;
    }

    public static CSVReader open(String filePath) throws IOException {
        BufferedReader fr = Files.newBufferedReader(Paths.get(filePath));
        return new CSVReader(fr);
    }

    public List<Question> read() throws IOException {
        List<Question> questions = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] current = line.split(",");
            Category category = Category.categoryMapper(current[1]);
            String questionText = current[2];
            List<String> answers = new ArrayList<>();
            for (int i = 3; i <= 6; ++i) {
                answers.add(current[i]);
            }
            int correctAnswer = mapCorrectAnswer(current[7]);
            questions.add(new Question(questionText, category, answers, correctAnswer));
        }

        return questions;
    }

    private static int mapCorrectAnswer(String toMap) {
        char c = toMap.toLowerCase().charAt(0);
        int mapped;
        switch(c){
            case 'a': mapped = 0; break;
            case 'b': mapped = 1; break;
            case 'c': mapped = 2; break;
            case 'd': mapped = 3; break;
            default: mapped = -1; break;
        }
        return mapped;
    }

}
