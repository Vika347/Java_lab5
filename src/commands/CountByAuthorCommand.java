package commands;

import managers.CollectionManager;
import models.LabWork;
import utility.Console;

/**
 * Команда count_by_author.
 *
 * Считает количество элементов, у которых автор совпадает с заданным.
 */
public class CountByAuthorCommand extends AbstractCommand {

    private final CollectionManager collectionManager;
    private final Console console;

    public CountByAuthorCommand(CollectionManager collectionManager, Console console) {
        super("count_by_author", "посчитать количество элементов с заданным автором");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public void execute(String[] arguments) {

        if (arguments.length != 1) {
            console.printError("Использование: count_by_author author");
            return;
        }

        String authorName = arguments[0];

        int count = 0;

        for (LabWork lab : collectionManager.getCollection().values()) {

            if (lab.getAuthor() != null
                    && lab.getAuthor().getName() != null
                    && lab.getAuthor().getName().equals(authorName)) {
                count++;
            }
        }

        console.println("Количество элементов с автором '" + authorName + "': " + count);
    }
}