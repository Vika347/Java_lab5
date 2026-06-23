package managers;

import models.*;
import utility.CsvParser;
import utility.Console;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Hashtable;

/**
 * Менеджер работы с файлом хранения коллекции.
 * <p>
 * Отвечает за загрузку коллекции из CSV-файла и сохранение
 * коллекции на диск.
 * </p>
 *
 * @author Виктория Родина
 */
public class FileManager {

    /**
     * Имя файла, используемого для хранения коллекции.
     */
    private final String fileName;

    /**
     * Объект для вывода сообщений и ошибок.
     */
    private final Console console;

    /**
     * Создаёт менеджер работы с файлом.
     *
     * @param fileName имя файла хранения коллекции
     * @param console объект для вывода сообщений и ошибок
     */
    public FileManager(String fileName, Console console) {
        this.fileName = fileName;
        this.console = console;
    }

    /**
     * Загружает коллекцию из файла.
     * <p>
     * Каждая строка файла преобразуется в объект {@link LabWork}
     * с помощью класса {@link CsvParser}.
     * </p>
     *
     * @return коллекция объектов {@link LabWork}, считанных из файла
     */
    public Hashtable<Integer, LabWork> readCollection() {
        Hashtable<Integer, LabWork> collection = new Hashtable<>();
        File file = new File(fileName);

        if (!file.exists()) {
            console.printError("Файл не найден: " + fileName + ". Будет создан новый.");
            return collection;
        }

        try (InputStreamReader reader = new InputStreamReader(
                new FileInputStream(file), StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(reader)) {

            String line;
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;

                try {
                    LabWork lab = CsvParser.parseLabWork(line);
                    collection.put(lab.getId(), lab);
                } catch (Exception e) {
                    console.printError("Ошибка парсинга строки: " + line);
                }
            }
        } catch (IOException e) {
            console.printError("Ошибка чтения файла: " + e.getMessage());
        }

        return collection;
    }

    /**
     * Сохраняет коллекцию в файл.
     * <p>
     * Каждый объект {@link LabWork} преобразуется в строку формата CSV
     * с помощью метода {@link CsvParser#toCsv(LabWork)}.
     * </p>
     *
     * @param collection коллекция объектов, подлежащая сохранению
     */
    public void writeCollection(Hashtable<Integer, LabWork> collection) {
        try (FileWriter writer = new FileWriter(fileName)) {

            for (LabWork lab : collection.values()) {
                writer.write(CsvParser.toCsv(lab) + "\n");
            }

        } catch (IOException e) {
            console.printError("Ошибка записи файла: " + e.getMessage());
        }
    }
}

