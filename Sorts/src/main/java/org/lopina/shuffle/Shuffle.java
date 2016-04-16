package org.lopina.shuffle;

import java.util.List;

public abstract class Shuffle<T> {
    public Shuffle() {
    }

    public abstract T[] shuffle(T[] input);
    public abstract List<T> shuffle(List<T> input);

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
}
