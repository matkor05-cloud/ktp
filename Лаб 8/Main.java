public class Main {
    public static void main(String[] args) {
        DataManager manager = new DataManager();
        
        // Регистрируем наш класс с методами обработки
        manager.registerDataProcessor(new CurrencyHandlers());
        
        // 1. Загружаем
        manager.loadData("input_wallet.txt");
        
        // 2. Обрабатываем (тут внутри магия потоков и аннотаций)
        manager.processData();
        
        // 3. Сохраняем и выводим статистику
        manager.saveData("report_rub.txt");
    }
}