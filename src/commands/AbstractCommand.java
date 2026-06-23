package commands;
/**
 * Абстрактный базовый класс команды.
 * <p>
 * Содержит общие свойства всех команд приложения:
 * имя команды и её описание.
 * Является промежуточным звеном между интерфейсом {@link Command}
 * и конкретными реализациями команд.
 * </p>
 *
 * <p>
 * Все классы команд наследуются от данного класса и реализуют
 * метод {@link #execute(String[])}.
 * </p>
 *
 * @author Виктория Родина
 */
public abstract class AbstractCommand implements Command {

    /**
     * Имя команды.
     * Используется для идентификации команды при вводе пользователем.
     */
    private final String name;

    /**
     * Описание команды.
     * Используется для вывода справки по доступным командам.
     */
    private final String description;

    /**
     * Создаёт новую команду с указанными именем и описанием.
     *
     * @param name имя команды
     * @param description краткое описание назначения команды
     */
    public AbstractCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Возвращает имя команды.
     *
     * @return имя команды
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Возвращает описание команды.
     *
     * @return описание команды
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Выполняет команду.
     * <p>
     * Реализация метода определяется в классах-наследниках.
     * </p>
     *
     * @param arguments массив аргументов команды
     */
    @Override
    public abstract void execute(String[] arguments);
}
