public class ArrayAverage {
    public static void main(String[] args) {
        // Специально используем массив строк, чтобы проверить ошибку преобразования в число
        String[] arr = {"10", "20", "abc", "30"}; 
        int sum = 0;
        int count = 0;

        try {
            // Специально идем до arr.length + 1, чтобы спровоцировать выход за границы
            for (int i = 0; i < arr.length + 1; i++) {
                sum += Integer.parseInt(arr[i]);
                count++;
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: В массиве найдено не число!");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ошибка: Выход за границы массива!");
        } finally {
            if (count > 0) {
                System.out.println("Среднее арифметическое: " + (double) sum / count);
            } else {
                System.out.println("Не удалось вычислить среднее.");
            }
        }
    }
}