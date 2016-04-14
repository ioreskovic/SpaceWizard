package org.lopina.sort;

import java.util.Comparator;
import java.util.List;

public abstract class Sort<T> {
    public Sort() {
    }

    public abstract T[] sort(T[] input, Comparator<? super T> comparator);
    public abstract List<T> sort(List<T> input, Comparator<? super T> comparator);

    protected void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    protected void swap(List<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    protected boolean less(T first, T second, Comparator<? super T> comparator) {
        return comparator.compare(first, second) < 0;
    }
}
