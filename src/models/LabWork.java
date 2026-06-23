package models;

import java.util.Date;

/**
 * Основной класс предметной области.
 * <p>
 * Представляет лабораторную работу, хранящуюся в коллекции.
 * Объекты данного класса являются элементами коллекции
 * {@code Hashtable<Integer, LabWork>}.
 * </p>
 *
 * <p>
 * Класс реализует интерфейс {@link Comparable} и определяет
 * естественный порядок сортировки объектов.
 * Сравнение выполняется по значению поля
 * {@code personalQualitiesMinimum}, а при равенстве — по идентификатору.
 * </p>
 *
 * @author Виктория Родина
 */
public class LabWork implements Comparable<LabWork> {

    /**
     * Уникальный идентификатор лабораторной работы.
     * Значение должно быть больше 0 и генерируется автоматически.
     */
    private int id;

    /**
     * Название лабораторной работы.
     * Поле не может быть {@code null} или пустой строкой.
     */
    private String name;

    /**
     * Координаты лабораторной работы.
     * Поле не может быть {@code null}.
     */
    private Coordinates coordinates;

    /**
     * Дата создания объекта.
     * Генерируется автоматически и не может быть {@code null}.
     */
    private Date creationDate;

    /**
     * Минимальное количество баллов.
     * Может принимать значение {@code null},
     * в противном случае должно быть больше 0.
     */
    private Integer minimalPoint;

    /**
     * Минимальное значение личных качеств.
     * Значение должно быть больше 0.
     */
    private float personalQualitiesMinimum;

    /**
     * Уровень сложности лабораторной работы.
     * Поле не может быть {@code null}.
     */
    private Difficulty difficulty;

    /**
     * Автор лабораторной работы.
     * Поле не может быть {@code null}.
     */
    private Person author;

    /**
     * Создаёт пустой объект лабораторной работы.
     */
    public LabWork() {}

    /**
     * Создаёт объект лабораторной работы с указанными параметрами.
     *
     * @param id идентификатор объекта
     * @param name название лабораторной работы
     * @param coordinates координаты объекта
     * @param creationDate дата создания
     * @param minimalPoint минимальное количество баллов
     * @param personalQualitiesMinimum минимальное значение личных качеств
     * @param difficulty уровень сложности
     * @param author автор лабораторной работы
     */
    public LabWork(int id, String name, Coordinates coordinates, Date creationDate,
                   Integer minimalPoint, float personalQualitiesMinimum,
                   Difficulty difficulty, Person author) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.minimalPoint = minimalPoint;
        this.personalQualitiesMinimum = personalQualitiesMinimum;
        this.difficulty = difficulty;
        this.author = author;
    }

    /**
     * @return идентификатор объекта
     */
    public int getId() {
        return id;
    }

    /**
     * Устанавливает идентификатор объекта.
     *
     * @param id новый идентификатор
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return название лабораторной работы
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает название лабораторной работы.
     *
     * @param name новое название
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return координаты объекта
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Устанавливает координаты объекта.
     *
     * @param coordinates новые координаты
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * @return дата создания объекта
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * Устанавливает дату создания объекта.
     *
     * @param creationDate дата создания
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return минимальное количество баллов
     */
    public Integer getMinimalPoint() {
        return minimalPoint;
    }

    /**
     * Устанавливает минимальное количество баллов.
     *
     * @param minimalPoint новое значение
     */
    public void setMinimalPoint(Integer minimalPoint) {
        this.minimalPoint = minimalPoint;
    }

    /**
     * @return минимальное значение личных качеств
     */
    public float getPersonalQualitiesMinimum() {
        return personalQualitiesMinimum;
    }

    /**
     * Устанавливает минимальное значение личных качеств.
     *
     * @param personalQualitiesMinimum новое значение
     */
    public void setPersonalQualitiesMinimum(float personalQualitiesMinimum) {
        this.personalQualitiesMinimum = personalQualitiesMinimum;
    }

    /**
     * @return уровень сложности
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     * Устанавливает уровень сложности.
     *
     * @param difficulty новый уровень сложности
     */
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * @return автор лабораторной работы
     */
    public Person getAuthor() {
        return author;
    }

    /**
     * Устанавливает автора лабораторной работы.
     *
     * @param author новый автор
     */
    public void setAuthor(Person author) {
        this.author = author;
    }

    /**
     * Сравнивает текущий объект с другим объектом LabWork.
     * <p>
     * Сначала сравниваются значения поля
     * {@code personalQualitiesMinimum}, а при их равенстве —
     * идентификаторы объектов.
     * </p>
     *
     * @param other объект для сравнения
     * @return отрицательное число, если текущий объект меньше;
     *         ноль, если объекты равны;
     *         положительное число, если текущий объект больше
     */
    @Override
    public int compareTo(LabWork other) {
        int cmp = Float.compare(this.personalQualitiesMinimum, other.personalQualitiesMinimum);
        if (cmp == 0) {
            return Integer.compare(this.id, other.id);
        }
        return cmp;
    }

    /**
     * Возвращает строковое представление объекта.
     *
     * @return строка, содержащая значения всех полей объекта
     */
    @Override
    public String toString() {
        return "LabWork{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", minimalPoint=" + minimalPoint +
                ", personalQualitiesMinimum=" + personalQualitiesMinimum +
                ", difficulty=" + difficulty +
                ", author=" + author +
                '}';
    }
}