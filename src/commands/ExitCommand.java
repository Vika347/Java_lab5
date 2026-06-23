package commands;

import utility.Console;

/**
 * Команда завершения программы.
 * <p>
 * Прекращает выполнение приложения без сохранения текущего состояния
 * коллекции в файл.
 * </p>
 *
 * <p>
 * Команда не принимает аргументов.
 * </p>
 *
 * @author Виктория Родина
 */
public class ExitCommand extends AbstractCommand {

    /**
     * Объект для вывода сообщений пользователю.
     */
    private final Console console;

    /**
     * Создаёт команду завершения программы.
     *
     * @param console объект для вывода информации и сообщений об ошибках
     */
    public ExitCommand(Console console) {
        super("exit", "завершить программу без сохранения");
        this.console = console;
    }

    /**
     * Завершает работу приложения.
     * <p>
     * Проверяет отсутствие аргументов. Если пользователем переданы
     * дополнительные параметры, выводится сообщение об ошибке.
     * </p>
     *
     * <p>
     * При корректном вызове выводит сообщение о завершении программы
     * и завершает выполнение приложения с кодом завершения {@code 0}.
     * </p>
     *
     * @param arguments массив аргументов команды
     */
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