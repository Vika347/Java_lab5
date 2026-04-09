package commands;

import collection.CollectionManager;
import utils.ConsoleReader;

/**
 * Команда remove_key - удаляет элемент по ключу.
 * Формат: remove_key <ключ>
 * @author Виктория Родина
 */
public class RemoveKeyCommand implements Command {

    /**
     * Выполнение команды remove_key
     * @param args - аргументы команды (args[1] - ключ)
     * @param collectionManager - менеджер коллекции
     * @param consoleReader - читатель консоли (не используется)
     * @return true - продолжение работы программы
     */
    @Override
    public boolean execute(String[] args, CollectionManager collectionManager, ConsoleReader consoleReader) {
        // Проверяем, указан ли ключ
        if (args.length < 2) {
            System.out.println("Ошибка: укажите ключ. Пример: remove_key 5");
            return true;
        }

        // Преобразуем ключ в число
        Long key;
        try {
            key = Long.parseLong(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: ключ должен быть целым числом");
            return true;
        }

        // Проверяем, существует ли элемент с таким ключом
        if (!collectionManager.containsKey(key)) {
            System.out.println("Ошибка: элемент с ключом " + key + " не найден");
            return true;
        }

        // Удаляем элемент
        collectionManager.remove(key);
        System.out.println("Элемент с ключом " + key + " удален");

        return true;
    }

    /**
     * Функция получения описания команды
     * @return описание команды
     */
    @Override
    public String getDescription() {
        return "удалить элемент по ключу";
    }

    /**
     * Функция получения имени команды
     * @return имя команды
     */
    @Override
    public String getName() {
        return "remove_key";
    }
}