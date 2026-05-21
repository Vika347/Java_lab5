package commands;

import managers.CollectionManager;
import utility.Console;

/**
 * Команда очистки коллекции.
 *
 * Удаляет все элементы из Hashtable.
 */
public class ClearCommand extends AbstractCommand {

    private final CollectionManager collectionManager;
    private final Console console;
    //Конструктор
    public ClearCommand(CollectionManager collectionManager, Console console) {
        super("clear", "очистить коллекцию");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    /**
     * Очищает коллекцию.
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