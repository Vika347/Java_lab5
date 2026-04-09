package commands;

import collection.CollectionManager;
import model.LabWork;
import utils.ConsoleReader;
import java.util.List;

/**
 * Команда filter_by_difficulty - выводит элементы с заданной сложностью.
 * @author Виктория Родина
 */
public class FilterByDifficultyCommand implements Command {

    /**
     * Выполнение команды filter_by_difficulty
     * @param args - аргументы команды (args[1] - сложность)
     * @param collectionManager - менеджер коллекции
     * @param consoleReader - читатель консоли (не используется)
     * @return true - продолжение работы программы
     */
    @Override
    public boolean execute(String[] args, CollectionManager collectionManager, ConsoleReader consoleReader) {
        if (args.length < 2) {
            System.out.println("Ошибка: укажите сложность. Пример: filter_by_difficulty HARD");
            System.out.println("Доступно: VERY_EASY, EASY, HARD, VERY_HARD, IMPOSSIBLE");
            return true;
        }

        String difficulty = args[1].toUpperCase(); //приведение к верхнему регистру

        // Проверяем, что сложность существует
        try {
            model.Difficulty.valueOf(difficulty);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: неверная сложность");
            return true;
        }
        // ФИЛЬТРАЦИЯ через CollectionManager
        List<LabWork> filtered = collectionManager.filterByDifficulty(difficulty);

        if (filtered.isEmpty()) {
            System.out.println("Элементы со сложностью " + difficulty + " не найдены");
        } else {
            System.out.println("Элементы со сложностью " + difficulty + ":");
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
        return "вывести элементы, значение поля difficulty которых равно заданному";
    }

    /**
     * Функция получения имени команды
     * @return имя команды
     */
    @Override
    public String getName() {
        return "filter_by_difficulty";
    }
}