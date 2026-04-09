package utils;

import model.*;

/**
 * Класс для валидации вводимых данных.
 * Содержит статические методы для проверки корректности полей.
 * @author Виктория Родина
 */
public class InputValidator {

    /**
     * Функция проверки имени
     * @param name - имя для проверки
     * @return true если имя не null и не пустое, иначе false
     */
    public static boolean validateName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    /**
     * Функция проверки минимального балла
     * @param point - минимальный балл для проверки
     * @return true если point == null или point > 0, иначе false
     */
    public static boolean validateMinimalPoint(Double point) {
        return point == null || point > 0;
    }

    /**
     * Функция проверки минимума личных качеств
     * @param value - значение для проверки
     * @return true если value > 0, иначе false
     */
    public static boolean validatePersonalQualitiesMinimum(double value) {
        return value > 0;
    }

    /**
     * Функция проверки сложности
     * @param difficultyStr - строка с названием сложности
     * @return true если строка соответствует одному из значений {@link Difficulty}, иначе false
     */
    public static boolean validateDifficulty(String difficultyStr) {
        try {
            Difficulty.valueOf(difficultyStr);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Функция проверки цвета
     * @param colorStr - строка с названием цвета
     * @return true если строка соответствует одному из значений {@link Color}, иначе false
     */
    public static boolean validateColor(String colorStr) {
        try {
            Color.valueOf(colorStr);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Функция проверки идентификатора
     * @param idStr - строка с идентификатором
     * @return true если строка является целым числом больше 0, иначе false
     */
    public static boolean validateId(String idStr) {
        try {
            long id = Long.parseLong(idStr);
            return id > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}