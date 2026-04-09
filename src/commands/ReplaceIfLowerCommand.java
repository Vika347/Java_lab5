package commands;

import collection.CollectionManager;
import model.LabWork;
import utils.ConsoleReader;

/**
 * Команда replace_if_lower - заменяет значение по ключу, если новое значение меньше.
 * Сравнение происходит по полю personalQualitiesMinimum.
 * Формат: replace_if_lower <ключ>
 * @author Виктория Родина
 */
public class ReplaceIfLowerCommand implements Command {

    /**
     * Выполнение команды replace_if_lower
     * @param args - аргументы команды (args[1] - ключ)
     * @param collectionManager - менеджер коллекции
     * @param consoleReader - читатель консоли для ввода новых данных
     * @return true - продолжение работы программы
     */
    @Override
    public boolean execute(String[] args, CollectionManager collectionManager, ConsoleReader consoleReader) {
        if (args.length < 2) {
            System.out.println("Ошибка: укажите ключ. Пример: replace_if_lower 5");
            return true;
        }

        try {
            Long key = Long.parseLong(args[1]);

            if (!collectionManager.containsKey(key)) {
                System.out.println("Ошибка: ключ " + key + " не найден");
                return true;
            }

            LabWork old = collectionManager.get(key);
            System.out.println("Текущее personalQualitiesMinimum: " + old.getPersonalQualitiesMinimum());

            System.out.println("Введите новые данные:");
            LabWork newElement = consoleReader.readLabWorkForReplace();

            collectionManager.replaceIfLower(key, newElement);

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
        return "заменить значение по ключу, если новое значение меньше старого";
    }

    /**
     * Функция получения имени команды
     * @return имя команды
     */
    @Override
    public String getName() {
        return "replace_if_lower";
    }
}