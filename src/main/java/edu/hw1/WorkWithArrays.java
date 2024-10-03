package edu.hw1;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;

public final class WorkWithArrays {
    private WorkWithArrays() {}

    //чтобы не ловить ошибки переполнения памяти, я сильно ограничил рандом
    public static int[] createArrayWithRandomVals() {
        Random random = new Random();
        return Arrays.stream(new int[random.nextInt(0, (int)1e2)])
                .map(a -> random.nextInt()).toArray();
    }

    public static int getMaxAbsValueOfArray(int[] array) {
        return Arrays.stream(array).map(Math::abs).max()
                .orElseThrow(NoSuchElementException::new);
    }

    public static int getMinValueOfArray(int[] array) {
        return Arrays.stream(array).map(Math::abs).min()
                .orElseThrow(NoSuchElementException::new);
    }

    //Мой вариант
    public static double getAverageValueOfArray(int[] array) {
        return Arrays.stream(array)
                .summaryStatistics().getAverage();
    }

    public static int getLastPositiveValue(int[] array) {
        var arr = Arrays.stream(array).filter(el -> el > 0).toArray();
        return arr[arr.length - 1];
    }

    public static int getSumOfPositiveElOfArray(int[] array) {
        return Arrays.stream(array).filter(a -> a > 0).sum();
    }

}
