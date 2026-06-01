public class VacuumCleaner extends Appliance {
    private int bagCapacity; // Емкость мешка

    public VacuumCleaner(String brand, String model, int powerWatts, int bagCapacity) {
        super(brand, model, powerWatts);
        this.bagCapacity = bagCapacity;
    }

    @Override
    public void turnOn() {
        System.out.println("Пылесос загудел.");
    }

    @Override
    public void performWork() {
        System.out.println("Пылесос собирает пыль в мешок объемом " + bagCapacity + "л.");
    }
}