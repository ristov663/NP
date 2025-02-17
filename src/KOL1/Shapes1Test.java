package KOL1;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

class Canvas implements Comparable<Canvas> {

    private String canvasId;
    private ArrayList<Integer> sizes;

    public Canvas() {
        this.canvasId = "";
        this.sizes = new ArrayList<>();
    }

    public Canvas(String canvasId, ArrayList<Integer> sizes) {
        this.canvasId = canvasId;
        this.sizes = sizes;
    }

    public String getCanvasId() {
        return canvasId;
    }
    public void setCanvasId(String canvasId) {
        this.canvasId = canvasId;
    }
    public int getSize() {
        return sizes.size();
    }
    public void setSizes(ArrayList<Integer> sizes) {
        this.sizes = sizes;
    }

    private int getPerimeter() {
        return sizes.stream().mapToInt(Integer::intValue).sum()*4;
    }

    @Override
    public String toString() {
        return canvasId + " " + getSize() + " " + getPerimeter();
    }

    @Override
    public int compareTo(Canvas o) {
        return Integer.compare(this.getPerimeter(), o.getPerimeter());
    }
}
class ShapesApplication {

    private ArrayList<Canvas> list;

    public ShapesApplication() {
        list = new ArrayList<>();
    }

    public int readCanvases (InputStream inputStream) {

        int counter = 0;
        Scanner scanner = new Scanner(inputStream);

        while (scanner.hasNextLine()) {

            String line = scanner.nextLine();

            String [] parts = line.split("\\s+");
            String canvasId = parts[0];

            ArrayList<Integer> sizes = new ArrayList<>();

            for (int i = 1; i < parts.length; i++) {
                sizes.add(Integer.parseInt(parts[i]));
            }

            counter = counter + sizes.size();

            list.add(new Canvas(canvasId, sizes));
        }

        return counter;
    }

    public void printLargestCanvasTo (OutputStream outputStream) {

        PrintWriter print = new PrintWriter(outputStream);

        print.println(list.stream().max(Canvas::compareTo).orElse(null));

        print.flush();
    }
}
public class Shapes1Test {

    public static void main(String[] args) {
        ShapesApplication shapesApplication = new ShapesApplication();

        System.out.println("===READING SQUARES FROM INPUT STREAM===");
        System.out.println(shapesApplication.readCanvases(System.in));
        System.out.println("===PRINTING LARGEST CANVAS TO OUTPUT STREAM===");
        shapesApplication.printLargestCanvasTo(System.out);

    }
}
