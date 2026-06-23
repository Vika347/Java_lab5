package models;

/**
 * Класс, описывающий географическую локацию.
 * <p>
 * Используется в классе {@link Person} для хранения информации
 * о местоположении автора лабораторной работы.
 * </p>
 *
 * @author Виктория Родина
 */
public class Location {

    /**
     * Координата по оси X.
     */
    private int x;

    /**
     * Координата по оси Y.
     */
    private float y;

    /**
     * Координата по оси Z.
     * Поле не может принимать значение {@code null}.
     */
    private Float z;

    /**
     * Создаёт пустой объект местоположения.
     */
    public Location() {}

    /**
     * Создаёт объект местоположения с указанными координатами.
     *
     * @param x координата по оси X
     * @param y координата по оси Y
     * @param z координата по оси Z
     */
    public Location(int x, float y, Float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Возвращает координату по оси X.
     *
     * @return значение координаты X
     */
    public int getX() {
        return x;
    }

    /**
     * Устанавливает координату по оси X.
     *
     * @param x новое значение координаты X
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Возвращает координату по оси Y.
     *
     * @return значение координаты Y
     */
    public float getY() {
        return y;
    }

    /**
     * Устанавливает координату по оси Y.
     *
     * @param y новое значение координаты Y
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * Возвращает координату по оси Z.
     *
     * @return значение координаты Z
     */
    public Float getZ() {
        return z;
    }

    /**
     * Устанавливает координату по оси Z.
     *
     * @param z новое значение координаты Z
     */
    public void setZ(Float z) {
        this.z = z;
    }

    /**
     * Возвращает строковое представление объекта местоположения.
     *
     * @return строка, содержащая значения координат X, Y и Z
     */
    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}