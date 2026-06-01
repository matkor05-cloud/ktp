import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        // Создаем HashMap: Ключ - номер зачетки (String), Значение - объект Student
        HashMap<String, Student> studentsMap = new HashMap<>();

        // 1. Операция вставки
        studentsMap.put("20-ИВТ-01", new Student("Иван", "Иванов", 20, 4.5));
        studentsMap.put("20-ИВТ-02", new Student("Мария", "Петрова", 19, 4.8));
        studentsMap.put("20-ИВТ-03", new Student("Алексей", "Сидоров", 21, 3.9));

        System.out.println("Всего студентов: " + studentsMap.size());

        // 2. Операция поиска по номеру зачетки
        String searchId = "20-ИВТ-02";
        if (studentsMap.containsKey(searchId)) {
            System.out.println("Найден студент: " + studentsMap.get(searchId));
        } else {
            System.out.println("Студент с такой зачеткой не найден.");
        }

        // 3. Операция удаления
        studentsMap.remove("20-ИВТ-03");
        System.out.println("Студент 20-ИВТ-03 удален. Текущее количество: " + studentsMap.size());
        
        // Вывод всех оставшихся
        for (String id : studentsMap.keySet()) {
            System.out.println("ID: " + id + " | " + studentsMap.get(id));
        }
    }
}