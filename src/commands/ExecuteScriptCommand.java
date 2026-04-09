package commands;

import collection.CollectionManager;
import utils.ConsoleReader;
import java.io.*;
import java.util.*;

/**
 * Команда execute_script - выполняет команды из файла-скрипта.
 * Поддерживает чтение команд из текстового файла.
 * Защищает от рекурсивных вызовов.
 * @author Виктория Родина
 */
public class ExecuteScriptCommand implements Command {
    /** Поле карта всех доступных команд */
    private Map<String, Command> commands;
    /** Поле набор имен выполняющихся скриптов (для защиты от рекурсии)
     *  (Скрипт может вызвать другой скрипт, который вызывает первый — бесконечный цикл)
     *  */
    private Set<String> runningScripts;

    /**
     * Конструктор - создание нового объекта команды execute_script
     * @param commands - карта всех команд
     */
    public ExecuteScriptCommand(Map<String, Command> commands) {
        this.commands = commands;
        this.runningScripts = new HashSet<>();
    }

    /**
     * Выполнение команды execute_script
     * @param args - аргументы команды (args[1] - имя файла)
     * @param collectionManager - менеджер коллекции
     * @param consoleReader - читатель консоли
     * @return true если программа должна продолжить работу, false для выхода
     */
    @Override
    public boolean execute(String[] args, CollectionManager collectionManager, ConsoleReader consoleReader) {
        if (args.length < 2) {
            System.out.println("Ошибка: укажите имя файла. Пример: execute_script script.txt");
            return true;
        }

        String fileName = args[1];

        // Проверка на рекурсию
        if (runningScripts.contains(fileName)) {
            System.out.println("Ошибка: рекурсия! Скрипт " + fileName + " уже выполняется");
            return true;
        }

        runningScripts.add(fileName); // добавляем в "стек" выполняющихся

        // ЧТЕНИЕ Файла
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue; // пропуск пустых и комментариев

                String[] cmdParts = line.split("\\s+", 2); // разделяем команду и аргументы
                Command cmd = commands.get(cmdParts[0]); // находим команду

                if (cmd != null) {
                    boolean result = cmd.execute(cmdParts, collectionManager, consoleReader);
                    if (!result) {
                        runningScripts.remove(fileName);
                        return false;
                    }
                } else {
                    System.out.println("Неизвестная команда: " + cmdParts[0]);
                }
            }
            // УСПЕХ — выводим сообщение только здесь
            System.out.println("Скрипт выполнен: " + fileName);

        } catch (FileNotFoundException e) {
            System.out.println("Ошибка: файл не найден - " + fileName);
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }

        runningScripts.remove(fileName);
        return true;
    }

    /**
     * Функция получения описания команды
     * @return описание команды
     */
    @Override
    public String getDescription() {
        return "выполнить скрипт из файла";
    }

    /**
     * Функция получения имени команды
     * @return имя команды
     */
    @Override
    public String getName() {
        return "execute_script";
    }
}