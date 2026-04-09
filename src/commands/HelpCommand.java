package commands;

import collection.CollectionManager;
import utils.ConsoleReader;
import java.util.*;

/**
 * Команда help - выводит список всех доступных команд в заданном порядке.
 * @author Виктория Родина
 */
public class HelpCommand implements Command {

    /** Поле карта всех доступных команд */
    private Map<String, Command> commands;

    /**
     * Конструктор - создание нового объекта команды help
     * @param commands - карта всех команд
     */
    public HelpCommand(Map<String, Command> commands) {
        this.commands = commands;
    }

    /**
     * Выполнение команды help
     * @param args - аргументы команды (не используются)
     * @param collectionManager - менеджер коллекции (не используется)
     * @param consoleReader - читатель консоли (не используется)
     * @return true - продолжение работы программы
     */
    @Override
    public boolean execute(String[] args, CollectionManager collectionManager, ConsoleReader consoleReader) {
        System.out.println("\nДоступные команды:");

        // Задаем порядок команд
        List<String> order = Arrays.asList(
                "help",
                "info",
                "show",
                "insert",
                "update",
                "remove_key",
                "clear",
                "save",
                "execute_script",
                "exit",
                "remove_greater",
                "replace_if_lower",
                "remove_greater_key",
                "count_by_author",
                "filter_by_difficulty",
                "filter_less_than_author"
        );

        // Выводим команды в заданном порядке
        for (String cmdName : order) {
            Command cmd = commands.get(cmdName);
            if (cmd != null) {
                System.out.println(cmd.getName() + " : " + cmd.getDescription());
            }
        }
        return true;
    }

    /**
     * Функция получения описания команды
     * @return описание команды
     */
    @Override
    public String getDescription() {
        return "вывести справку по доступным командам";
    }

    /**
     * Функция получения имени команды
     * @return имя команды
     */
    @Override
    public String getName() {
        return "help";
    }
}