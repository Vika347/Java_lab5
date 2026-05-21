package commands;

import managers.CollectionManager;
import models.LabWork;
import utility.Console;
import utility.LabWorkAsker;

/**
 * Команда remove_greater.
 *
 * Удаляет все элементы коллекции,
 * которые больше заданного LabWork.
 */
public class RemoveGreaterCommand extends AbstractCommand {

    private final CollectionManager collectionManager;
    private final LabWorkAsker labWorkAsker;
    private final Console console;

    public RemoveGreaterCommand(CollectionManager collectionManager,
                                LabWorkAsker labWorkAsker,
                                Console console) {
        super("remove_greater", "удалить элементы, превышающие заданный");
        this.collectionManager = collectionManager;
        this.labWorkAsker = labWorkAsker;
        this.console = console;
    }

    @Override
    public void execute(String[] arguments) {

        if (arguments.length != 0) {
            console.printError("remove_greater не принимает аргументы.");
            return;
        }

        LabWork target = labWorkAsker.askLabWork();

        if (target == null) {
            console.printError("Операция отменена.");
            return;
        }

        collectionManager.removeGreater(target);

        console.println("Элементы, превышающие заданный, удалены.");
    }
}