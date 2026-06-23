package utility;

import models.*;
import managers.IdGenerator;

import java.util.Date;

/**
 * Класс для создания и заполнения объектов {@link LabWork}.
 * Поддерживает интерактивный ввод и чтение данных из скрипта.
 */
public class LabWorkAsker {

    private final ReaderManager readerManager;
    private final Console console;
    private boolean interactiveMode = true;

    /**
     * Создаёт объект для ввода данных LabWork.
     *
     * @param readerManager менеджер чтения данных
     * @param console объект для вывода сообщений
     */
    public LabWorkAsker(ReaderManager readerManager, Console console) {
        this.readerManager = readerManager;
        this.console = console;
    }

    /**
     * Включает или выключает интерактивный режим.
     *
     * @param interactive true, если режим интерактивный; false, если данные читаются из скрипта
     */
    public void setInteractiveMode(boolean interactive) {
        this.interactiveMode = interactive;
    }

    /**
     * Создаёт и заполняет объект {@link LabWork}.
     *
     * @return заполненный объект LabWork или null, если операция отменена
     */
    public LabWork askLabWork() {
        try {
            LabWork lab = new LabWork();
            lab.setId(IdGenerator.nextId());
            lab.setCreationDate(new Date());

            lab.setName(askName());
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

    /**
     * Запрашивает название лабораторной работы.
     *
     * @return название LabWork
     */
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

    /**
     * Запрашивает координаты лабораторной работы.
     *
     * @return объект координат
     */
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

    /**
     * Запрашивает значение минимального балла.
     *
     * @return значение minimalPoint или null
     */
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

    /**
     * Запрашивает значение минимальных личных качеств.
     *
     * @return значение personalQualitiesMinimum
     */
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

    /**
     * Запрашивает сложность лабораторной работы.
     *
     * @return значение перечисления Difficulty
     */
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

    /**
     * Запрашивает данные автора лабораторной работы.
     *
     * @return объект Person
     */
    private Person askPerson() {
        Person p = new Person();

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

        console.print("Введите passportID (можно пусто): ");
        String passport = readerManager.readLine();
        if (passport == null) throw new RuntimeException("Ввод прерван");
        p.setPassportID(Validator.validatePassportId(passport) && !passport.isBlank() ? passport : null);

        p.setEyeColor(askColor("eyeColor"));
        p.setHairColor(askColor("hairColor"));
        p.setLocation(askLocation());

        return p;
    }

    /**
     * Запрашивает цвет для указанного поля.
     *
     * @param fieldName имя поля, для которого запрашивается цвет
     * @return значение Color или null
     */
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

    /**
     * Запрашивает местоположение автора.
     *
     * @return объект Location
     */
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