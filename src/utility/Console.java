package utility;

/**
 * Утилитарный класс для работы с консолью.
 *
 * Отвечает за:
 * - вывод обычных сообщений
 * - вывод ошибок
 *
 * Позволяет централизовать взаимодействие с пользователем.
 */
public class Console {

    /**
     * Выводит обычное сообщение в консоль.
     */
    public void println(String message) {
        System.out.println(message);
    }

    /**
     * Выводит сообщение об ошибке.
     */
    public void printError(String message) {
        System.err.println("[ОШИБКА] " + message);
    }

    /**
     * Выводит сообщение без переноса строки.
     */
    public void print(String message) {
        System.out.print(message);
    }
}