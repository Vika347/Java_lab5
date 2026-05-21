package commands;

import managers.CollectionManager;
import utility.Console;

/**
 * Команда remove_key.
 *
 * Удаляет элемент коллекции по ключу.
 */
public class RemoveKeyCommand extends AbstractCommand {

    private final CollectionManager collectionManager;
    private final Console console;

    public RemoveKeyCommand(CollectionManager collectionManager, Console console) {
        super("remove_key", "удалить элемент по ключу");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public void execute(String[] arguments) {

        if (arguments.length != 1) {
            console.printError("Использование: remove_key key");
            return;
        }

        Integer key;

        try {
            key = Integer.parseInt(arguments[0]);
        } catch (NumberFormatException e) {
            console.printError("Ключ должен быть числом.");
            return;
        }

        if (!collectionManager.containsKey(key)) {
            console.printError("Элемента с таким ключом не существует.");
            return;
        }

        collectionManager.remove(key);
        console.println("Элемент успешно удалён.");
    }
}