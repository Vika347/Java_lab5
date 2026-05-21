package managers;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Генератор уникальных id для объектов LabWork.
 */
public class IdGenerator {

    private static final AtomicInteger counter = new AtomicInteger(0); //(Потокобезопасен, операции атомарны)

    //получить следующий ID
    public static int nextId() {
        return counter.incrementAndGet();
    }

    public static void init(int maxId) {
        counter.set(maxId);
    }
}