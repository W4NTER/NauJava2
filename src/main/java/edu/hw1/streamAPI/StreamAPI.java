package edu.hw1.streamAPI;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public final class StreamAPI {

    public static List<Employee> fillList() {
        List<Employee> employees = new ArrayList<>();
        employees.add(
                new Employee("Kashapov Vadim Vinerovich", 22, "IT", 100_000.0));
        employees.add(
                new Employee("Litunov Maksim Igorevich", 20, "HR", 10_000.0));
        employees.add(
                new Employee("Ivanov Ivan Ivanovich", 45, "Management", 200_000.0));
        employees.add(
                new Employee("Kolbin Nikita Sergeevich", 20, "IT", 80_000.0));
        employees.add(
                new Employee("Saad Abdurahmed Saleh", 32, "Security", 100_001.0));
        return employees;
    }

    //Мой вариант
    public static Double averageSalaryByDepartment(List<Employee> list, String department) {
        return list.stream().filter(employee -> employee.getDepartment().equals(department))
                .mapToDouble(Employee::getSalary)
                .average().orElseThrow(NoSuchElementException::new);
    }

    public static List<Employee> employeesOldestThirty(List<Employee> list) {
        return list.stream().filter(e -> e.getAge() > 30).collect(Collectors.toList());
    }

    public static List<Employee> sortBySalary(List<Employee> employees) {
        List<Employee> list = new ArrayList<>(employees);
        return list.stream().sorted(Comparator.comparing(Employee::getSalary)).toList();
    }

    public static List<String> toStringNameDepartment(List<Employee> employees) {
        return employees.stream()
                .map(e -> e.getFullName() + " - " + e.getDepartment())
                .collect(Collectors.toList());
    }

    public static Boolean containsEmployeeOverNSalary(List<Employee> list) {
        return list.stream().anyMatch(e -> e.getSalary() > 100_000.00);
    }
}
