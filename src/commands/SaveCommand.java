package commands;

import managers.CollectionManager;
import utility.Console;

/**
 * Команда сохранения коллекции в файл.
 * <p>
 * Выполняет сохранение текущего состояния коллекции
 * с помощью {@link managers.FileManager}.
 * </p>
 *
 * <p>
 * Команда не принимает аргументов.
 * </p>
 *
 * @author Виктория Родина
 */
public class SaveCommand extends AbstractCommand {

    /**
     * Менеджер коллекции, содержащий объекты {@code LabWork}.
     */
    private final CollectionManager collectionManager;

    /**
     * Объект для вывода информации и сообщений об ошибках.
     */
    private final Console console;

    /**
     * Создаёт команду сохранения коллекции.
     *
     * @param collectionManager менеджер коллекции
     * @param console объект для вывода информации и сообщений об ошибках
     */
    public SaveCommand(CollectionManager collectionManager, Console console) {
        super("save", "сохранить коллекцию в файл");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    /**
     * Выполняет сохранение коллекции в файл.
     * <p>
     * Проверяет отсутствие аргументов. Если пользователь передал
     * дополнительные параметры, выводится сообщение об ошибке.
     * </p>
     *
     * <p>
     * При успешном сохранении выводится сообщение о завершении операции.
     * В случае возникновения исключения выводится сообщение об ошибке.
     * </p>
     *
     * @param arguments массив аргументов команды
     */
    @Override
    public void execute(String[] arguments) {

        if (arguments.length != 0) {
            console.printError("save не принимает аргументы.");
            return;
        }

        try {
            collectionManager.saveCollection();
            console.println("Коллекция успешно сохранена.");
        } catch (Exception e) {
            console.printError("Ошибка сохранения: " + e.getMessage());
        }
    }
}