package utility;

import models.*;

import java.util.Date;

/**
 * Утилитарный класс для преобразования объектов {@link LabWork}
 * в формат CSV и обратно.
 * <p>
 * Используется для загрузки коллекции из файла и сохранения
 * коллекции в файл. Позволяет преобразовывать строковое
 * представление данных в объекты и выполнять обратное преобразование.
 * </p>
 *
 * <p>
 * Все методы класса являются статическими.
 * </p>
 *
 * @author Виктория Родина
 */
public class CsvParser {

    /**
     * Создаёт объект {@link LabWork} из строки в формате CSV.
     * <p>
     * Строка должна содержать данные, разделённые символом {@code ';'}.
     * В случае некорректного формата выбрасывается исключение.
     * </p>
     *
     * @param line строка с данными объекта LabWork
     * @return созданный объект {@link LabWork}
     * @throws IllegalArgumentException если строка содержит недостаточное
     * количество полей
     * @throws NumberFormatException если числовые поля содержат некорректные значения
     */
    public static LabWork parseLabWork(String line) {
        String[] data = line.split(";");

        if (data.length < 14) {
            throw new IllegalArgumentException("Некорректная CSV строка");
        }

        LabWork lab = new LabWork();
        lab.setId(Integer.parseInt(data[0].trim()));
        lab.setName(data[1].trim());

        Coordinates coordinates = new Coordinates();
        coordinates.setX(Long.parseLong(data[2].trim()));
        coordinates.setY(Long.parseLong(data[3].trim()));
        lab.setCoordinates(coordinates);

        lab.setCreationDate(new Date());

        String minPoint = data[4].trim();
        lab.setMinimalPoint(minPoint.isEmpty() ? null : Integer.parseInt(minPoint));
        lab.setPersonalQualitiesMinimum(Float.parseFloat(data[5].trim()));
        lab.setDifficulty(Difficulty.valueOf(data[6].trim().toUpperCase()));

        Person author = new Person();
        author.setName(data[7].trim());
        author.setPassportID(data[8].trim().isEmpty() ? null : data[8].trim());
        author.setEyeColor(parseColor(data[9].trim()));
        author.setHairColor(parseColor(data[10].trim()));

        Location location = new Location();
        location.setX(Integer.parseInt(data[11].trim()));
        location.setY(Float.parseFloat(data[12].trim()));
        location.setZ(Float.parseFloat(data[13].trim()));
        author.setLocation(location);

        lab.setAuthor(author);
        return lab;
    }

    /**
     * Преобразует строковое представление цвета
     * в значение перечисления {@link Color}.
     *
     * @param value строковое представление цвета
     * @return соответствующее значение {@link Color} или {@code null},
     * если строка пуста или равна {@code null}
     * @throws IllegalArgumentException если значение не соответствует
     * ни одной константе перечисления
     */
    private static Color parseColor(String value) {
        if (value == null || value.isEmpty()) return null;
        return Color.valueOf(value.toUpperCase());
    }

    /**
     * Преобразует объект {@link LabWork} в строку формата CSV.
     * <p>
     * Значения полей разделяются символом {@code ';'}.
     * Поля, допускающие значение {@code null},
     * сохраняются как пустые строки.
     * </p>
     *
     * @param lab объект лабораторной работы
     * @return строка в формате CSV
     */
    public static String toCsv(LabWork lab) {
        return lab.getId() + ";" +
                lab.getName() + ";" +
                lab.getCoordinates().getX() + ";" +
                lab.getCoordinates().getY() + ";" +
                (lab.getMinimalPoint() == null ? "" : lab.getMinimalPoint()) + ";" +
                lab.getPersonalQualitiesMinimum() + ";" +
                lab.getDifficulty() + ";" +
                lab.getAuthor().getName() + ";" +
                (lab.getAuthor().getPassportID() == null ? "" : lab.getAuthor().getPassportID()) + ";" +
                (lab.getAuthor().getEyeColor() == null ? "" : lab.getAuthor().getEyeColor()) + ";" +
                (lab.getAuthor().getHairColor() == null ? "" : lab.getAuthor().getHairColor()) + ";" +
                lab.getAuthor().getLocation().getX() + ";" +
                lab.getAuthor().getLocation().getY() + ";" +
                lab.getAuthor().getLocation().getZ();
    }
}