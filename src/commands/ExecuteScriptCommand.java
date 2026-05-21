package commands;

import managers.CommandManager;
import managers.ScriptManager;
import utility.Console;

/**
 * Команда execute_script.
 */
public class ExecuteScriptCommand extends AbstractCommand {

    private final ScriptManager scriptManager;
    private final CommandManager commandManager;
    private final Console console;

    public ExecuteScriptCommand(ScriptManager scriptManager,
                                CommandManager commandManager,
                                Console console) {
        super("execute_script", "выполнить скрипт из файла");
        this.scriptManager = scriptManager;
        this.commandManager = commandManager;
        this.console = console;
    }

    @Override
    public void execute(String[] arguments) {
        if (arguments.length != 1) {
            console.printError("Использование: execute_script file_name");
            return;
        }

        String fileName = arguments[0];

        if (!scriptManager.startScript(fileName)) {
            return;
        }

        String line;
        while ((line = scriptManager.readLine()) != null) {
            if (line.trim().isEmpty()) continue;
            commandManager.executeCommand(line);
        }

        scriptManager.stopScript();
        console.println("Скрипт " + fileName + " выполнен.");
    }
}