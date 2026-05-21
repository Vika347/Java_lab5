package managers;

import utility.Console;
import utility.ReaderManager;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Менеджер выполнения скриптов.
 */
public class ScriptManager {

    private final ReaderManager readerManager;
    private final Console console;
    private final Set<String> executingScripts = new HashSet<>();

    //Конструктор
    public ScriptManager(ReaderManager readerManager, Console console) {
        this.readerManager = readerManager;
        this.console = console;
    }

    /**
     * Начинает выполнение скрипта.
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
     * Читает следующую строку из скрипта.
     */
    public String readLine() {
        return readerManager.readLine();
    }

    /**
     * Завершает выполнение текущего скрипта.
     */
    public void stopScript() {
        if (!executingScripts.isEmpty()) {
            executingScripts.clear();
        }
        readerManager.popScript();
    }
}