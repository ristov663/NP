package ISPIT;

import java.time.*;
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Subtitle {
    private final int id;
    private final String text;
    private LocalTime from;
    private LocalTime to;

    public Subtitle(int id, String text, String time) {
        this.id = id;
        this.text = text;

        String [] times = time.split("-->");
        String [] timesFrom = times[0].split("[:,]");
        String [] timesTo = times[1].split("[:,]");

        this.from = LocalTime.of(
                Integer.parseInt(timesFrom[0]),
                Integer.parseInt(timesFrom[1]),
                Integer.parseInt(timesFrom[2]),
                Integer.parseInt(timesFrom[3]) * 1000000
        );

        this.to = LocalTime.of(
                Integer.parseInt(timesTo[0]),
                Integer.parseInt(timesTo[1]),
                Integer.parseInt(timesTo[2]),
                Integer.parseInt(timesTo[3]) * 1000000
        );
    }

    public void shift(int time) {
        from = from.plus(time, ChronoUnit.MILLIS);
        to = to.plus(time, ChronoUnit.MILLIS);
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss,SSS");
        return String.format("%d%n%s --> %s%n%s%n", id, from.format(dtf), to.format(dtf), text);
    }
}

class Subtitles {

    private final List<Subtitle> list;

    public Subtitles() {
        this.list = new ArrayList<>();
    }

    public int loadSubtitles(InputStream inputStream) {

        Scanner scanner = new Scanner(inputStream);

        while (scanner.hasNextLine()) {

            int id = scanner.nextInt();
            scanner.nextLine();

            String time = scanner.nextLine();

            List<String> strings = new ArrayList<>();

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                if (line.trim().length() == 0) {
                    break;
                }
                strings.add(line);
            }
            list.add(new Subtitle(id, String.join("\n", strings), time));
        }
        return list.size();
    }

    public void print() {

        for (int i = 0; i < list.size(); i++) {
            list.get(i).toString();
        }
    }

    public void shift(int ms) {

        for (int i = 0; i < list.size(); i++) {
            list.get(i).shift(ms);
        }
    }
}

public class SubtitlesTest {
    public static void main(String[] args) {
        Subtitles subtitles = new Subtitles();
        int n = subtitles.loadSubtitles(System.in);
        System.out.println("+++++ ORIGINIAL SUBTITLES +++++");
        subtitles.print();
        int shift = n * 37;
        shift = (shift % 2 == 1) ? -shift : shift;
        System.out.println(String.format("SHIFT FOR %d ms", shift));
        subtitles.shift(shift);
        System.out.println("+++++ SHIFTED SUBTITLES +++++");
        subtitles.print();
    }
}