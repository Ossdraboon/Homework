package homework.benny.calculator;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Calculator {
    private static final String EXIT_FLAG = "exit";
    private static final List<String> SUPPORTED_OPERATIONS = List.of("+", "-", "*", "/", "^");
    // you can ignore this initialization, I just wanted to avoid duplicates. This is way more complicated than what it needs to be :D
    // equivalent to List.of("+", "-", "*", "/", "^", "M+", "M-", "MC", "MR", "MS", "exit")
    private static final List<String> CALCULATOR_INPUTS = Stream.concat(SUPPORTED_OPERATIONS.stream(), Stream.of("M+", "M-", "MC", "MR", "MS", EXIT_FLAG)).map(Object::toString).toList();
    // You created the scanner every single time when wanting to read a new input. I moved it into a static variable so that all instances
    // of Calculator share the same scanner. For more details why this may make sense, you can read this post https://coderanch.com/wiki/678613/Don-close-Scanner-tied-System
    private static final Scanner sc = new Scanner(System.in);
    private float memory = 0.0f;
    private float calculationValue = 0.0f;
    // I created these two values to talk about static vs none static in the other file (Application.java)
    // note that just setting variables public and modifying them is not a good practice, I only do it for the sake of simplicity
    // usually the variables would be private, and you would write getter and setter methods
    public static float staticVariable = 0.0f;
    public float memberVariable = 0.0f;

    private boolean isValidInput(String input) {
        return CALCULATOR_INPUTS.contains(input);
    }

    public void start() {
        calculationValue = readNumber("Erste Zahl   ");
        /*
            I changed your boolean variable to a constant and interrupt the execution via break
            this allows me to separate concerns a little easier. While trying to move your operator logic into a method
            I had problems because there were two possible outcomes
            a) stop execution b) modify calculation value
            I moved the stop execution part out of the method so that I now have a clearer separation
        */
        while (true) {
            String operator = readOperator("Operator     ");
            if (EXIT_FLAG.equals(operator)) {
                // stop execution on exit, otherwise continue calculation
                break;
            }
            applyOperator(operator, calculationValue);
        }

        System.out.println("Auf Wiedersehen!");
    }

    private void applyOperator(String operator, float calculationValue) {
        // since we now only apply values to numbers instead of the boolean toggle state which you had with exit earlier, I was able to replace your if-else
        // with the 'new' switch expression which was introduced in java 12. You can find more details here: https://jax.de/blog/core-java-jvm-languages/wechselhaft-switch-expressions-in-java-12/
        switch (operator) {
            case "M+" -> memory += calculationValue;
            case "M-" -> memory -= calculationValue;
            case "MR" -> {
                calculationValue = memory;
                System.out.println("Ergebnis     : " + calculationValue);
            }
            case "MC" -> memory = 0;
            case "MS" -> memory = calculationValue;
            default -> {
                float operand = readNumber("Nächste Zahl ");
                applyOperation(operator, operand);
            }
        }
    }

    private void applyOperation(String operation, float operand) {
        /*
         your 'wert' variable did not really hold any meaning. Instead of settings values to 'wert' and then overwriting 'zahl' with 'wert'
          at the end, you can just write the values to 'zahl' directly. I am doing this here and skip the assigning values to 'wert' part

          I also rewrote your if-else if construct using a switch statement
         */
        switch (operation) {
            case "+" -> calculationValue += operand;
            case "-" -> calculationValue -= operand;
            case "*" -> calculationValue *= operand;
            case "^" -> calculationValue = (float) Math.pow(calculationValue, operand);
            case "/" -> calculationValue /= operand;
            default -> throw new IllegalStateException("This code should not be reachable, unless we messed up");
        }

        System.out.println("Ergebnis     : " + calculationValue);
    }

    private String readOperator(String message) {
        String operator = "";
        boolean firstTry = true;

        /*
            I have replaced your equals checks with an in my opinion more readable approach
            note: if you want to keep the equals checks that is totally fine, but you should also move them into a method as the code is quite lengthy
            when doing an equals check against string literals, always write the string literal first. An example:
                Do "-".equals(operator) instead of operator.equals("-"), this adds an implicit null-check
                what does that mean? If your operator variable was null, the first code would return false and the second would throw an error
         */
        while (!isValidInput(operator)) {
            if (!firstTry) {
                //note: this println statement does not contain all the valid options
                System.out.println("+ , - , * , / , ^ , oder exit");
            }
            System.out.print(message + ": ");
            operator = sc.nextLine();
            firstTry = false;
        }
        return operator;
    }

    //note: this method returns a 0 if someone enters nonsense. In other methods you would retry until something valid is added. Here you don't. Why is that?
    private float readNumber(String text) {
        System.out.print(text + ": ");
        try {
            String line = sc.nextLine();
            return Float.parseFloat(line);

        } catch (NumberFormatException nfe) {
            // using system.err instead of out
            System.err.println("Deine Zahl ist ungültig!!!");
        }
        return 0.0f;
    }

}
