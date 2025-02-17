package LAB4;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

class RandomGenerator {

    static Random random = new Random();

    // Generate a random integer between a and b (inclusive of "a", exclusive of b)
    public static int randomBetween(int a, int b) {
        return a + random.nextInt(b - a);
    }

    // Generate a random double between 0.0 and 1.0
    public static double randomDouble() {
        return random.nextDouble();
    }

    // Generate a random double between a and b
    public static double randomDoubleBetween(double a, double b) {
        return a + random.nextDouble() * (b - a);
    }

    // Generate a random integer within a specified range (inclusive)
    public static int randomIntInclusive(int a, int b) {
        return a + random.nextInt(b - a + 1);
    }

    // Generate a list of random integers between a and b (exclusive)
    public static List<Integer> listOfRandomBetween(int a, int b, int length) {
        return IntStream.range(0, length)
                .mapToObj(i -> randomBetween(a, b))
                .collect(Collectors.toList());
    }

    // Generate a list of random longs between a and b (exclusive)
    public static List<Long> listOfRandomBetween(long a, long b, int length) {
        return LongStream.range(0, length)
                .mapToObj(i -> a + (long) (random.nextDouble() * (b - a)))
                .collect(Collectors.toList());
    }


    //NEW
    // Metod shto vraka random broj so odreden broj na cifri
    public static int randomWithDigits(int numDigits) {

        int number;

        int min = (int) Math.pow(10, numDigits-1);
        int max = (int) Math.pow(10, numDigits) - 1;

        number = randomBetween(min, max);

        return number;
    }
}

interface TestNumber<T extends Number> {
    boolean test(T number);
}

interface TestNumbersList<T extends Number> {
    boolean test(List<T> numbers);
}

public class RandomTest {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Test randomBetween for integers
        int a = sc.nextInt();
        int b = sc.nextInt();

        int x = RandomGenerator.randomBetween(a, b);

        TestNumber<Integer> testRandomBetween = n -> n >= a && n < b;
        System.out.println("Test for randomBetween method: " + testRandomBetween.test(x));

        // Test randomDouble
        double randomDouble = RandomGenerator.randomDouble();
        TestNumber<Double> testRandomDouble = n -> n>=0.0 && n<1.0;
        System.out.println("Test for randomDouble method: " + testRandomDouble.test(randomDouble));


        // Test randomDoubleBetween

        double da = sc.nextDouble();
        double db = sc.nextDouble();
        double randomDoubleBetween = RandomGenerator.randomDoubleBetween(da, db);

        TestNumber<Double> testRandomDoubleBetween = n -> n >= da && n < db;
        System.out.println("Test for randomDoubleBetween method: " + testRandomDoubleBetween.test(randomDoubleBetween));

        // Test randomIntInclusive

        int inclusiveA = sc.nextInt();
        int inclusiveB = sc.nextInt();
        int randomIntInclusive = RandomGenerator.randomIntInclusive(inclusiveA, inclusiveB);

        TestNumber<Integer> testRandomIntInclusive = n -> n >= inclusiveA && n <= inclusiveB;
        System.out.println("Test for randomIntInclusive method: " + testRandomIntInclusive.test(randomIntInclusive));

        // Test listOfRandomBetween for integers

        int listA = sc.nextInt();
        int listB = sc.nextInt();
        int listLength = sc.nextInt();
        List<Integer> randomIntList = RandomGenerator.listOfRandomBetween(listA, listB, listLength);

        TestNumbersList<Integer> testRandomIntList = numbers -> numbers.stream().allMatch(n -> n >= listA && n < listB);
        System.out.println("Test for listOfRandomBetween (int): " + testRandomIntList.test(randomIntList));

        // Test listOfRandomBetween for longs

        long longA = sc.nextLong();
        long longB = sc.nextLong();
        int longListLength = sc.nextInt();
        List<Long> randomLongList = RandomGenerator.listOfRandomBetween(longA, longB, longListLength);

        TestNumbersList<Long> testRandomLongList = numbers -> numbers.stream().allMatch(n -> n >= longA && n < longB);
        System.out.println("Test for listOfRandomBetween (long): " + testRandomLongList.test(randomLongList));

        sc.close();
    }
}

