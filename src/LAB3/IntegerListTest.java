package LAB3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class IntegerList {

    private ArrayList<Integer> array;

    public IntegerList() {
        array = new ArrayList<>();
    }
    public IntegerList(Integer... numbers) {
        array = new ArrayList<>(Arrays.asList(numbers));
    }

    public void add(int el, int idx) {
        if(idx > array.size()) {

            int length = idx - array.size();

            for(int i = 0; i < length; i++) {
                array.add(0);
            }
            array.add(el);
            return;
        }
        array.add(idx, el);
    }

    public int remove(int idx) {
        if(idx < 0 || idx >= array.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return array.remove(idx);
    }

    public void set(int el, int idx) {
        if(idx < 0 || idx >= array.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        array.set(idx, el);
    }

    public int get(int idx) {
        if(idx < 0 || idx >= array.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return array.get(idx);
    }

    public int size() {
        return array.size();
    }


    public int count(int el) {
        int count = 0;

        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).equals(el)) {
                count++;
            }
        }
        return count;
    }

    public void removeDuplicates() {
        for(int i = array.size() - 1; i > 0; i--) {
            for(int j = i - 1; j >= 0; j--) {

                if(array.get(i).equals(array.get(j))) {
                    array.remove(j);
                    j++;
                    break;
                }
            }
        }
    }

    public int sumFirst(int k) {
        if(k < 0) {
            return 0;
        }
        if(k > array.size()) {
            k = array.size();
        }
        int sum = 0;

        for(int i = 0; i < k; i++) {
            sum += array.get(i);
        }
        return sum;
    }

    public int sumLast(int k) {
        if(k < 0) {
            return 0;
        }
        if(k > array.size()) {
            k = array.size();
        }
        int sum = 0;
        for(int i = array.size() - 1; i > array.size() - 1 - k; i--) {
            sum += array.get(i);
        }
        return sum;
    }

    public void shiftRight(int idx, int k) {
        if(idx < 0 || idx >= array.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }

        int targetIdx = (idx + k) % array.size();
        Integer element = array.remove(idx);
        array.add(targetIdx, element);
    }

    public void shiftLeft(int idx, int k) {
        if(idx < 0 || idx >= array.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }

        int targetIdx = (idx - k) % array.size();
        if(targetIdx < 0) targetIdx = array.size() + targetIdx;
        Integer element = array.remove(idx);
        array.add(targetIdx, element);
    }

    public IntegerList addValue(int value) {
        IntegerList newList = new IntegerList();

        for (int i = 0; i < array.size(); i++) {
            newList.add(array.get(i) + value, i);
        }
        return newList;
    }
}
public class IntegerListTest {

    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        int k = jin.nextInt();
        if ( k == 0 ) { //test standard methods
            int subtest = jin.nextInt();
            if ( subtest == 0 ) {
                IntegerList list = new IntegerList();
                while ( true ) {
                    int num = jin.nextInt();
                    if ( num == 0 ) {
                        list.add(jin.nextInt(), jin.nextInt());
                    }
                    if ( num == 1 ) {
                        list.remove(jin.nextInt());
                    }
                    if ( num == 2 ) {
                        print(list);
                    }
                    if ( num == 3 ) {
                        break;
                    }
                }
            }
            if ( subtest == 1 ) {
                int n = jin.nextInt();
                Integer a[] = new Integer[n];
                for ( int i = 0 ; i < n ; ++i ) {
                    a[i] = jin.nextInt();
                }
                IntegerList list = new IntegerList(a);
                print(list);
            }
        }
        if ( k == 1 ) { //test count,remove duplicates, addValue
            int n = jin.nextInt();
            Integer a[] = new Integer[n];
            for ( int i = 0 ; i < n ; ++i ) {
                a[i] = jin.nextInt();
            }
            IntegerList list = new IntegerList(a);
            while ( true ) {
                int num = jin.nextInt();
                if ( num == 0 ) { //count
                    System.out.println(list.count(jin.nextInt()));
                }
                if ( num == 1 ) {
                    list.removeDuplicates();
                }
                if ( num == 2 ) {
                    print(list.addValue(jin.nextInt()));
                }
                if ( num == 3 ) {
                    list.add(jin.nextInt(), jin.nextInt());
                }
                if ( num == 4 ) {
                    print(list);
                }
                if ( num == 5 ) {
                    break;
                }
            }
        }
        if ( k == 2 ) { //test shiftRight, shiftLeft, sumFirst , sumLast
            int n = jin.nextInt();
            Integer a[] = new Integer[n];
            for ( int i = 0 ; i < n ; ++i ) {
                a[i] = jin.nextInt();
            }
            IntegerList list = new IntegerList(a);
            while ( true ) {
                int num = jin.nextInt();
                if ( num == 0 ) { //count
                    list.shiftLeft(jin.nextInt(), jin.nextInt());
                }
                if ( num == 1 ) {
                    list.shiftRight(jin.nextInt(), jin.nextInt());
                }
                if ( num == 2 ) {
                    System.out.println(list.sumFirst(jin.nextInt()));
                }
                if ( num == 3 ) {
                    System.out.println(list.sumLast(jin.nextInt()));
                }
                if ( num == 4 ) {
                    print(list);
                }
                if ( num == 5 ) {
                    break;
                }
            }
        }
    }

    public static void print(IntegerList il) {
        if ( il.size() == 0 ) System.out.print("EMPTY");
        for ( int i = 0 ; i < il.size() ; ++i ) {
            if ( i > 0 ) System.out.print(" ");
            System.out.print(il.get(i));
        }
        System.out.println();
    }

}