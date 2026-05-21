package commands;

import managers.CollectionManager;
import models.Difficulty;
import models.LabWork;
import utility.Console;

/**
 * Команда filter_by_difficulty.
 *
 * Выводит элементы коллекции с заданной сложностью.
 */
public class FilterByDifficultyCommand extends AbstractCommand {

    private final CollectionManager collectionManager;
    private final Console console;

    public FilterByDifficultyCommand(CollectionManager collectionManager, Console console) {
        super("filter_by_difficulty", "вывести элементы с заданной сложностью");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public void execute(String[] arguments) {

        if (arguments.length != 1) {
            console.printError("Использование: filter_by_difficulty DIFFICULTY");
            return;
        }

        Difficulty difficulty;

        try {
            difficulty = Difficulty.valueOf(arguments[0]);
        } catch (IllegalArgumentException e) {
            console.printError("Неверное значение Difficulty.");
            return;
        }

        boolean found = false;

        for (LabWork lab : collectionManager.getCollection().values()) {

            if (lab.getDifficulty() == difficulty) {
                console.println(lab.toString());
                found = true;
            }
        }

        if (!found) {
            console.println("Элементы с данной сложностью не найдены.");
        }
    }
}