package commands;

import managers.CommandManager;
import managers.ScriptManager;
import utility.Console;

/**
 * Команда выполнения скрипта.
 * <p>
 * Считывает команды из указанного файла и выполняет их последовательно.
 * Файл должен содержать команды в том же формате, что и при вводе
 * в интерактивном режиме.
 * </p>
 *
 * <p>
 * Команда принимает один аргумент — имя файла со скриптом.
 * </p>
 *
 * @author Виктория Родина
 */
public class ExecuteScriptCommand extends AbstractCommand {

    /**
     * Менеджер скриптов, отвечающий за чтение команд из файла.
     */
    private final ScriptManager scriptManager;

    /**
     * Менеджер команд, используемый для выполнения команд,
     * считанных из скрипта.
     */
    private final CommandManager commandManager;

    /**
     * Объект для вывода сообщений пользователю.
     */
    private final Console console;

    /**
     * Создаёт команду выполнения скрипта.
     *
     * @param scriptManager менеджер скриптов
     * @param commandManager менеджер команд
     * @param console объект для вывода информации и сообщений об ошибках
     */
    public ExecuteScriptCommand(ScriptManager scriptManager,
                                CommandManager commandManager,
                                Console console) {
        super("execute_script", "выполнить скрипт из файла");
        this.scriptManager = scriptManager;
        this.commandManager = commandManager;
        this.console = console;
    }

    /**
     * Выполняет команды, содержащиеся в указанном файле.
     * <p>
     * Проверяет корректность количества аргументов. Если имя файла
     * не указано или передано больше одного аргумента, выводится
     * сообщение об ошибке.
     * </p>
     *
     * <p>
     * При успешном открытии файла последовательно считывает строки
     * и передаёт их менеджеру команд для выполнения. Пустые строки
     * игнорируются.
     * </p>
     *
     * <p>
     * После завершения выполнения скрипта закрывает его и выводит
     * сообщение об успешном завершении.
     * </p>
     *
     * @param arguments массив аргументов команды, содержащий имя файла скрипта
     */
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