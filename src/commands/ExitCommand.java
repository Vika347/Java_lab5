package commands;

import utility.Console;

/**
 * Команда exit.
 *
 * Завершает работу приложения без сохранения.
 */
public class ExitCommand extends AbstractCommand {

    private final Console console;

    public ExitCommand(Console console) {
        super("exit", "завершить программу без сохранения");
        this.console = console;
    }

    @Override
    public void execute(String[] arguments) {

        if (arguments.length > 0) {
            console.printError("exit не принимает аргументы.");
            return;
        }

        console.println("Завершение программы...");
        System.exit(0);
    }
}