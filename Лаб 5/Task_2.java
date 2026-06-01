import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Task_2 {
    public static void main(String[] args) {
        String[] passwords = {"123", "Пароль", "Qwerty123", "Админ123", "абвгдеёжзклм", "Инна765123"};

        for (String pwd : passwords) {
            try {
                boolean isValid = validatePassword(pwd);
                System.out.println("Пароль [" + pwd + "] -> " + (isValid ? "КОРРЕКТЕН" : "НЕКОРРЕКТЕН"));
            } catch (Exception e) {
                System.err.println("Ошибка при проверке: " + e.getMessage());
            }
        }
    }

    //Проверяет пароль по правилам регулярного выражения
    public static boolean validatePassword(String password) {
        if (password == null) {
            return false;
        }

        String regex = "^(?=.*[A-ZА-ЯЁ])(?=.*\\d)[A-Za-zА-Яа-яЁё\\d]{8,16}$";

        // Компилируем шаблон и сопоставляем его с паролем
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }
}