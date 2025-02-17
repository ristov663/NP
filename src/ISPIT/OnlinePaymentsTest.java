package ISPIT;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Item implements Comparable<Item> {

    private final String name;
    private final int price;

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return price == item.price && Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    @Override
    public String toString() {
        return String.format("%s %d", name, price);
    }

    @Override
    public int compareTo(Item o) {
        return Comparator
                .comparing(Item::getPrice)
                .reversed()
                .compare(this, o);
    }
}

class OnlinePayments {

    private String index;
    Map<String, List<Item>> students;

    public OnlinePayments() {
        students = new HashMap<>();
    }

    void readItems (InputStream is) {

        Scanner scanner = new Scanner(is);

        while (scanner.hasNextLine()) {

            String line = scanner.nextLine();
            String [] parts = line.split(";");

            String index = parts[0];
            String name = parts[1];
            int price = Integer.parseInt(parts[2]);

            Item item = new Item(name, price);

            students.computeIfAbsent(index, x -> new ArrayList<>());
            students.computeIfPresent(index, (k, v) -> {
                v.add(item);
                return v;
            });
        }
    }

    void printStudentReport (String index, OutputStream os) {

        PrintWriter pw = new PrintWriter(os);

        if (!students.containsKey(index)) {
            pw.println("Student " + index + " not found!");
            pw.flush();
            return;
        }

        List<Item> items = students
                .get(index)
                .stream()
                .sorted()
                .collect(Collectors.toList());

        int net = items.stream().mapToInt(Item::getPrice).sum();

        int fee = (int) Math.round(net * 0.0114);
        fee = Math.max(3, Math.min(300, fee));

        pw.println("Student: " + index + " Net: " + net + " Fee: " + fee + " Total: " + (net+fee) + "\nItems:");

        for (int i = 0; i < items.size(); i++) {
            pw.println((i+1) + ". " + items.get(i));
        }
        pw.flush();
    }
}

public class OnlinePaymentsTest {
    public static void main(String[] args) {
        OnlinePayments onlinePayments = new OnlinePayments();

        onlinePayments.readItems(System.in);

        IntStream.range(151020, 151025).mapToObj(String::valueOf).forEach(id -> onlinePayments.printStudentReport(id, System.out));
    }
}