package controller;

import model.CSVReader;

import java.util.ArrayList;
import java.util.EnumMap;
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
    private final EnumMap<Category, List<Question>> questionByCat;

    private Controller(String filePath) {
        csvFile = CSVReader.open(filePath);
        questions = csvFile.read();
        questionByCat = new EnumMap<Category, List<Question>>(Category.class);
    }

    public static Controller start(String filePath) {
        Controller c = new Controller(filePath);
        c.resetQuestionsEnumSet();
        return c;
    }

    /**
     * return an equally distributed questions sheet
     * @param n -> number of question to be introduced in the answer sheet
     * */
    public List<Question> getSheet(int n) {
        List<Question> questionSheet = new ArrayList<Question>();
        int k = n/(Category.values().length);
        for(Category c : Category.values()) {
            List<Question> l = questionByCat.get(c);
            for(int i=0; i<k && !l.isEmpty(); ++i) {
                int rand = (int) (Math.random() * l.size());
                questionSheet.add(l.remove(rand));
            }
        }
        resetQuestionsEnumSet();
        return questionSheet;
    }

    public List<Question> getSheet(EnumMap<Category, Integer> numByCat) {
        List<Question> questionSheet = new ArrayList<Question>();
        for(Category c : Category.values()) {
            int k = numByCat.get(c);
            List<Question> l = questionByCat.get(c);
            for(int i = 0; i<k && !l.isEmpty(); ++i) {
                int rand = (int) (Math.random() * l.size());
                questionSheet.add(l.remove(rand));
            }
        }
        resetQuestionsEnumSet();
        return questionSheet;
    }

    private void resetQuestionsEnumSet() {
        for(Category i : Category.values()) {
            questionByCat.put(i, new ArrayList<Question>());
        }

        for(Question i : questions) {
            questionByCat.get(i.getCategory()).add(i);
        }
    }

}
