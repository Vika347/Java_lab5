package utility;

import java.util.Scanner;
import java.util.NoSuchElementException;
//Централизованное управление вводом
/**
 * Менеджер ввода с консоли.
 */
public class InputManager {

    private final Scanner scanner;

    //Конструктор
    public InputManager() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Читает строку из консоли.
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