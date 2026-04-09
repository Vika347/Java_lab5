package commands;

import collection.CollectionManager;
import utils.ConsoleReader;

/**
 * Интерфейс для всех команд.
 * Все команды должны реализовывать этот интерфейс.
 * @author Виктория Родина
 */
public interface Command {

    /**
     * Функция выполнения команды
     * @param args - аргументы команды (массив строк)
     * @param collectionManager - менеджер коллекции
     * @param consoleReader - читатель консоли для ввода данных
     * @return true если программа должна продолжить работу, false для выхода
     */
    boolean execute(String[] args, CollectionManager collectionManager, ConsoleReader consoleReader);

    /**
     * Функция получения описания команды для справки
     * @return описание команды
     */
    String getDescription();

    /**
     * Функция получения имени команды
     * @return имя команды
     */
    String getName();
}