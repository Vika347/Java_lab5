package model;

/**
 * Перечисление цветов.
 * Используется для eyeColor и hairColor в классе Person.
 * Доступные цвета: GREEN, RED, BLACK, YELLOW, ORANGE, WHITE.
 * @author Виктория Родина
 */

public enum Color {
    /** Зеленый цвет */
    GREEN,
    /** Красный цвет */
    RED,
    /** Черный цвет */
    BLACK,
    /** Желтый цвет */
    YELLOW,
    /** Оранжевый цвет */
    ORANGE,
    /** Белый цвет */
    WHITE;


    /**
     * Функция получения строки со всеми доступными значениями.
     * @return строка со всеми цветами через запятую
     */
    public static String getAvailableValues() {
        StringBuilder sb = new StringBuilder();
        for (Color c : values()) {
            sb.append(c).append(", ");
        }
        return sb.substring(0, sb.length() - 2);
    }
}