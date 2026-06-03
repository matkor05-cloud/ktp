import java.util.List;
import java.util.stream.Collectors;

public class CurrencyHandlers {

    // Метод для фильтрации: убираем суммы меньше 0 (ошибки)
    @DataProcessor
    public List<Double> filterNegativeValues(List<Double> data) {
        System.out.println("Фильтрация запущена в потоке: " + Thread.currentThread().getName());
        return data.stream()
                   .filter(n -> n > 0)
                   .collect(Collectors.toList());
    }

    // Метод для трансформации: конвертируем доллары в рубли (курс 90)
    @DataProcessor
    public List<Double> convertToRubles(List<Double> data) {
        System.out.println("Конвертация запущена в потоке: " + Thread.currentThread().getName());
        double exchangeRate = 90.0;
        return data.stream()
                   .map(n -> n * exchangeRate)
                   .collect(Collectors.toList());
    }
}