package commands;

import managers.CollectionManager;
import models.LabWork;
import utility.Console;
import utility.LabWorkAsker;
import utility.ReaderManager;
/**
 * Команда insert.
 * добавляет новый элемент в коллекцию
 */
public class InsertCommand extends AbstractCommand {

    private final CollectionManager collectionManager;
    private final LabWorkAsker labWorkAsker;
    private final Console console;
    private final ReaderManager readerManager;  // ← добавили поле

    public InsertCommand(CollectionManager collectionManager,
                         LabWorkAsker labWorkAsker,
                         Console console,
                         ReaderManager readerManager) {
        super("insert", "добавить новый элемент по ключу");
        this.collectionManager = collectionManager;
        this.labWorkAsker = labWorkAsker;
        this.console = console;
        this.readerManager = readerManager;  // ← сохраняем
    }

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