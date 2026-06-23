package managers;

import commands.Command;
import utility.Console;
import utility.ReaderManager;

import java.util.*;

/**
 * Менеджер команд.
 * <p>
 * Отвечает за регистрацию, хранение, поиск и выполнение команд приложения.
 * Поддерживает интерактивный режим работы с пользователем.
 * </p>
 *
 * @author Виктория Родина
 */
public class CommandManager {

    /**
     * Коллекция зарегистрированных команд.
     * Ключом является имя команды.
     */
    private final Map<String, Command> commands = new LinkedHashMap<>();

    /**
     * Объект для вывода сообщений пользователю.
     */
    private final Console console;

    /**
     * Создаёт менеджер команд.
     *
     * @param console объект для вывода сообщений и ошибок
     */
    public CommandManager(Console console) {
        this.console = console;
    }

    /**
     * Регистрирует команду.
     *
     * @param command команда, которую необходимо добавить
     */
    public void register(Command command) {
        commands.put(command.getName(), command);
    }

    /**
     * Возвращает команду по её имени.
     *
     * @param name имя команды
     * @return объект команды или {@code null}, если команда не найдена
     */
    public Command getCommand(String name) {
        return commands.get(name);
    }

    /**
     * Выполняет команду по введённой строке.
     * <p>
     * Разбивает строку на имя команды и её аргументы,
     * после чего вызывает выполнение соответствующей команды.
     * </p>
     *
     * @param input строка, введённая пользователем
     */
    public void executeCommand(String input) {
        if (input == null || input.isBlank()) return;

        String[] parts = input.trim().split("\\s+");
        String commandName = parts[0];

        Command command = commands.get(commandName);

        if (command == null) {
            console.printError("Неизвестная команда: " + commandName);
            return;
        }

        String[] args = Arrays.copyOfRange(parts, 1, parts.length);

        try {
            command.execute(args);
        } catch (Exception e) {
            console.printError("Ошибка выполнения команды: " + e.getMessage());
        }
    }

    /**
     * Запускает интерактивный режим работы программы.
     * <p>
     * В цикле считывает команды пользователя и передаёт их
     * на выполнение. При получении конца ввода завершает работу.
     * </p>
     *
     * @param readerManager менеджер чтения пользовательского ввода
     */
    public void startInteractiveMode(ReaderManager readerManager) {
        while (true) {
            console.print("> ");
            String line = readerManager.readLine();

            if (line == null) {
                console.println("\nЗавершение работы...");
                break;
            }

            if (line.trim().isEmpty()) {
                continue;
            }

            executeCommand(line);
        }
    }

    /**
     * Возвращает все зарегистрированные команды.
     *
     * @return коллекция зарегистрированных команд
     */
    public Collection<Command> getCommands() {
        return commands.values();
    }
}


