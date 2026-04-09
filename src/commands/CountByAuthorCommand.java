package commands;

import collection.CollectionManager;
import utils.ConsoleReader;
import java.util.Arrays;

/**
 * Команда count_by_author - выводит количество элементов по автору.
 * @author Виктория Родина
 */
public class CountByAuthorCommand implements Command {

    /**
     * Выполнение команды count_by_author
     * @param args - аргументы команды (args[1...] - имя автора)
     * @param collectionManager - менеджер коллекции
     * @param consoleReader - читатель консоли (не используется)
     * @return true - продолжение работы программы
     */
    @Override
    public boolean execute(String[] args, CollectionManager collectionManager, ConsoleReader consoleReader) {
        // проверка наличия аргумента
        if (args.length < 2) {
            System.out.println("Ошибка: укажите имя автора. Пример: count_by_author Иванов");
            return true;
        }

        // объединение всех частей имени автора (поддержка имён из нескольких слов)
        String authorName = String.join(" ", Arrays.copyOfRange(args, 1, args.length));

        // подсчет через CollectionManager
        long count = collectionManager.countByAuthor(authorName);

        System.out.println("Количество работ автора '" + authorName + "': " + count);
        return true;
    }

    /**
     * Функция получения описания команды
     * @return описание команды
     */
    @Override
    public String getDescription() {
        return "вывести количество элементов, значение поля author которых равно заданному";
    }

    /**
     * Функция получения имени команды
     * @return имя команды
     */
    @Override
    public String getName() {
        return "count_by_author";
    }
}