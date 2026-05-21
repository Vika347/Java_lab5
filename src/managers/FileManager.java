package managers;

import models.*;
import utility.CsvParser; // утилитарный класс для преобразования CSV ↔ LabWork
import utility.Console;//для вывода сообщений об ошибках

import java.io.*; //все классы для ввода-вывода
import java.nio.charset.StandardCharsets;
import java.util.Hashtable;

/**
 * Менеджер работы с файлом хранения коллекции.
 * Умеет читать и писать файлы.
 * Он отвечает за сохранение коллекции на диск и загрузку с диска
 */
public class FileManager {

    private final String fileName;
    private final Console console;

    //Конструктор
    public FileManager(String fileName, Console console) {
        this.fileName = fileName;
        this.console = console;
    }

    //Метод для чтения из файла
    public Hashtable<Integer, LabWork> readCollection() {
        Hashtable<Integer, LabWork> collection = new Hashtable<>();
        File file = new File(fileName);

        //Проверяет, существует ли файл.
        if (!file.exists()) {
            console.printError("Файл не найден: " + fileName + ". Будет создан новый.");
            return collection;
        }

        try (InputStreamReader reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(reader)) {

            String line;
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;
                //Пытается превратить строку CSV в объект LabWork через CsvParser
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

    //запись в файл
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