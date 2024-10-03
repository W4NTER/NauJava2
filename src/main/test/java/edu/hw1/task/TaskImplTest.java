package edu.hw1.task;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskImplTest {

    @Test
    @DisplayName("Проверка скачивания")
    void testThatDownloadingIsCorrectReturnedSucceed() throws InterruptedException {
        TaskImpl task = new TaskImpl("src/main/test/java/edu/hw1/resources", "https://httpbin.org/image/png", "testFile1");
        task.start();
        Thread.sleep(1000); //если сразу стопнуть, не успеет скачаться
        task.stop();
        assertTrue(Files.exists(Paths.get("src/main/test/java/edu/hw1/resources/testFile1.txt")));
    }

    @Test
    @DisplayName("Проверка скачивания удаления всех ресурсов, если скачивание не завершилось")
    void testThatDownloadingIsntCompleteReturnedSucceed() throws InterruptedException {
        TaskImpl task = new TaskImpl("src/main/test/java/edu/hw1/resources", "https://httpbin.org/image/png", "testFile2");
        task.start();
        task.stop();
        assertTrue(!Files.exists(Paths.get("src/main/test/java/edu/hw1/resources/testFile2.txt")));
    }
}
