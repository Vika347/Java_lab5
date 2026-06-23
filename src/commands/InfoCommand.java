package commands;

import managers.CollectionManager;
import utility.Console;

/**
 * Команда вывода информации о коллекции.
 * <p>
 * Отображает основную информацию о коллекции:
 * тип используемой коллекции, количество элементов и дату её инициализации.
 * </p>
 *
 * <p>
 * Команда не принимает аргументов.
 * </p>
 *
 * @author Виктория Родина
 */
public class InfoCommand extends AbstractCommand {

    /**
     * Менеджер коллекции, содержащий информацию о коллекции.
     */
    private final CollectionManager collectionManager;

    /**
     * Объект для вывода информации и сообщений об ошибках.
     */
    private final Console console;

    /**
     * Создаёт команду вывода информации о коллекции.
     *
     * @param collectionManager менеджер коллекции
     * @param console объект для вывода информации и сообщений об ошибках
     */
    public InfoCommand(CollectionManager collectionManager, Console console) {
        super("info", "информация о коллекции");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    /**
     * Выводит информацию о коллекции.
     * <p>
     * Если пользователем переданы аргументы, выводится сообщение об ошибке,
     * так как команда {@code info} не принимает параметров.
     * </p>
     *
     * <p>
     * При корректном вызове выводятся:
     * </p>
     * <ul>
     *     <li>тип используемой коллекции;</li>
     *     <li>количество элементов в коллекции;</li>
     *     <li>дата инициализации коллекции.</li>
     * </ul>
     *
     * @param arguments массив аргументов команды
     */
    @Override
    public void execute(String[] arguments) {

        if (arguments.length > 0) {
            console.printError("info не принимает аргументы.");
            return;
        }

        console.println("Тип коллекции: Hashtable<Integer, LabWork>");
        console.println("Количество элементов: " + collectionManager.size());
        console.println("Дата инициализации: " + collectionManager.getInitializationDate());
    }
}