package commands;

import managers.CollectionManager;
import models.LabWork;
import utility.Console;
import utility.LabWorkAsker;

/**
 * Команда обновления элемента коллекции.
 * <p>
 * Заменяет элемент с указанным ключом новым объектом {@link LabWork}.
 * При этом значения полей {@code id} и {@code creationDate}
 * сохраняются из старого объекта и не изменяются.
 * </p>
 *
 * <p>
 * Команда принимает один аргумент — ключ обновляемого элемента.
 * </p>
 *
 * @author Виктория Родина
 */
public class UpdateCommand extends AbstractCommand {

    /**
     * Менеджер коллекции, содержащий объекты {@link LabWork}.
     */
    private final CollectionManager collectionManager;

    /**
     * Объект для создания и заполнения экземпляров {@link LabWork}.
     */
    private final LabWorkAsker labWorkAsker;

    /**
     * Объект для вывода информации и сообщений об ошибках.
     */
    private final Console console;

    /**
     * Создаёт команду обновления элемента коллекции.
     *
     * @param collectionManager менеджер коллекции
     * @param labWorkAsker объект для создания экземпляров {@link LabWork}
     * @param console объект для вывода информации и сообщений об ошибках
     */
    public UpdateCommand(CollectionManager collectionManager,
                         LabWorkAsker labWorkAsker,
                         Console console) {
        super("update", "обновить элемент по ключу");
        this.collectionManager = collectionManager;
        this.labWorkAsker = labWorkAsker;
        this.console = console;
    }

    /**
     * Выполняет обновление элемента коллекции по указанному ключу.
     * <p>
     * Проверяет корректность количества аргументов и существование
     * элемента с заданным ключом.
     * </p>
     *
     * <p>
     * Новый объект {@link LabWork} создаётся с помощью {@link LabWorkAsker}.
     * Поля {@code id} и {@code creationDate} копируются из старого объекта,
     * чтобы сохранить их первоначальные значения.
     * </p>
     *
     * <p>
     * Если создание нового объекта отменено, операция прерывается.
     * </p>
     *
     * @param arguments массив аргументов команды, содержащий ключ элемента
     */
    @Override
    public void execute(String[] arguments) {
        if (arguments.length != 1) {
            console.printError("Использование: update key");
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
            console.printError("Элемент с таким ключом не существует.");
            return;
        }

        LabWork newLab = labWorkAsker.askLabWork();

        if (newLab == null) {
            console.printError("Обновление отменено.");
            return;
        }

        LabWork oldLab = collectionManager.getCollection().get(key);
        newLab.setId(oldLab.getId());
        newLab.setCreationDate(oldLab.getCreationDate());

        collectionManager.update(key, newLab);
        console.println("Элемент успешно обновлён.");
    }
}