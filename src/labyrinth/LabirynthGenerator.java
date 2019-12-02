package labyrinth;

import java.util.Random;

public class LabirynthGenerator {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";


    private Random random = new Random();
    private char[][] labirynth = null;
    private int height;
    private int width;
    private boolean isCreated = false;

    public void createLabirynth(int height, int width) {

        //skalowanie do rozmiarow planszy
        height = height * 2 + 1;
        width = width * 2 + 1;
        this.height = height;
        this.width = width;
        isCreated = true;

        labirynth = new char[height][width];

        //sciany z góry i z dołu
        for (int i = 0; i < height; i++) {
            labirynth[i][0] = '+';
            labirynth[i][width - 1] = '+';
        }
        //sciany z lewej i prawej
        for (int i = 0; i < width; i++) {
            labirynth[0][i] = '+';
            labirynth[height - 1][i] = '+';
        }
        //wnetrze
        for (int i = 1; i < height - 1; i++)
            for (int j = 1; j < width - 1; j++)
                labirynth[i][j] = '0';
        //sciany
        generateWall(0, width - 1, 0, height - 1);
        //wyjscia
        labirynth[0][random.nextInt((width - 1) / 2) * 2 + 1] = '#';
        labirynth[height - 1][random.nextInt((width - 1) / 2) * 2 + 1] = '*';
    }


    private void generateWall(int fromX, int toX, int fromY, int toY) {
        if (toX - fromX <= 2 || toY - fromY <= 2)
            return;

        int whereSetWall;
        int whereMakeTunnel;
        //Poziome ściany
        if (toY - fromY > toX - fromX) {

            whereSetWall = random.nextInt(((toY - fromY) / 2) - 1) + 1;
            whereSetWall = whereSetWall * 2 + fromY;

            for (int i = fromX; i < toX; i++)
                labirynth[whereSetWall][i] = '+';

            whereMakeTunnel = random.nextInt((toX - fromX - 1) / 2) + 1;
            whereMakeTunnel = whereMakeTunnel * 2 - 1 + fromX;
            labirynth[whereSetWall][whereMakeTunnel] = '0';

            generateWall(fromX, toX, whereSetWall, toY);
            generateWall(fromX, toX, fromY, whereSetWall);
        }
        //Pionowe ściany
        else {

            whereSetWall = random.nextInt(((toX - fromX) / 2) - 1) + 1;
            whereSetWall = whereSetWall * 2 + fromX;

            for (int i = fromY; i < toY; i++)
                labirynth[i][whereSetWall] = '+';

            whereMakeTunnel = random.nextInt((toY - fromY - 1) / 2) + 1;
            whereMakeTunnel = whereMakeTunnel * 2 - 1 + fromY;
            labirynth[whereMakeTunnel][whereSetWall] = '0';

            generateWall(whereSetWall, toX, fromY, toY);
            generateWall(fromX, whereSetWall, fromY, toY);
        }
    }

    public void show(char[][] labirynth) {

        for (int i = 0; i < labirynth.length; i++) {
            for (int j = 0; j < labirynth[0].length; j++) {
                if (labirynth[i][j] == '+') System.out.print(ANSI_YELLOW + labirynth[i][j] + ANSI_RESET);
                if (labirynth[i][j] == '*') System.out.print(ANSI_BLUE + labirynth[i][j] + ANSI_RESET);
                if (labirynth[i][j] == '#') System.out.print(ANSI_BLUE + labirynth[i][j] + ANSI_RESET);
                if (labirynth[i][j] == '0') System.out.print(ANSI_RED + labirynth[i][j] + ANSI_RESET);
                if (labirynth[i][j] >= '1' && labirynth[i][j] <= '9')
                    System.out.print(ANSI_PURPLE + labirynth[i][j] + ANSI_RESET);
            }
            System.out.println();
        }

    }

    public void loadLabirynth() {
        isCreated = true;
        height = labirynth.length;
        width = labirynth[0].length;
    }

    public char[][] getLabirynth() {
        return labirynth;
    }

    public void setLabirynth(char[][] labirynth) {
        if (labirynth == null) {
            isCreated = false;
            height = 0;
            width = 0;
        }
        this.labirynth = labirynth;
    }
}
