package commands;

import collection.CollectionManager;
import model.LabWork;
import utils.ConsoleReader;

/**
 * Команда update - обновляет элемент по его id.
 * Ищет элемент по id, затем обновляет его, сохраняя оригинальные id и creationDate.
 * Формат: update <id>
 * @author Виктория Родина
 */
public class UpdateCommand implements Command {

    /**
     * Выполнение команды update
     * @param args - аргументы команды (args[1] - id)
     * @param collectionManager - менеджер коллекции
     * @param consoleReader - читатель консоли для ввода новых данных
     * @return true - продолжение работы программы
     */
    @Override
    public boolean execute(String[] args, CollectionManager collectionManager, ConsoleReader consoleReader) {
        // Проверяем, указан ли id
        if (args.length < 2) {
            System.out.println("Ошибка: укажите id. Пример: update 1");
            return true;
        }

        // Преобразуем id в число
        long id;
        try {
            id = Long.parseLong(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: id должен быть целым числом");
            return true;
        }

        // Ищем ключ по id (коллекция хранится по ключам, а мы знаем id)
        Long key = collectionManager.findKeyById(id);

        if (key == null) {
            System.out.println("Ошибка: элемент с id " + id + " не найден");
            return true;
        }

        // Получаем старый элемент
        LabWork oldLabWork = collectionManager.get(key);

        // Запрашиваем новые данные
        System.out.println("Введите новые данные (текущее название: " + oldLabWork.getName() + "):");
        LabWork newLabWork = consoleReader.readLabWork();

        // Создаем обновленный объект, сохраняя старые id и creationDate
        LabWork updatedLabWork = new LabWork(
                oldLabWork.getId(),           // старый id
                newLabWork.getName(),         // новое имя
                newLabWork.getCoordinates(),  // новые координаты
                oldLabWork.getCreationDate(), // старая дата создания
                newLabWork.getMinimalPoint(), // новый minimalPoint
                newLabWork.getPersonalQualitiesMinimum(), // новое значение
                newLabWork.getDifficulty(),   // новая сложность
                newLabWork.getAuthor()        // новый автор
        );

        // Сохраняем обновленный элемент в коллекцию
        collectionManager.put(key, updatedLabWork);

        System.out.println("Элемент с id " + id + " обновлен");

        return true;
    }

    /**
     * Функция получения описания команды
     * @return описание команды
     */
    @Override
    public String getDescription() {
        return "обновить значение элемента по id";
    }

    /**
     * Функция получения имени команды
     * @return имя команды
     */
    @Override
    public String getName() {
        return "update";
    }
}
