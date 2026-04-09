package model;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Класс лабораторной работы со свойствами
 * @author Виктория Родина
 */
public class LabWork implements Comparable<LabWork> {
    /** Поле счетчик для генерации id */
    private static long nextId = 1;
    /** Поле идентификатор (генерируется автоматически) */
    private long id;
    /** Поле название (не может быть null, не пустое) */
    private String name;
    /** Поле координаты (не может быть null) */
    private Coordinates coordinates;
    /** Поле дата создания (генерируется автоматически, не может быть null) */
    private ZonedDateTime creationDate;
    /** Поле минимальный балл (может быть null, >0) */
    private Double minimalPoint;
    /** Поле минимум личных качеств (>0) */
    private double personalQualitiesMinimum;
    /** Поле сложность (не может быть null) */
    private Difficulty difficulty;
    /** Поле автор (не может быть null) */
    private Person author;

    /**
     * Конструктор - создание нового объекта (id и creationDate генерируются автоматически)
     * @param name - название (не null, не пустое)
     * @param coordinates - координаты (не null)
     * @param minimalPoint - минимальный балл (null или >0)
     * @param personalQualitiesMinimum - минимум личных качеств (>0)
     * @param difficulty - сложность (не null)
     * @param author - автор (не null)
     */
    public LabWork(String name, Coordinates coordinates, Double minimalPoint,
                   double personalQualitiesMinimum, Difficulty difficulty, Person author) {
        this.id = generateId();
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = ZonedDateTime.now();
        this.minimalPoint = minimalPoint;
        this.personalQualitiesMinimum = personalQualitiesMinimum;
        this.difficulty = difficulty;
        this.author = author;
    }

    /**
     * Конструктор - создание нового объекта (для загрузки из файла)
     * @param id - идентификатор
     * @param name - название
     * @param coordinates - координаты
     * @param creationDate - дата создания
     * @param minimalPoint - минимальный балл
     * @param personalQualitiesMinimum - минимум личных качеств
     * @param difficulty - сложность
     * @param author - автор
     */
    public LabWork(long id, String name, Coordinates coordinates, ZonedDateTime creationDate,
                   Double minimalPoint, double personalQualitiesMinimum,
                   Difficulty difficulty, Person author) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.minimalPoint = minimalPoint;
        this.personalQualitiesMinimum = personalQualitiesMinimum;
        this.difficulty = difficulty;
        this.author = author;

