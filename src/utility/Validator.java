package utility;

import models.*;
//проверяет, правильные ли данные, прежде чем они попадут в коллекцию
/**
 * Утилитарный класс для валидации данных.
 *
 * Содержит бизнес-правила для проверки объектов модели.
 */
public class Validator {

    /**
     * Проверка строки на непустое значение.
     */
    public static boolean validateName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    /**
     * Проверка координат.
     * По заданию ограничений нет, метод оставлен для расширяемости.
     */
    public static boolean validateCoordinates(long x, long y) {
        return true;
    }

    /**
     * minimalPoint: может быть null или > 0.
     */
    public static boolean validateMinimalPoint(Integer value) {
        return value == null || value > 0;
    }

    /**
     * personalQualitiesMinimum: должно быть > 0.
     */
    public static boolean validatePersonalQualities(float value) {
        return value > 0;
    }

    /**
     * Проверка имени человека.
     */
    public static boolean validatePersonName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    /**
     * passportID: может быть null или непустая строка.
     */
    public static boolean validatePassportId(String id) {
        return id == null || !id.trim().isEmpty();
    }

    /**
     * location.z не может быть null.
     */
    public static boolean validateLocationZ(Float z) {
        return z != null;
    }

    /**
     * Полная проверка LabWork (минимально необходимая).
     */
    public static boolean validateLabWork(LabWork lab) {

        if (lab == null) return false;

        return validateName(lab.getName())
                && lab.getCoordinates() != null
                && validatePersonalQualities(lab.getPersonalQualitiesMinimum())
                && lab.getDifficulty() != null
                && lab.getAuthor() != null
                && validatePersonName(lab.getAuthor().getName())
                && lab.getAuthor().getLocation() != null
                && validateLocationZ(lab.getAuthor().getLocation().getZ());
    }
}