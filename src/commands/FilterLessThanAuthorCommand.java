package commands;

import managers.CollectionManager;
import models.LabWork;
import utility.Console;

/**
 * Команда filter_less_than_author.
 *
 * Выводит элементы, у которых имя автора меньше заданного (лексикографически).
 */
public class FilterLessThanAuthorCommand extends AbstractCommand {

    private final CollectionManager collectionManager;
    private final Console console;

    public FilterLessThanAuthorCommand(CollectionManager collectionManager, Console console) {
        super("filter_less_than_author", "вывести элементы, автор которых меньше заданного");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public void execute(String[] arguments) {

        if (arguments.length != 1) {
            console.printError("Использование: filter_less_than_author authorName");
            return;
        }

        String authorName = arguments[0];
        boolean found = false;

        for (LabWork lab : collectionManager.getCollection().values()) {

            if (lab.getAuthor() != null
                    && lab.getAuthor().getName() != null
                    && lab.getAuthor().getName().compareTo(authorName) < 0) {

                console.println(lab.toString());
                found = true;
            }
        }

        if (!found) {
            console.println("Элементы не найдены.");
        }
    }
}