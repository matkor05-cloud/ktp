import java.io.*; // 1. Импортируем все инструменты для работы с файлами

public class FileCopy {
    public static void main(String[] args) {
        // 2. Объявляем переменные для файлов заранее вне блока try.
        // Это нужно, чтобы блок finally их "видел".
        FileInputStream in = null; 
        FileOutputStream out = null;

        try {
            // 3. Пытаемся "открыть" файлы. 
            // FileInputStream — читает байты из файла source.txt
            in = new FileInputStream("source.txt"); 
            // FileOutputStream — записывает байты в файл dest.txt
            out = new FileOutputStream("dest.txt");

            int c; // Переменная для хранения одного байта данных
            
            // 4. Цикл чтения: читаем по одному байту, пока не дойдем до конца файла (-1)
            while ((c = in.read()) != -1) {
                out.write(c); // Записываем этот байт в файл-приемник
            }
            System.out.println("Файл успешно скопирован.");

        } catch (FileNotFoundException e) {
            // 5. Сработает, если source.txt не существует
            System.out.println("Ошибка: Исходный файл не найден!");
        } catch (IOException e) {
            // 6. Сработает при любой другой ошибке (например, диск переполнен)
            System.out.println("Ошибка при чтении или записи!");
        } finally {
            // 7. Блок finally выполняется ВСЕГДА. 
            // В Варианте 1 нам нужно обработать ошибки именно при ЗАКРЫТИИ.
            try {
                // Если файл был открыт (не null), закрываем его
                if (in != null) in.close(); 
                if (out != null) out.close();
                System.out.println("Потоки закрыты.");
            } catch (IOException e) {
                // 8. Редкая ситуация: если файл не удалось закрыть, 
                // Java снова выкинет IOException, и мы его тут ловим.
                System.out.println("Ошибка при закрытии файлов!");
            }
        }
    }
}