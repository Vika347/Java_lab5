package utility;

import models.*;

/**
 * Утилитарный класс для валидации данных.
 * <p>
 * Содержит методы проверки значений полей моделей перед добавлением
 * объектов в коллекцию или сохранением данных.
 * </p>
 *
 * <p>
 * Класс реализует основные бизнес-правила, связанные с ограничениями
 * полей классов {@link LabWork}, {@link Person}, {@link Coordinates}
 * и {@link Location}.
 * </p>
 *
 * @author Виктория Родина
 */
public class Validator {

    /**
     * Проверяет строку на непустое значение.
     *
     * @param name проверяемая строка
     * @return {@code true}, если строка не равна {@code null}
     *         и не является пустой после удаления пробелов;
     *         иначе {@code false}
     */
    public static boolean validateName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    /**
     * Проверяет координаты объекта.
     * <p>
     * В текущей реализации дополнительные ограничения на координаты
     * отсутствуют, поэтому метод всегда возвращает {@code true}.
     * Метод оставлен для возможного расширения логики проверки.
     * </p>
     *
     * @param x координата по оси X
     * @param y координата по оси Y
     * @return {@code true}, так как ограничения на координаты отсутствуют
     */
    public static boolean validateCoordinates(long x, long y) {
        return true;
    }

    /**
     * Проверяет значение минимального балла.
     * <p>
     * Значение может быть {@code null}. Если значение не равно
     * {@code null}, оно должно быть больше 0.
     * </p>
     *
     * @param value проверяемое значение minimalPoint
     * @return {@code true}, если значение равно {@code null}
     *         или больше 0; иначе {@code false}
     */
    public static boolean validateMinimalPoint(Integer value) {
        return value == null || value > 0;
    }

    /**
     * Проверяет значение минимальных личных качеств.
     *
     * @param value проверяемое значение personalQualitiesMinimum
     * @return {@code true}, если значение больше 0; иначе {@code false}
     */
    public static boolean validatePersonalQualities(float value) {
        return value > 0;
    }

    /**
     * Проверяет имя человека.
     *
     * @param name проверяемое имя
     * @return {@code true}, если имя не равно {@code null}
     *         и не является пустой строкой после удаления пробелов;
     *         иначе {@code false}
     */
    public static boolean validatePersonName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    /**
     * Проверяет идентификатор паспорта.
     * <p>
     * Значение может быть {@code null}. Если значение не равно
     * {@code null}, оно не должно быть пустой строкой.
     * </p>
     *
     * @param id проверяемый идентификатор паспорта
     * @return {@code true}, если идентификатор равен {@code null}
     *         или является непустой строкой; иначе {@code false}
     */
    public static boolean validatePassportId(String id) {
        return id == null || !id.trim().isEmpty();
    }

    /**
     * Проверяет координату Z местоположения.
     *
     * @param z проверяемое значение координаты Z
     * @return {@code true}, если значение не равно {@code null};
     *         иначе {@code false}
     */
    public static boolean validateLocationZ(Float z) {
        return z != null;
    }

    /**
     * Выполняет минимально необходимую проверку объекта {@link LabWork}.
     * <p>
     * Проверяются основные обязательные поля объекта:
     * название, координаты, значение personalQualitiesMinimum,
     * сложность, автор, имя автора, местоположение автора
     * и координата Z местоположения.
     * </p>
     *
     * @param lab проверяемый объект лабораторной работы
     * @return {@code true}, если объект соответствует минимальным требованиям;
     *         иначе {@code false}
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