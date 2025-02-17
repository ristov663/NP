package LAB1;

import java.util.Scanner;
import java.util.stream.IntStream;

public class IntegerConverterTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String roman = scanner.nextLine();
        System.out.println(IntegerConverter.toInteger(roman));

        scanner.close();
    }
}

class IntegerConverter {
    /**
     * Roman to decimal converter
     *
     * @param n number in decimal format
     * @return string representation of the number in Roman numeral
     */

    public static int toInteger(String n) {
        // your solution here
        String [] R = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int [] val = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

        int result = 0;


        for (int i=0; i<n.length(); i++) {

            switch (String.valueOf(n.charAt(i))) {
                case "M":
                    result += 1000;
                    break;
                case "CM":
                    result += 900;
                    break;
                case "D":
                    result += 500;
                    break;
                case "CD":
                    result += 400;
                    break;
                case "C":
                    result += 100;
                    break;
                case "XC":
                    result += 90;
                    break;
                case "L":
                    result += 50;
                    break;
                case "XL":
                    result += 40;
                    break;
                case "X":
                    result += 10;
                    break;
                case "IX":
                    result += 9;
                    break;
                case "V":
                    result += 5;
                    break;
                case "IV":
                    result += 4;
                    break;
                case "I":
                    result += 1;
                    break;
            }
        }


        return result;
    }

}

