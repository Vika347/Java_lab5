package model;

/**
 * Класс местоположения со свойствами <b>x</b>, <b>y</b> и <b>z</b>.
 * @author Виктория Родина
 */
public class Location {
    /** Поле координата X */
    private long x;
    /** Поле координата Y */
    private double y;
    /** Поле координата Z (не может быть null) */
    private Integer z;

    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param x - координата X
     * @param y - координата Y
     * @param z - координата Z (не может быть null)
     */
    public Location(long x, double y, Integer z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Функция получения значения поля {@link Location#x}
     * @return возвращает координату X
     */
    public long getX() { return x; }

    /**
     * Функция получения значения поля {@link Location#y}
     * @return возвращает координату Y
     */
    public double getY() { return y; }

    /**
     * Функция получения значения поля {@link Location#z}
     * @return возвращает координату Z
     */
    public Integer getZ() { return z; }

    /**
     * Процедура определения координаты X {@link Location#x}
     * @param x - координата X
     */
    public void setX(long x) { this.x = x; }

    /**
     * Процедура определения координаты Y {@link Location#y}
     * @param y - координата Y
     */
    public void setY(double y) { this.y = y; }

    /**
     * Процедура определения координаты Z {@link Location#z}
     * @param z - координата Z (не может быть null)
     */
    public void setZ(Integer z) { this.z = z; }

    /**
     * Функция преобразования объекта в строку для CSV формата
     * @return строка вида "x;y;z"
     */
    @Override
    public String toString() {
        return x + ";" + y + ";" + z;
    }

    /**
     * Функция создания объекта Location из строки CSV
     * @param csv - строка формата "x;y;z"
     * @return объект Location или null при ошибке
     */
    public static Location parse(String csv) {
        if (csv == null || csv.isEmpty() || csv.equals("null")) {
            return null;
        }
        try {
            String[] parts = csv.split(";");
            if (parts.length != 3) {
                return null;
            }
            long x = Long.parseLong(parts[0]);
            double y = Double.parseDouble(parts[1]);
            Integer z = Integer.parseInt(parts[2]);
            return new Location(x, y, z);
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
        Location that = (Location) obj;
        return x == that.x && Double.compare(that.y, y) == 0 && z.equals(that.z);
    }

    /**
     * Функция получения хеш-кода объекта
     * @return хеш-код
     */
    @Override
    public int hashCode() {
        int result = (int) (x ^ (x >>> 32));
        result = 31 * result + Double.hashCode(y);
        result = 31 * result + z.hashCode();
        return result;
    }
}