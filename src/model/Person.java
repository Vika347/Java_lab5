package model;

/**
 * Класс автора со свойствами.
 * @author Виктория Родина
 */
public class Person {
    /** Поле имя (не может быть null, не пустое) */
    private String name;
    /** Поле ID паспорта (может быть null, уникальный) */
    private String passportID;
    /** Поле цвет глаз (может быть null) */
    private Color eyeColor;
    /** Поле цвет волос (может быть null) */
    private Color hairColor;
    /** Поле местоположение (может быть null) */
    private Location location;

    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param name - имя (не может быть null или пустым)
     * @param passportID - ID паспорта (может быть null)
     * @param eyeColor - цвет глаз (может быть null)
     * @param hairColor - цвет волос (может быть null)
     * @param location - местоположение (может быть null)
     */
    public Person(String name, String passportID, Color eyeColor, Color hairColor, Location location) {
        this.name = name;
        this.passportID = passportID;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.location = location;
    }

    /**
     * Функция получения значения поля {@link Person#name}
     * @return возвращает имя автора
     */
    public String getName() { return name; }

    /**
     * Функция получения значения поля {@link Person#passportID}
     * @return возвращает ID паспорта
     */
    public String getPassportID() { return passportID; }

    /**
     * Функция получения значения поля {@link Person#eyeColor}
     * @return возвращает цвет глаз
     */
    public Color getEyeColor() { return eyeColor; }

    /**
     * Функция получения значения поля {@link Person#hairColor}
     * @return возвращает цвет волос
     */
    public Color getHairColor() { return hairColor; }

    /**
     * Функция получения значения поля {@link Person#location}
     * @return возвращает местоположение
     */
    public Location getLocation() { return location; }

    /**
     * Процедура определения имени {@link Person#name}
     * @param name - новое имя
     */
    public void setName(String name) { this.name = name; }

    /**
     * Процедура определения ID паспорта {@link Person#passportID}
     * @param passportID - новый ID паспорта
     */
    public void setPassportID(String passportID) { this.passportID = passportID; }

    /**
     * Процедура определения цвета глаз {@link Person#eyeColor}
     * @param eyeColor - новый цвет глаз
     */
    public void setEyeColor(Color eyeColor) { this.eyeColor = eyeColor; }

    /**
     * Процедура определения цвета волос {@link Person#hairColor}
     * @param hairColor - новый цвет волос
     */
    public void setHairColor(Color hairColor) { this.hairColor = hairColor; }

    /**
     * Процедура определения местоположения {@link Person#location}
     * @param location - новое местоположение
     */
    public void setLocation(Location location) { this.location = location; }

    /**
     * Функция преобразования объекта в строку для CSV формата
     * @return строка вида "name;passportID;eyeColor;hairColor;location"
     */
    @Override
    public String toString() {
        return name + ";" +
                (passportID == null ? "null" : passportID) + ";" +
                (eyeColor == null ? "null" : eyeColor.name()) + ";" +
                (hairColor == null ? "null" : hairColor.name()) + ";" +
                (location == null ? "null" : location.toString());
    }

    /**
     * Функция создания объекта Person из строки CSV
     * @param csv - строка формата "name;passportID;eyeColor;hairColor;location"
     * @return объект Person или null при ошибке
     */
    public static Person parse(String csv) {
        if (csv == null || csv.isEmpty() || csv.equals("null")) {
            return null;
        }

        try {
            String[] parts = csv.split(";", 5);
            if (parts.length != 5) {
                return null;
            }

            String name = parts[0];
            if (name == null || name.isEmpty()) {
                return null;
            }

            String passportID = parts[1].equals("null") ? null : parts[1];
            Color eyeColor = parts[2].equals("null") ? null : Color.valueOf(parts[2]);
            Color hairColor = parts[3].equals("null") ? null : Color.valueOf(parts[3]);
            Location location = Location.parse(parts[4]);

            return new Person(name, passportID, eyeColor, hairColor, location);

        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * Функция сравнения объектов
     * @param obj - объект для сравнения
     * @return true если авторы равны, иначе false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person that = (Person) obj;
        if (passportID != null && that.passportID != null) {
            return passportID.equals(that.passportID);
        }
        return name.equals(that.name);
    }

    /**
     * Функция получения хеш-кода объекта
     * @return хеш-код
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }
}