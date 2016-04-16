package org.lopina.sort;

import org.lopina.sort.impl.InsertionSort;
import org.lopina.sort.impl.SelectionSort;
import org.lopina.sort.impl.ShellSort;

public final class Sorts {
    private Sorts() {
    }

    public static <T> SelectionSort<T> selection() {
        return new SelectionSort<T>();
    }

    public static <T> InsertionSort<T> insertion() {
        return new InsertionSort<T>();
    }

    public static <T> Sort<T> bubble() {
        return null;
    }

    public static <T> Sort<T> shell() {
        return new ShellSort<T>();
    }

    public static <T> Sort<T> shell(ShellSort.StepStrategy stepStrategy) {
        return new ShellSort<T>(stepStrategy);
    }

    public static <T> Sort<T> quick() {
        return null;
    }

    public static <T> Sort<T> merge() {
        return null;
    }

    public static <T> Sort<T> heap() {
        return null;
    }
}
