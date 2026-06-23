package commands;

import managers.CollectionManager;
import models.LabWork;
import utility.Console;
import utility.LabWorkAsker;
import utility.ReaderManager;

/**
 * Команда добавления нового элемента в коллекцию.
 * <p>
 * Создаёт новый объект {@link LabWork} и добавляет его в коллекцию
 * по указанному ключу.
 * </p>
 *
 * <p>
 * Команда принимает один аргумент — ключ нового элемента.
 * В случае интерактивного режима поля объекта вводятся пользователем,
 * а при выполнении скрипта данные считываются из файла скрипта.
 * </p>
 *
 * @author Виктория Родина
 */
public class InsertCommand extends AbstractCommand {

    /**
     * Менеджер коллекции, отвечающий за хранение объектов.
     */
    private final CollectionManager collectionManager;

    /**
     * Объект, отвечающий за создание и заполнение экземпляров {@link LabWork}.
     */
    private final LabWorkAsker labWorkAsker;

    /**
     * Объект для вывода информации и сообщений об ошибках.
     */
    private final Console console;

    /**
     * Менеджер чтения пользовательского ввода и скриптов.
     */
    private final ReaderManager readerManager;

    /**
     * Создаёт команду добавления элемента в коллекцию.
     *
     * @param collectionManager менеджер коллекции
     * @param labWorkAsker объект для создания экземпляров {@link LabWork}
     * @param console объект для вывода информации и сообщений об ошибках
     * @param readerManager менеджер чтения пользовательского ввода
     */
    public InsertCommand(CollectionManager collectionManager,
                         LabWorkAsker labWorkAsker,
                         Console console,
                         ReaderManager readerManager) {
        super("insert", "добавить новый элемент по ключу");
        this.collectionManager = collectionManager;
        this.labWorkAsker = labWorkAsker;
        this.console = console;
        this.readerManager = readerManager;
    }

    /**
     * Выполняет добавление нового элемента в коллекцию.
     * <p>
     * Проверяет корректность переданного ключа и отсутствие элемента
     * с таким ключом в коллекции. Затем создаёт новый объект
     * {@link LabWork} с помощью {@link LabWorkAsker}.
     * </p>
     *
     * <p>
     * Если команда выполняется из скрипта, объект создаётся
     * в неинтерактивном режиме. После завершения создания
     * интерактивный режим восстанавливается.
     * </p>
     *
     * @param arguments массив аргументов команды, содержащий ключ нового элемента
     */
    @Override
    public void execute(String[] arguments) {
        if (arguments.length != 1) {
            console.printError("Использование: insert key");
            return;
        }

        Integer key;
        try {
            key = Integer.parseInt(arguments[0]);
        } catch (NumberFormatException e) {
            console.printError("Ключ должен быть числом.");
            return;
        }

        if (collectionManager.containsKey(key)) {
            console.printError("Элемент с таким ключом уже существует.");
            return;
        }

        // Автоматически определяем режим (скрипт или консоль)
        if (readerManager != null && readerManager.isScriptMode()) {
            labWorkAsker.setInteractiveMode(false);
        }

        LabWork labWork = labWorkAsker.askLabWork();

        // Возвращаем интерактивный режим обратно
        labWorkAsker.setInteractiveMode(true);

        if (labWork == null) {
            console.printError("Создание объекта отменено.");
            return;
        }

        collectionManager.insert(key, labWork);
        console.println("Элемент успешно добавлен.");
    }
}