package AdventOfCode.Year2023.Day2;

import java.io.*;

public class Day02Part1 {
    public static void main(String[] args) {
        String path = "C:\\Users\\ossdr\\IdeaProjects\\Homework\\src\\AdventOfCode\\Year2023\\Data\\day02.txt";
        String[] lines = LinesIntoArray(path);

        String testString = "Game 1: 1 green, 4 blue; 1 blue, 2 green, 1 red; 1 red, 1 green, 2 blue; 1 green, 1 red; 1 green; 1 green, 1 blue, 1 red";
        String newString[] = testString.split(";");

        CalculateRows(lines);


    }


    public static void CalculateRows(String[] lines) {
        String row = "";
        String[] rows = new String[100];
        String values = "";
        String[] rowValues = new String[0];

        int sum = 0;

        for (int i = 0; i < lines.length; i++) {
            row = lines[i];
            rows = SplitGame(row);
            int validRoundCounter = 0;

            for (int j = 0; j < rows.length; j++) {
                values = rows[j];
                rowValues = SplitRow(values);
                Boolean validRound = IsRoundValid(rowValues);

                if (validRound == true) {
                    validRoundCounter++;
                }

                //System.out.println("Game Nr. "+(i+1) + ") Round " + (j+1) + ") == " + validRound );
                // System.out.println("Game " + (i+1) + ") ");
                // System.out.println("Round " + (j+1) + " -- ");
                // PrintRow(rowValues);
            }
            if (validRoundCounter == rows.length) {
                System.out.println("Game " + (i + 1) + " is Valid.");
                sum = sum + (i +1);

            }else {
                System.out.println("Game " + (i + 1) + " is not Valid.");
            }


        }
        System.out.println("sum of all Valid games = " + sum);

//            for (String value:rows) {
//                String[] singleInput = SplitRow(value);
//                PrintRow(singleInput);
//                System.out.println("=".repeat(80));
//            }

        //PrintGame(SplitGame(row));
        //System.out.println("=".repeat(80));


    }

    public static Boolean IsRoundValid(String[] values) {
        String testValue = "";
        for (int i = 0; i < values.length; i++) {
            testValue = values[i];

            if (testValue.contains("green")) {
                String greenValue = testValue.replaceAll("green", "");
                int green = Integer.parseInt(greenValue.trim());
                if (green > 13) {
                    return false;

                }

            } else if (testValue.contains("red")) {
                String redValue = testValue.replaceAll("red", "");
                int green = Integer.parseInt(redValue.trim());
                if (green > 12) {
                    return false;

                }

            } else if (testValue.contains("blue")) {
                String blueValue = testValue.replaceAll("blue", "");
                int green = Integer.parseInt(blueValue.trim());
                if (green > 14) {
                    return false;

                }

            }
        }
        return true;

    }

    public static String[] SplitRow(String row) {
        String[] singleValue = row.split(",");

        return singleValue;
    }

    public static String[] SplitGame(String game) {
        String[] removeGame = game.split(":");
        String values = removeGame[1];
        String[] rounds = values.split(";");

        return rounds;

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

    public static String[] LinesIntoArray(String path) {
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
