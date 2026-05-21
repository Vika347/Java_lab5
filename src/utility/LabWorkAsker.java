package utility;

import models.*;
import managers.IdGenerator;

import java.util.Date;

public class LabWorkAsker {

    private final ReaderManager readerManager;
    private final Console console;
    private boolean interactiveMode = true;

    //Конструктор
    public LabWorkAsker(ReaderManager readerManager, Console console) {
        this.readerManager = readerManager;
        this.console = console;
    }

    /**
     * Включает/выключает интерактивный режим.
     * Если interactiveMode = false, данные читаются из скрипта построчно.
     */
    //Переключение режима
    public void setInteractiveMode(boolean interactive) {
        this.interactiveMode = interactive;
    }

    public LabWork askLabWork() {
        try {
            LabWork lab = new LabWork();
            lab.setId(IdGenerator.nextId());//Генерирует уникальный ID через IdGenerator
            lab.setCreationDate(new Date());//Устанавливает текущую дату создания

            lab.setName(askName());//Запрашивает имя
            lab.setCoordinates(askCoordinates());
            lab.setMinimalPoint(askMinimalPoint());
            lab.setPersonalQualitiesMinimum(askPersonalQualities());
            lab.setDifficulty(askDifficulty());
            lab.setAuthor(askPerson());

            return lab;
        } catch (RuntimeException e) {
            console.printError("Операция отменена: " + e.getMessage());
            return null;
        }
    }

    private String askName() {
        if (interactiveMode) {
            while (true) {
                console.print("Введите имя LabWork: ");
                String value = readerManager.readLine();
                if (value == null) throw new RuntimeException("Ввод прерван");
                if (Validator.validateName(value)) return value;
                console.printError("Имя не может быть пустым.");
            }
        } else {
            String value = readerManager.readLine();
            if (value == null) throw new RuntimeException("Ввод прерван");
            if (!Validator.validateName(value)) throw new RuntimeException("Имя не может быть пустым");
            return value;
        }
    }

    private Coordinates askCoordinates() {
        if (interactiveMode) {
            while (true) {
                try {
                    console.print("Введите X: ");
                    String xStr = readerManager.readLine();
                    if (xStr == null) throw new RuntimeException("Ввод прерван");
                    long x = Long.parseLong(xStr);

                    console.print("Введите Y: ");
                    String yStr = readerManager.readLine();
                    if (yStr == null) throw new RuntimeException("Ввод прерван");
                    long y = Long.parseLong(yStr);

                    Coordinates c = new Coordinates();
                    c.setX(x);
                    c.setY(y);
                    return c;
                } catch (NumberFormatException e) {
                    console.printError("Координаты должны быть числами.");
                }
            }
        } else {
            // Неинтерактивный режим
            try {
                String xStr = readerManager.readLine();
                if (xStr == null) throw new RuntimeException("Ввод прерван");
                long x = Long.parseLong(xStr);

                String yStr = readerManager.readLine();
                if (yStr == null) throw new RuntimeException("Ввод прерван");
                long y = Long.parseLong(yStr);

                Coordinates c = new Coordinates();
                c.setX(x);
                c.setY(y);
                return c;
            } catch (NumberFormatException e) {
                throw new RuntimeException("Ошибка парсинга координат");
            }
        }
    }

    private Integer askMinimalPoint() {
        if (interactiveMode) {
            while (true) {
                console.print("Введите minimalPoint (или пусто): ");
                String value = readerManager.readLine();
                if (value == null) throw new RuntimeException("Ввод прерван");
                if (value.isBlank()) return null;
                try {
                    int result = Integer.parseInt(value);
                    if (Validator.validateMinimalPoint(result)) return result;
                    console.printError("Значение должно быть > 0");
                } catch (NumberFormatException e) {
                    console.printError("Введите корректное число.");
                }
            }
        } else {
            String value = readerManager.readLine();
            if (value == null) throw new RuntimeException("Ввод прерван");
            if (value.isBlank()) return null;
            try {
                int result = Integer.parseInt(value);
                if (!Validator.validateMinimalPoint(result)) throw new RuntimeException("Значение должно быть > 0");
                return result;
            } catch (NumberFormatException e) {
                throw new RuntimeException("Введите корректное число");
            }
        }
    }

    private float askPersonalQualities() {
        if (interactiveMode) {
            while (true) {
                try {
                    console.print("Введите personalQualitiesMinimum: ");
                    String value = readerManager.readLine();
                    if (value == null) throw new RuntimeException("Ввод прерван");
                    float val = Float.parseFloat(value);
                    if (Validator.validatePersonalQualities(val)) return val;
                    console.printError("Значение должно быть > 0");
                } catch (NumberFormatException e) {
                    console.printError("Введите число.");
                }
            }
        } else {
            String value = readerManager.readLine();
            if (value == null) throw new RuntimeException("Ввод прерван");
            float val = Float.parseFloat(value);
            if (!Validator.validatePersonalQualities(val)) throw new RuntimeException("Значение должно быть > 0");
            return val;
        }
    }

