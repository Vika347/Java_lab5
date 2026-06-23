package utility;

import java.io.*;
import java.util.Stack;

/**
 * Менеджер источников ввода.
 * <p>
 * Поддерживает чтение данных как с консоли, так и из файлов скриптов.
 * Позволяет временно переключать источник ввода на файл, а после завершения
 * скрипта возвращаться к предыдущему источнику.
 * </p>
 *
 * <p>
 * Также предотвращает рекурсивный запуск одного и того же скрипта.
 * </p>
 *
 * @author Виктория Родина
 */
public class ReaderManager {

    /**
     * Текущий источник ввода.
     * Если значение равно {@code null}, чтение выполняется с консоли.
     */
    private BufferedReader currentReader;

    /**
     * Стек предыдущих источников ввода.
     * Используется при вложенном выполнении скриптов.
     */
    private final Stack<BufferedReader> readerStack = new Stack<>();

    /**
     * Стек имён выполняемых скриптов.
     * Используется для обнаружения рекурсии.
     */
    private final Stack<String> scriptStack = new Stack<>();

    /**
     * Объект для вывода сообщений пользователю.
     */
    private final Console console;

    /**
     * Менеджер чтения данных с консоли.
     */
    private final InputManager inputManager;

    /**
     * Создаёт менеджер источников ввода.
     *
     * @param console объект для вывода сообщений
     * @param inputManager менеджер ввода с консоли
     */
    public ReaderManager(Console console, InputManager inputManager) {
        this.console = console;
        this.inputManager = inputManager;
        this.currentReader = null;
    }

    /**
     * Считывает строку из текущего источника ввода.
     * <p>
     * Если активный скрипт отсутствует, строка считывается с консоли.
     * Если выполняется скрипт, строка считывается из файла.
     * После окончания файла происходит возврат к предыдущему источнику ввода.
     * </p>
     *
     * @return считанная строка или {@code null}, если ввод завершён
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
     * <p>
     * Перед открытием файла проверяет, не выполняется ли уже скрипт
     * с таким именем. Это позволяет предотвратить рекурсивный запуск.
     * </p>
     *
     * @param fileName имя файла скрипта
     * @return {@code true}, если переключение выполнено успешно;
     *         {@code false}, если файл не найден или обнаружена рекурсия
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
     * Завершает чтение текущего скрипта и возвращается
     * к предыдущему источнику ввода.
     * <p>
     * Закрывает текущий файл скрипта, восстанавливает предыдущий
     * {@link BufferedReader} из стека и удаляет имя завершённого
     * скрипта из стека активных скриптов.
     * </p>
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
     * Проверяет, выполняется ли чтение из файла скрипта.
     *
     * @return {@code true}, если активен режим чтения скрипта;
     *         {@code false}, если ввод выполняется с консоли
     */
    public boolean isScriptMode() {
        return currentReader != null;
    }
}