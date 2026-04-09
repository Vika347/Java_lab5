package utils;

import model.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Класс для чтения пользовательского ввода с консоли.
 * Обеспечивает валидацию и повторный запрос при ошибках.
 * @author Виктория Родина
 */
public class ConsoleReader {
    /** Поле сканер для чтения ввода с консоли */
    private Scanner scanner;

    /** Стек для скриптов */
    private Deque<Scanner> scriptScanners = new ArrayDeque<>();

    /** Флаг выполнения скрипта */
    private boolean inScript = false;

    /**
     * Конструктор - инициализирует Scanner для чтения из System.in.
     */
    public ConsoleReader() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Переключить чтение из файла скрипта
     * @param fileName - имя файла скрипта
     * @throws FileNotFoundException если файл не найден
     */
    public void pushScriptFile(String fileName) throws FileNotFoundException {
        scriptScanners.push(scanner);
        scanner = new Scanner(new File(fileName));
        inScript = true;
        System.out.println("Переключено на чтение из файла: " + fileName);
    }

    /**
     * Вернуться к предыдущему источнику ввода
     */
    public void popScriptFile() {
        if (!scriptScanners.isEmpty()) {
            scanner.close();
            scanner = scriptScanners.pop();
            inScript = !scriptScanners.isEmpty();
            if (!inScript) {
                System.out.println("Возврат к чтению из консоли");
            }
        }
    }

    /**
     * Проверить, есть ли следующая строка
     * @return true если есть следующая строка
     */
    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }

    /**
     * Прочитать строку без приглашения (для скриптов)
     * @return прочитанная строка или null
     */
    public String readLine() {
        return scanner.hasNextLine() ? scanner.nextLine().trim() : null;
    }

    /**
     * Функция чтения строки с приглашением
     * @param prompt - приглашение для вывода
     * @return введенная строка (обрезанная)
     */
    public String readLine(String prompt) {
        if (!inScript) {
            System.out.print(prompt);
        }
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
                if (!inScript) {
                    System.out.println("Ошибка: Введите целое число.");
                } else {
                    System.err.println("Ошибка: ожидалось целое число, получено: " + input);
                    return 0;
                }
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
                if (!inScript) {
                    System.out.println("Ошибка: Введите число.");
                } else {
                    System.err.println("Ошибка: ожидалось число, получено: " + input);
                    return 0;
                }
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
                if (!inScript) {
                    System.out.println("Ошибка: Введите целое число.");
                } else {
                    return null;
                }
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
                if (!inScript) {
                    System.out.println("Ошибка: Введите число или оставьте пустым для null.");
                } else {
                    return null;
                }
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
            if (!inScript) {
                System.out.println("Ошибка: Имя не может быть пустым.");
            } else {
                return "DefaultName";
            }
        }
    }

    /**
     * Функция чтения координат
     * @return объект {@link Coordinates}
     */
    public Coordinates readCoordinates() {
        if (!inScript) {
            System.out.println("Введите координаты:");
        }
        int x = readInt("  x (int): ");
        int y = readInt("  y (int): ");
        return new Coordinates(x, y);
    }

    /**
     * Функция чтения уровня сложности
     * @return объект {@link Difficulty}
     */
    public Difficulty readDifficulty() {
        if (!inScript) {
            System.out.println("Доступные уровни сложности: " + Difficulty.getAvailableValues());
        }
        while (true) {
            String input = readLine("difficulty: ").toUpperCase();
            if (InputValidator.validateDifficulty(input)) {
                return Difficulty.valueOf(input);
            }
            if (!inScript) {
                System.out.println("Ошибка: Неверное значение. Доступны: " + Difficulty.getAvailableValues());
            } else {
                return Difficulty.EASY;
            }
        }
    }

    /**
     * Функция чтения цвета
     * @param fieldName - название поля для вывода
     * @param allowNull - разрешено ли null значение
     * @return объект {@link Color} или null
     */
    public Color readColor(String fieldName, boolean allowNull) {
        if (!inScript) {
            System.out.println("Доступные цвета: " + Color.getAvailableValues());
        }
        while (true) {
            String input = readLine(fieldName + " (Enter для null): ");
            if (allowNull && input.isEmpty()) {
                return null;
            }
            String upperInput = input.toUpperCase();
            if (InputValidator.validateColor(upperInput)) {
                return Color.valueOf(upperInput);
            }
            if (!inScript) {
                System.out.println("Ошибка: Неверное значение. Доступны: " + Color.getAvailableValues());
            } else {
                return null;
            }
        }
    }

    /**
     * Функция чтения местоположения
     * @param allowNull - разрешено ли null значение
     * @return объект {@link Location} или null
     */
    public Location readLocation(boolean allowNull) {
        if (allowNull && !inScript) {
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
        } else if (allowNull && inScript) {
            String choice = readLine("Ввести местоположение? (y/n): ");
            if (choice.equalsIgnoreCase("n")) {
                return null;
            }
        }

        if (!inScript) {
            System.out.println("Введите местоположение:");
        }
        long x = readLong("  x (long): ");
        double y = readDouble("  y (double): ");

        Integer z = null;
        while (true) {
            String input = readLine("  z (Integer, не может быть null): ");
            try {
                z = Integer.parseInt(input);
                break;
            } catch (NumberFormatException e) {
                if (!inScript) {
                    System.out.println("Ошибка: Введите целое число.");
                } else {
                    z = 0;
                    break;
                }
            }
        }

        return new Location(x, y, z);
    }

    /**
     * Функция чтения автора
     * @return объект {@link Person}
     */
    public Person readPerson() {
        if (!inScript) {
            System.out.println("Введите данные автора:");
        }
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
        if (!inScript) {
            System.out.println("Введите данные лабораторной работы:");
        }

        String name = readName("  name: ");
        Coordinates coordinates = readCoordinates();

        Double minimalPoint = readNullableDouble("  minimalPoint: ");

        while (minimalPoint != null && minimalPoint <= 0) {
            if (!inScript) {
                System.out.println("Ошибка: minimalPoint должно быть больше 0");
            }
            minimalPoint = readNullableDouble("  minimalPoint: ");
        }

        double personalQualitiesMinimum = readDouble("  personalQualitiesMinimum (>0): ");
        while (personalQualitiesMinimum <= 0) {
            if (!inScript) {
                System.out.println("Ошибка: personalQualitiesMinimum должно быть больше 0");
            }
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
        if (!inScript) {
            System.out.println("Введите новые данные лабораторной работы:");
        }

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

    /**
     * Проверка, выполняется ли сейчас скрипт
     * @return true если выполняется скрипт
     */
    public boolean isExecutingScript() {
        return inScript;
    }
}