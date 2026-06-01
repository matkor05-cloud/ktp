public class Refrigerator extends Appliance {
    private double temperature; // Доп. поле

    public Refrigerator(String brand, String model, int powerWatts, double temperature) {
        super(brand, model, powerWatts); // Вызов конструктора родителя
        this.temperature = temperature;
    }

    public Refrigerator() {
        super();
        this.temperature = 4.0;
    }

    // ПОЛИМОРФИЗМ: Переопределение (Override) метода родителя
    @Override
    public void turnOn() {
        System.out.println("Холодильник включен в розетку и начал охлаждение.");
    }

    @Override
    public void performWork() {
        System.out.println("Холодильник поддерживает температуру " + temperature + " градусов.");
    }

    // ПЕРЕГРУЗКА метода (Overload) - один и тот же метод с разными параметрами
    public void setTemperature(double temp) {
        this.temperature = temp;
    }

    public void setTemperature(double temp, String scale) {
        if (scale.equals("C")) this.temperature = temp;
        System.out.println("Установлена температура в шкале: " + scale);
    }
}