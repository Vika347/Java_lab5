package commands;

import managers.CollectionManager;
import utility.Console;

import java.util.Iterator;
import java.util.Map;

/**
 * Команда удаления элементов с ключом, превышающим заданный.
 * <p>
 * Удаляет из коллекции все элементы, ключ которых больше
 * указанного пользователем значения.
 * </p>
 *
 * <p>
 * Команда принимает один аргумент — целочисленный ключ.
 * </p>
 *
 * @author Виктория Родина
 */
public class RemoveGreaterKeyCommand extends AbstractCommand {

    /**
     * Менеджер коллекции, содержащий элементы типа LabWork.
     */
    private final CollectionManager collectionManager;

    /**
     * Объект для вывода информации и сообщений об ошибках.
     */
    private final Console console;

    /**
     * Создаёт команду удаления элементов с ключом,
     * превышающим заданное значение.
     *
     * @param collectionManager менеджер коллекции
     * @param console объект для вывода информации и сообщений об ошибках
     */
    public RemoveGreaterKeyCommand(CollectionManager collectionManager, Console console) {
        super("remove_greater_key", "удалить элементы с ключом больше заданного");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    /**
     * Выполняет удаление элементов, ключ которых больше указанного.
     * <p>
     * Проверяет корректность количества аргументов и преобразует
     * переданное значение в целое число.
     * </p>
     *
     * <p>
     * Проходит по коллекции с помощью итератора и удаляет все элементы,
     * ключ которых превышает заданное значение.
     * </p>
     *
     * <p>
     * После завершения операции выводит сообщение о результате удаления.
     * </p>
     *
     * @param arguments массив аргументов команды, содержащий ключ
     */
    @Override
    public void execute(String[] arguments) {

        if (arguments.length != 1) {
            console.printError("Использование: remove_greater_key key");
            return;
        }

        Integer key;

        try {
            key = Integer.parseInt(arguments[0]);
        } catch (NumberFormatException e) {
            console.printError("Ключ должен быть числом.");
            return;
        }

        boolean removed = false;

        Iterator<Map.Entry<Integer, models.LabWork>> iterator =
                collectionManager.getCollection().entrySet().iterator();

        while (iterator.hasNext()) {

            Map.Entry<Integer, models.LabWork> entry = iterator.next();

            if (entry.getKey() > key) {
                iterator.remove();
                removed = true;
            }
        }

        if (removed) {
            console.println("Элементы успешно удалены.");
        } else {
            console.println("Элементов для удаления не найдено.");
        }
    }
}