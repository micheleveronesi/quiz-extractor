package view;

import controller.Controller;
import controller.Question;

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
                    openFile();
                    break;
                case 2:
                    if(fileLoaded)
                        printAllQuestions(controller.getQuestions());
                    else
                        System.out.println("NOT ALLOWED");
                    break;
                case 3:
                    generaFoglioEquidistribuito();
                    break;
                case 5:
                    if(fileLoaded) {
                        if (controller.closeCSV()) {
                            System.out.println("File chiuso con successo");
                            fileLoaded = false;
                        } else
                            System.out.println("ERRORE IN CHIUSURA");
                    }
                    else
                        System.out.println("NOT ALLOWED");
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
        System.out.println("1) Apri file CSV");
        if(fileLoaded) {
            System.out.println("2) Stampa tutte le domande a video");
            System.out.println("3) Estrai quiz in modo equidistribuito");
            System.out.println("4) Estrai quiz personalizzato");
            System.out.println("5) Chiudi file caricato");
        }
        System.out.println("0) Esci");
    }

    private void openFile() {
        String filePath = Input.getInstance().readString("Inserisci path al file CSV: ");
        fileLoaded = controller.readFile(filePath);
        if(fileLoaded)
            System.out.println("File caricato correttamente");
        else
            System.out.println("Errore nel caricamento");
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

    private void generaFoglioEquidistribuito() {
        int n = -1;
        boolean sbagliato = false;
        do {
            if(sbagliato)
                System.out.println("NUMERO NON VALIDO, REINSERIRE");
            n = Input.getInstance().readInt("Quante domande vuoi inserire? ");
            sbagliato = true;
        } while(n <= 0);
        List<Question> sheet = controller.getSheet(n);
        printer.generateFiles(sheet);
    }

}
