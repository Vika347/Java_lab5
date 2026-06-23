package commands;

import managers.CollectionManager;
import utility.Console;

/**
 * Команда удаления элемента по ключу.
 * <p>
 * Удаляет элемент из коллекции по указанному ключу.
 * Если элемент с таким ключом отсутствует, пользователю выводится
 * сообщение об ошибке.
 * </p>
 *
 * <p>
 * Команда принимает один аргумент — целочисленный ключ элемента.
 * </p>
 *
 * @author Виктория Родина
 */
public class RemoveKeyCommand extends AbstractCommand {

    /**
     * Менеджер коллекции, содержащий элементы типа LabWork.
     */
    private final CollectionManager collectionManager;

    /**
     * Объект для вывода информации и сообщений об ошибках.
     */
    private final Console console;

    /**
     * Создаёт команду удаления элемента по ключу.
     *
     * @param collectionManager менеджер коллекции
     * @param console объект для вывода информации и сообщений об ошибках
     */
    public RemoveKeyCommand(CollectionManager collectionManager, Console console) {
        super("remove_key", "удалить элемент по ключу");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    /**
     * Выполняет удаление элемента из коллекции по заданному ключу.
     * <p>
     * Проверяет корректность количества аргументов и преобразует
     * переданную строку в целочисленный ключ.
     * </p>
     *
     * <p>
     * Если элемент с указанным ключом отсутствует, операция прерывается
     * и выводится сообщение об ошибке. В противном случае элемент
     * удаляется из коллекции.
     * </p>
     *
     * @param arguments массив аргументов команды, содержащий ключ элемента
     */
    @Override
    public void execute(String[] arguments) {

        if (arguments.length != 1) {
            console.printError("Использование: remove_key key");
            return;
        }

        Integer key;

        try {
            key = Integer.parseInt(arguments[0]);
        } catch (NumberFormatException e) {
            console.printError("Ключ должен быть числом.");
            return;
        }

        if (!collectionManager.containsKey(key)) {
            console.printError("Элемента с таким ключом не существует.");
            return;
        }

        collectionManager.remove(key);
        console.println("Элемент успешно удалён.");
    }
}