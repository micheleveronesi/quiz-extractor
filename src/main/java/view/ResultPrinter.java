package view;

import controller.Question;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.List;

/**
 * PRINTER CLASS:
 * This class take elaborated data from the controller and print
 * them into a txt file
 * */

public class ResultPrinter {
    private int numeroQuiz;
    private File questionario, correttore;
    private FileWriter scrittoreQuestionario, scrittoreCorrettore;
    private boolean ready;

    public ResultPrinter() {
        numeroQuiz = 0;
        questionario = correttore = null;
        ready = false;
    }

    public void generateFiles(List<Question> questions) {
        try {
            String nomeQuestionario = "questionario.txt", nomeCorrettore = "correttore.txt";
            if(numeroQuiz > 0) {
                nomeQuestionario = nomeQuestionario + numeroQuiz + ".txt";
                nomeCorrettore = nomeCorrettore + numeroQuiz + ".txt";
            }
            questionario = new File(nomeQuestionario);
            correttore = new File(nomeCorrettore);
            if(questionario.createNewFile() && correttore.createNewFile()) {
                ++numeroQuiz;
                ready = true;
                stampaQuestionario(questions);
            }
            else
                ready = false;
        } catch(IOException e) {
            System.out.println("ERRORE NELLA CREAZIONE DEL FILE");
            ready = false;
        }
    }

    private void stampaQuestionario(List<Question> questions) {
        if(ready) {
            try {
                scrittoreQuestionario = new FileWriter(questionario.getName());
                scrittoreCorrettore = new FileWriter(correttore.getName());
                int numDomanda = 1;
                for (Question i : questions) {
                    scrittoreQuestionario.write(numDomanda + ") " + i.getQuestion() + "\n");
                    List<String> answers = i.getAnswers();
                    scrittoreQuestionario.write("\t A) " + answers.get(0) + "\n");
                    scrittoreQuestionario.write("\t B) " + answers.get(1) + "\n");
                    scrittoreQuestionario.write("\t C) " + answers.get(2) + "\n");
                    scrittoreQuestionario.write("\t D) " + answers.get(3) + "\n\n");

                    scrittoreCorrettore.write("DOMANDA " + numDomanda + " --> " + getStringCorrectAnswer(i.getIndexCorrectAnswer()) + "\n");
                    ++numDomanda;
                }
                scrittoreQuestionario.close();
                scrittoreCorrettore.close();
            } catch (IOException e) {
                System.out.println("ERRORE IN SCRITTURA");
            }
        }
        else
            System.out.println("STAMPANTE NON PRONTA");
    }

    private static String getStringCorrectAnswer(int k) {
        String s;
        switch(k) {
            case 0:
                s = "A";
                break;
            case 1:
                s = "B";
                break;
            case 2:
                s = "C";
                break;
            case 3:
                s = "D";
                break;
            default:
                s = "ERR";
                break;
        }
        return s;
    }

}
