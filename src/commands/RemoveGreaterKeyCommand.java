package commands;

import managers.CollectionManager;
import utility.Console;

import java.util.Iterator;
import java.util.Map;

/**
 * Команда remove_greater_key.
 *
 * Удаляет из коллекции все элементы,
 * ключ которых больше заданного.
 */
public class RemoveGreaterKeyCommand extends AbstractCommand {

    private final CollectionManager collectionManager;
    private final Console console;

    public RemoveGreaterKeyCommand(CollectionManager collectionManager, Console console) {
        super("remove_greater_key", "удалить элементы с ключом больше заданного");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public void execute(String[] arguments) {

        if (arguments.length != 1) {
            console.printError("Использование: remove_greater_key key");
            return;
        }

        Integer key;

        try {
            key = Integer.parseInt(arguments[0]);
        } catch (NumberFormatException e) {
            console.printError("Ключ должен быть числом.");
            return;
        }

        boolean removed = false;

        Iterator<Map.Entry<Integer, models.LabWork>> iterator =
                collectionManager.getCollection().entrySet().iterator();

        while (iterator.hasNext()) {

            Map.Entry<Integer, models.LabWork> entry = iterator.next();

            if (entry.getKey() > key) {
                iterator.remove();
                removed = true;
            }
        }

        if (removed) {
            console.println("Элементы успешно удалены.");
        } else {
            console.println("Элементов для удаления не найдено.");
        }
    }
}