package ISPIT;

import java.util.*;
import java.util.stream.Collectors;

class DuplicateNumberException extends Exception {
    public DuplicateNumberException (String number) {
        super(String.format("Duplicate number: %s", number));
    }
}

class Contact implements Comparable<Contact> {

    private final String name;
    private final String number;

    public Contact(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }
    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return String.format("%s %s", name, number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return name.equals(contact.name) && number.equals(contact.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, number);
    }

    @Override
    public int compareTo(Contact o) {
        return Comparator.comparing(Contact::getName)
                .thenComparing(Contact::getNumber)
                .compare(this, o);
    }
}

class PhoneBook {

    private final Map<String, Contact> contactsByNumber;
    private final Map<String, Set<Contact>> contactsByName;

    PhoneBook() {
        this.contactsByNumber = new HashMap<>();
        this.contactsByName = new HashMap<>();
    }

    void addContact(String name, String number) throws DuplicateNumberException {

        if(contactsByNumber.containsKey(number)) {
            throw new DuplicateNumberException(number);
        }

        Contact contact = new Contact(name, number);

        contactsByNumber.put(number, contact);

        contactsByName.putIfAbsent(name, new TreeSet<>());
        contactsByName.computeIfPresent(name, (k, v) -> {
            v.add(contact);
            return v;
        });
    }

    void contactsByNumber(String number) {

        List<Contact> list = contactsByNumber
                .values()
                .stream()
                .filter(c -> c.getNumber().contains(number))
                .collect(Collectors.toList());

        if (list.isEmpty()) {
            System.out.println("NOT FOUND");
        }
        else {
            list.forEach(System.out::println);
        }
    }

    void contactsByName(String name) {

        Set<Contact> set = contactsByName.get(name);

        if (set.isEmpty()) {
            System.out.println("NOT FOUND");
        }
        else {
            set.forEach(System.out::println);
        }
    }
}
public class PhoneBookTest {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String line = scanner.nextLine();
            String[] parts = line.split(":");
            try {
                phoneBook.addContact(parts[0], parts[1]);
            } catch (DuplicateNumberException e) {
                System.out.println(e.getMessage());
            }
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(line);
            String[] parts = line.split(":");
            if (parts[0].equals("NUM")) {
                phoneBook.contactsByNumber(parts[1]);
            } else {
                phoneBook.contactsByName(parts[1]);
            }
        }
    }
}