package ISPIT;

import java.util.*;
import java.util.stream.Collectors;

class Student {
    private final String index;
    private final List<Integer> points;

    public Student(String index, List<Integer> points) {
        this.index = index;
        this.points = points;
    }

    public String getIndex() {
        return index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(index, student.index) && Objects.equals(points, student.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, points);
    }

    public boolean isFailed() {
        return points.size() < 8;
    }

    public boolean isPassed() {
        return points.size() >= 8;
    }

    public double getAveragePoints() {
        double sum = 0.0;
        for (int i = 0; i < points.size(); i++) {
            sum += points.get(i);
        }
        return sum / 10;
    }

    public int getYear() {
        return 20 - Integer.parseInt(index.substring(0, 2));
    }

    @Override
    public String toString() {
        if (isFailed()) {
            return String.format("%s NO %.2f", index, getAveragePoints());
        }

        else {
            return String.format("%s YES %.2f", index, getAveragePoints());
        }
    }
}

class LabExercises {

    private final List<Student> students;

    public LabExercises() {
        this.students = new ArrayList<>();
    }

    public void addStudent (Student student) {
        students.add(student);
    }

    public void printByAveragePoints (boolean ascending, int n) {

        Comparator<Student> comparator = Comparator
                .comparing(Student::getAveragePoints)
                .thenComparing(Student::getIndex);

        if(!ascending) {
            comparator = comparator.reversed();
        }

        students
                .stream()
                .sorted(comparator)
                .limit(n)
                .forEach(System.out::println);
    }

    public List<Student> failedStudents () {

        return students
                .stream()
                .filter(Student::isFailed)
                .sorted(Comparator
                        .comparing(Student::getIndex)
                        .thenComparing(Student::getAveragePoints))
                .collect(Collectors.toList());
    }

    public Map<Integer,Double> getStatisticsByYear() {

        List<Student> passedStudents = students
                .stream()
                .filter(Student::isPassed)
                .collect(Collectors.toList());

        return passedStudents
                .stream()
                .collect(Collectors.groupingBy(Student::getYear, Collectors.averagingDouble(Student::getAveragePoints)));
    }
}

public class LabExercisesTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LabExercises labExercises = new LabExercises();
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] parts = input.split("\\s+");
            String index = parts[0];
            List<Integer> points = Arrays.stream(parts).skip(1)
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(Collectors.toList());

            labExercises.addStudent(new Student(index, points));
        }

        System.out.println("===printByAveragePoints (ascending)===");
        labExercises.printByAveragePoints(true, 100);
        System.out.println("===printByAveragePoints (descending)===");
        labExercises.printByAveragePoints(false, 100);
        System.out.println("===failed students===");
        labExercises.failedStudents().forEach(System.out::println);
        System.out.println("===statistics by year");
        labExercises.getStatisticsByYear().entrySet().stream()
                .map(entry -> String.format("%d : %.2f", entry.getKey(), entry.getValue()))
                .forEach(System.out::println);
    }
}