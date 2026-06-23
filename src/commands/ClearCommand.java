package commands;

import managers.CollectionManager;
import utility.Console;

/**
 * Команда очистки коллекции.
 * <p>
 * Удаляет все элементы из коллекции, хранящейся в {@link CollectionManager}.
 * После выполнения команды коллекция становится пустой.
 * </p>
 *
 * <p>
 * Команда не принимает аргументов.
 * </p>
 *
 * @author Виктория Родина
 */
public class ClearCommand extends AbstractCommand {

    /**
     * Менеджер коллекции, отвечающий за хранение и управление элементами.
     */
    private final CollectionManager collectionManager;

    /**
     * Объект для вывода сообщений пользователю.
     */
    private final Console console;

    /**
     * Создаёт команду очистки коллекции.
     *
     * @param collectionManager менеджер коллекции
     * @param console объект для вывода сообщений в консоль
     */
    public ClearCommand(CollectionManager collectionManager, Console console) {
        super("clear", "очистить коллекцию");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    /**
     * Выполняет очистку коллекции.
     * <p>
     * Если пользователем переданы аргументы, выводится сообщение об ошибке,
     * поскольку команда {@code clear} не принимает параметров.
     * </p>
     * <p>
     * При корректном вызове удаляет все элементы из коллекции
     * и выводит сообщение об успешном выполнении операции.
     * </p>
     *
     * @param arguments массив аргументов команды
     */
    @Override
    public void execute(String[] arguments) {

        if (arguments.length > 0) {
            console.printError("Команда clear не принимает аргументы.");
            return;
        }

        collectionManager.clear();
        console.println("Коллекция успешно очищена.");
    }
}