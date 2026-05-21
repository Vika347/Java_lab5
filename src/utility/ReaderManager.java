package utility;

import java.io.*;
import java.util.Stack;

/**
 * Менеджер источников ввода.
 * Поддерживает ввод с консоли и из файлов (скриптов).
 */
public class ReaderManager {

    private BufferedReader currentReader;
    private final Stack<BufferedReader> readerStack = new Stack<>();
    private final Stack<String> scriptStack = new Stack<>();
    private final Console console;
    private final InputManager inputManager;

    //конструктор
    public ReaderManager(Console console, InputManager inputManager) {
        this.console = console;
        this.inputManager = inputManager;
        this.currentReader = null;
    }

    /**
     * Читает строку из текущего источника.
     */
    public String readLine() {
        try {
            if (currentReader == null) {
                // Чтение с консоли
                String line = inputManager.readLine();
                if (line == null) {
                    console.println("\nВвод с клавиатуры завершён (Ctrl+D)");
                    return null;
                }
                return line;
            } else {
                // Чтение из файла
                String line = currentReader.readLine();
                if (line == null) {
                    popScript();
                    return readLine();
                }
                return line;
            }
        } catch (IOException e) {
            console.printError("Ошибка чтения: " + e.getMessage());
            return null;
        }
    }

    /**
     * Переключает ввод на файл скрипта.
     */
    public boolean pushScript(String fileName) {
        if (scriptStack.contains(fileName)) {
            console.printError("Обнаружена рекурсия скриптов: " + fileName);
            return false;
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            readerStack.push(currentReader);
            scriptStack.push(fileName);
            currentReader = reader;
            return true;
        } catch (FileNotFoundException e) {
            console.printError("Файл скрипта не найден: " + fileName);
            return false;
        }
    }

    /**
     * Возвращается к предыдущему источнику ввода.
     */
    public void popScript() {
        try {
            if (currentReader != null) {
                currentReader.close();
            }
        } catch (IOException e) {
            console.printError("Ошибка закрытия файла: " + e.getMessage());
        }
        currentReader = readerStack.isEmpty() ? null : readerStack.pop();
        if (!scriptStack.isEmpty()) scriptStack.pop();
    }

    /**
     * Проверяет, активен ли скрипт.
     */
    public boolean isScriptMode() {
        return currentReader != null;
    }
}