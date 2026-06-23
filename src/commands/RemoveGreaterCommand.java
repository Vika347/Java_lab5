package commands;

import managers.CollectionManager;
import models.LabWork;
import utility.Console;
import utility.LabWorkAsker;

/**
 * Команда удаления элементов, превышающих заданный.
 * <p>
 * Удаляет из коллекции все объекты {@link LabWork},
 * которые больше указанного пользователем объекта
 * согласно естественному порядку сравнения.
 * </p>
 *
 * <p>
 * Команда не принимает аргументов. Объект для сравнения
 * вводится пользователем по полям с помощью {@link LabWorkAsker}.
 * </p>
 *
 * @author Виктория Родина
 */
public class RemoveGreaterCommand extends AbstractCommand {

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
     * Создаёт команду удаления элементов, превышающих заданный.
     *
     * @param collectionManager менеджер коллекции
     * @param labWorkAsker объект для создания экземпляров {@link LabWork}
     * @param console объект для вывода информации и сообщений об ошибках
     */
    public RemoveGreaterCommand(CollectionManager collectionManager,
                                LabWorkAsker labWorkAsker,
                                Console console) {
        super("remove_greater", "удалить элементы, превышающие заданный");
        this.collectionManager = collectionManager;
        this.labWorkAsker = labWorkAsker;
        this.console = console;
    }

    /**
     * Выполняет удаление всех элементов коллекции,
     * превышающих заданный объект.
     * <p>
     * Проверяет отсутствие аргументов. Затем запрашивает
     * у пользователя объект {@link LabWork}, который используется
     * в качестве эталона для сравнения.
     * </p>
     *
     * <p>
     * Если создание объекта было отменено, операция прерывается.
     * В противном случае из коллекции удаляются все элементы,
     * превосходящие введённый объект.
     * </p>
     *
     * @param arguments массив аргументов команды
     */
    @Override
    public void execute(String[] arguments) {

        if (arguments.length != 0) {
            console.printError("remove_greater не принимает аргументы.");
            return;
        }

        LabWork target = labWorkAsker.askLabWork();

        if (target == null) {
            console.printError("Операция отменена.");
            return;
        }

        collectionManager.removeGreater(target);

        console.println("Элементы, превышающие заданный, удалены.");
    }
}