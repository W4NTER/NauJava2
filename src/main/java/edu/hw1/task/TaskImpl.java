package edu.hw1.task;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

// Как будто многопоточка выбивается из логики курса (рано просто для нее)
// Также буду рад объяснению как это тестировать
// (в теории WireMock подойдет, но надо немного запотеть, надеюсь есть еще способы...)
public class TaskImpl implements Task {
    private final String url;
    private final String path;
    private Thread downloadFileThread;
    private static final int BUFFER_SIZE = 1024;
    private volatile int bytesRead = -1;

    public TaskImpl(String path, String url) {
        this.path = path;
        this.url = url;
    }

    @Override
    public void start() {
        downloadFileThread = new Thread(() -> {
            try (BufferedInputStream in = new BufferedInputStream(URI.create(url).toURL().openStream());
                 FileOutputStream out = new FileOutputStream(path + "/file.txt")) {

                byte[] dataBuffer = new byte[BUFFER_SIZE];
                while ((bytesRead = in.read(dataBuffer, 0, BUFFER_SIZE)) != -1 && !Thread.interrupted()) {
                    out.write(dataBuffer, 0, bytesRead);
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        downloadFileThread.start();
    }

    @Override
    public void stop() {
        try {
            Thread.sleep(2000);
            downloadFileThread.interrupt();
            downloadFileThread.join();
            if (bytesRead != -1) {
                Files.delete(Paths.get(path + "/", "file.txt"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
