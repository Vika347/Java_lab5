package commands;

import managers.CollectionManager;
import models.LabWork;
import utility.Console;
import utility.LabWorkAsker;

/**
 * Команда замены элемента по ключу, если новое значение меньше старого.
 * <p>
 * Создаёт новый объект {@link LabWork} и заменяет существующий элемент
 * коллекции с указанным ключом только в том случае, если новый объект
 * меньше старого согласно естественному порядку сравнения.
 * </p>
 *
 * <p>
 * Команда принимает один аргумент — ключ элемента.
 * </p>
 *
 * @author Виктория Родина
 */
public class ReplaceIfLowerCommand extends AbstractCommand {

    /**
     * Менеджер коллекции, содержащий объекты {@link LabWork}.
     */
    private final CollectionManager collectionManager;

    /**
     * Объект для создания экземпляров {@link LabWork}.
     */
    private final LabWorkAsker labWorkAsker;

    /**
     * Объект для вывода информации и сообщений об ошибках.
     */
    private final Console console;

    /**
     * Создаёт команду условной замены элемента.
     *
     * @param collectionManager менеджер коллекции
     * @param labWorkAsker объект для создания экземпляров {@link LabWork}
     * @param console объект для вывода информации и сообщений об ошибках
     */
    public ReplaceIfLowerCommand(CollectionManager collectionManager,
                                 LabWorkAsker labWorkAsker,
                                 Console console) {
        super("replace_if_lower",
                "заменить значение по ключу, если новое значение меньше старого");
        this.collectionManager = collectionManager;
        this.labWorkAsker = labWorkAsker;
        this.console = console;
    }

    /**
     * Выполняет замену элемента по ключу.
     * <p>
     * Проверяет корректность аргументов и существование элемента
     * с указанным ключом. Затем запрашивает новый объект
     * {@link LabWork}.
     * </p>
     *
     * <p>
     * Если новый объект меньше старого в соответствии с методом
     * {@link LabWork#compareTo(LabWork)}, выполняется замена.
     * В противном случае элемент остаётся без изменений.
     * </p>
     *
     * @param arguments массив аргументов команды, содержащий ключ элемента
     */
    @Override
    public void execute(String[] arguments) {
        if (arguments.length != 1) {
            console.printError("Использование: replace_if_lower key");
            return;
        }

        Integer key;
        try {
            key = Integer.parseInt(arguments[0]);
        } catch (NumberFormatException e) {
            console.printError("Ключ должен быть числом");
            return;
        }

        if (!collectionManager.containsKey(key)) {
            console.printError("Элемента с ключом " + key + " не существует");
            return;
        }

        LabWork oldLab = collectionManager.get(key);
        LabWork newLab = labWorkAsker.askLabWork();

        if (newLab == null) {
            console.printError("Операция отменена");
            return;
        }

        if (newLab.compareTo(oldLab) < 0) {
            collectionManager.replaceIfLower(key, newLab);
            console.println("Элемент заменён (новый меньше старого)");
        } else {
            console.println("Новый элемент не меньше старого, замена не выполнена");
        }
    }
}