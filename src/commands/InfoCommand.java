package commands;

import collection.CollectionManager;
import utils.ConsoleReader;

/**
 * Команда info - выводит информацию о коллекции.
 * Выводит: тип коллекции, дату создания, количество элементов.
 * @author Виктория Родина
 */
public class InfoCommand implements Command {

    /**
     * Выполнение команды info
     * @param args - аргументы команды (не используются)
     * @param collectionManager - менеджер коллекции
     * @param consoleReader - читатель консоли (не используется)
     * @return true - продолжение работы программы
     */
    @Override
    public boolean execute(String[] args, CollectionManager collectionManager, ConsoleReader consoleReader) {
        System.out.println("Тип: " + collectionManager.getCollectionType());
        System.out.println("Дата: " + collectionManager.getInitializationDate());
        System.out.println("Кол-во: " + collectionManager.size());
        return true;
    }

    /**
     * Функция получения описания команды
     * @return описание команды
     */
    @Override
    public String getDescription() {
        return "информация о коллекции";
    }

    /**
     * Функция получения имени команды
     * @return имя команды
     */
    @Override
    public String getName() {
        return "info";
    }
}