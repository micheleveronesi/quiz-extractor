package controller;

import model.CSVReader;
import view.CLI;

import java.io.IOException;
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
    private CSVReader csvFile;
    private List<Question> questions;
    private final EnumMap<Category, List<Question>> questionByCat;
    private final CLI view;

    private Controller() {
        questions = new ArrayList<>();
        questionByCat = new EnumMap<>(Category.class);
        view = new CLI(this);
    }

    public static Controller start() {
        Controller c = new Controller();
        c.view.start();
        return c;
    }

    public List<Question> getQuestions() { return questions; }

    /**
     * return an equally distributed questions sheet
     * @param n -> number of question to be introduced in the answer sheet
     * */
    public List<Question> getSheet(int n) {
        List<Question> questionSheet = new ArrayList<>();
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
        List<Question> questionSheet = new ArrayList<>();
        for(Category c : Category.values()) {
            int k = numByCat.get(c);
            List<Question> l = questionByCat.get(c);
            int max = l.size();
            int i;
            for(i = 0; i<k && !l.isEmpty(); ++i) {
                int rand = (int) (Math.random() * l.size());
                questionSheet.add(l.remove(rand));
            }
            if(l.isEmpty() && i<k) {
                view.segnalaNumeroAlto(c, max);
            }
        }
        resetQuestionsEnumSet();
        return questionSheet;
    }

    public boolean readFile(String filePath) {
        try {
            csvFile = CSVReader.open(filePath);
            questions = csvFile.read();
            resetQuestionsEnumSet();
            return true;
        } catch(IOException e){
            return false;
        }
    }

    private void resetQuestionsEnumSet() {
        for(Category i : Category.values()) {
            questionByCat.put(i, new ArrayList<>());
        }

        for(Question i : questions) {
            questionByCat.get(i.getCategory()).add(i);
        }
    }

    public boolean closeCSV() {
        return csvFile.close();
    }

}
