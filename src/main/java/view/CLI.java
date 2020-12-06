package view;

import controller.Controller;

import java.util.Scanner;

/**
 * VIEW CLASS:
 * This class manage the interaction with the user
 * throughout command line
 * */

public class CLI {
    private final Scanner keyboard;
    private boolean fileLoaded;
    private final Controller controller;

    public CLI(Controller controller) {
        keyboard = new Scanner(System.in);
        fileLoaded = false;
        this.controller = controller;
    }

    private void start() {
        System.out.println("SNS - Sezione Rosolina");
        System.out.println("Sistema di generazione quiz esame");
        boolean stop = false;
        int choice = -1;
        do {
            printMenu();
            choice = keyboard.nextInt();
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
        System.out.print("Inserisci path al file CSV: ");
        String filePath = keyboard.nextLine();
        fileLoaded = controller.readFile(filePath);
    }

}
