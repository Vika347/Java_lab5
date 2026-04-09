import collection.CollectionManager;
import collection.FileManager;
import commands.*;
import utils.ConsoleReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Главный класс программы.
 * Точка входа в приложение.
 * @author Виктория Родина
 * @version 1.0
 */
public class Main {

    /** Имя переменной окружения, в которой хранится путь к файлу */
    private static final String ENV_VAR_NAME = "LAB_WORK_FILE";

    /**
     * Точка входа в программу.
     * @param args - аргументы командной строки (не используются)
     */
    public static void main(String[] args) {

        // 1. Читаем имя файла из переменной окружения
        String fileName = System.getenv(ENV_VAR_NAME);

        if (fileName == null || fileName.trim().isEmpty()) {
            System.err.println("ОШИБКА: Переменная окружения " + ENV_VAR_NAME + " не установлена!");
            System.err.println("Установите ее командой: export " + ENV_VAR_NAME + "=/путь/к/файлу.csv");
            System.exit(1);
        }

        System.out.println("Файл данных: " + fileName);

        // 2. Создаем компоненты программы
        CollectionManager collectionManager = new CollectionManager();
        FileManager fileManager = new FileManager(fileName);
        ConsoleReader consoleReader = new ConsoleReader();

        // 3. Загружаем данные из файла
        System.out.println("Загрузка данных...");
        fileManager.load(collectionManager);
        System.out.println("Загружено элементов: " + collectionManager.size());

        // 4. Создаем все команды
        Map<String, Command> commands = new HashMap<>();

        HelpCommand helpCommand = new HelpCommand(commands);
        commands.put("help", helpCommand);

        commands.put("info", new InfoCommand());
        commands.put("show", new ShowCommand());
        commands.put("insert", new InsertCommand());
        commands.put("update", new UpdateCommand());
        commands.put("remove_key", new RemoveKeyCommand());
        commands.put("clear", new ClearCommand());
        commands.put("save", new SaveCommand(fileManager));
        commands.put("exit", new ExitCommand());
        commands.put("remove_greater", new RemoveGreaterCommand());
        commands.put("replace_if_lower", new ReplaceIfLowerCommand());
        commands.put("remove_greater_key", new RemoveGreaterKeyCommand());
        commands.put("count_by_author", new CountByAuthorCommand());
        commands.put("filter_by_difficulty", new FilterByDifficultyCommand());
        commands.put("filter_less_than_author", new FilterLessThanAuthorCommand());
        commands.put("execute_script", new ExecuteScriptCommand(commands));

        // 5. Запускаем интерактивный цикл
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("\nПрограмма запущена. Введите 'help' для списка команд.\n");

        while (running) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                continue;
            }

            String[] parts = input.split("\\s+", 2);
            String commandName = parts[0];
            String[] commandArgs = parts;

            Command cmd = commands.get(commandName);

            if (cmd == null) {
                System.out.println("Неизвестная команда: " + commandName);
                System.out.println("Введите 'help' для списка команд.");
            } else {
                try {
                    running = cmd.execute(commandArgs, collectionManager, consoleReader);
                } catch (Exception e) {
                    System.out.println("Ошибка при выполнении команды: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }

        // 6. Завершение программы
        System.out.println("До свидания!");
        scanner.close();
        consoleReader.close();
    }
}