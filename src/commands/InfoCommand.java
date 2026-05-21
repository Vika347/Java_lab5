package commands;

import managers.CollectionManager;
import utility.Console;

/**
 * Команда info.
 * Выводит информацию о коллекции.
 */
public class InfoCommand extends AbstractCommand {

    private final CollectionManager collectionManager;
    private final Console console;

    public InfoCommand(CollectionManager collectionManager, Console console) {
        super("info", "информация о коллекции");
        this.collectionManager = collectionManager;
        this.console = console;
    }

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