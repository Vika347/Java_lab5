package managers;

import commands.Command;
import utility.Console;
import utility.ReaderManager;

import java.util.*;

/**
 * Менеджер команд.
 * Он управляет командами: регистрирует их, ищет по имени и выполняет
 */
public class CommandManager {

    private final Map<String, Command> commands = new LinkedHashMap<>();
    private final Console console;

    //Конструктор
    public CommandManager(Console console) {
        this.console = console;
    }

    //Регистрация команд(сохраняет команду в Map под её именем)
    public void register(Command command) {
        commands.put(command.getName(), command);
    }

    //Получение команды по имени
    public Command getCommand(String name) {
        return commands.get(name);
    }

    //Выполнение команды по строке ввода
    public void executeCommand(String input) {
        if (input == null || input.isBlank()) return;

        //Разбивает строку на части по пробелам.
        String[] parts = input.trim().split("\\s+");
        String commandName = parts[0];
        //Ищет команду в Map по имени
        Command command = commands.get(commandName);

        if (command == null) {
            console.printError("Неизвестная команда: " + commandName);
            return; //Если команда не найдена — выводим ошибку и выходим.
        }

        // Копирует все части, начиная со второй (индекс 1), в отдельный массив — это аргументы команды
        String[] args = Arrays.copyOfRange(parts, 1, parts.length);

        try {
            command.execute(args);
        } catch (Exception e) {
            console.printError("Ошибка выполнения команды: " + e.getMessage());
        }
    }

    /**
     * Интерактивный режим работы.
     */
    //Запускает бесконечный цикл, который
    public void startInteractiveMode(ReaderManager readerManager) {
        while (true) {
            console.print("> ");
            String line = readerManager.readLine();

            // Ctrl+D — завершаем программу
            if (line == null) {
                console.println("\nЗавершение работы...");
                break;
            }

            if (line.trim().isEmpty()) {
                continue;
            }

            executeCommand(line);//Передаёт введённую строку методу executeCommand() для выполнения.
        }
    }

    //Получение всех команд
    public Collection<Command> getCommands() {
        return commands.values();
    }
}