package homework.sandro.calculator;

import java.util.Scanner;

public class Taschenrechner {


    public static void main(String[] args) {
        System.out.println("*****+++++Taschenrechner Premium Deluxe 3000 (s Series)+++++*****");
        System.out.println();

        boolean repeat = true;
        float memory = 0.0f;
        float zahl = readZahl("Erste Zahl   ");

        while (repeat) {
            String operator = readOperator("Operator     ");
            if (operator.equals("exit")) {
                repeat = false;
            } else if (operator.equals("M+")) {
                memory += zahl;
            } else if (operator.equals("M-")) {
                memory -= zahl;
            } else if (operator.equals("MR")) {
                zahl = memory;
                System.out.println("Ergebnis     : " + zahl);
            } else if (operator.equals("MC")) {
                memory = 0;
            } else if (operator.equals("MS")) {
                memory = zahl;
            } else {
                float zahl2 = readZahl("Nächste Zahl ");
                float wert = 0.0f;

                if (operator.equals("+")) {
                    wert = zahl + zahl2;
                } else if (operator.equals("-")) {
                    wert = zahl - zahl2;
                } else if (operator.equals("*")) {
                    wert = zahl * zahl2;
                } else if (operator.equals("^")) {
                    wert = 1;
                    for (int i = 0; i < zahl2; i++) {
                        wert *= zahl;
                    }
                } else if (operator.equals("/")) {
                    wert = zahl / zahl2;
                } else {
                    System.out.println("Unknown operator");
                }
                System.out.println("Ergebnis     : " + wert);
                zahl = wert;
            }
        }

        System.out.println("Auf Wiedersehen!");
    }

    public static String readOperator(String message) {
        Scanner sc = new Scanner(System.in);
        String operator = "";
        boolean firstTry = true;

        while (
                !operator.equals("+") &&
                        !operator.equals("-") &&
                        !operator.equals("*") &&
                        !operator.equals("/") &&
                        !operator.equals("^") &&
                        !operator.equals("M+") &&
                        !operator.equals("M-") &&
                        !operator.equals("MC") &&
                        !operator.equals("MR") &&
                        !operator.equals("MS") &&
                        !operator.equals("exit")
        ) {
            if (!firstTry) {
                System.out.println("+ , - , * , / , ^ , oder exit");
            }
            System.out.print(message + ": ");
            operator = sc.nextLine();
            firstTry = false;
        }

        return operator;
    }

    public static String readText(String message) {
        Scanner sc = new Scanner(System.in);
        System.out.println(message);
        return sc.nextLine();
    }

    public static float readZahl(String text) {
        System.out.print(text + ": ");
        Scanner sc = new Scanner(System.in);
        float value = 0.0f;
        try {
            String line = sc.nextLine();
            value = Float.valueOf(line);

        } catch (NumberFormatException nfe) {
            System.out.println("Deine Zahl ist ungültig!!!");
        }

        return value;
    }

    public static boolean readJaNein(String message) {
        Scanner sc = new Scanner(System.in);
        System.out.println(message);
        String answer = sc.nextLine();

        return answer.equals("ja") || answer.equals("Ja") || answer.equals("j") || answer.equals("J");
    }
}

