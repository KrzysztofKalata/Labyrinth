package saveAndLoad;

import java.io.*;

public class SaveAndLoad {

    public void save(char[][] labirynth, String fileName) {
        try {

            PrintWriter printWriter = new PrintWriter(fileName);
            for (int i = 0; i < labirynth.length; i++) {
                for (int j = 0; j < labirynth[0].length; j++) {
                    printWriter.print(labirynth[i][j]);
                }
                printWriter.println();
            }
            printWriter.close();

        } catch (FileNotFoundException e) {
            System.out.println("Nie udało sie zapisac pliku");
            e.printStackTrace();
        }
    }

    public char[][] load(String fileName) {
        File file;
        FileReader reader;
        FileReader reader2;

        char[][] labirynth;
        int width = 0;
        int height = 0;

        try {
            file = new File(fileName);
            reader = new FileReader(file);
            int i;

            while ((i = reader.read()) != -1) {
                if ((char) i == '\n') {
                    height++;
                }
                if (height == 0) {
                    width++;
                }
            }
            width--;
            reader.close();

            reader2 = new FileReader(file);

            labirynth = new char[height][width];
            for (int j = 0; j < height; j++) {
                for (int k = 0; k < width; k++) {
                    labirynth[j][k] = (char) reader2.read();
                }
                reader2.read();
                reader2.read();
            }

            return labirynth;

        } catch (Exception e) {
            System.out.println("Nie udało sie otworzyc pliku");
            e.printStackTrace();
        }
        return null;
    }
}

