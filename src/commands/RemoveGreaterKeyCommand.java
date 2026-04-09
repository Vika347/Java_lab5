package commands;

import collection.CollectionManager;
import utils.ConsoleReader;

/**
 * Команда remove_greater_key - удаляет все элементы, ключ которых больше заданного.
 * Формат: remove_greater_key <ключ>
 * @author Виктория Родина
 */
public class RemoveGreaterKeyCommand implements Command {

    /**
     * Выполнение команды remove_greater_key
     * @param args - аргументы команды (args[1] - ключ)
     * @param collectionManager - менеджер коллекции
     * @param consoleReader - читатель консоли (не используется)
     * @return true - продолжение работы программы
     */
    @Override
    public boolean execute(String[] args, CollectionManager collectionManager, ConsoleReader consoleReader) {
        if (args.length < 2) { //проверка аргументов
            System.out.println("Ошибка: укажите ключ. Пример: remove_greater_key 5");
            return true;
        }
       // парсинг ключа
        try {
            Long key = Long.parseLong(args[1]);

            int beforeSize = collectionManager.size();
            collectionManager.removeGreaterKey(key);
            int removed = beforeSize - collectionManager.size();

            System.out.println("Удалено элементов с ключом больше " + key + ": " + removed);

        } catch (NumberFormatException e) {
            System.out.println("Ошибка: ключ должен быть целым числом");
        }

        return true;
    }

    /**
     * Функция получения описания команды
     * @return описание команды
     */
    @Override
    public String getDescription() {
        return "удалить все элементы, ключ которых превышает заданный";
    }

    /**
     * Функция получения имени команды
     * @return имя команды
     */
    @Override
    public String getName() {
        return "remove_greater_key";
    }
}