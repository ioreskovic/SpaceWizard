package org.lopina.sort.impl;

import org.lopina.sort.Sort;

import java.util.Comparator;
import java.util.List;

public class InsertionSort<T> extends Sort<T> {
    @Override
    public T[] sort(T[] input, Comparator<? super T> comparator) {
        int n = input.length;

        for (int i = 0; i < n; i++) {
            for (int j = i; j > 0; j--) {
                T left = input[j - 1];
                T right = input[j];

                if (less(right, left, comparator)) {
                    swap(input, j - 1, j);
                } else {
                    break;
                }
            }
        }

        return input;
    }

    @Override
    public List<T> sort(List<T> input, Comparator<? super T> comparator) {
        int n = input.size();

        for (int i = 0; i < n; i++) {
            for (int j = i; j > 0; j--) {
                T left = input.get(j - 1);
                T right = input.get(j);

                if (less(right, left, comparator)) {
                    swap(input, j - 1, j);
                } else {
                    break;
                }
            }
        }

        return input;
    }
}
