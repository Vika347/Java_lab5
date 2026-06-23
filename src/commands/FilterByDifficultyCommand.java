package commands;

import managers.CollectionManager;
import models.Difficulty;
import models.LabWork;
import utility.Console;

/**
 * Команда фильтрации элементов по сложности.
 * <p>
 * Выводит все объекты {@link LabWork}, значение поля сложности
 * которых совпадает с указанным пользователем значением.
 * </p>
 *
 * <p>
 * Команда принимает один аргумент — значение перечисления {@link Difficulty}.
 * </p>
 *
 * @author Виктория Родина
 */
public class FilterByDifficultyCommand extends AbstractCommand {

    /**
     * Менеджер коллекции, содержащий объекты {@link LabWork}.
     */
    private final CollectionManager collectionManager;

    /**
     * Объект для вывода информации и сообщений об ошибках.
     */
    private final Console console;

    /**
     * Создаёт команду фильтрации по сложности.
     *
     * @param collectionManager менеджер коллекции
     * @param console объект для вывода информации и сообщений об ошибках
     */
    public FilterByDifficultyCommand(CollectionManager collectionManager, Console console) {
        super("filter_by_difficulty", "вывести элементы с заданной сложностью");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    /**
     * Выполняет фильтрацию элементов коллекции по уровню сложности.
     * <p>
     * Проверяет корректность количества аргументов и преобразует
     * переданную строку в значение перечисления {@link Difficulty}.
     * Если указанное значение не существует, выводится сообщение об ошибке.
     * </p>
     *
     * <p>
     * Все элементы коллекции, имеющие указанную сложность,
     * выводятся в консоль. Если подходящих элементов не найдено,
     * пользователю выводится соответствующее сообщение.
     * </p>
     *
     * @param arguments массив аргументов команды, содержащий значение сложности
     */
    @Override
    public void execute(String[] arguments) {

        if (arguments.length != 1) {
            console.printError("Использование: filter_by_difficulty DIFFICULTY");
            return;
        }

        Difficulty difficulty;

        try {
            difficulty = Difficulty.valueOf(arguments[0]);
        } catch (IllegalArgumentException e) {
            console.printError("Неверное значение Difficulty.");
            return;
        }

        boolean found = false;

        for (LabWork lab : collectionManager.getCollection().values()) {

            if (lab.getDifficulty() == difficulty) {
                console.println(lab.toString());
                found = true;
            }
        }

        if (!found) {
            console.println("Элементы с данной сложностью не найдены.");
        }
    }
}