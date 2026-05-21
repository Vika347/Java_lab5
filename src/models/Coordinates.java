package models;

/**
 * Класс координат.
 *
 * Используется в LabWork для хранения положения объекта.
 */
public class Coordinates {

    private long x;
    private long y;

    public Coordinates() {}

    public Coordinates(long x, long y) {
        this.x = x;
        this.y = y;
    }

    public long getX() {
        return x;
    }

    public void setX(long x) {
        this.x = x;
    }

    public long getY() {
        return y;
    }

    public void setY(long y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinates{x=" + x + ", y=" + y + "}";
    }
}