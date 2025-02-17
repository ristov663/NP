package KOL1;

import java.io.*;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Participant {
    private int id;
    private String startTime;
    private String endTime;
    private long runTimeSeconds;

    public Participant(int id, String startTime, String endTime) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.runTimeSeconds = calculateRunTimeInSeconds();
    }

    public static Participant createParticipant(String line) {
        String[] parts = line.split("\\s+");
        int id = Integer.parseInt(parts[0]);
        String startTime = parts[1];
        String endTime = parts[2];
        return new Participant(id, startTime, endTime);
    }

    private long calculateRunTimeInSeconds() {
        LocalTime start = LocalTime.parse(startTime);
        LocalTime end = LocalTime.parse(endTime);
        return Duration.between(start, end).getSeconds();
    }

    public long getRunTimeSeconds() {
        return runTimeSeconds;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        long hours = runTimeSeconds / 3600;
        long minutes = (runTimeSeconds % 3600) / 60;
        long seconds = runTimeSeconds % 60;
        return String.format("%d %02d:%02d:%02d", id, hours, minutes, seconds);
    }
}

class TeamRace {
    public static void findBestTeam(InputStream is, OutputStream os) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(os));

        List<Participant> participants = br.lines()
                .map(Participant::createParticipant)
                .sorted(Comparator.comparingLong(Participant::getRunTimeSeconds))
                .collect(Collectors.toList());

        List<Participant> bestTeam = participants.subList(0, 4);
        long totalRunTime = bestTeam.stream()
                .mapToLong(Participant::getRunTimeSeconds)
                .sum();

        bestTeam.forEach(pw::println);

        long hours = totalRunTime / 3600;
        long minutes = (totalRunTime % 3600) / 60;
        long seconds = totalRunTime % 60;
        pw.printf("%02d:%02d:%02d%n", hours, minutes, seconds);

        pw.flush();
    }
}

public class RaceTest {
    public static void main(String[] args) {
        try {
            TeamRace.findBestTeam(System.in, System.out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
