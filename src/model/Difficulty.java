package model;

/**
 * Перечисление уровней сложности лабораторной работы.
 * @author Виктория Родина
 */
public enum Difficulty {
    /** Очень легкий уровень */
    VERY_EASY,
    /** Легкий уровень */
    EASY,
    /** Сложный уровень */
    HARD,
    /** Очень сложный уровень */
    VERY_HARD,
    /** Невозможный уровень */
    IMPOSSIBLE;

    /**
     * Функция получения строки со всеми доступными значениями.
     * @return строка со всеми уровнями сложности через запятую
     */
    public static String getAvailableValues() {
        StringBuilder sb = new StringBuilder();
        for (Difficulty d : values()) {
            sb.append(d).append(", ");
        }
        // Удаляем последнюю запятую и пробел
        return sb.substring(0, sb.length() - 2);
    }
}