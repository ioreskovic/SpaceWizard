package org.lopina.arrays.impl;

import org.lopina.arrays.ArrayReverser;

import java.util.Arrays;

public class LinearArrayReverser<T> implements ArrayReverser<T> {
    public T[] reversed(T[] original) {
        T[] reversed = Arrays.copyOf(original, original.length);
        reverse(reversed);

        return reversed;
    }

    public void reverse(T[] array) {
        int length = array.length;

        for (int i = 0; i < length / 2; i++) {
            swap(array, i, length - i - 1);
        }
    }

    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
