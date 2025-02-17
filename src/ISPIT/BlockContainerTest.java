package ISPIT;

import java.util.*;
import java.util.stream.Collectors;

class BlockContainer<T extends Comparable<T>> {

    private List<Set<T>> blocks;
    private final int maxElements;

    public BlockContainer (int n) {

        this.maxElements = n;
        this.blocks = new ArrayList<>();
    }


    public void add(T a) {

        if(blocks.isEmpty()) {
            Set<T> treeSet = new TreeSet<>();
            treeSet.add(a);
            blocks.add(treeSet);
        }
        else {
            Set<T> lastSet = blocks.get(blocks.size() - 1);

            if (lastSet.size() >= maxElements) {
                lastSet = new TreeSet<>();
                lastSet.add(a);
                blocks.add(lastSet);
            }
            else {
                lastSet.add(a);
            }
        }
    }

    public boolean remove(T a) {

        boolean flag = false;

        if(!blocks.isEmpty()) {
            Set<T> lastSet = blocks.get(blocks.size() - 1);
            lastSet.remove(a);
            flag = true;

            if(lastSet.isEmpty()) {
                blocks.remove(blocks.size() - 1);
            }
        }
        return flag;
    }

    public void sort() {

        List<T> list = new ArrayList<>();
        blocks.forEach(list::addAll);
        list.sort(Comparator.naturalOrder());

        blocks = new ArrayList<>(list
                .stream()
                .collect(Collectors.groupingBy(i -> list.indexOf(i) / maxElements, Collectors.toCollection(TreeSet::new)))
                .values());
    }

    @Override
    public String toString() {
        List<String> strings = new ArrayList<>();
        blocks.forEach(s -> strings.add(s.toString()));

        return String.join(",", strings);
    }
}

public class BlockContainerTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int size = scanner.nextInt();
        BlockContainer<Integer> integerBC = new BlockContainer<Integer>(size);
        scanner.nextLine();
        Integer lastInteger = null;
        for(int i = 0; i < n; ++i) {
            int element = scanner.nextInt();
            lastInteger = element;
            integerBC.add(element);
        }
        System.out.println("+++++ Integer Block Container +++++");
        System.out.println(integerBC);
        System.out.println("+++++ Removing element +++++");
        integerBC.remove(lastInteger);
        System.out.println("+++++ Sorting container +++++");
        integerBC.sort();
        System.out.println(integerBC);
        BlockContainer<String> stringBC = new BlockContainer<String>(size);
        String lastString = null;
        for(int i = 0; i < n; ++i) {
            String element = scanner.next();
            lastString = element;
            stringBC.add(element);
        }
        System.out.println("+++++ String Block Container +++++");
        System.out.println(stringBC);
        System.out.println("+++++ Removing element +++++");
        stringBC.remove(lastString);
        System.out.println("+++++ Sorting container +++++");
        stringBC.sort();
        System.out.println(stringBC);
    }
}