package edu.hw1;

import java.util.Arrays;
import java.util.Random;

public final class WorkWithArrays {
    private WorkWithArrays() {}

    //чтобы не ловить ошибки переполнения памяти, я сильно ограничил рандом
    public static int[] createArrayWithRandomVals() {
        Random random = new Random();
        return Arrays.stream(new int[Math.abs(random.nextInt((int)1e2))])
                .map(a -> random.nextInt()).toArray();
    }

    public static int getMaxAbsValueOfArray(int[] array) {
        return Arrays.stream(array).map(Math::abs).max().orElse(Integer.MIN_VALUE);
    }

    public static int getMinValueOfArray(int[] array) {
        return Arrays.stream(array).map(Math::abs).min().orElse(Integer.MAX_VALUE);
    }

    //Мой вариант
    public static double getAverageValueOfArray(int[] array) {
        return (double) Arrays.stream(array)
                .summaryStatistics().getSum() / array.length;
    }

    public static int getLastPositiveValue(int[] array) {
        int res = -1;
         for (int i = array.length - 1; i >= 0; i--) {
             if (array[i] > 0) {
                 return array[i];
             }
         }
        return res;
    }

    public static int getSumOfPositiveElOfArray(int[] array) {
        return Arrays.stream(array).filter(a -> a > 0).sum();
    }

}