    private Difficulty askDifficulty() {
        if (interactiveMode) {
            while (true) {
                console.println("Доступные значения Difficulty: VERY_EASY, EASY, HARD, VERY_HARD, IMPOSSIBLE");
                console.print("Введите difficulty: ");
                String value = readerManager.readLine();
                if (value == null) throw new RuntimeException("Ввод прерван");
                try {
                    return Difficulty.valueOf(value.toUpperCase());
                } catch (IllegalArgumentException e) {
                    console.printError("Неверное значение difficulty.");
                }
            }
        } else {
            String value = readerManager.readLine();
            if (value == null) throw new RuntimeException("Ввод прерван");
            return Difficulty.valueOf(value.toUpperCase());
        }
    }

    private Person askPerson() {
        Person p = new Person();

        // Имя автора
        if (interactiveMode) {
            while (true) {
                console.print("Введите имя автора: ");
                String name = readerManager.readLine();
                if (name == null) throw new RuntimeException("Ввод прерван");
                if (Validator.validatePersonName(name)) {
                    p.setName(name);
                    break;
                }
                console.printError("Имя не может быть пустым.");
            }
        } else {
            String name = readerManager.readLine();
            if (name == null) throw new RuntimeException("Ввод прерван");
            if (!Validator.validatePersonName(name)) throw new RuntimeException("Имя не может быть пустым");
            p.setName(name);
        }

        // PassportID
        console.print("Введите passportID (можно пусто): ");
        String passport = readerManager.readLine();
        if (passport == null) throw new RuntimeException("Ввод прерван");
        p.setPassportID(Validator.validatePassportId(passport) && !passport.isBlank() ? passport : null);

        // EyeColor
        p.setEyeColor(askColor("eyeColor"));

        // HairColor
        p.setHairColor(askColor("hairColor"));

        // Location
        p.setLocation(askLocation());

        return p;
    }

    private Color askColor(String fieldName) {
        if (interactiveMode) {
            while (true) {
                console.println("Доступные значения " + fieldName + ": GREEN, RED, ORANGE, WHITE, BLACK, YELLOW");
                console.print("Введите значение (или пусто): ");
                String value = readerManager.readLine();
                if (value == null) throw new RuntimeException("Ввод прерван");
                if (value.isBlank()) return null;
                try {
                    return Color.valueOf(value.toUpperCase());
                } catch (IllegalArgumentException e) {
                    console.printError("Неверное значение Color.");
                }
            }
        } else {
            String value = readerManager.readLine();
            if (value == null) throw new RuntimeException("Ввод прерван");
            if (value.isBlank()) return null;
            return Color.valueOf(value.toUpperCase());
        }
    }

    private Location askLocation() {
        if (interactiveMode) {
            while (true) {
                try {
                    console.print("Введите location.x: ");
                    String xStr = readerManager.readLine();
                    if (xStr == null) throw new RuntimeException("Ввод прерван");
                    int x = Integer.parseInt(xStr);

                    console.print("Введите location.y: ");
                    String yStr = readerManager.readLine();
                    if (yStr == null) throw new RuntimeException("Ввод прерван");
                    float y = Float.parseFloat(yStr);

                    console.print("Введите location.z: ");
                    String zStr = readerManager.readLine();
                    if (zStr == null) throw new RuntimeException("Ввод прерван");
                    float z = Float.parseFloat(zStr);

                    Location l = new Location();
                    l.setX(x);
                    l.setY(y);
                    l.setZ(z);
                    return l;
                } catch (NumberFormatException e) {
                    console.printError("Ошибка ввода location.");
                }
            }
        } else {
            try {
                String xStr = readerManager.readLine();
                if (xStr == null) throw new RuntimeException("Ввод прерван");
                int x = Integer.parseInt(xStr);

                String yStr = readerManager.readLine();
                if (yStr == null) throw new RuntimeException("Ввод прерван");
                float y = Float.parseFloat(yStr);

                String zStr = readerManager.readLine();
                if (zStr == null) throw new RuntimeException("Ввод прерван");
                float z = Float.parseFloat(zStr);

                Location l = new Location();
                l.setX(x);
                l.setY(y);
                l.setZ(z);
                return l;
            } catch (NumberFormatException e) {
                throw new RuntimeException("Ошибка парсинга location");
            }
        }
    }
}