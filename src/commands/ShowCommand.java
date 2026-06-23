package commands;

import managers.CollectionManager;
import models.LabWork;
import utility.Console;

/**
 * Команда вывода элементов коллекции.
 * <p>
 * Выводит все объекты {@link LabWork}, содержащиеся в коллекции,
 * в отсортированном порядке.
 * </p>
 *
 * <p>
 * Если коллекция пуста, пользователю выводится соответствующее сообщение.
 * Команда не принимает аргументов.
 * </p>
 *
 * @author Виктория Родина
 */
public class ShowCommand extends AbstractCommand {

    /**
     * Менеджер коллекции, содержащий объекты {@link LabWork}.
     */
    private final CollectionManager collectionManager;

    /**
     * Объект для вывода информации и сообщений об ошибках.
     */
    private final Console console;

    /**
     * Создаёт команду вывода элементов коллекции.
     *
     * @param collectionManager менеджер коллекции
     * @param console объект для вывода информации и сообщений об ошибках
     */
    public ShowCommand(CollectionManager collectionManager, Console console) {
        super("show", "вывести все элементы коллекции");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    /**
     * Выполняет вывод всех элементов коллекции.
     * <p>
     * Проверяет отсутствие аргументов. Если пользователем переданы
     * дополнительные параметры, выводится сообщение об ошибке.
     * </p>
     *
     * <p>
     * Если коллекция пуста, выводится соответствующее сообщение.
     * В противном случае все элементы выводятся в отсортированном порядке
     * с использованием их строкового представления.
     * </p>
     *
     * @param arguments массив аргументов команды
     */
    @Override
    public void execute(String[] arguments) {

        if (arguments.length > 0) {
            console.printError("Команда show не принимает аргументы.");
            return;
        }

        if (collectionManager.size() == 0) {
            console.println("Коллекция пуста.");
            return;
        }

        for (LabWork lab : collectionManager.getSorted()) {
            console.println(lab.toString());
        }
    }
}