import Algorithms.BFS;
import Algorithms.Tremaux;
import labyrinth.LabirynthGenerator;
import saveAndLoad.SaveAndLoad;

import java.util.Scanner;

public class Main {

    static final String ANSI_RESET = "\u001B[0m";
    static final String ANSI_RED = "\u001B[31m";
    static final String ANSI_YELLOW = "\u001B[33m";
    static final String ANSI_BLUE = "\u001B[34m";
    static final String ANSI_PURPLE = "\u001B[35m";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        LabirynthGenerator labirynthGenerator = new LabirynthGenerator();
        SaveAndLoad saveAndLoad = new SaveAndLoad();
        BFS bfs = new BFS();
        Tremaux tremaux = new Tremaux();

        System.out.println();
        System.out.println("Witam w programie L.A.B.I.R.Y.N.T");
        System.out.println();
        System.out.println("Przykladowy labirynt");
        labirynthGenerator.setLabirynth(saveAndLoad.load("pokaz.txt"));
        labirynthGenerator.loadLabirynth();
        labirynthGenerator.show(labirynthGenerator.getLabirynth());
        System.out.println();
        System.out.println("Na" + ANSI_BLUE + " niebiesko " + ANSI_RESET + "zaznaczono wyjscia.");
        System.out.println("Na " + ANSI_RED + "czerwono" + ANSI_RESET + " polaczenia pomiedzy punktami grafu.");
        System.out.println("Na " + ANSI_YELLOW + "zolto" + ANSI_RESET + " sciany.");
        System.out.println("A na " + ANSI_PURPLE + "fioletowo " + ANSI_RESET + "oznaczenia punkow na grafie \n(widoczne tylko na pokazowym labiryncie) \nwykorzystywane do przedstawienia sciezek przejscia labiryntu.");
        labirynthGenerator.setLabirynth(null);
        showMenu();
        int input = scanner.nextInt();
        String inputFile = new String();
        String saveFile = new String();

        while (input != 7) {
            switch (input) {
                case 1:
                    System.out.println();
                    System.out.println("Podaj dlugosc:");
                    int height = scanner.nextInt();
                    System.out.println("Podaj szerokosc:");
                    int width = scanner.nextInt();
                    labirynthGenerator.createLabirynth(height, width);
                    System.out.println("Stworzono labirynt.");
                    break;
                case 2:
                    if (labirynthGenerator.getLabirynth() == null) {
                        System.out.println();
                        System.out.println("Brak wygenerowanego labiryntu do zapisu.");
                        break;
                    } else {
                        System.out.println();
                        System.out.println("Podaj nazwe/sciezke pliku do zapisu");
                        saveFile = scanner.next();
                        saveAndLoad.save(labirynthGenerator.getLabirynth(), saveFile);
                        System.out.println();
                        System.out.println("Labirynt zapisany.");
                        break;
                    }
                case 3:
                    System.out.println();
                    System.out.println("Podaj nazwe/sciezke do pliku:");
                    inputFile = scanner.next();
                    labirynthGenerator.setLabirynth(saveAndLoad.load(inputFile));
                    labirynthGenerator.loadLabirynth();
                    System.out.println();
                    System.out.println("Wczytano plik");
                    break;
                case 4:

                    System.out.println();
                    System.out.println("Droga znaleziona przez algorytm BTS:");
                    bfs.searchExit(labirynthGenerator.getLabirynth());
                    break;
                case 5:
                    System.out.println();
                    System.out.println("Droga znaleziona przez algorytm Tremaux:");
                    tremaux.searchExit(labirynthGenerator.getLabirynth());
                    break;
                case 6:
                    labirynthGenerator.show(labirynthGenerator.getLabirynth());
                    break;
                case 7:
                    return;
                default:
                    System.out.println();
                    System.out.println("Podano zla liczbe, sprobuj ponownie.");
                    break;
            }
            showMenu();
            input = scanner.nextInt();
        }
    }

    static void showMenu() {
        System.out.println();
        System.out.println("Wybierz opcje:");
        System.out.println("1. Wygeneruj labirynt");
        System.out.println("2. Zapisz labirynt do pliku");
        System.out.println("3. Wczytaj labirynt z pliku");
        System.out.println("4. Znajdz sciezke za pomoca algorytmu BFS");
        System.out.println("5. Znajdz sciezke za pomoca algorytmu Tremaux'a");
        System.out.println("6. Pokaz labirynt");
        System.out.println("8. Zakoncz dzialanie programu");
    }
}
