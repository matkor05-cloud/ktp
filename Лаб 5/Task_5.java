import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Scanner;

public class Task_5 {
    public static void main(String[] args) {
        String text = "Быстрая лиса бежит по лесу. The quick fox.";

        Scanner scanner = new Scanner(System.in);

        try {
            if (text == null) {
                throw new IllegalStateException("Исходный текст для поиска не задан (null).");
            }

            System.out.print("Введите букву для поиска слов: ");
            String input = scanner.nextLine();

            if (input == null || input.trim().isEmpty()) {
                throw new IllegalArgumentException("Вы не ввели букву для поиска.");
            }

            char targetChar = input.trim().charAt(0);

            if (!Character.isLetter(targetChar)) {
                throw new IllegalArgumentException("Введенный символ '" + targetChar + "' не является буквой.");
            }

            String letterRange = Character.UnicodeBlock.of(targetChar) == Character.UnicodeBlock.CYRILLIC
                    ? "а-яА-ЯёЁ"
                    : "a-zA-Z";

            // Собираем регулярное выражение
            String regex = "\\b[" + Character.toLowerCase(targetChar) + Character.toUpperCase(targetChar) + "][" + letterRange + "]*\\b";


            Pattern pattern = Pattern.compile(regex, Pattern.UNICODE_CHARACTER_CLASS);
            Matcher matcher = pattern.matcher(text);

            System.out.println("Слова, начинающиеся на букву '" + targetChar + "':");

            boolean found = false;

            while (matcher.find()) {
                System.out.println(matcher.group());
                found = true;
            }

            if (!found) {
                System.out.println("Слов на эту букву в тексте не обнаружено.");
            }

        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка ввода данных: " + e.getMessage());
        } catch (IllegalStateException e) {
            System.err.println("Ошибка состояния программы: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Произошла непредвиденная ошибка: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}