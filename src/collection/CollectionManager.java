package collection;

import model.LabWork;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Класс для управления коллекцией LabWork.
 * Хранит элементы в Hashtable с ключом типа Long.
 * @author Виктория Родина
 */
public class CollectionManager {
    /** Поле коллекция для хранения лабораторных работ */
    private Hashtable<Long, LabWork> collection;
    /** Поле дата инициализации коллекции */
    private LocalDateTime initializationDate;

    /**
     * Конструктор - инициализирует пустую коллекцию.
     */
    public CollectionManager() {
        this.collection = new Hashtable<>();
        this.initializationDate = LocalDateTime.now();
    }

    //ОСНОВНАЯ ИНФОРМАЦИЯ

    /**
     * Функция получения типа коллекции
     * @return строковое представление типа коллекции
     */
    public String getCollectionType() {
        return collection.getClass().getSimpleName();
    }

    /**
     * Функция получения даты инициализации коллекции
     * @return дата создания менеджера коллекции
     */
    public LocalDateTime getInitializationDate() {
        return initializationDate;
    }

    /**
     * Функция получения количества элементов в коллекции
     * @return размер коллекции
     */
    public int size() {
        return collection.size();
    }

    /**
     * Функция проверки пустоты коллекции
     * @return true если коллекция пуста, иначе false
     */
    public boolean isEmpty() {
        return collection.isEmpty();
    }

    // ОПЕРАЦИИ С ЭЛЕМЕНТАМИ

    /**
     * Функция получения всех элементов коллекции
     * @return коллекция всех LabWork
     */
    public Collection<LabWork> getAll() {
        return collection.values();
    }

    /**
     * Функция получения элемента по ключу
     * @param key - ключ элемента
     * @return элемент LabWork или null если не найден
     */
    public LabWork get(Long key) {
        return collection.get(key);
    }

    /**
     * Процедура добавления элемента в коллекцию
     * @param key - ключ элемента
     * @param value - элемент LabWork
     */
    public void put(Long key, LabWork value) {
        collection.put(key, value);
    }

    /**
     * Функция удаления элемента по ключу
     * @param key - ключ элемента
     * @return удаленный элемент или null
     */
    public LabWork remove(Long key) {
        return collection.remove(key);
    }

    /**
     * Процедура очистки всей коллекции
     */
    public void clear() {
        collection.clear();
    }

    /**
     * Функция проверки наличия ключа в коллекции
     * @param key - проверяемый ключ
     * @return true если ключ существует, иначе false
     */
    public boolean containsKey(Long key) {
        return collection.containsKey(key);
    }

    /**
     * Функция получения всех ключей коллекции
     * @return набор всех ключей
     */
    public Set<Long> keySet() {
        return collection.keySet();
    }

    // СПЕЦИАЛЬНЫЕ ОПЕРАЦИИ

    /**
     * Процедура удаления всех элементов, превышающих заданный
     * @param element - элемент для сравнения
     */
    public void removeGreater(LabWork element) {
        int beforeSize = collection.size();
        collection.values().removeIf(e -> e.compareTo(element) > 0);
        int removedCount = beforeSize - collection.size();
        System.out.println("Удалено элементов: " + removedCount);
    }

    /**
     * Процедура удаления всех элементов с ключом больше заданного
     * @param key - ключ для сравнения
     */
    public void removeGreaterKey(Long key) {
        int beforeSize = collection.size();
        List<Long> keysToRemove = new ArrayList<>();
        for (Long k : collection.keySet()) {
            if (k > key) {
                keysToRemove.add(k);
            }
        }
        for (Long k : keysToRemove) {
            collection.remove(k);
        }
        int removedCount = beforeSize - collection.size();
        System.out.println("Удалено элементов: " + removedCount);
    }

