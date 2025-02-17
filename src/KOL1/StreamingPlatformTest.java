//package KOL1;
//
//import java.io.OutputStream;
//import java.io.PrintWriter;
//import java.util.*;
//import java.util.stream.Collectors;
//
//public class StreamingPlatformTest {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        StreamingPlatform sp = new StreamingPlatform();
//        while (sc.hasNextLine()){
//            String line = sc.nextLine();
//            String [] parts = line.split(" ");
//            String method = parts[0];
//            String data = Arrays.stream(parts).skip(1).collect(Collectors.joining(" "));
//            if (method.equals("addItem")){
//                sp.addItem(data);
//            }
//            else if (method.equals("listAllItems")){
//                sp.listAllItems(System.out);
//            } else if (method.equals("listFromGenre")){
//                System.out.println(data);
//                sp.listFromGenre(data, System.out);
//            }
//        }
//
//    }
//}
