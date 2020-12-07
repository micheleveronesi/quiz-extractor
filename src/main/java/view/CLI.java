package view;

import controller.Controller;

import java.util.Scanner;

/**
 * VIEW CLASS:
 * This class manage the interaction with the user
 * throughout command line
 * */

public class CLI {
    private final Input keyboard;
    private boolean fileLoaded;
    private final Controller controller;

    public CLI(Controller controller) {
        keyboard = new Input();
        fileLoaded = false;
        this.controller = controller;
    }

    public void start() {
        System.out.println("SNS - Sezione Rosolina");
        System.out.println("Sistema di generazione quiz esame");
        boolean stop = false;
        int choice = -1;
        do {
            printMenu();
            choice = keyboard.readInt("Inserisci scelta: ");
            switch (choice) {
                case 0:
                    stop = true;
                    break;
                case 1:
                    openFile();
                    break;
                default:
                    System.out.println("SCELTA NON VALIDA");
                    break;
            }
        } while(!stop);
    }

    private void printMenu() {
        System.out.println("Opzioni disponibili");
        System.out.println("1) Apri file CSV");
        if(fileLoaded) {

        }
        System.out.println("0) Esci");
    }

    private void openFile() {
        String filePath = keyboard.readString("Inserisci path al file CSV: ");
        fileLoaded = controller.readFile(filePath);
        if(fileLoaded)
            System.out.println("File caricato correttamente");
        else
            System.out.println("Errore nel caricamento");
    }

}
