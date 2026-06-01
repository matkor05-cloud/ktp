import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Демонстрация конструкторов
        Refrigerator ref = new Refrigerator("Samsung", "RB34", 150, 2.5);
        VacuumCleaner vac = new VacuumCleaner("Dyson", "V15", 600, 1);

        // Ввод данных пользователем
        System.out.println("Введите название модели для новой Посудомоечной машины:");
        String model = scanner.nextLine();
        // Можно создать прямо в коде, Dishwasher пишется по аналогии с Пылесосом

        // Демонстрация Полиморфизма
        Appliance[] kitchen = { ref, vac };

        System.out.println("\n--- Работа устройств ---");
        for (Appliance a : kitchen) {
            a.displayInfo(); // Общий метод
            a.turnOn();      // Переопределенный метод
            a.performWork(); // Переопределенный метод
            System.out.println();
        }

        // Перегрузка
        ref.setTemperature(-18, "C");

        // Счетчик объектов
        System.out.println("Всего создано бытовых приборов: " + Appliance.getApplianceCount());
    }
}