package edu.hw1.streamAPI;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StreamAPITest {
    private final static List<Employee> EMPLOYEES = StreamAPI.fillList();

    @Test
    @DisplayName("Проверка подсчета средней зарплаты по департаменту")
    void testThatGetAverageSalaryByDepartmentReturnedSucceed() {
        String DEPARTMENT = "IT";

        Double result = StreamAPI.averageSalaryByDepartment(EMPLOYEES, DEPARTMENT);

        Double EXPECTED_RESULT = 90_000.0;
        assertEquals(EXPECTED_RESULT, result);
    }

    @Test
    @DisplayName("Проверка количества сотрудников старше 30")
    void testThatGetCountEmployeesOldestThirtyReturnedSucceed() {
        List<Employee> result = StreamAPI.employeesOldestThirty(EMPLOYEES);

        int EXPECTED_VALUE = 2;
        assertEquals(EXPECTED_VALUE, result.size());
    }

    @Test
    @DisplayName("Проверка сортировки по зарплате")
    void testThatListSortedBySalaryReturnedSucceed() {
        List<Employee> result = StreamAPI.sortBySalary(EMPLOYEES);
        double[] res = {
                result.get(0).getSalary(),
                result.get(1).getSalary(),
                result.get(2).getSalary(),
                result.get(3).getSalary(),
                result.get(4).getSalary()
        };

        double[] EXPECTED_VALUE = {
                10_000.0,
                80_000.0,
                100_000.0,
                100_001.0,
                200_000.0,
        };
        assertArrayEquals(EXPECTED_VALUE, res);
    }

    @Test
    @DisplayName("Проверка правильности превращения листа объектов в лист строк")
    void testThatListObjectsToListStringsReturnedSucceed() {
        List<String> result = StreamAPI.toStringNameDepartment(EMPLOYEES);

        List<String> EXPECTED_LIST = List.of(
                "Kashapov Vadim Vinerovich - IT",
                "Litunov Maksim Igorevich - HR",
                "Ivanov Ivan Ivanovich - Management",
                "Kolbin Nikita Sergeevich - IT",
                "Saad Abdurahmed Saleh - Security");

        assertEquals(EXPECTED_LIST, result);
    }

    @Test
    @DisplayName("Проверка существования сотрудников с зп выше 100_000")
    void testThatEmployeesWithSalaryOverNReturnedSucceed() {
        Boolean result = StreamAPI.containsEmployeeOverNSalary(EMPLOYEES);

        assertTrue(result);
    }
}
