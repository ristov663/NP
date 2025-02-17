package ISPIT;
import java.util.*;

class Participant implements Comparable<Participant> {

    private final String code;
    private final String name;
    private final int age;

    Participant(String code, String name, int age) {
        this.code = code;
        this.name = name;
        this.age = age;
    }

    public String getCode() {
        return code;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return age == that.age && Objects.equals(code, that.code) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name, age);
    }

    @Override
    public String toString() {
        return String.format("%s %s %d", code, name, age);
    }

    @Override
    public int compareTo(Participant o) {
        return Comparator
                .comparing(Participant::getName)
                .thenComparing(Participant::getAge)
                .compare(this, o);
    }
}

class Audition {

    Map<String, Set<Participant>> participants;

    public Audition() {
        this.participants = new HashMap<>();
    }

    public void addParticipant(String city, String code, String name, int age) {

        participants.computeIfAbsent(city, x -> new HashSet<>());
        Participant participant = new Participant(code, name, age);
        participants.get(city).add(participant);
    }

    public void listByCity(String city) {

        participants
                .get(city)
                .stream()
                .sorted()
                .forEach(System.out::println);
    }
}

public class AuditionTest {
    public static void main(String[] args) {
        Audition audition = new Audition();
        List<String> cities = new ArrayList<String>();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");
            if (parts.length > 1) {
                audition.addParticipant(parts[0], parts[1], parts[2],
                        Integer.parseInt(parts[3]));
            } else {
                cities.add(line);
            }
        }
        for (String city : cities) {
            System.out.printf("+++++ %s +++++\n", city);
            audition.listByCity(city);
        }
        scanner.close();
    }
}