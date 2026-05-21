package commands;

import managers.CollectionManager;
import models.LabWork;
import utility.Console;
import utility.LabWorkAsker;

/**
 * Команда update.
 *
 * Обновляет элемент коллекции по ключу.
 * id и creationDate не изменяются.
 */
public class UpdateCommand extends AbstractCommand {

    private final CollectionManager collectionManager;
    private final LabWorkAsker labWorkAsker;
    private final Console console;

    public UpdateCommand(CollectionManager collectionManager,
                         LabWorkAsker labWorkAsker,
                         Console console) {
        super("update", "обновить элемент по ключу");
        this.collectionManager = collectionManager;
        this.labWorkAsker = labWorkAsker;
        this.console = console;
    }

    @Override
    public void execute(String[] arguments) {
        if (arguments.length != 1) {
            console.printError("Использование: update key");
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
            console.printError("Элемент с таким ключом не существует.");
            return;
        }

        LabWork newLab = labWorkAsker.askLabWork();

        if (newLab == null) {
            console.printError("Обновление отменено.");
            return;
        }

        LabWork oldLab = collectionManager.getCollection().get(key);
        newLab.setId(oldLab.getId());
        newLab.setCreationDate(oldLab.getCreationDate());

        collectionManager.update(key, newLab);
        console.println("Элемент успешно обновлён.");
    }
}