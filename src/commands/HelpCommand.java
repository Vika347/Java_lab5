package commands;

import managers.CommandManager;
import utility.Console;

/**
 * Команда help.
 * Выводит список всех доступных команд.
 */
public class HelpCommand extends AbstractCommand {

    private final CommandManager commandManager;
    private final Console console;

    public HelpCommand(CommandManager commandManager, Console console) {
        super("help", "вывести список команд");
        this.commandManager = commandManager;
        this.console = console;
    }

    @Override
    public void execute(String[] arguments) {

        if (arguments.length > 0) {
            console.printError("help не принимает аргументы.");
            return;
        }

        for (Command command : commandManager.getCommands()) {
            console.println(command.getName() + " : " + command.getDescription());
        }
    }
}