package AdventOfCode.Year2023.Day1;

import java.io.*;

public class Day01Part2 {

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
        int result = Integer.parseInt(number);

        return result;
        //sumOfAllLines(result);

    }

    private static String replaceWordsToNumbers(String text){
        //lukis input
        /*
        String[] arr = {"one"};
        String replacedString = text;
        int numberValue = 1;
        for(String number : arr) {
            replacedString = replacedString.replaceAll(number, number.charAt(0)+""+numberValue+""+number.charAt(number.length()-1));
            numberValue++;
            }
            */


        String replacedString = text.replaceAll("one","o1e");                //o und e
        replacedString = replacedString.replaceAll("two","t2o");             //t und o
        replacedString = replacedString.replaceAll("three","t3e");           //t und e
        replacedString = replacedString.replaceAll("four","4");
        replacedString = replacedString.replaceAll("five","5e");             //e
        replacedString = replacedString.replaceAll("six","6");
        replacedString = replacedString.replaceAll("seven","7n");            //n
        replacedString = replacedString.replaceAll("eight","e8t");           //e und t
        replacedString = replacedString.replaceAll("nine","n9e");            //n und e

        //54659

        return replacedString;
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
                    String replacedLine = replaceWordsToNumbers(text);
                    int onlyDigitsLine = onlyDigits(replacedLine);
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
