# Лабораторная работа №4

## Описание

**Задание 1.** Программа `ArrayAverage` вычисляет среднее арифметическое элементов массива. Обрабатываются исключения:
- `NumberFormatException` — если элемент не является числом
- `ArrayIndexOutOfBoundsException` — выход за границы массива

**Задание 2.** Программа `FileCopy` копирует содержимое файла `source.txt` в `dest.txt`. Обрабатываются исключения `FileNotFoundException` и `IOException`. Потоки закрываются в блоке `finally`.

**Задание 3.** Создано пользовательское исключение `CustomFileNotFoundException`. В классе `ExceptionLogger` реализована проверка существования файла. Если файл не найден — выбрасывается исключение, которое логируется в файл `log.txt` с указанием даты и времени.

## Файлы
- `ArrayAverage.java`
- `FileCopy.java`
- `CustomFileNotFoundException.java`
- `ExceptionLogger.java`
