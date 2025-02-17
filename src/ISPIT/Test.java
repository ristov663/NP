//package ISPIT;
//
//import java.io.InputStream;
//import java.util.*;
//import java.util.stream.Collectors;
//
//class Product implements Comparable<Product> {
//
//    private final int price;
//    private final int discountedPrice;
//
//    public Product(int price, int discountedPrice) {
//        this.price = price;
//        this.discountedPrice = discountedPrice;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Product product = (Product) o;
//        return price == product.price && discountedPrice == product.discountedPrice;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(price, discountedPrice);
//    }
//
//    @Override
//    public String toString() {
//        return String.format("%2d%% %d/%d", getDiscountRelative(), discountedPrice, price);
//    }
//
//    public int getDiscountRelative() {
//        return 100 - (discountedPrice * 100 / price);
//    }
//
//    public int getDiscountAbsolute() {
//        return price - discountedPrice;
//    }
//
//    @Override
//    public int compareTo(Product o) {
//        return Comparator
//                .comparing(Product::getDiscountRelative)
//                .thenComparing(Product::getDiscountAbsolute)
//                .reversed()
//                .compare(this, o);
//    }
//}
//
//class Store {
//
//    private final String name;
//    private final List<Product> products;
//
//    public Store(String name, List<Product> products) {
//        this.name = name;
//        this.products = products;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Store store = (Store) o;
//        return Objects.equals(name, store.name) && Objects.equals(products, store.products);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(name, products);
//    }
//
//    public double getDiscountAverage() {
//        int sumOfDiscount = 0;
//        for (int i = 0; i < products.size(); i++) {
//            sumOfDiscount += products.get(i).getDiscountRelative();
//        }
//        return (sumOfDiscount * 1.0) / products.size();
//    }
//
//    public int getDiscountTotal() {
//
//        int sumOfDiscount = 0;
//        for (int i = 0; i < products.size(); i++) {
//            sumOfDiscount += products.get(i).getDiscountAbsolute();
//        }
//        return sumOfDiscount;
//    }
//
//    @Override
//    public String toString() {
//        return String.format("%s%nAverage discount: %.1f%%%nTotal discount: %d%n%s",
//                name,
//                getDiscountAverage(),
//                getDiscountTotal(),
//                products
//                        .stream()
//                        .sorted(Comparator.comparing(Product::getDiscountRelative)
//                                .thenComparing(Product::getDiscountAbsolute)
//                                .reversed())
//                        .map(Product::toString)
//                        .collect(Collectors.joining("\n")));
//    }
//}
//
//class Discounts {
//
//    private final List<Store> stores;
//
//    public Discounts() {
//        this.stores = new ArrayList<>();
//    }
//
//    public int readStores(InputStream inputStream) {
//
////        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
//
//        Scanner scanner = new Scanner(inputStream);
//        int counter = 0;
//
//        while (scanner.hasNextLine()) {
//            String line = scanner.nextLine().trim();
//            if (line.isEmpty()) continue;
//
//            String [] parts = line.split("\\s+");
//
//            String name = parts[0];
//            List<Product> products = new ArrayList<>();
//
//            for (int i = 1; i < parts.length; i++) {
//                String [] prices = parts[i].split(":");
//
//                int discountedPrice = Integer.parseInt(prices[0]);
//                int price = Integer.parseInt(prices[1]);
//
//                Product product = new Product(price, discountedPrice);
//                products.add(product);
//
//            }
//            Store store = new Store(name, products);
//            stores.add(store);
//            counter++;
//        }
//        return counter;
//    }
//
//    public List<Store> byAverageDiscount() {
//
//        return stores
//                .stream()
//                .sorted(Comparator
//                        .comparing(Store::getDiscountAverage)
//                        .reversed()
//                        .thenComparing(Store::getName))
//                .limit(3)
//                .collect(Collectors.toList());
//    }
//
//    public List<Store> byTotalDiscount() {
//
//        return stores
//                .stream()
//                .sorted(Comparator
//                        .comparing(Store::getDiscountTotal)
//                        .reversed()
//                        .thenComparing(Store::getName))
//                .limit(3)
//                .collect(Collectors.toList());
//    }
//}
//
//public class Test {
//    public static void main(String[] args) {
//        Discounts discounts = new Discounts();
//        int stores = discounts.readStores(System.in);
//        System.out.println("Stores read: " + stores);
//        System.out.println("=== By average discount ===");
//        discounts.byAverageDiscount().forEach(System.out::println);
//        System.out.println("=== By total discount ===");
//        discounts.byTotalDiscount().forEach(System.out::println);
//    }
//}