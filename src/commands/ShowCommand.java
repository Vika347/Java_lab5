package commands;

import collection.CollectionManager;
import model.LabWork;
import utils.ConsoleReader;
import java.util.Collection;

/**
 * Команда show - выводит все элементы коллекции.
 * Если коллекция пуста - сообщает об этом.
 * Если есть элементы - выводит каждый в строковом представлении.
 * @author Виктория Родина
 */
public class ShowCommand implements Command {

    /**
     * Выполнение команды show
     * @param args - аргументы команды (не используются)
     * @param collectionManager - менеджер коллекции
     * @param consoleReader - читатель консоли (не используется)
     * @return true - продолжение работы программы
     */
    @Override
    public boolean execute(String[] args, CollectionManager collectionManager, ConsoleReader consoleReader) {
        Collection<LabWork> allElements = collectionManager.getAll();

        if (allElements.isEmpty()) {
            System.out.println("Коллекция пуста");
            return true;
        }

        System.out.println("Элементы коллекции:");
        for (LabWork labWork : allElements) {
            System.out.println(labWork);
        }

        return true;
    }

    /**
     * Функция получения описания команды
     * @return описание команды
     */
    @Override
    public String getDescription() {
        return "вывести все элементы коллекции";
    }

    /**
     * Функция получения имени команды
     * @return имя команды
     */
    @Override
    public String getName() {
        return "show";
    }
}