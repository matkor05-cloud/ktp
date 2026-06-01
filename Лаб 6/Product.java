import java.util.*;

class Product implements Comparable<Product> {
    String name;
    double price;
    int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public double getTotal() {
        return price * quantity;
    }

    @Override
    public int compareTo(Product o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return name + " - " + quantity + " шт. по " + price + " руб., итого: " + getTotal();
    }
}

public class SalesAccounting {
    private TreeSet<Product> products;
    private Map<String, Integer> salesCount;

    public SalesAccounting() {
        products = new TreeSet<>();
        salesCount = new HashMap<>();
    }

    public void addProduct(Product p) {
        products.add(p);
        salesCount.put(p.name, salesCount.getOrDefault(p.name, 0) + p.quantity);
    }

    public void printAllProducts() {
        System.out.println("Список проданных товаров:");
        for (Product p : products) {
            System.out.println(p);
        }
    }

    public double getTotalSum() {
        double sum = 0;
        for (Product p : products) {
            sum += p.getTotal();
        }
        return sum;
    }

    public String getMostPopular() {
        String mostPopular = null;
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : salesCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostPopular = entry.getKey();
            }
        }
        return mostPopular;
    }

    public static void main(String[] args) {
        SalesAccounting accounting = new SalesAccounting();
        accounting.addProduct(new Product("Хлеб", 50, 3));
        accounting.addProduct(new Product("Молоко", 80, 2));
        accounting.addProduct(new Product("Масло", 120, 1));
        accounting.addProduct(new Product("Хлеб", 50, 2));

        accounting.printAllProducts();
        System.out.println("Общая сумма продаж: " + accounting.getTotalSum() + " руб.");
        System.out.println("Наиболее популярный товар: " + accounting.getMostPopular());
    }
}