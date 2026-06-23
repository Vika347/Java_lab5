package models;

/**
 * Класс координат.
 * <p>
 * Используется в классе {@link LabWork} для хранения координат объекта.
 * Содержит значения по осям X и Y.
 * </p>
 *
 * @author Виктория Родина
 */
public class Coordinates {

    /**
     * Координата по оси X.
     */
    private long x;

    /**
     * Координата по оси Y.
     */
    private long y;

    /**
     * Создаёт пустой объект координат.
     */
    public Coordinates() {}

    /**
     * Создаёт объект координат с указанными значениями.
     *
     * @param x координата по оси X
     * @param y координата по оси Y
     */
    public Coordinates(long x, long y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Возвращает значение координаты по оси X.
     *
     * @return координата X
     */
    public long getX() {
        return x;
    }

    /**
     * Устанавливает значение координаты по оси X.
     *
     * @param x новое значение координаты X
     */
    public void setX(long x) {
        this.x = x;
    }

    /**
     * Возвращает значение координаты по оси Y.
     *
     * @return координата Y
     */
    public long getY() {
        return y;
    }

    /**
     * Устанавливает значение координаты по оси Y.
     *
     * @param y новое значение координаты Y
     */
    public void setY(long y) {
        this.y = y;
    }

    /**
     * Возвращает строковое представление объекта координат.
     *
     * @return строка, содержащая значения координат X и Y
     */
    @Override
    public String toString() {
        return "Coordinates{x=" + x + ", y=" + y + "}";
    }
}