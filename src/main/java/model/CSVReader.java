package model;
import controller.Category;
import controller.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * MODEL CLASS
 * this class have to access data stored in a CSV files
 * and provide them to the controller in a List data structure
 * */
public class CSVReader {
    private final Scanner reader;

    private CSVReader(Scanner reader) {
        this.reader = reader;
    }

    public static CSVReader open(String filePath){
        Scanner sc = new Scanner(filePath);
        sc.useDelimiter(",");
        return new CSVReader(sc);
    }

    public List<Question> read() {
        List<Question> questions = new ArrayList<Question>();
        while(reader.hasNext()){
            String[] current = reader.next().split(",");
            Category category = Category.categoryMapper(current[1]);
            String questionText = current[2];
            List<String> answers = new ArrayList<String>();
            for(int i=3; i <= 6; ++i) {
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
