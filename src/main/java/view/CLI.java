package view;

import controller.Category;
import controller.Controller;
import controller.Question;

import java.util.EnumMap;
import java.util.List;

/**
 * VIEW CLASS:
 * This class manage the interaction with the user
 * throughout command line
 * */

public class CLI {
    private boolean fileLoaded;
    private final Controller controller;
    private final ResultPrinter printer;

    public CLI(Controller controller) {
        fileLoaded = false;
        this.controller = controller;
        printer = new ResultPrinter();
    }

    public void start() {
        System.out.println("SNS - Sezione Rosolina");
        System.out.println("Sistema di generazione quiz esame");
        openFile(); // precarico subito il file che si chiama domande.csv
        boolean stop = false;
        int choice = -1;
        do {
            printMenu();
            choice = Input.getInstance().readInt("Inserisci scelta: ");
            switch (choice) {
                case 0:
                    stop = true;
                    if(fileLoaded)
                        if(controller.closeCSV()) {
                            System.out.println("File chiuso con successo");
                        }
                        else {
                            System.out.println("ERRORE IN CHIUSURA");
                            stop = false;
                        }
                    break;
                case 1:
                    printer.generateFiles(controller.getSheet(getDataAboutCategories()));
                    break;
                default:
                    System.out.println("SCELTA NON VALIDA");
                    break;
            }
        } while(!stop);
        System.out.println("FINE");
    }

    private void printMenu() {
        System.out.println("Opzioni disponibili");
        if(fileLoaded)
            System.out.println("1) Genera foglio esame");

        System.out.println("0) Esci");
    }

    private void openFile() {
        fileLoaded = controller.readFile("domande.csv");
        if(fileLoaded)
            System.out.println("File caricato correttamente");
        else
            System.out.println("Errore nel caricamento");
    }

    private EnumMap<Category, Integer> getDataAboutCategories() {
        EnumMap<Category, Integer> data = new EnumMap<>(Category.class);
        for(Category c : Category.values()) {
            Integer i = Input.getInstance().readInt(
                    "Inserisci il numero di domande per la categoria " + c.toString() + ": ");
            data.put(c, i);
        }
        return data;
    }

    public void segnalaNumeroAlto(Category c, int max){
        System.out.println("ATTENZIONE: Il numero inserito per la categoria " + c.toString() + " supera il numero di domande disponibili.");
        System.out.println("Per questa categoria sono disponibili " + max + " domande");
    }

    private static void printAllQuestions(List<Question> questions) {
        if(questions.isEmpty())
            System.out.println("NON CI SONO DOMANDE");
        else{
            int i=1;
            for(Question q : questions) {
                System.out.println("DOMANDA " + i);
                System.out.println("Categoria: " + q.getCategory());
                System.out.println("Testo: " + q.getQuestion());
                List<String> answers = q.getAnswers();
                for(int k = 0; k < answers.size(); ++k)
                    System.out.println(k+") " + answers.get(k));
                System.out.println("Risposta corretta: " + q.getIndexCorrectAnswer() + "\n");
                ++i;
            }
        }
    }

}
