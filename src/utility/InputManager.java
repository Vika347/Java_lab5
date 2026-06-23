package utility;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс управления пользовательским вводом.
 * <p>
 * Инкапсулирует работу с объектом {@link Scanner} и предоставляет
 * единый интерфейс для чтения данных из стандартного потока ввода.
 * </p>
 *
 * <p>
 * Используется для централизованного управления вводом данных
 * с консоли и обработки возможных ошибок чтения.
 * </p>
 *
 * @author Виктория Родина
 */
public class InputManager {

    /**
     * Сканер, используемый для чтения данных из стандартного потока ввода.
     */
    private final Scanner scanner;

    /**
     * Создаёт менеджер ввода.
     * <p>
     * Инициализирует объект {@link Scanner}, связанный
     * со стандартным потоком ввода {@code System.in}.
     * </p>
     */
    public InputManager() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Считывает одну строку из консоли.
     * <p>
     * Если поток ввода завершён или возникает исключение
     * {@link NoSuchElementException}, возвращается значение {@code null}.
     * </p>
     *
     * @return считанная строка или {@code null}, если ввод невозможен
     */
    public String readLine() {
        try {
            if (scanner.hasNextLine()) {
                return scanner.nextLine();
            } else {
                return null;
            }
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}