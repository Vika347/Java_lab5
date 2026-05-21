package managers;

import models.LabWork;
import models.Difficulty;

import java.util.*;

/**
 * Менеджер коллекции LabWork.
 * Отвечает за хранение и управление коллекцией объектов LabWork в оперативной памяти программы
 */
public class CollectionManager {

    private final Hashtable<Integer, LabWork> collection = new Hashtable<>();
    private final FileManager fileManager; //Менеджер для работы с файлами
    private final Date initializationDate = new Date(); //Дата и время создания менеджера коллекции

    //Конструктор (охраняет переданный FileManager в поле)
    public CollectionManager(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    //Загрузка и сохранение
    public void loadCollection() {
        collection.clear(); //Очищает текущую коллекцию
        collection.putAll(fileManager.readCollection()); //Читает CSV файл, возвращает Hashtable,добавляет все прочитанные элементы
        updateIdGenerator(); //Обновляет генератор ID на основе максимального ID
    }

    private void updateIdGenerator() {
        int maxId = collection.keySet() //получаем все ключи
                .stream() // превращаем в поток
                .max(Integer::compareTo) //ищем максимальное значение
                .orElse(0);  // если коллекция пуста → 0
        if (maxId > 0) {
            IdGenerator.init(maxId);
        }
    }

    //Передаёт коллекцию FileManager для записи в файл.
    public void saveCollection() {
        fileManager.writeCollection(collection);
    }

    public Hashtable<Integer, LabWork> getCollection() {
        return collection;
    }

    //Базовые CRUD операции

    // CREATE (создание)
    public void insert(Integer key, LabWork labWork) {
        collection.put(key, labWork); //Добавляет элемент в коллекцию по ключу.
    }

    // UPDATE (обновление) ID и дата создания НЕ меняются
    public void update(Integer key, LabWork newLabWork) {
        if (!collection.containsKey(key)) return; //Проверка существования ключа
        LabWork old = collection.get(key); //Получаем старый элемент
        newLabWork.setId(old.getId()); //Устанавливаем ID нового элемента
        newLabWork.setCreationDate(old.getCreationDate()); //Устанавливаем дату нового элемента
        collection.put(key, newLabWork); //Сохраняем в коллекцию
    }

    // DELETE (удаление элементов по ключу)
    public void remove(Integer key) {
        collection.remove(key);
    }

    //DELETE ALL (очищение всей коллекции)
    public void clear() {
        collection.clear();
    }

    //READ (возвращает элемент по ключу)
    public LabWork get(Integer key) {
        return collection.get(key);
    }

    //Сортировка:
    public List<LabWork> getSorted() {
        List<LabWork> list = new ArrayList<>(collection.values()); //Получаем все значения Hashtable, копируем в ArrayList
        Collections.sort(list); //Сортируем список (использует compareTo из LabWork)
        return list; //Возвращаем отсортированный список
    }

    public int size() {
        return collection.size();
    }

    public Date getInitializationDate() {
        return initializationDate;
    }

    // проверка существования элемент с таким ключом
    public boolean containsKey(Integer key) {
        return collection.containsKey(key);
    }

   // удалить все элементы, больше заданного
    public void removeGreater(LabWork element) {
        collection.values().removeIf(lw -> lw.compareTo(element) > 0);
    }

    //Удаление элементов, больших заданного
    public void removeGreaterKey(Integer key) {
        collection.keySet().removeIf(k -> k > key);
    }

    //Замена, если новый элемент меньше
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

    //Подсчёт по автору
    public long countByAuthor(String authorName) {
        return collection.values().stream()
                .filter(lw -> lw.getAuthor().getName().equals(authorName))
                .count();
    }

    //Фильтр по сложности
    public List<LabWork> filterByDifficulty(Difficulty difficulty) {
        return collection.values().stream()
                .filter(lw -> lw.getDifficulty() == difficulty)
                .toList();
    }

    //Фильтр по автору (меньше заданного)
    public List<LabWork> filterLessThanAuthor(String authorName) {
        return collection.values().stream()
                .filter(lw -> lw.getAuthor().getName().compareTo(authorName) < 0)
                .toList();
    }
}
