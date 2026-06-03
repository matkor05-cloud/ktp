import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.Arrays;

public class DataManager {
    private final List<Object> processors = new ArrayList<>();
    private List<Double> data = new ArrayList<>();

    // Регистрация объекта-обработчика
    public void registerDataProcessor(Object processor) {
        processors.add(processor);
    }

    // Загрузка данных (имитируем чтение из файла)
    public void loadData(String source) {
        System.out.println("Загрузка данных из: " + source);
        // Допустим, это суммы в долларах
        data = Arrays.asList(10.0, 50.0, -5.0, 100.0, 25.0); 
    }

    // Многопоточная обработка
    public void processData() {
        // Создаем пул потоков (по числу ядер процессора)
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<CompletableFuture<List<Double>>> futures = new ArrayList<>();

        for (Object processor : processors) {
            // Ищем все методы класса, помеченные нашей аннотацией
            for (Method method : processor.getClass().getDeclaredMethods()) {
                if (method.isAnnotationPresent(DataProcessor.class)) {
                    
                    // Запускаем каждый метод в отдельном потоке (асинхронно)
                    CompletableFuture<List<Double>> future = CompletableFuture.supplyAsync(() -> {
                        try {
                            // Вызываем метод через Reflection
                            return (List<Double>) method.invoke(processor, data);
                        } catch (Exception e) {
                            throw new RuntimeException("Ошибка при вызове метода: " + method.getName(), e);
                        }
                    }, executor);
                    
                    futures.add(future);
                }
            }
        }

        // Собираем результаты всех потоков
        try {
            // Ждем завершения всех задач
            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
            
            // Для примера возьмем результат последнего успешного преобразования
            // (В реальной задаче логика склейки может быть сложнее)
            for (CompletableFuture<List<Double>> f : futures) {
                this.data = f.get(); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown(); // Обязательно закрываем пул потоков
        }
    }

    // Сохранение (вывод результата)
    public void saveData(String destination) {
        System.out.println("Результаты сохранены в: " + destination);
        System.out.println("Итоговые суммы в рублях: " + data);
        
        // Агрегация через Stream: считаем общую сумму
        double total = data.stream().mapToDouble(Double::doubleValue).sum();
        System.out.println("Общая сумма всех транзакций: " + total + " руб.");
    }
}