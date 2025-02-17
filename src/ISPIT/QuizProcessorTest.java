package ISPIT;

import java.io.InputStream;
import java.util.*;

class QuizProcessor {

    public static Map<String, Double> processAnswers(InputStream is) {

        Scanner scanner = new Scanner(is);
        Map<String, Double> map = new LinkedHashMap<>();

        while (scanner.hasNextLine()) {

            Double points = 0.0;

            String line = scanner.nextLine();
            String [] parts = line.split(";");

            String index = parts[0];
            String [] answers = parts[1].split(", ");
            String [] correctAnswers = parts[2].split(", ");

            if (answers.length != correctAnswers.length) {
                System.out.println("A quiz must have same number of correct and selected answers");
                continue;
            }
            for (int i = 0; i < answers.length; i++) {
                if (answers[i].equals(correctAnswers[i])) {
                    points += 1.0;
                }
                else {
                    points -= 0.25;
                }
            }
            map.put(index, points);
        }
        return map;
    }
}
public class QuizProcessorTest {
    public static void main(String[] args) {
        QuizProcessor.processAnswers(System.in).forEach((k, v) -> System.out.printf("%s -> %.2f%n", k, v));
    }
}