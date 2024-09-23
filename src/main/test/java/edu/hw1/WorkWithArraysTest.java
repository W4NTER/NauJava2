package edu.hw1;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorkWithArraysTest {
    private final static int[] GIVEN_ARRAY = {1, 2, -189, 3, 2};

    @Test
    @DisplayName("Проверка нахождения максимального элемента по модулю в массиве")
    void testThatGetMaxAbsElOfArrayReturnedSucceed() {

        int result = WorkWithArrays.getMaxAbsValueOfArray(GIVEN_ARRAY);

        final int EXPECTED_VALUE = 189;
        assertEquals(EXPECTED_VALUE, result);
    }

    @Test
    @DisplayName("Проверка нахождения минимального элемента по модулю в массиве")
    void testThatGetMinAbsElOfArrayReturnedSucceed() {
        int result = WorkWithArrays.getMinValueOfArray(GIVEN_ARRAY);

        final int EXPECTED_VALUE = 1;
        assertEquals(EXPECTED_VALUE, result);
    }

    @Test
    @DisplayName("Рассчет среднего значения в массиве")
    void testThatGetAverageValueOfGivenArrayReturnedSucceed() {
        double result = WorkWithArrays.getAverageValueOfArray(GIVEN_ARRAY);

        final double EXPECTED_VALUE = -36.2;
        assertEquals(EXPECTED_VALUE, result);
    }

    @Test
    @DisplayName("Нахождение последнего положительного элемента в массиве")
    void testThatGetLastPositiveElOfArrayReturnedSucceed() {
        int result = WorkWithArrays.getLastPositiveValue(GIVEN_ARRAY);

        final int EXPECTED_VALUE = 2;
        assertEquals(EXPECTED_VALUE, result);
    }

    @Test
    @DisplayName("Сумма положительных элементов массива")
    void testThatGetSumOfPositiveElsOfArrayReturnedSucceed() {
        int result = WorkWithArrays.getSumOfPositiveElOfArray(GIVEN_ARRAY);

        final int EXPECTED_VALUE = 8;
        assertEquals(EXPECTED_VALUE, result);
    }
}
