package AdventOfCode.Year2023.Day1;

import java.io.*;

public class Day01Part1 {

    public static void main(String[] args) {

        String path = "C:\\Users\\ossdr\\IdeaProjects\\Homework\\src\\AdventOfCode\\Year2023\\Data\\day01.txt";
        int sum = evaluateText(path);
        System.out.println("Sum of All Lines = " + sum);

    }


    private static int calibrateValue(int lineData) {
        String value = String.valueOf(lineData);
        String number = "";

        if (value.length() == 1) {
            number = value + value;
            System.out.println("only one " + number);
        } else {
            String[] arr = value.split("");
            String firstNumb = arr[0];
            String lastNumb = arr[arr.length - 1];
            number = firstNumb + lastNumb;
            System.out.println("more then one " + number);

        }
       // lukis input
       // number = value.charAt(0)+""+value.charAt(value.length()-1);
        int result = Integer.parseInt(number);

        return result;
        //sumOfAllLines(result);

    }

    private static int onlyDigits(String line) {

        int result = Integer.parseInt(line.replaceAll("[\\D]", ""));
        //  System.out.println("test "+result);

        return result;
        //calibrateValue(result);

    }

    private static int evaluateText(String path) {
        File textFile = new File(path);
        BufferedReader reader = null;
        int count = 1;
        int sum = 0;
        try {
            reader = new BufferedReader(new FileReader(textFile));
            try {
                String text = reader.readLine();
                while (text != null) {
                    //System.out.println(count + ".)" + text);
                    count++;
                    int onlyDigitsLine = onlyDigits(text);
                    int valueLine = calibrateValue(onlyDigitsLine);
                    sum += valueLine;


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
        return sum;
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
