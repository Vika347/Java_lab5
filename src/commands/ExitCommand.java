package commands;

import collection.CollectionManager;
import utils.ConsoleReader;

/**
 * Команда exit - завершает программу.
 * Важно: изменения не сохраняются автоматически!
 * Необходимо предварительно выполнить команду save.
 * @author Виктория Родина
 */
public class ExitCommand implements Command {

    /**
     * Выполнение команды exit
     * @param args - аргументы команды (не используются)
     * @param collectionManager - менеджер коллекции (не используется)
     * @param consoleReader - читатель консоли (не используется)
     * @return false - завершение работы программы
     */
    @Override
    public boolean execute(String[] args, CollectionManager collectionManager, ConsoleReader consoleReader) {
        System.out.println("Завершение программы...");
        System.out.println("Внимание: изменения не сохранены! Используйте save перед exit.");

        return false;
    }

    /**
     * Функция получения описания команды
     * @return описание команды
     */
    @Override
    public String getDescription() {
        return "завершить программу (без сохранения)";
    }

    /**
     * Функция получения имени команды
     * @return имя команды
     */
    @Override
    public String getName() {
        return "exit";
    }
}