import java.util.Scanner;

// АБСТРАКЦИЯ: Класс помечен как abstract, его нельзя создать через new
public abstract class Appliance {
    // ИНКАПСУЛЯЦИЯ: Поля приватные
    private String brand;
    private String model;
    private int powerWatts;

    // СТАТИЧЕСКАЯ ПЕРЕМЕННАЯ (Счетчик созданных объектов)
    private static int applianceCount = 0;

    // Конструктор со всеми параметрами (Инициализация)
    public Appliance(String brand, String model, int powerWatts) {
        this.brand = brand;
        this.model = model;
        this.powerWatts = powerWatts;
        applianceCount++; // Увеличиваем счетчик при создании каждого объекта
    }

    // Конструктор по умолчанию (требование задания)
    public Appliance() {
        this("Unknown", "Generic", 0);
    }

    // ГЕТТЕРЫ И СЕТТЕРЫ
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public int getPowerWatts() { return powerWatts; }
    public void setPowerWatts(int powerWatts) { 
        if(powerWatts >= 0) this.powerWatts = powerWatts; 
    }

    // Статический метод для получения общего количества
    public static int getApplianceCount() {
        return applianceCount;
    }

    // Абстрактные методы (поведение объектов)
    public abstract void turnOn();
    public abstract void performWork();

    // Обычный метод для вывода информации
    public void displayInfo() {
        System.out.println("--- Устройство ---");
        System.out.println("Бренд: " + brand + ", Модель: " + model + ", Мощность: " + powerWatts + "Вт");
    }
}