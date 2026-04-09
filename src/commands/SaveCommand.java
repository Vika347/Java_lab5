package commands;

import collection.CollectionManager;
import collection.FileManager;
import utils.ConsoleReader;

/**
 * Команда save - сохраняет коллекцию в файл.
 * Сохраняет все элементы в CSV файл, указанный в переменной окружения.
 * @author Виктория Родина
 * @version 1.0
 */
public class SaveCommand implements Command {

    /** Поле менеджер файлов для сохранения */ //сохраняет ссылку на FileManager
    private FileManager fileManager;

    /**
     * Конструктор - создание нового объекта команды save
     * @param fileManager - менеджер файлов, который знает, в какой файл сохранять
     */
    public SaveCommand(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    /**
     * Выполнение команды save
     * @param args - аргументы команды (не используются)
     * @param collectionManager - менеджер коллекции
     * @param consoleReader - читатель консоли (не используется)
     * @return true - продолжение работы программы
     */
    @Override
    public boolean execute(String[] args, CollectionManager collectionManager, ConsoleReader consoleReader) {
        fileManager.save(collectionManager);
        return true;
    }

    /**
     * Функция получения описания команды
     * @return описание команды
     */
    @Override
    public String getDescription() {
        return "сохранить коллекцию в файл";
    }

    /**
     * Функция получения имени команды
     * @return имя команды
     */
    @Override
    public String getName() {
        return "save";
    }
}