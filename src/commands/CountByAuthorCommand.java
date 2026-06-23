package commands;

import managers.CollectionManager;
import models.LabWork;
import utility.Console;

/**
 * Команда подсчёта элементов по автору.
 * <p>
 * Подсчитывает количество объектов {@link LabWork}, имя автора которых
 * совпадает с указанным пользователем значением.
 * </p>
 *
 * <p>
 * Команда принимает один аргумент — имя автора.
 * </p>
 *
 * @author Виктория Родина
 */
public class CountByAuthorCommand extends AbstractCommand {

    /**
     * Менеджер коллекции, содержащий объекты {@link LabWork}.
     */
    private final CollectionManager collectionManager;

    /**
     * Объект для вывода сообщений пользователю.
     */
    private final Console console;

    /**
     * Создаёт команду подсчёта элементов по автору.
     *
     * @param collectionManager менеджер коллекции
     * @param console объект для вывода информации и сообщений об ошибках
     */
    public CountByAuthorCommand(CollectionManager collectionManager, Console console) {
        super("count_by_author", "посчитать количество элементов с заданным автором");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    /**
     * Выполняет команду подсчёта элементов с указанным автором.
     * <p>
     * Проверяет корректность количества аргументов. Если аргумент отсутствует
     * или передано несколько аргументов, выводится сообщение об ошибке.
     * </p>
     *
     * <p>
     * При корректном вызове проходит по всем элементам коллекции и подсчитывает
     * количество объектов, имя автора которых совпадает с переданным значением.
     * </p>
     *
     * @param arguments массив аргументов команды, содержащий имя автора
     */
    @Override
    public void execute(String[] arguments) {

        if (arguments.length != 1) {
            console.printError("Использование: count_by_author author");
            return;
        }

        String authorName = arguments[0];

        int count = 0;

        for (LabWork lab : collectionManager.getCollection().values()) {

            if (lab.getAuthor() != null
                    && lab.getAuthor().getName() != null
                    && lab.getAuthor().getName().equals(authorName)) {
                count++;
            }
        }

        console.println("Количество элементов с автором '" + authorName + "': " + count);
    }
}