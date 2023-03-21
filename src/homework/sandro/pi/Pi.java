package homework.sandro.pi;

public class Pi {

    private static void calculatePi(int accuracy) {
        double pi = 0.0;
        boolean addValues = true;

        for (int i = 0; i <= accuracy; i++) {

            double iterationValue = 4.0 / (i * 2 + 1);


            if (addValues) {

                pi = pi + iterationValue;
            } else {

                pi = pi - iterationValue;
            }

            addValues = !addValues;
        }

        System.out.println(pi);
    }

    private static void calculatePiPrettier(int accuracy) {
        double pi = 0.0;
        for (int i = 0; i <= accuracy; i++) {
            double iterationValue = 4.0 / (i * 2 + 1);

            pi = i % 2 == 0 ? pi + iterationValue : pi - iterationValue;
        }
        System.out.println(pi);
    }

    public static void main(String args[]) {
        calculatePi(200000);
        calculatePiPrettier(200000);

    }
}