package commands;

import managers.CollectionManager;
import models.LabWork;
import utility.Console;
import utility.LabWorkAsker;

public class ReplaceIfLowerCommand extends AbstractCommand {

    private final CollectionManager collectionManager;
    private final LabWorkAsker labWorkAsker;
    private final Console console;

    public ReplaceIfLowerCommand(CollectionManager collectionManager, LabWorkAsker labWorkAsker, Console console) {
        super("replace_if_lower", "заменить значение по ключу, если новое значение меньше старого");
        this.collectionManager = collectionManager;
        this.labWorkAsker = labWorkAsker;
        this.console = console;
    }

    @Override
    public void execute(String[] arguments) {
        if (arguments.length != 1) {
            console.printError("Использование: replace_if_lower key");
            return;
        }

        Integer key;
        try {
            key = Integer.parseInt(arguments[0]);
        } catch (NumberFormatException e) {
            console.printError("Ключ должен быть числом");
            return;
        }

        if (!collectionManager.containsKey(key)) {
            console.printError("Элемента с ключом " + key + " не существует");
            return;
        }

        LabWork oldLab = collectionManager.get(key);
        LabWork newLab = labWorkAsker.askLabWork();

        if (newLab == null) {
            console.printError("Операция отменена");
            return;
        }

        if (newLab.compareTo(oldLab) < 0) {
            collectionManager.replaceIfLower(key, newLab);
            console.println("Элемент заменён (новый меньше старого)");
        } else {
            console.println("Новый элемент не меньше старого, замена не выполнена");
        }
    }
}