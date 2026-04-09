package commands;

import collection.CollectionManager;
import model.LabWork;
import utils.ConsoleReader;

/**
 * Команда insert - добавляет новый элемент в коллекцию.
 * Формат: insert <ключ>
 * @author Виктория Родина
 */
public class InsertCommand implements Command {

    /**
     * Выполнение команды insert
     * @param args - аргументы команды (args[1] - ключ)
     * @param collectionManager - менеджер коллекции
     * @param consoleReader - читатель консоли для ввода данных
     * @return true - продолжение работы программы
     */
    @Override
    public boolean execute(String[] args, CollectionManager collectionManager, ConsoleReader consoleReader) {
        // Проверяем, указан ли ключ
        if (args.length < 2) {
            System.out.println("Ошибка: укажите ключ. Пример: insert 5");
            return true;
        }

        // Преобразуем ключ из строки в число
        Long key;
        try {
            key = Long.parseLong(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: ключ должен быть целым числом");
            return true;
        }

        // Проверяем, не существует ли уже элемент с таким ключом
        if (collectionManager.containsKey(key)) {
            System.out.println("Ошибка: элемент с ключом " + key + " уже существует");
            return true;
        }

        // Запрашиваем у пользователя данные лабораторной работы
        System.out.println("Введите данные лабораторной работы:");
        LabWork newLabWork = consoleReader.readLabWork();

        // Добавляем элемент в коллекцию
        collectionManager.put(key, newLabWork);

        System.out.println("Элемент добавлен. Ключ: " + key + ", ID: " + newLabWork.getId());

        return true;
    }

    /**
     * Функция получения описания команды
     * @return описание команды
     */
    @Override
    public String getDescription() {
        return "добавить новый элемент с заданным ключом";
    }

    /**
     * Функция получения имени команды
     * @return имя команды
     */
    @Override
    public String getName() {
        return "insert";
    }
}