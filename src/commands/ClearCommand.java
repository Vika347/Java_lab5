package commands;

import collection.CollectionManager;
import utils.ConsoleReader;

/**
 * Команда clear - очищает всю коллекцию.
 * Удаляет все элементы без возможности восстановления.
 * @author Виктория Родина
 */
public class ClearCommand implements Command {

    /**
     * Выполнение команды clear
     * @param args - аргументы команды (не используются)
     * @param collectionManager - менеджер коллекции
     * @param consoleReader - читатель консоли (не используется)
     * @return true - продолжение работы программы
     */
    @Override
    public boolean execute(String[] args, CollectionManager collectionManager, ConsoleReader consoleReader) {
        int beforeSize = collectionManager.size(); // запоминаем размер

        if (beforeSize == 0) { //проверка на пустоту
            System.out.println("Коллекция уже пуста");
            return true;
        }

        collectionManager.clear(); //очистка коллекции

        System.out.println("Коллекция очищена. Удалено элементов: " + beforeSize);

        return true;
    }

    /**
     * Функция получения описания команды
     * @return описание команды
     */
    @Override
    public String getDescription() {
        return "очистить коллекцию";
    }

    /**
     * Функция получения имени команды
     * @return имя команды
     */
    @Override
    public String getName() {
        return "clear";
    }
}