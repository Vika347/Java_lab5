package managers;

import models.LabWork;
import models.Difficulty;

import java.util.*;

/**
 * Менеджер коллекции объектов {@link LabWork}.
 * <p>
 * Отвечает за хранение коллекции в оперативной памяти программы,
 * выполнение основных операций управления элементами, загрузку данных
 * из файла и сохранение коллекции в файл.
 * </p>
 *
 * <p>
 * Для хранения используется коллекция {@link Hashtable}
 * с ключом типа {@link Integer} и значением типа {@link LabWork}.
 * </p>
 *
 * @author Виктория Родина
 */
public class CollectionManager {

    /**
     * Коллекция лабораторных работ.
     */
    private final Hashtable<Integer, LabWork> collection = new Hashtable<>();

    /**
     * Менеджер для чтения коллекции из файла и записи коллекции в файл.
     */
    private final FileManager fileManager;

    /**
     * Дата и время инициализации менеджера коллекции.
     */
    private final Date initializationDate = new Date();

    /**
     * Создаёт менеджер коллекции.
     *
     * @param fileManager менеджер работы с файлом
     */
    public CollectionManager(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    /**
     * Загружает коллекцию из файла.
     * <p>
     * Перед загрузкой текущая коллекция очищается. После чтения данных
     * обновляется генератор идентификаторов на основе максимального ключа
     * коллекции.
     * </p>
     */
    public void loadCollection() {
        collection.clear();
        collection.putAll(fileManager.readCollection());
        updateIdGenerator();
    }

    /**
     * Обновляет генератор идентификаторов.
     * <p>
     * Находит максимальный ключ в коллекции и передаёт его в
     * {@link IdGenerator#init(int)}.
     * </p>
     */
    private void updateIdGenerator() {
        int maxId = collection.keySet()
                .stream()
                .max(Integer::compareTo)
                .orElse(0);
        if (maxId > 0) {
            IdGenerator.init(maxId);
        }
    }

    /**
     * Сохраняет коллекцию в файл.
     */
    public void saveCollection() {
        fileManager.writeCollection(collection);
    }

    /**
     * Возвращает коллекцию лабораторных работ.
     *
     * @return коллекция объектов {@link LabWork}
     */
    public Hashtable<Integer, LabWork> getCollection() {
        return collection;
    }

    /**
     * Добавляет элемент в коллекцию по заданному ключу.
     *
     * @param key ключ элемента
     * @param labWork добавляемый объект {@link LabWork}
     */
    public void insert(Integer key, LabWork labWork) {
        collection.put(key, labWork);
    }

    /**
     * Обновляет элемент коллекции по ключу.
     * <p>
     * При обновлении сохраняются старые значения полей {@code id}
     * и {@code creationDate}.
     * </p>
     *
     * @param key ключ обновляемого элемента
     * @param newLabWork новый объект {@link LabWork}
     */
    public void update(Integer key, LabWork newLabWork) {
        if (!collection.containsKey(key)) return;
        LabWork old = collection.get(key);
        newLabWork.setId(old.getId());
        newLabWork.setCreationDate(old.getCreationDate());
        collection.put(key, newLabWork);
    }

    /**
     * Удаляет элемент коллекции по ключу.
     *
     * @param key ключ удаляемого элемента
     */
    public void remove(Integer key) {
        collection.remove(key);
    }

    /**
     * Очищает коллекцию.
     */
    public void clear() {
        collection.clear();
    }

    /**
     * Возвращает элемент коллекции по ключу.
     *
     * @param key ключ элемента
     * @return объект {@link LabWork}, связанный с указанным ключом,
     * или {@code null}, если элемент не найден
     */
    public LabWork get(Integer key) {
        return collection.get(key);
    }

    /**
     * Возвращает элементы коллекции в отсортированном порядке.
     * <p>
     * Сортировка выполняется согласно естественному порядку объектов
     * {@link LabWork}.
     * </p>
     *
     * @return список отсортированных объектов {@link LabWork}
     */
    public List<LabWork> getSorted() {
        List<LabWork> list = new ArrayList<>(collection.values());
        Collections.sort(list);
        return list;
    }

    /**
     * Возвращает количество элементов в коллекции.
     *
     * @return размер коллекции
     */
    public int size() {
        return collection.size();
    }

    /**
     * Возвращает дату инициализации коллекции.
     *
     * @return дата инициализации менеджера коллекции
     */
    public Date getInitializationDate() {
        return initializationDate;
    }

    /**
     * Проверяет наличие элемента с указанным ключом.
     *
     * @param key проверяемый ключ
     * @return {@code true}, если ключ содержится в коллекции;
     * иначе {@code false}
     */
    public boolean containsKey(Integer key) {
        return collection.containsKey(key);
    }

    /**
     * Удаляет из коллекции все элементы, превышающие заданный объект.
     *
     * @param element объект {@link LabWork}, с которым сравниваются элементы коллекции
     */
    public void removeGreater(LabWork element) {
        collection.values().removeIf(lw -> lw.compareTo(element) > 0);
    }

    /**
     * Удаляет из коллекции все элементы, ключ которых больше заданного.
     *
     * @param key ключ, с которым сравниваются ключи элементов коллекции
     */
    public void removeGreaterKey(Integer key) {
        collection.keySet().removeIf(k -> k > key);
    }

    /**
     * Заменяет элемент по ключу, если новый элемент меньше старого.
     *
     * @param key ключ заменяемого элемента
     * @param newElement новый объект {@link LabWork}
     * @return {@code true}, если замена была выполнена;
     * иначе {@code false}
     */
    public boolean replaceIfLower(Integer key, LabWork newElement) {
        LabWork old = collection.get(key);
        if (old != null && newElement.compareTo(old) < 0) {
            newElement.setId(old.getId());
            newElement.setCreationDate(old.getCreationDate());
            collection.put(key, newElement);
            return true;
        }
        return false;
    }

    /**
     * Подсчитывает количество элементов с заданным именем автора.
     *
     * @param authorName имя автора
     * @return количество элементов, автор которых имеет указанное имя
     */
    public long countByAuthor(String authorName) {
        return collection.values().stream()
                .filter(lw -> lw.getAuthor().getName().equals(authorName))
                .count();
    }

    /**
     * Возвращает элементы с указанным уровнем сложности.
     *
     * @param difficulty уровень сложности
     * @return список элементов с заданной сложностью
     */
    public List<LabWork> filterByDifficulty(Difficulty difficulty) {
        return collection.values().stream()
                .filter(lw -> lw.getDifficulty() == difficulty)
                .toList();
    }

    /**
     * Возвращает элементы, имя автора которых лексикографически меньше заданного.
     *
     * @param authorName имя автора для сравнения
     * @return список элементов, удовлетворяющих условию фильтрации
     */
    public List<LabWork> filterLessThanAuthor(String authorName) {
        return collection.values().stream()
                .filter(lw -> lw.getAuthor().getName().compareTo(authorName) < 0)
                .toList();
    }
}