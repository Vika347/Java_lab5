package commands;

import collection.CollectionManager;
import model.LabWork;
import utils.ConsoleReader;
import java.util.List;
import java.util.Arrays;

/**
 * Команда filter_less_than_author - выводит элементы с автором меньше заданного.
 * Сравнение происходит лексикографически (по алфавиту).
 * @author Виктория Родина
 */
public class FilterLessThanAuthorCommand implements Command {

    /**
     * Выполнение команды filter_less_than_author
     * @param args - аргументы команды (args[1...] - имя автора)
     * @param collectionManager - менеджер коллекции
     * @param consoleReader - читатель консоли (не используется)
     * @return true - продолжение работы программы
     */
    @Override
    public boolean execute(String[] args, CollectionManager collectionManager, ConsoleReader consoleReader) {
        if (args.length < 2) {
            System.out.println("Ошибка: укажите имя автора. Пример: filter_less_than_author Иванов");
            return true;
        }

        // объединение всех частей имени автора (поддержка имён из нескольких слов)
        String authorName = String.join(" ", Arrays.copyOfRange(args, 1, args.length));

        List<LabWork> filtered = collectionManager.filterLessThanAuthor(authorName);

        if (filtered.isEmpty()) {
            System.out.println("Элементы с автором меньше '" + authorName + "' не найдены");
        } else {
            System.out.println("Элементы с автором меньше '" + authorName + "':");
            for (LabWork lw : filtered) {
                System.out.println(lw);
            }
            System.out.println("Всего: " + filtered.size());
        }

        return true;
    }

    /**
     * Функция получения описания команды
     * @return описание команды
     */
    @Override
    public String getDescription() {
        return "вывести элементы, значение поля author которых меньше заданного";
    }

    /**
     * Функция получения имени команды
     * @return имя команды
     */
    @Override
    public String getName() {
        return "filter_less_than_author";
    }
}