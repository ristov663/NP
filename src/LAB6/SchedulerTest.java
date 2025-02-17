package LAB6;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

class Scheduler<T> {
    private TreeMap<Date, T> schedules;

    Scheduler() {
        schedules = new TreeMap<>();
    }

    public void add(Date d, T t) {
        schedules.put(d, t);
    }

    public boolean remove(Date d) {
        if (schedules.containsKey(d)) {
            schedules.remove(d);
            return true;
        }
        return false;
    }

    public T next() {
        return schedules.ceilingEntry(new Date()).getValue();
    }

    public T last() {
        return schedules.floorEntry(new Date()).getValue();
    }

    public ArrayList<T> getAll(Date begin, Date end) {
        ArrayList<T> list = new ArrayList<>();
        schedules.subMap(begin, true, end, true).values().forEach(list::add);
        return list;
    }

    public T getFirst() {
        return schedules.firstEntry().getValue();
    }

    public T getLast() {
        return schedules.lastEntry().getValue();
    }
}

public class SchedulerTest {

    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        int k = jin.nextInt();

        if (k == 0) { // Test case with a small set of string tasks
            Scheduler<String> scheduler = new Scheduler<>();
            Date now = new Date();
            scheduler.add(new Date(now.getTime() - 7200000), jin.next());
            scheduler.add(new Date(now.getTime() - 3600000), jin.next());
            scheduler.add(new Date(now.getTime() - 14400000), jin.next());
            scheduler.add(new Date(now.getTime() + 7200000), jin.next());
            scheduler.add(new Date(now.getTime() + 14400000), jin.next());
            scheduler.add(new Date(now.getTime() + 3600000), jin.next());
            scheduler.add(new Date(now.getTime() + 18000000), jin.next());
            System.out.println(scheduler.getFirst());
            System.out.println(scheduler.getLast());
        }

        if (k == 3) { // Test case with string tasks and range queries
            Scheduler<String> scheduler = new Scheduler<>();
            Date now = new Date();
            scheduler.add(new Date(now.getTime() - 7200000), jin.next());
            scheduler.add(new Date(now.getTime() - 3600000), jin.next());
            scheduler.add(new Date(now.getTime() - 14400000), jin.next());
            scheduler.add(new Date(now.getTime() + 7200000), jin.next());
            scheduler.add(new Date(now.getTime() + 14400000), jin.next());
            scheduler.add(new Date(now.getTime() + 3600000), jin.next());
            scheduler.add(new Date(now.getTime() + 18000000), jin.next());
            System.out.println(scheduler.next());
            System.out.println(scheduler.last());
            ArrayList<String> res = scheduler.getAll(
                    new Date(now.getTime() - 10000000),
                    new Date(now.getTime() + 17000000)
            );
            Collections.sort(res);
            for (String t : res) {
                System.out.print(t + " , ");
            }
        }

        if (k == 4) { // Test case with integer tasks and complex range queries
            Scheduler<Integer> scheduler = new Scheduler<>();
            int counter = 0;
            ArrayList<Date> to_remove = new ArrayList<>();

            while (jin.hasNextLong()) {
                Date d = new Date(jin.nextLong());
                int i = jin.nextInt();
                if ((counter & 7) == 0) {
                    to_remove.add(d);
                }
                scheduler.add(d, i);
                ++counter;
            }
            jin.next();

            while (jin.hasNextLong()) {
                Date l = new Date(jin.nextLong());
                Date h = new Date(jin.nextLong());
                ArrayList<Integer> res = scheduler.getAll(l, h);
                Collections.sort(res);
                System.out.println(printWithGMT(l) + " <: " + print(res) + " >: " + printWithGMT(h));
            }
            System.out.println("test");

            ArrayList<Integer> res = scheduler.getAll(new Date(0), new Date(Long.MAX_VALUE));
            Collections.sort(res);
            System.out.println(print(res));
            for (Date d : to_remove) {
                scheduler.remove(d);
            }
            res = scheduler.getAll(new Date(0), new Date(Long.MAX_VALUE));
            Collections.sort(res);
            System.out.println(print(res));
        }
    }

    private static <T> String print(ArrayList<T> res) {
        if (res == null || res.size() == 0) return "NONE";
        StringBuilder sb = new StringBuilder();
        for (T t : res) {
            sb.append(t).append(" , ");
        }
        return sb.substring(0, sb.length() - 3);
    }

    private static String printWithGMT(Date date) {
        DateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        return df.format(date).replace("UTC", "GMT");
    }
}