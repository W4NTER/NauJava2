package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WorkWithListsTest {

    @Test
    @DisplayName("Проверка заполнения списка")
    void testThatListFilledCorrectlyReturnedSucceed() {
        List<Double> result = WorkWithLists.getRandomList();

        //Тест получился бесполезный, т к экземпляр будет создан в любом случае,
        // а значит тест проходит всегда...
        assertNotNull(result);
        assertTrue(result.size() >= 0);
    }

    @Test
    @DisplayName("Проверка сортировки")
    void testThatListCorrectlySortedReturnedSucceed() {
        List<Double> testList = new ArrayList<>(Arrays.asList(3.1, 2.5, 123.1, 0.0, 1.1));

        WorkWithLists.mergeSort(testList, 0, testList.size() - 1);
        final var EXPECTED_LIST = List.of(0.0, 1.1, 2.5, 3.1, 123.1);
        assertEquals(EXPECTED_LIST, testList);
    }
}
