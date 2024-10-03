package edu.hw1.task;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskImpl2Test {

    @Test
    @DisplayName("проверка корректности скачивания файла")
    void testThatDownloadIsCorrectReturnedSucceed() throws InterruptedException {
        Task task = new TaskImpl2("src/main/test/java/edu/hw1/resources", "https://httpbin.org/image/png", "testFile3");
        Thread thread = new Thread(task::start);
        thread.start();
        thread.join();
        task.stop();
        assertTrue(Files.exists(Paths.get("src/main/test/java/edu/hw1/resources/testFile3.txt")));
    }


//    @Test
//    @DisplayName("проверка корректности скачивания файла")
//    void testThatDownloadIsStoppedReturnedSucceed() throws InterruptedException {
//        Task task = new TaskImpl2("src/main/test/java/edu/hw1/resources", "https://httpbin.org/image/png", "testFile4");
//        Thread thread = new Thread(task::start);
//        thread.start();
//        Thread thread1 = new Thread(task::stop);
////        Thread.sleep(1200); // Вот тут я возможно что-то делаю не так, приходится ждать больше секунды,
//        //только для того, чтобы поток старт начал работать и инициализировал isRunning, иначе сначала отработает stop
//        // и старту уже ничего не помешает
//        thread1.start();
////        task.stop();
//        thread.join();
//        thread1.join();
//        assertFalse(Files.exists(Paths.get("src/main/test/java/edu/hw1/resources/testFile4.txt")));
//    }
}
