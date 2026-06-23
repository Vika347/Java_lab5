package commands;

import managers.CollectionManager;
import models.LabWork;
import utility.Console;

/**
 * Команда фильтрации элементов по имени автора.
 * <p>
 * Выводит все объекты {@link LabWork}, имя автора которых
 * лексикографически меньше заданного пользователем значения.
 * </p>
 *
 * <p>
 * Сравнение выполняется с помощью метода {@link String#compareTo(String)}.
 * Команда принимает один аргумент — имя автора.
 * </p>
 *
 * @author Виктория Родина
 */
public class FilterLessThanAuthorCommand extends AbstractCommand {

    /**
     * Менеджер коллекции, содержащий объекты {@link LabWork}.
     */
    private final CollectionManager collectionManager;

    /**
     * Объект для вывода информации и сообщений об ошибках.
     */
    private final Console console;

    /**
     * Создаёт команду фильтрации по имени автора.
     *
     * @param collectionManager менеджер коллекции
     * @param console объект для вывода информации и сообщений об ошибках
     */
    public FilterLessThanAuthorCommand(CollectionManager collectionManager, Console console) {
        super("filter_less_than_author", "вывести элементы, автор которых меньше заданного");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    /**
     * Выполняет фильтрацию элементов коллекции по имени автора.
     * <p>
     * Проверяет корректность количества аргументов. Затем проходит по всем
     * элементам коллекции и выводит те объекты, имя автора которых
     * лексикографически меньше переданного значения.
     * </p>
     *
     * <p>
     * Если подходящих элементов не найдено, пользователю выводится
     * соответствующее сообщение.
     * </p>
     *
     * @param arguments массив аргументов команды, содержащий имя автора
     */
    @Override
    public void execute(String[] arguments) {

        if (arguments.length != 1) {
            console.printError("Использование: filter_less_than_author authorName");
            return;
        }

        String authorName = arguments[0];
        boolean found = false;

        for (LabWork lab : collectionManager.getCollection().values()) {

            if (lab.getAuthor() != null
                    && lab.getAuthor().getName() != null
                    && lab.getAuthor().getName().compareTo(authorName) < 0) {

                console.println(lab.toString());
                found = true;
            }
        }

        if (!found) {
            console.println("Элементы не найдены.");
        }
    }
}