package commands;

import managers.CollectionManager;
import models.LabWork;
import utility.Console;

/**
 * Команда show.
 *
 * Выводит все элементы коллекции в отсортированном порядке.
 */
public class ShowCommand extends AbstractCommand {

    private final CollectionManager collectionManager;
    private final Console console;

    public ShowCommand(CollectionManager collectionManager, Console console) {
        super("show", "вывести все элементы коллекции");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public void execute(String[] arguments) {

        if (arguments.length > 0) {
            console.printError("Команда show не принимает аргументы.");
            return;
        }

        if (collectionManager.size() == 0) {
            console.println("Коллекция пуста.");
            return;
        }

        for (LabWork lab : collectionManager.getSorted()) {
            console.println(lab.toString());
        }
    }
}