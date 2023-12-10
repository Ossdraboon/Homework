package AdventOfCode.Year2023.Day1;

public class sandbox {
    public static void main(String[] args) {

        String example = "eighthreerznlsixrhtkjp23mtflmbrzq395three";

        String replacedString = example.replaceAll("one","1e");          //e
        replacedString = replacedString.replaceAll("two","t2w");          //t und o
        replacedString = replacedString.replaceAll("three","t3e");        //t und e
        replacedString = replacedString.replaceAll("four","4");
        replacedString = replacedString.replaceAll("five","5e");         //e
        replacedString = replacedString.replaceAll("six","6");
        replacedString = replacedString.replaceAll("seven","7n");        //n
        replacedString = replacedString.replaceAll("eight","e8t");        //e und t
        replacedString = replacedString.replaceAll("nine","n9e");         //n und e


        System.out.println(example);
        System.out.println("----");
        System.out.println(replacedString);


    }
}
