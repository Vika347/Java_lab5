package utils;

import model.*;
import java.util.Scanner;

/**
 * Класс для чтения пользовательского ввода с консоли.
 * Обеспечивает валидацию и повторный запрос при ошибках.
 * @author Виктория Родина
 */
public class ConsoleReader {
    /** Поле сканер для чтения ввода с консоли */
    private Scanner scanner;

    /**
     * Конструктор - инициализирует Scanner для чтения из System.in.
     */
    public ConsoleReader() {
        this.scanner = new Scanner(System.in);
    }

    // БАЗОВЫЕ МЕТОДЫ ЧТЕНИЯ

    /**
     * Функция чтения строки с приглашением
     * @param prompt - приглашение для вывода
     * @return введенная строка (обрезанная)
     */
    public String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    /**
     * Функция чтения целого числа (int)
     * @param prompt - приглашение для вывода
     * @return введенное целое число
     */
    public int readInt(String prompt) {
        while (true) {
            try {
                String input = readLine(prompt);
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Введите целое число.");
            }
        }
    }

    /**
     * Функция чтения числа с плавающей точкой (double)
     * @param prompt - приглашение для вывода
     * @return введенное число
     */
    public double readDouble(String prompt) {
        while (true) {
            try {
                String input = readLine(prompt);
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Введите число.");
            }
        }
    }

    /**
     * Функция чтения long числа (может быть null)
     * @param prompt - приглашение для вывода
     * @return введенное число или null
     */
    public Long readLong(String prompt) {
        while (true) {
            try {
                String input = readLine(prompt);
                if (input.isEmpty()) return null;
                return Long.parseLong(input);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Введите целое число.");
            }
        }
    }

    /**
     * Функция чтения Double (может быть null)
     * @param prompt - приглашение для вывода
     * @return введенное число или null
     */
    public Double readNullableDouble(String prompt) {
        while (true) {
            String input = readLine(prompt);
            if (input.isEmpty()) return null;
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Введите число или оставьте пустым для null.");
            }
        }
    }

    // МЕТОДЫ ДЛЯ КОМПЛЕКСНЫХ ТИПОВ

    /**
     * Функция чтения имени (не может быть null или пустым)
     * @param prompt - приглашение для вывода
     * @return валидное имя
     */
    public String readName(String prompt) {
        while (true) {
            String input = readLine(prompt);
            if (InputValidator.validateName(input)) {
                return input;
            }
            System.out.println("Ошибка: Имя не может быть пустым.");
        }
    }

    /**
     * Функция чтения координат
     * @return объект {@link Coordinates}
     */
    public Coordinates readCoordinates() {
        System.out.println("Введите координаты:");
        int x = readInt("  x (int): ");
        int y = readInt("  y (int): ");
        return new Coordinates(x, y);
    }

    /**
     * Функция чтения уровня сложности
     * @return объект {@link Difficulty}
     */
    public Difficulty readDifficulty() {
        System.out.println("Доступные уровни сложности: " + Difficulty.getAvailableValues());
        while (true) {
            String input = readLine("difficulty: ").toUpperCase();
            if (InputValidator.validateDifficulty(input)) {
                return Difficulty.valueOf(input);
            }
            System.out.println("Ошибка: Неверное значение. Доступны: " + Difficulty.getAvailableValues());
        }
    }

    /**
     * Функция чтения цвета
     * @param fieldName - название поля для вывода
     * @param allowNull - разрешено ли null значение
     * @return объект {@link Color} или null
     */
    public Color readColor(String fieldName, boolean allowNull) {
        System.out.println("Доступные цвета: " + Color.getAvailableValues());
        while (true) {
            String input = readLine(fieldName + " (Enter для null): ");
            if (allowNull && input.isEmpty()) {
                return null;
            }
            String upperInput = input.toUpperCase();
            if (InputValidator.validateColor(upperInput)) {
                return Color.valueOf(upperInput);
            }
            System.out.println("Ошибка: Неверное значение. Доступны: " + Color.getAvailableValues());
        }
    }

    /**
     * Функция чтения местоположения
     * @param allowNull - разрешено ли null значение
     * @return объект {@link Location} или null
     */
    public Location readLocation(boolean allowNull) {
        if (allowNull) {
            while (true) {
                String choice = readLine("Ввести местоположение? (y/n): ");
                if (choice.equalsIgnoreCase("y")) {
                    break;
                } else if (choice.equalsIgnoreCase("n")) {
                    return null;
                } else {
                    System.out.println("Ошибка: введите 'y' или 'n'");
                }
            }
        }

        System.out.println("Введите местоположение:");
        long x = readLong("  x (long): ");
        double y = readDouble("  y (double): ");

        Integer z = null;
        while (true) {
            String input = readLine("  z (Integer, не может быть null): ");
            try {
                z = Integer.parseInt(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Введите целое число.");
            }
        }

        return new Location(x, y, z);
    }

    /**
     * Функция чтения автора
     * @return объект {@link Person}
     */
    public Person readPerson() {
        System.out.println("Введите данные автора:");
        String name = readName("  name: ");
        String passportID = readLine("  passportID (Enter для null): ");
        if (passportID.isEmpty()) passportID = null;

        Color eyeColor = readColor("  eyeColor", true);
        Color hairColor = readColor("  hairColor", true);
        Location location = readLocation(true);

        return new Person(name, passportID, eyeColor, hairColor, location);
    }

    /**
     * Функция чтения лабораторной работы для добавления
     * @return объект {@link LabWork}
     */
    public LabWork readLabWork() {
        String name = readName("  name: ");
        Coordinates coordinates = readCoordinates();

        Double minimalPoint = readNullableDouble("  minimalPoint: ");

        while (minimalPoint != null && minimalPoint <= 0) {
            System.out.println("Ошибка: minimalPoint должно быть больше 0");
            minimalPoint = readNullableDouble("  minimalPoint: ");
        }

        double personalQualitiesMinimum = readDouble("  personalQualitiesMinimum (>0): ");
        while (personalQualitiesMinimum <= 0) {
            System.out.println("Ошибка: personalQualitiesMinimum должно быть больше 0");
            personalQualitiesMinimum = readDouble("  personalQualitiesMinimum (>0): ");
        }

        Difficulty difficulty = readDifficulty();
        Person author = readPerson();

        return new LabWork(name, coordinates, minimalPoint, personalQualitiesMinimum, difficulty, author);
    }

    /**
     * Функция чтения лабораторной работы для команды replace_if_lower
     * @return объект {@link LabWork}
     */
    public LabWork readLabWorkForReplace() {
        System.out.println("Введите новые данные лабораторной работы:");

        String name = readName("  name: ");
        Coordinates coordinates = readCoordinates();

        Double minimalPoint = readNullableDouble("  minimalPoint: ");

        double personalQualitiesMinimum = readDouble("  personalQualitiesMinimum: ");

        Difficulty difficulty = readDifficulty();
        Person author = readPerson();

        return new LabWork(name, coordinates, minimalPoint, personalQualitiesMinimum, difficulty, author);
    }

    /**
     * Процедура закрытия Scanner (вызывается при завершении программы)
     */
    public void close() {
        scanner.close();
    }
}