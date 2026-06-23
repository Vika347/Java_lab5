package managers;

import utility.Console;
import utility.ReaderManager;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Менеджер выполнения скриптов.
 * <p>
 * Отвечает за запуск и завершение выполнения скриптов,
 * а также за предотвращение рекурсивного вызова скриптов.
 * Использует {@link ReaderManager} для переключения источника
 * ввода между консолью и файлами скриптов.
 * </p>
 *
 * @author Виктория Родина
 */
public class ScriptManager {

    /**
     * Менеджер чтения данных из консоли и файлов скриптов.
     */
    private final ReaderManager readerManager;

    /**
     * Объект для вывода сообщений и ошибок.
     */
    private final Console console;

    /**
     * Множество имён выполняющихся скриптов.
     * <p>
     * Используется для обнаружения и предотвращения
     * рекурсивного вызова скриптов.
     * </p>
     */
    private final Set<String> executingScripts = new HashSet<>();

    /**
     * Создаёт менеджер выполнения скриптов.
     *
     * @param readerManager менеджер чтения данных
     * @param console объект для вывода сообщений и ошибок
     */
    public ScriptManager(ReaderManager readerManager, Console console) {
        this.readerManager = readerManager;
        this.console = console;
    }

    /**
     * Начинает выполнение скрипта.
     * <p>
     * Проверяет наличие рекурсивного вызова и, если его нет,
     * переключает источник ввода на указанный файл с помощью
     * {@link ReaderManager#pushScript(String)}.
     * </p>
     *
     * @param fileName имя файла скрипта
     * @return {@code true}, если выполнение скрипта успешно начато,
     *         иначе {@code false}
     */
    public boolean startScript(String fileName) {
        if (executingScripts.contains(fileName)) {
            console.printError("Обнаружена рекурсия скриптов: " + fileName);
            return false;
        }

        //Просит ReaderManager переключить чтение на файл
        if (!readerManager.pushScript(fileName)) {
            return false;
        }

        //Добавляем имя скрипта в множество выполняющихся и возвращаем true
        executingScripts.add(fileName);
        return true;
    }

    /**
     * Считывает следующую строку из текущего источника ввода.
     * <p>
     * Если выполняется скрипт, строка считывается из файла.
     * В противном случае чтение производится из консоли.
     * </p>
     *
     * @return следующая считанная строка или {@code null},
     *         если данные отсутствуют
     */
    public String readLine() {
        return readerManager.readLine();
    }

    /**
     * Завершает выполнение текущего скрипта.
     * <p>
     * Очищает список выполняющихся скриптов и возвращает
     * источник ввода к предыдущему состоянию с помощью
     * {@link ReaderManager#popScript()}.
     * </p>
     */
    public void stopScript() {
        if (!executingScripts.isEmpty()) {
            executingScripts.clear();
        }
        readerManager.popScript();
    }
}