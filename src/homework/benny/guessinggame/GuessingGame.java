package homework.benny.guessinggame;

import java.util.Random;
import java.util.Scanner;

public class GuessingGame {
    // instantiate scanner a single time and use for all instances of GuessingGame
    private static final Scanner sc = new Scanner(System.in);
    private static final Random rand = new Random();

    public static void main(String[] args) {
        String name = readText("Wie ist dein Name?");
        System.out.println("Willkommen " + name + " beim super coolen Zahlen Ratespiel ");
        System.out.println("Du hast nur 5 versuche!");
        int maxValue = 100;
        int numberToGuess = rand.nextInt(maxValue + 1);

        // since you only want to run n-iterations, for-loop instead of while is a bit easier
        // guessedNumber is only needed inside the loop, no need to declare it outside
        for (int i = 0; i < 5; i++) {
            int guessedNumber = getNextNumberFromPlayer("Was ist deine Nummer", 0, maxValue);
            if (guessedNumber < numberToGuess) {
                System.out.println("deine Zahl ist zu niedrig");
            } else if (guessedNumber > numberToGuess) {
                System.out.println("deine Zahl ist zu hoch");
            } else {
                System.out.println("du hast gewonnen!!!");
                break;
            }
            if (i == 4) {
                System.out.println("Looser!\nDie richtige Zahl war: " + numberToGuess);
            }
        }
        System.out.println("Danke fürs Spielen");
    }

    public static String readText(String message) {
        System.out.println(message);
        return sc.nextLine();
    }

    public static int getNextNumberFromPlayer(String message, int minValue, int maxValue) {
        int value = 0;
        boolean numberIsValid = false;
        while (!numberIsValid) {
            System.out.println(message);
            try {
                String line = sc.nextLine();
                value = Integer.valueOf(line);
                if ((value >= minValue) && (value <= maxValue)) {
                    numberIsValid = true;
                } else {
                    System.out.printf("Die Zahl muss zwischen %s und %s sein.%n", minValue, maxValue);
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Deine Zahl ist ungültig!!!");
            }
        }
        return value;
    }


}

