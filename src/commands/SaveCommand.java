package commands;

import managers.CollectionManager;
import utility.Console;

/**
 * Команда save.
 *
 * Сохраняет коллекцию в файл через FileManager.
 */
public class SaveCommand extends AbstractCommand {

    private final CollectionManager collectionManager;
    private final Console console;

    public SaveCommand(CollectionManager collectionManager, Console console) {
        super("save", "сохранить коллекцию в файл");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public void execute(String[] arguments) {

        if (arguments.length != 0) {
            console.printError("save не принимает аргументы.");
            return;
        }

        try {
            collectionManager.saveCollection();
            console.println("Коллекция успешно сохранена.");
        } catch (Exception e) {
            console.printError("Ошибка сохранения: " + e.getMessage());
        }
    }
}