    /**
     * Функция замены значения по ключу, если новое значение меньше старого
     * @param key - ключ элемента
     * @param newLabWork - новый элемент
     * @return true если замена выполнена, false если условие не выполнено
     */
    public boolean replaceIfLower(Long key, LabWork newLabWork) {
        if (!collection.containsKey(key)) {
            System.out.println("Ошибка: Ключ " + key + " не найден.");
            return false;
        }

        LabWork oldLabWork = collection.get(key);

        if (newLabWork.getPersonalQualitiesMinimum() < oldLabWork.getPersonalQualitiesMinimum()) {
            LabWork updatedLabWork = new LabWork(
                    oldLabWork.getId(),
                    newLabWork.getName(),
                    newLabWork.getCoordinates(),
                    oldLabWork.getCreationDate(),
                    newLabWork.getMinimalPoint(),
                    newLabWork.getPersonalQualitiesMinimum(),
                    newLabWork.getDifficulty(),
                    newLabWork.getAuthor()
            );
            collection.put(key, updatedLabWork);
            System.out.println("Замена выполнена. Новое значение меньше старого.");
            return true;
        } else {
            System.out.println("Замена не выполнена. Новое значение не меньше старого.");
            return false;
        }
    }

    //МЕТОДЫ ФИЛЬТРАЦИИ И ПОДСЧЕТА

    /**
     * Функция получения количества элементов с заданным автором
     * @param authorName - имя автора
     * @return количество элементов
     */
    public long countByAuthor(String authorName) {
        return collection.values().stream()
                .filter(lw -> lw.getAuthor().getName().equals(authorName))
                .count();
    }

    /**
     * Функция получения элементов с заданной сложностью
     * @param difficultyStr - название сложности
     * @return список элементов с указанной сложностью
     */
    public List<LabWork> filterByDifficulty(String difficultyStr) {
        return collection.values().stream()
                .filter(lw -> lw.getDifficulty().name().equals(difficultyStr))
                .toList();
    }

    /**
     * Функция получения элементов, у которых имя автора меньше заданного
     * @param authorName - имя автора для сравнения
     * @return список элементов
     */
    public List<LabWork> filterLessThanAuthor(String authorName) {
        return collection.values().stream()
                .filter(lw -> lw.getAuthor().getName().compareTo(authorName) < 0)
                .toList();
    }

    //  ВСПОМОГАТЕЛЬНЫЕ МЕТОДЫ

    /**
     * Функция поиска ключа по id элемента
     * @param id - идентификатор LabWork
     * @return ключ или null если не найден
     */
    public Long findKeyById(long id) {
        for (Map.Entry<Long, LabWork> entry : collection.entrySet()) {
            if (entry.getValue().getId() == id) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * Процедура вывода информации о коллекции
     */
    public void printInfo() {
        System.out.println("Информация о коллекции:");
        System.out.println("  Тип: " + getCollectionType());
        System.out.println("  Дата инициализации: " + initializationDate);
        System.out.println("  Количество элементов: " + size());
    }

    /**
     * Процедура вывода всех элементов коллекции
     */
    public void printAll() {
        if (isEmpty()) {
            System.out.println("Коллекция пуста.");
            return;
        }

        System.out.println("Элементы коллекции:");
        for (LabWork lw : getAll()) {
            System.out.println(lw);
        }
    }

    /**
     * Процедура вывода элементов, отфильтрованных по сложности
     * @param difficultyStr - сложность для фильтрации
     */
    public void printFilterByDifficulty(String difficultyStr) {
        List<LabWork> filtered = filterByDifficulty(difficultyStr);
        if (filtered.isEmpty()) {
            System.out.println("Элементы со сложностью " + difficultyStr + " не найдены.");
        } else {
            System.out.println("Элементы со сложностью " + difficultyStr + ":");
            filtered.forEach(System.out::println);
        }
    }

    /**
     * Процедура вывода элементов с автором меньше заданного
     * @param authorName - имя автора для сравнения
     */
    public void printFilterLessThanAuthor(String authorName) {
        List<LabWork> filtered = filterLessThanAuthor(authorName);
        if (filtered.isEmpty()) {
            System.out.println("Элементы с автором меньше '" + authorName + "' не найдены.");
        } else {
            System.out.println("Элементы с автором меньше '" + authorName + "':");
            filtered.forEach(System.out::println);
        }
    }

    /**
     * Процедура вывода количества элементов по автору
     * @param authorName - имя автора
     */
    public void printCountByAuthor(String authorName) {
        long count = countByAuthor(authorName);
        System.out.println("Количество работ автора '" + authorName + "': " + count);
    }
}