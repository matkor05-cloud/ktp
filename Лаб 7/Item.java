import java.util.ArrayList;
import java.util.List;

class Item {
    private String name;
    private int weight;
    
    public Item(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }
    
    public int getWeight() {
        return weight;
    }
    
    public String getName() {
        return name;
    }
}

class Warehouse {
    private List<Item> items;
    private List<Item> transferredItems;
    private static final int MAX_WEIGHT = 150;
    
    public Warehouse(List<Item> items) {
        this.items = new ArrayList<>(items);
        this.transferredItems = new ArrayList<>();
    }
    
    public synchronized Item getItem() {
        if (items.isEmpty()) {
            return null;
        }
        return items.remove(0);
    }
    
    public synchronized void addTransferredItem(Item item) {
        transferredItems.add(item);
    }
    
    public synchronized int getCurrentLoadWeight() {
        int total = 0;
        for (Item item : transferredItems) {
            total += item.getWeight();
        }
        return total;
    }
    
    public synchronized boolean hasItems() {
        return !items.isEmpty();
    }
}

class Loader extends Thread {
    private Warehouse sourceWarehouse;
    private Warehouse destinationWarehouse;
    private List<Item> currentLoad;
    private int currentWeight;
    
    public Loader(String name, Warehouse source, Warehouse destination) {
        super(name);
        this.sourceWarehouse = source;
        this.destinationWarehouse = destination;
        this.currentLoad = new ArrayList<>();
        this.currentWeight = 0;
    }
    
    @Override
    public void run() {
        while (sourceWarehouse.hasItems()) {
            Item item = sourceWarehouse.getItem();
            if (item != null) {
                if (currentWeight + item.getWeight() <= 150) {
                    currentLoad.add(item);
                    currentWeight += item.getWeight();
                    System.out.println(getName() + " взял " + item.getName() + 
                                       " (вес: " + item.getWeight() + " кг)");
                } else {
                    sourceWarehouse.addTransferredItem(item);
                }
            }
            
            if (currentWeight == 150 || !sourceWarehouse.hasItems()) {
                System.out.println(getName() + " переносит груз весом " + 
                                   currentWeight + " кг на другой склад");
                for (Item i : currentLoad) {
                    destinationWarehouse.addTransferredItem(i);
                }
                currentLoad.clear();
                currentWeight = 0;
            }
            
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class WarehouseTransfer {
    public static void main(String[] args) {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Коробка1", 30));
        items.add(new Item("Коробка2", 45));
        items.add(new Item("Коробка3", 25));
        items.add(new Item("Коробка4", 50));
        items.add(new Item("Коробка5", 20));
        items.add(new Item("Коробка6", 35));
        items.add(new Item("Коробка7", 40));
        items.add(new Item("Коробка8", 15));
        items.add(new Item("Коробка9", 55));
        
        Warehouse source = new Warehouse(items);
        Warehouse destination = new Warehouse(new ArrayList<>());
        
        Loader loader1 = new Loader("Грузчик-1", source, destination);
        Loader loader2 = new Loader("Грузчик-2", source, destination);
        Loader loader3 = new Loader("Грузчик-3", source, destination);
        
        loader1.start();
        loader2.start();
        loader3.start();
        
        try {
            loader1.join();
            loader2.join();
            loader3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Все товары перенесены!");
    }
}