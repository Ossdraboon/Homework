package homework.benny.pi;

public class PiText {

    // berechnung von PI, Leibniz Reihe

    // accuracy ist nur eine Varable um zu sagen, wie oft die Schleife durchlaufen
    // soll; für deinen Fall kannst du das auch einfach weglassen und z.B. in die
    // Schleife <= 1000 reinschreiben je größer i in der Schleife wird, desto
    // kleiner wird das Ergebnis der Kalkulation 4/[(i*2)+1]. Das ganze geht gegen
    // 0, sodass es irgendwann keinen Impact mehr hat. Du könntest also auch die
    // Schleife 100000 durchlaufen lassen, hat irgendwan einfach keinen Einluss
    // mehr, weil der Wert zu klein wird du kannst auch testweise i mal <= 3 z.B.
    // stellen oder so, dann wirst du feststellen, dass das noch sehr ungenau ist.
    // Deshalb hab ich die Variable auch accuracy genannt. Je höher der Wert,
    // desto genauer das Ergebnis. Ab einem bestimmten Punkt brauchst du aber
    // nicht mehr Iterationen, weil der Bruch einfach gegen 0 geht und das
    // Aufsummieren zu nichts mehr führt in den vorderen Kommastellen des
    // Ergebnisses
    private static void calculatePi(int accuracy) {
        double pi = 0.0;
        boolean addValues = true;

        for (int i = 0; i <= accuracy; i++) {
            // ein einzelner Step in einer Schleife (ein Durchlauf) nennt sich
            // Iteration. Deshalb nenne ich die jeweilige Variable einer einzigen
            // Interation iterationValue WICHTIG!!! Würdest du 4 statt 4.0 schreiben
            // (kannst du gerne testen), würde hier Unfug rauskommen. Der Hintergrund
            // ist, dass Java anhand der Variablen entscheidet, welche Form von
            // Division angewandt werden muss. unser i in der Schleife ist eine
            // Ganzzahl. Die Zahl 4 ist auch eine Ganzzahl -> Ganzzahl / Ganzzahl
            // würde im Ergebnis auch eine Ganzzahl geben d.h. du würdest Ergebnisse
            // ohne Komma erhalten. D.h. im ersten Durchlauf der Schleife bekommst du
            // den Wert 4 und ab dann immer 0. Dein PI wäre also = 4, was Unfug ist.
            // Durch 4.0 erkennt Java, dass hier eine Gleitkommazahl im Spiel ist =>
            // Das Ergebnis wird Nachkommastellen habe und es kommt kein Bullshit raus
            double iterationValue = 4.0 / (i * 2 + 1);

            // wir wissen wir wollen als erstes addieren und abwechselnd subtrahiren,
            // addieren, subtrahieren... dazu benutzt ich jetzt mal einfach einen
            // boolean der immer nur zwei Stati haben kann. Und den switche ich vom
            // Wert jede Iteration
            if (addValues) {
                // schöner geht auch: pi += iterationValue;
                pi = pi + iterationValue;
            } else {
                // schöner geht auch: pi -= iterationValue;
                pi = pi - iterationValue;
            }

            // boolean hat zwei Werte, true und false. Das ! ist die Negation eines
            // boolean. Steht er auf true, kommt false raus. Steht er auf false, kommt
            // true raus. Wir wechseln also einfach jede Iteration den Wert. Dadurch
            // wird addiert, dann subtrahiert, dann addiert, dann subtrahiert, .....
            addValues = !addValues;
        }
        // hinweis: du könntest auch statt void double returnen und statt println hier return pi sagen.
        // Dann landet das Ergebnis in der main Methode und du kannst es dort z.B. einer Variable zuewisen und ausgben
        System.out.println(pi);
    }

    private static void calculatePiPrettier(int accuracy) {
        double pi = 0.0;
        for (int i = 0; i <= accuracy; i++) {
            // ein einzelner Step in einer Schleife (ein Durchlauf) nennt sich
            // Iteration. Deshalb nenne ich die jeweilige Variable einer einzigen
            // Interation iterationValue WICHTIG!!! Würdest du 4 statt 4.0 schreiben
            // (kannst du gerne testen), würde hier Unfug rauskommen. Der Hintergrund
            // ist, dass Java anhand der Variablen entscheidet, welche Form von
            // Division angewandt werden muss. unser i in der Schleife ist eine
            // Ganzzahl. Die Zahl 4 ist auch eine Ganzzahl -> Ganzzahl / Ganzzahl
            // würde im Ergebnis auch eine Ganzzahl geben d.h. du würdest Ergebnisse
            // ohne Komma erhalten. D.h. im ersten Durchlauf der Schleife bekommst du
            // den Wert 4 und ab dann immer 0. Dein PI wäre also = 4, was Unfug ist.
            // Durch 4.0 erkennt Java, dass hier eine Gleitkommazahl im Spiel ist =>
            // Das Ergebnis wird Nachkommastellen habe und es kommt kein Bullshit raus
            double iterationValue = 4.0 / (i * 2 + 1);

            // Das % ist der sogennante Modulo Operator. Er gibt dir den Rest von
            // einer Ganzzahloperation. Du kannst damit also gucken, ob eine Zahl
            // Gerade, oder Ungerade ist. In unserem Fall wollen wir bei Geraden
            // Zahlen addieren und bei ungeraden Zahlen subtrahierne. Um den Wert
            // zuzuweisen habe ich außerdem den ternaryOperator verwendet. Dieser
            // evaluiert einen boolean (i % 2 == 0) und wenn der boolean true ist
            // kommt das erste zurück (pi + iterationValue), wenn der boolean false
            // ist kommt das zweite zurück (pi - iterationValue)
            pi = i % 2 == 0 ? pi + iterationValue : pi - iterationValue;
        }
        System.out.println(pi);
    }

    public static void main(String args[]) {
        calculatePi(200000);
        calculatePiPrettier(200000);
        // beispiel wenn methoden double statt void returnen
        // double pi = calculatePi(200000);
        // System.out.println(pi)
    }
}
