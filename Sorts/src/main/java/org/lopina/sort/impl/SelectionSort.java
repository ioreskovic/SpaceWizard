package org.lopina.sort.impl;

import org.lopina.sort.Sort;

import java.util.Comparator;
import java.util.List;

public class SelectionSort<T> extends Sort<T> {
    public T[] sort(T[] input, Comparator<? super T> comparator) {
        int n = input.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (less(input[j], input[i], comparator)) {
                    swap(input, i, j);
                }
            }
        }

        return input;
    }

    public List<T> sort(List<T> input, Comparator<? super T> comparator) {
        int n = input.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (less(input.get(j), input.get(i), comparator)) {
                    swap(input, i, j);
                }
            }
        }

        return input;
    }
}
