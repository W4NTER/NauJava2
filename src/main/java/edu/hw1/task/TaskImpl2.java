package edu.hw1.task;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TaskImpl2 implements Task{
    private final String url;
    private final String path;
    private static final int BUFFER_SIZE = 1024;
    private volatile int bytesRead = -1;
    private final String fileName;
    private volatile Boolean isRunning = false;

    public TaskImpl2(String path, String url, String fileName) {
        this.path = path;
        this.url = url;
        this.fileName = fileName;
    }

    @Override
    public void start() {
        try (BufferedInputStream in = new BufferedInputStream(URI.create(url).toURL().openStream());
             FileOutputStream out = new FileOutputStream(String.format("%s/%s.txt", path, fileName))) {
            isRunning = true;
            System.out.println("start");

            byte[] dataBuffer = new byte[BUFFER_SIZE];
            while (isRunning && (bytesRead = in.read(dataBuffer, 0, BUFFER_SIZE)) != -1) {
                out.write(dataBuffer, 0, bytesRead);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void stop() {
        try {
            while (!isRunning) {
                Thread.sleep(10);
            }
            isRunning = false;
            System.out.println("stop");
            if (bytesRead != -1) {
                Files.delete(Paths.get(String.format("%s/%s.txt", path, fileName)));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
