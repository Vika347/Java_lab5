package commands;

/**
 * Абстрактный базовый класс для всех команд приложения.
 *
 * Содержит:
 * - имя команды
 * - описание команды
 *
 * Наследуется всеми конкретными командами.
 */
public abstract class AbstractCommand implements Command {

    /**
     * Имя команды.
     */
    private final String name;

    /**
     * Описание команды.
     */
    private final String description;

    /**
     * Создаёт команду(конструктор)
     */
    public AbstractCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Возвращает имя команды. Геттеры
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Возвращает описание команды.
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Выполнение команды (реализуется в наследниках).
     */
    @Override
    public abstract void execute(String[] arguments);
}
