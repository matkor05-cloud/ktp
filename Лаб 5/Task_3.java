import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Task_3 {
    public static void main(String[] args) {
        String text = "Этот ТеКст сОшибками finDIT.";
        try {
            if (text == null) {
                throw new IllegalArgumentException("Текст не может быть null");
            }

            Pattern pattern = Pattern.compile("([a-zа-яё])([A-ZА-ЯЁ])");
            Matcher matcher = pattern.matcher(text);

            String highlightedText = matcher.replaceAll("$1!$2!");

            System.out.println("Было:  " + text);
            System.out.println("Стало: " + highlightedText);

        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка валидации: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Произошла непредвиденная ошибка: " + e.getMessage());
        }
    }
}