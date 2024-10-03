package edu.hw1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class WorkWithLists {

    public static List<Double> getRandomList() {
        List<Double> list = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < random.nextInt((int)1e2); i++) {
            list.add(random.nextDouble());
        }
        return list;
    }

    //Мой вариант, остальные не вижу смысла делать,
    // все есть в интернете и особого смысла переизобретать сортировки - нет.
    public static void sort(List<Double> list) {
        mergeSort(list, 0, list.size() - 1);
    }

    private static void mergeSort(List<Double> list, int first, int last) {
        if (last <= first) {
            return;
        }

        int mid = first + (last - first) / 2;
        mergeSort(list, first, mid);
        mergeSort(list, mid + 1, last);

        List<Double> buf = new ArrayList<>(list.subList(first, last + 1));

        int i = first, j = mid + 1;
        for (int k = first; k <= last; k++) {
            if (i > mid) {
                list.set(k, buf.get(j - first));
                j++;
            } else if (j > last) {
                list.set(k, buf.get(i - first));
                i++;
            } else if (buf.get(j - first) < buf.get(i - first)) {
                list.set(k, buf.get(j - first));
                j++;
            } else {
                list.set(k, buf.get(i - first));
                i++;
            }
        }
    }
}
