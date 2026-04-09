package collection;

import model.LabWork;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Класс для работы с файлом.
 * Чтение через InputStreamReader, запись через FileWriter.
 * Формат файла: CSV с разделителем ";"
 * @author Виктория Родина
 */
public class FileManager {
    /** Поле имя файла для сохранения/загрузки */
    private String fileName;

    /**
     * Конструктор - создание нового объекта FileManager
     * @param fileName - путь к файлу для сохранения/загрузки
     */
    public FileManager(String fileName) {
        this.fileName = fileName;
    }

    // ЗАГРУЗКА ИЗ ФАЙЛА

    /**
     * Процедура загрузки данных из файла в коллекцию
     * Использует InputStreamReader для чтения
     * @param collectionManager - менеджер коллекции для заполнения
     */
    public void load(CollectionManager collectionManager) {
        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("Файл не существует. Будет создан новый при сохранении.");
            return;
        }

        if (!file.canRead()) {
            System.err.println("Ошибка: Нет прав на чтение файла " + fileName);
            return;
        }

        long maxId = 0;
        int loadedCount = 0;

        try (InputStreamReader reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)) {
            StringBuilder content = new StringBuilder();
            char[] buffer = new char[1024];
            int bytesRead;

            while ((bytesRead = reader.read(buffer)) != -1) {
                content.append(buffer, 0, bytesRead);
            }

            String[] lines = content.toString().split("\n");

            for (int i = 0; i < lines.length; i++) {
                String line = lines[i].trim();
                if (line.isEmpty()) continue;

                try {
                    LabWork labWork = LabWork.fromCsv(line);
                    if (labWork != null) {
                        collectionManager.put(labWork.getId(), labWork);
                        loadedCount++;

                        if (labWork.getId() > maxId) {
                            maxId = labWork.getId();
                        }
                    } else {
                        System.err.println("Ошибка при парсинге строки " + (i + 1) + ": " + line);
                    }
                } catch (Exception e) {
                    System.err.println("Ошибка при парсинге строки " + (i + 1) + ": " + line);
                    System.err.println("Причина: " + e.getMessage());
                }
            }

            if (maxId > 0) {
                LabWork.setNextId(maxId + 1);
            }

            System.out.println("Загружено элементов: " + loadedCount);
            if (loadedCount != lines.length) {
                System.out.println("Пропущено некорректных строк: " + (lines.length - loadedCount));
            }

        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден: " + fileName);
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    // СОХРАНЕНИЕ В ФАЙЛ

    /**
     * Процедура сохранения коллекции в файл
     * Использует FileWriter для записи
     * @param collectionManager - менеджер коллекции для сохранения
     */
    public void save(CollectionManager collectionManager) {
        File file = new File(fileName);

        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            if (!parentDir.mkdirs()) {
                System.err.println("Ошибка: Не удалось создать директорию " + parentDir.getPath());
                return;
            }
        }

        try {
            if (!file.exists() && !file.createNewFile()) {
                System.err.println("Ошибка: Не удалось создать файл " + fileName);
                return;
            }
        } catch (IOException e) {
            System.err.println("Ошибка при создании файла: " + e.getMessage());
            return;
        }

        if (!file.canWrite()) {
            System.err.println("Ошибка: Нет прав на запись в файл " + fileName);
            return;
        }

        try (FileWriter writer = new FileWriter(file, false)) {
            int savedCount = 0;

            for (LabWork labWork : collectionManager.getAll()) {
                writer.write(labWork.toCsv());
                writer.write("\n");
                savedCount++;
            }

            System.out.println("Коллекция сохранена в файл: " + fileName);
            System.out.println("Сохранено элементов: " + savedCount);

        } catch (IOException e) {
            System.err.println("Ошибка при сохранении файла: " + e.getMessage());
        }
    }

    // ВСПОМОГАТЕЛЬНЫЕ МЕТОДЫ

    /**
     * Функция проверки существования файла
     * @return true если файл существует, иначе false
     */
    public boolean fileExists() {
        return new File(fileName).exists();
    }

    /**
     * Функция получения имени файла
     * @return имя файла
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Процедура установки нового имени файла
     * @param fileName - новое имя файла
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}