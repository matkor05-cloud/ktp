import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Task_1 {
    public static void main(String[] args) {
        String text = "Цена товара 19.99, скидка 5 долларов, итого 14.99.";

        try {
            if (text == null) {
                throw new IllegalArgumentException("Исходный текст не может быть null");
            }

            Pattern pattern = Pattern.compile("\\d+(?:\\.\\d+)?");
            Matcher matcher = pattern.matcher(text);

            System.out.println("Найденные числа в тексте:");
            while (matcher.find()) {
                System.out.println(matcher.group());
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка обработки данных: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Произошла непредвиденная ошибка: " + e.getMessage());
        }
    }
}