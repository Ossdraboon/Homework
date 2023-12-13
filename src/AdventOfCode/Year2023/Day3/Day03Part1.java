package AdventOfCode.Year2023.Day3;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day03Part1 {

    public static void main(String[] args) {
        String path = "C:\\Users\\ossdr\\IdeaProjects\\Homework\\src\\AdventOfCode\\Year2023\\Data\\day03.txt";
        String input = TextIntoListOfStrings(path);

        char[][] field = StringsIntoCharArray(input);
        Print2DArray(field);
        System.out.println("=".repeat(80));

        for (int row = 0; row < field.length; row++) {
            for (int col = 0; col < field[row].length; col++) {
                CheckSurroundingSpots(field, row, col);
            }
        }

    }

    public static void CheckSurroundingSpots(char[][] field, int row, int col) {
        System.out.println("checking field pos ["+ row + "][" +col + "]");
        char base = field[row][col];
        char surrounding = ' ';

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                int neighbourRow = row + i;
                int neighbourCol = col + j;

                if (neighbourRow < 0) {
                    continue;
                } else if (neighbourRow >= field.length) {
                    continue;
                }
                if (neighbourCol < 0) {
                    continue;
                } else if (neighbourCol >= field[0].length) {
                    continue;
                }
                surrounding = field[neighbourRow][neighbourCol];
                System.out.println("base [" +base +"] is surrounded by (" + surrounding+")");

            }
        }
        System.out.println("=".repeat(80));
    }

    public static char[][] StringsIntoCharArray(String input) {
        int row = 140;
        int col = 140;
        char[][] array = new char[row][col];


        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                array[i][j] = input.charAt(j + i * col);

            }
        }
        return array;
    }

    public static void Print2DArray(char[][] values) {
        for (int i = 0; i < values.length; i++) {
            System.out.println(Arrays.toString(values[i]));
        }
    }

    public static String TextIntoListOfStrings(String path) {
        File textFile = new File(path);
        BufferedReader reader = null;
        String AllLines = "";
        try {
            reader = new BufferedReader(new FileReader(textFile));
            try {
                String text = reader.readLine();
                while (text != null) {
                    AllLines += text;
                    text = reader.readLine();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            close(reader);
        }
        return AllLines;

    }

    private static void close(Closeable stuff) {
        if (stuff != null) {
            try {
                stuff.close();
            } catch (IOException ioe) {
                //noop
            }
        }
    }


}
