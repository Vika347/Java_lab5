package app;

import commands.*;
import managers.*;
import utility.*;

/**
 * Главный класс приложения.
 * <p>
 * Отвечает за запуск консольной программы, инициализацию основных менеджеров,
 * загрузку коллекции из файла, регистрацию доступных команд и запуск
 * интерактивного режима работы.
 * </p>
 *
 * <p>
 * Имя файла для загрузки и сохранения коллекции берётся из переменной
 * окружения {@code LAB_FILE}. Если переменная не задана или пуста,
 * приложение завершает работу с сообщением об ошибке.
 * </p>
 *
 * @author Виктория Родина
 */
public class Main {

    /**
     * Точка входа в приложение.
     * <p>
     * Метод выполняет следующие действия:
     * </p>
     * <ol>
     *     <li>Создаёт объект консоли для вывода сообщений.</li>
     *     <li>Получает имя файла из переменной окружения {@code LAB_FILE}.</li>
     *     <li>Создаёт и связывает менеджеры приложения.</li>
     *     <li>Загружает коллекцию из файла.</li>
     *     <li>Регистрирует все доступные команды.</li>
     *     <li>Запускает интерактивный режим обработки пользовательского ввода.</li>
     * </ol>
     *
     * <p>
     * В случае возникновения ошибки при запуске приложения пользователю
     * выводится сообщение об ошибке.
     * </p>
     *
     * @param args аргументы командной строки; в данной реализации не используются
     */
    public static void main(String[] args) {

        Console console = new Console();

        try {
            String fileName = System.getenv("LAB_FILE"); //Чтение переменной окружения

            if (fileName == null || fileName.isBlank()) {
                console.printError("Переменная окружения LAB_FILE не задана.");
                return;
            }

            // Инициализация менеджеров
            InputManager inputManager = new InputManager();
            ReaderManager readerManager = new ReaderManager(console, inputManager); //Нельзя создать ReaderManager без Console и InputManager
            FileManager fileManager = new FileManager(fileName, console);
            CollectionManager collectionManager = new CollectionManager(fileManager); //Нельзя создать CollectionManager без FileManager
            LabWorkAsker labWorkAsker = new LabWorkAsker(readerManager, console);
            ScriptManager scriptManager = new ScriptManager(readerManager, console);
            CommandManager commandManager = new CommandManager(console);

            // Загрузка коллекции
            collectionManager.loadCollection();

            // Регистрация команд
            commandManager.register(new HelpCommand(commandManager, console));
            commandManager.register(new InfoCommand(collectionManager, console));
            commandManager.register(new ShowCommand(collectionManager, console));
            commandManager.register(new InsertCommand(collectionManager, labWorkAsker, console, readerManager));
            commandManager.register(new UpdateCommand(collectionManager, labWorkAsker, console));
            commandManager.register(new RemoveKeyCommand(collectionManager, console));
            commandManager.register(new ClearCommand(collectionManager, console));
            commandManager.register(new SaveCommand(collectionManager, console));
            commandManager.register(new ExecuteScriptCommand(scriptManager, commandManager, console));
            commandManager.register(new ExitCommand(console));
            commandManager.register(new RemoveGreaterCommand(collectionManager, labWorkAsker, console));
            commandManager.register(new ReplaceIfLowerCommand(collectionManager, labWorkAsker, console));
            commandManager.register(new RemoveGreaterKeyCommand(collectionManager, console));
            commandManager.register(new CountByAuthorCommand(collectionManager, console));
            commandManager.register(new FilterByDifficultyCommand(collectionManager, console));
            commandManager.register(new FilterLessThanAuthorCommand(collectionManager, console));

            console.println("Приложение успешно запущено.");
            console.println("Введите help для списка команд.");

            commandManager.startInteractiveMode(readerManager); //запуск интерактивного режима

        } catch (Exception exception) {
            console.printError("Ошибка запуска приложения: " + exception.getMessage());
        }
    }
}