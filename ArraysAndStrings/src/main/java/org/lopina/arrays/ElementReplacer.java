package org.lopina.arrays;

public interface ElementReplacer<T> {
    void replace(T[] input, T replacee, T[] replacement);
}
