package commands;

import managers.CommandManager;
import utility.Console;

/**
 * Команда вывода справочной информации.
 * <p>
 * Отображает список всех зарегистрированных команд и их краткие описания.
 * Используется для получения информации о доступных возможностях программы.
 * </p>
 *
 * <p>
 * Команда не принимает аргументов.
 * </p>
 *
 * @author Виктория Родина
 */
public class HelpCommand extends AbstractCommand {

    /**
     * Менеджер команд, содержащий список всех доступных команд.
     */
    private final CommandManager commandManager;

    /**
     * Объект для вывода информации и сообщений об ошибках.
     */
    private final Console console;

    /**
     * Создаёт команду вывода справки.
     *
     * @param commandManager менеджер команд
     * @param console объект для вывода информации и сообщений об ошибках
     */
    public HelpCommand(CommandManager commandManager, Console console) {
        super("help", "вывести список команд");
        this.commandManager = commandManager;
        this.console = console;
    }

    /**
     * Выполняет вывод списка всех доступных команд.
     * <p>
     * Если пользователем переданы аргументы, выводится сообщение об ошибке,
     * так как команда {@code help} не принимает параметров.
     * </p>
     *
     * <p>
     * Для каждой зарегистрированной команды выводятся её имя и описание.
     * </p>
     *
     * @param arguments массив аргументов команды
     */
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