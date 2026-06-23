package models;

/**
 * Класс, описывающий автора лабораторной работы.
 * <p>
 * Содержит информацию о человеке, являющемся автором объекта
 * {@link LabWork}. Включает имя, идентификатор паспорта,
 * цвет глаз, цвет волос и местоположение.
 * </p>
 *
 * @author Виктория Родина
 */
public class Person {

    /**
     * Имя автора.
     * Поле не может быть {@code null} или пустой строкой.
     */
    private String name;

    /**
     * Идентификатор паспорта.
     * Может быть {@code null}. Уникальность значения
     * обеспечивается вне данного класса.
     */
    private String passportID;

    /**
     * Цвет глаз.
     * Может принимать значение {@code null}.
     */
    private Color eyeColor;

    /**
     * Цвет волос.
     * Может принимать значение {@code null}.
     */
    private Color hairColor;

    /**
     * Местоположение автора.
     * Может принимать значение {@code null}.
     */
    private Location location;

    /**
     * Создаёт пустой объект автора.
     */
    public Person() {}

    /**
     * Создаёт объект автора с указанными параметрами.
     *
     * @param name имя автора
     * @param passportID идентификатор паспорта
     * @param eyeColor цвет глаз
     * @param hairColor цвет волос
     * @param location местоположение
     */
    public Person(String name, String passportID,
                  Color eyeColor, Color hairColor,
                  Location location) {
        this.name = name;
        this.passportID = passportID;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.location = location;
    }

    /**
     * Возвращает имя автора.
     *
     * @return имя автора
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает имя автора.
     *
     * @param name новое имя автора
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Возвращает идентификатор паспорта.
     *
     * @return идентификатор паспорта
     */
    public String getPassportID() {
        return passportID;
    }

    /**
     * Устанавливает идентификатор паспорта.
     *
     * @param passportID новый идентификатор паспорта
     */
    public void setPassportID(String passportID) {
        this.passportID = passportID;
    }

    /**
     * Возвращает цвет глаз.
     *
     * @return цвет глаз
     */
    public Color getEyeColor() {
        return eyeColor;
    }

    /**
     * Устанавливает цвет глаз.
     *
     * @param eyeColor новый цвет глаз
     */
    public void setEyeColor(Color eyeColor) {
        this.eyeColor = eyeColor;
    }

    /**
     * Возвращает цвет волос.
     *
     * @return цвет волос
     */
    public Color getHairColor() {
        return hairColor;
    }

    /**
     * Устанавливает цвет волос.
     *
     * @param hairColor новый цвет волос
     */
    public void setHairColor(Color hairColor) {
        this.hairColor = hairColor;
    }

    /**
     * Возвращает местоположение автора.
     *
     * @return объект местоположения
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Устанавливает местоположение автора.
     *
     * @param location новое местоположение
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Возвращает строковое представление объекта автора.
     *
     * @return строка, содержащая значения всех полей объекта
     */
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", passportID='" + passportID + '\'' +
                ", eyeColor=" + eyeColor +
                ", hairColor=" + hairColor +
                ", location=" + location +
                '}';
    }
}