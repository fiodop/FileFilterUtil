# FileFilterUtil

FileFilterUtil — это утилита для обработки текстовых файлов, которая извлекает целые числа, числа с плавающей точкой и строки из каждого файла, записывая их в отдельные выходные файлы. Она также выводит статистику по каждому типу данных, включая минимальные и максимальные значения, суммы и средние значения.

## Особенности реализации

- Обработка текстовых файлов для извлечения целых чисел, чисел с плавающей запятой и строк.
- Запись данных в отдельные выходные файлы: integers.txt, doubles.txt, strings.txt.
- Статистика по каждому типу данных (целые числа, числа с плавающей запятой и строки), включая:
    - количество значений
    - минимальные и максимальные значения
    - сумма и среднее значение
    - для строк: минимальная и максимальная длина.

## Требования

- Java: 11 и выше.
- Система сборки: Maven (для управления зависимостями и сборки проекта).
- Зависимости:
    - Пока проект не использует внешние библиотеки, только стандартные библиотеки Java.

## Инструкция по запуску
1. Запуск программы:

   Перейдите в директорию resourses и используйте команду:

   java -jar FileFilterUtil-1.0-SNAPSHOT.jar -a -f input1.txt input2.txt


Убедитесь, что все входные файлы находятся в правильной директории, и укажите их в качестве аргументов при запуске, если необходимо.

2. Пример использования:

   Для использования утилиты с двумя входными файлами, используйте команду:

   java -jar FileFilterUtil-1.0-SNAPSHOT.jar -a -f input1.txt input2.txt


Программа обработает файлы и создаст три выходных файла: integers.txt, doubles.txt, и strings.txt, а также выведет статистику по каждому типу данных в консоль.

## Конфигурация

Вы можете настроить поведение программы через параметры, указанные при запуске:
- outputPath: путь для сохранения выходных файлов.
- prefix: префикс для имени выходных файлов.
- appendMode: флаг для добавления новых данных в существующие файлы или перезаписи.
- shortStats: флаг для вывода краткой статистики.
- fullStats: флаг для вывода полной статистики.

## Структура проекта

FileFilterUtil/
├── .gitignore
├── .idea/
├── src/
│   └── main/
│       └── java/com/example/fileFilterUtil/
│           ├── FileProcessor.java
│           └── Main.java
├── resources/
│   └── input1.txt
│   └── input2.txt
├── pom.xml
└── README.md

- src/main/java/com/example/fileFilterUtil/FileProcessor.java — основной класс для обработки данных.
- src/main/java/com/example/fileFilterUtil/Main.java — класс с точкой входа.
- resources/ — папка с примером входных данных.