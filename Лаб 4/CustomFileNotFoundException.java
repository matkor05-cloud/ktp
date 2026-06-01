import java.io.*;
import java.util.Date; // Для записи времени ошибки

// 1. Создаем свой класс ошибки. 
// "extends Exception" делает этот класс полноценным исключением.
class CustomFileNotFoundException extends Exception {
    public CustomFileNotFoundException(String message) {
        super(message); // Передаем текст ошибки в главный класс Exception
    }
}

public class ExceptionLogger {
    
    // 2. Метод для записи ошибок в текстовый файл (логер)
    public static void logException(Exception e) {
        // "true" в FileWriter означает "дописывать в конец", а не стирать файл
        try (FileWriter fw = new FileWriter("log.txt", true)) {
            // Записываем дату + текст ошибки
            fw.write(new Date() + " Произошла ошибка: " + e.getMessage() + "\n");
        } catch (IOException ioEx) {
            System.out.println("Не удалось записать лог.");
        }
    }

    public static void main(String[] args) {
        String path = "secret_data.txt"; // Путь к файлу

        try {
            File file = new File(path);
            
            // 3. Сами проверяем условие. Если файла нет — бьем тревогу!
            if (!file.exists()) {
                // Команда throw запускает исключение вручную
                throw new CustomFileNotFoundException("Файл по пути " + path + " потерялся!");
            }
        } catch (CustomFileNotFoundException e) {
            // 4. Ловим именно НАШУ ошибку
            System.out.println("Внимание: " + e.getMessage());
            
            // 5. Вызываем логер, чтобы сохранить инфу об ошибке в файл
            logException(e);
        }
    }
}