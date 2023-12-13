package AdventOfCode.Year2023.Day2;

import java.io.*;

public class Day02Part2 {
    public static void main(String[] args) {
        String path = "C:\\Users\\ossdr\\IdeaProjects\\Homework\\src\\AdventOfCode\\Year2023\\Data\\day02.txt";
        String[] lines = TextIntoStringArray(path);

        String testString = "Game 1: 1 green, 4 blue; 1 blue, 2 green, 1 red; 1 red, 1 green, 2 blue; 1 green, 1 red; 1 green; 1 green, 1 blue, 1 red";
        String newString[] = testString.split(";");

        int sumOfAllPowers = CalculatePowerOfAllGames(lines);
        System.out.println("Sum of all Games/Power = " +sumOfAllPowers);

    }


    public static int CalculatePowerOfAllGames(String[] lines) {
        String row;
        String[] rowValues;
        int tempPower;
        int allRowsPower = 0;

        for (int i = 0; i < lines.length; i++) {
            row = lines[i];
            rowValues = SplitLineIntoValues(row);

            tempPower = CalculatePowerOfGame(rowValues);
            allRowsPower += tempPower;
        }
        return allRowsPower;
    }


    public static int CalculatePowerOfGame(String[] values) {

        int maxGreen = 0;
        int maxRed = 0;
        int maxBlue = 0;

        int linePower;

        for (int i = 0; i < values.length; i++) {


            if (values[i].contains("green")) {
                String greenValue = values[i].replaceAll("green", "");
                int green = Integer.parseInt(greenValue.trim());
                if (green > maxGreen) {
                    maxGreen = green;

                }

            } else if (values[i].contains("red")) {
                String redValue = values[i].replaceAll("red", "");
                int red = Integer.parseInt(redValue.trim());
                if (red > maxRed) {
                    maxRed = red;

                }

            } else if (values[i].contains("blue")) {
                String blueValue = values[i].replaceAll("blue", "");
                int blue = Integer.parseInt(blueValue.trim());
                if (blue > maxBlue) {
                    maxBlue = blue;

                }

            }

        }

        linePower = maxGreen * maxBlue * maxRed;

        return linePower;
    }


    public static String[] SplitLineIntoValues(String game) {
        String[] removeGame = game.split(":");
        String line = removeGame[1];
        String[] value = line.split(";|,");

        return value;

    }

    public static void PrintRow(String[] row) {
        for (int i = 0; i < row.length; i++) {
            System.out.println(row[i]);
        }
    }

    public static void PrintGame(String[] game) {
        for (int i = 0; i < game.length; i++) {
            System.out.println("Round " + (i + 1) + " )   " + game[i]);
        }
    }

    public static void PrintLines(String[] lines) {
        for (int i = 0; i < lines.length; i++) {
            System.out.println("Row " + (i + 1) + " )   " + lines[i]);

        }

    }

    public static String[] TextIntoStringArray(String path) {
        File textFile = new File(path);
        BufferedReader reader = null;
        int counter = 0;
        String[] AllLines = new String[100];
        try {
            reader = new BufferedReader(new FileReader(textFile));
            try {
                String text = reader.readLine();
                while (text != null) {
                    //System.out.println(counter + ".)" + text);
                    AllLines[counter] = text;
                    counter++;
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
