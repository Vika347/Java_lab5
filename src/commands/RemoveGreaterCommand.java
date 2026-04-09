package commands;

import collection.CollectionManager;
import model.LabWork;
import utils.ConsoleReader;

/**
 * Команда remove_greater - удаляет все элементы, превышающие заданный.
 * Сравнение происходит по id (сортировка по умолчанию).
 * @author Виктория Родина
 */
public class RemoveGreaterCommand implements Command {

    /**
     * Выполнение команды remove_greater
     * @param args - аргументы команды (не используются)
     * @param collectionManager - менеджер коллекции
     * @param consoleReader - читатель консоли для ввода элемента сравнения
     * @return true - продолжение работы программы
     */
    @Override
    public boolean execute(String[] args, CollectionManager collectionManager, ConsoleReader consoleReader) {
        if (collectionManager.isEmpty()) {
            System.out.println("Коллекция пуста");
            return true;
        }

        System.out.println("Введите элемент для сравнения:");
        LabWork element = consoleReader.readLabWork();
        // УДАЛЕНИЕ (через CollectionManager)
        int beforeSize = collectionManager.size();
        collectionManager.removeGreater(element);
        int removed = beforeSize - collectionManager.size();

        System.out.println("Удалено элементов: " + removed);
        return true;
    }

    /**
     * Функция получения описания команды
     * @return описание команды
     */
    @Override
    public String getDescription() {
        return "удалить все элементы, превышающие заданный";
    }

    /**
     * Функция получения имени команды
     * @return имя команды
     */
    @Override
    public String getName() {
        return "remove_greater";
    }
}