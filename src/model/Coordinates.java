package model;

/**
 * Класс, представляющий координаты на плоскости.
 * Содержит две целочисленные координаты: x и y.
 * @author Виктория Родина
 */

public class Coordinates {
    /** Поле координата X */
    private int x;
    /** Поле координата Y */
    private int y;

    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param x - координата X
     * @param y - координата Y
     */
    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Функция получения значения поля {@link Coordinates#x}
     * @return возвращает координату X
     */
    public int getX() {
        return x;
    }

    /**
     * Процедура определения координаты X {@link Coordinates#x}
     * @param x - координата X
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Функция получения значения поля {@link Coordinates#y}
     * @return возвращает координату Y
     */
    public int getY() {
        return y;
    }

    /**
     * Процедура определения координаты Y {@link Coordinates#y}
     * @param y - координата Y
     */
    public void setY(int y) {
        this.y = y;
    }


    /**
     * Функция преобразования объекта в строку для CSV формата
     * @return строка вида "x;y"
     */
    @Override
    public String toString() {
        return x + ";" + y;
    }


    /**
     * Функция создания объекта Coordinates из строки CSV
     * @param csv - строка формата "x;y"
     * @return объект Coordinates или null при ошибке
     */
    public static Coordinates parse(String csv) {
        if (csv == null || csv.isEmpty() || csv.equals("null")) {
            return null;
        }
        try {
            String[] parts = csv.split(";");
            if (parts.length != 2) {
                return null;
            }
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            return new Coordinates(x, y);
        } catch (NumberFormatException e) {
            return null;
        }
    }


    /**
     * Функция сравнения объектов
     * @param obj - объект для сравнения
     * @return true если координаты равны, иначе false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Coordinates that = (Coordinates) obj;
        return x == that.x && y == that.y;
    }

    /**
     * Функция получения хеш-кода объекта
     * @return хеш-код
     */
    @Override
    public int hashCode() {
        return 31 * x + y;
    }
}