        if (id >= nextId) {
            nextId = id + 1;
        }
    }

    /**
     * Функция генерации нового уникального id
     * @return новый id
     */
    private static synchronized long generateId() {
        return nextId++;
    }

    /**
     * Процедура установки следующего id (используется после загрузки из файла)
     * @param id - следующий доступный id
     */
    public static void setNextId(long id) {
        if (id > nextId) {
            nextId = id;
        }
    }

    /**
     * Функция получения значения поля {@link LabWork#id}
     * @return возвращает id лабораторной работы
     */
    public long getId() { return id; }

    /**
     * Функция получения значения поля {@link LabWork#name}
     * @return возвращает название
     */
    public String getName() { return name; }

    /**
     * Функция получения значения поля {@link LabWork#coordinates}
     * @return возвращает координаты
     */
    public Coordinates getCoordinates() { return coordinates; }

    /**
     * Функция получения значения поля {@link LabWork#creationDate}
     * @return возвращает дату создания
     */
    public ZonedDateTime getCreationDate() { return creationDate; }

    /**
     * Функция получения значения поля {@link LabWork#minimalPoint}
     * @return возвращает минимальный балл
     */
    public Double getMinimalPoint() { return minimalPoint; }

    /**
     * Функция получения значения поля {@link LabWork#personalQualitiesMinimum}
     * @return возвращает минимум личных качеств
     */
    public double getPersonalQualitiesMinimum() { return personalQualitiesMinimum; }

    /**
     * Функция получения значения поля {@link LabWork#difficulty}
     * @return возвращает сложность
     */
    public Difficulty getDifficulty() { return difficulty; }

    /**
     * Функция получения значения поля {@link LabWork#author}
     * @return возвращает автора
     */
    public Person getAuthor() { return author; }

    /**
     * Процедура определения названия {@link LabWork#name}
     * @param name - новое название
     */
    public void setName(String name) { this.name = name; }

    /**
     * Процедура определения координат {@link LabWork#coordinates}
     * @param coordinates - новые координаты
     */
    public void setCoordinates(Coordinates coordinates) { this.coordinates = coordinates; }

    /**
     * Процедура определения минимального балла {@link LabWork#minimalPoint}
     * @param minimalPoint - новый минимальный балл
     */
    public void setMinimalPoint(Double minimalPoint) { this.minimalPoint = minimalPoint; }

    /**
     * Процедура определения минимума личных качеств {@link LabWork#personalQualitiesMinimum}
     * @param personalQualitiesMinimum - новое значение
     */
    public void setPersonalQualitiesMinimum(double personalQualitiesMinimum) {
        this.personalQualitiesMinimum = personalQualitiesMinimum;
    }

    /**
     * Процедура определения сложности {@link LabWork#difficulty}
     * @param difficulty - новая сложность
     */
    public void setDifficulty(Difficulty difficulty) { this.difficulty = difficulty; }

    /**
     * Процедура определения автора {@link LabWork#author}
     * @param author - новый автор
     */
    public void setAuthor(Person author) { this.author = author; }

    /**
     * Функция сравнения двух лабораторных работ по id (сортировка по умолчанию)
     * @param other - другая лабораторная работа
     * @return результат сравнения
     */
    @Override
    public int compareTo(LabWork other) {
        return Long.compare(this.id, other.id);
    }

    /**
     * Функция преобразования объекта в строку для CSV формата
     * @return строка вида "id;name;coordinates;creationDate;minimalPoint;personalQualitiesMinimum;difficulty;author"
     */
    public String toCsv() {
        return id + ";" +
                name + ";" +
                coordinates.toString() + ";" +
                creationDate.format(DateTimeFormatter.ISO_ZONED_DATE_TIME) + ";" +
                (minimalPoint == null ? "null" : minimalPoint) + ";" +
                personalQualitiesMinimum + ";" +
                difficulty.name() + ";" +
                author.toString();
    }

    /**
     * Функция создания объекта LabWork из строки CSV
     * @param csv - строка CSV
     * @return объект LabWork или null при ошибке
     */
    public static LabWork fromCsv(String csv) {
        if (csv == null || csv.isEmpty()) {
            return null;
        }

        try {
            String[] parts = csv.split(";", 8);
            if (parts.length != 8) {
                return null;
            }

            long id = Long.parseLong(parts[0]);
            String name = parts[1];
            if (name == null || name.isEmpty()) return null;

            Coordinates coordinates = Coordinates.parse(parts[2]);
            if (coordinates == null) return null;

            ZonedDateTime creationDate;
            try {
                creationDate = ZonedDateTime.parse(parts[3]);
            } catch (DateTimeParseException e) {
                return null;
            }

            Double minimalPoint = parts[4].equals("null") ? null : Double.parseDouble(parts[4]);
            if (minimalPoint != null && minimalPoint <= 0) return null;

            double personalQualitiesMinimum = Double.parseDouble(parts[5]);
            if (personalQualitiesMinimum <= 0) return null;

            Difficulty difficulty;
            try {
                difficulty = Difficulty.valueOf(parts[6]);
            } catch (IllegalArgumentException e) {
                return null;
            }

            Person author = Person.parse(parts[7]);
            if (author == null) return null;

            return new LabWork(id, name, coordinates, creationDate, minimalPoint,
                    personalQualitiesMinimum, difficulty, author);

        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Функция строкового представления объекта для вывода пользователю
     * @return строка с информацией о лабораторной работе
     */
    @Override
    public String toString() {
        return String.format(
                "LabWork{id=%d, name='%s', difficulty=%s, author='%s'}",
                id, name, difficulty.name(), author.getName()
        );
    }

    /**
     * Функция сравнения объектов
     * @param obj - объект для сравнения
     * @return true если id равны, иначе false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        LabWork that = (LabWork) obj;
        return id == that.id;
    }

    /**
     * Функция получения хеш-кода объекта
     * @return хеш-код
     */
    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }
}