package org.lopina.arrays.impl;

import org.lopina.arrays.PermutationChecker;

import java.util.HashMap;
import java.util.Map;

public class MapBasedPermutationChecker<T> implements PermutationChecker<T> {

    public boolean arePermutations(T[] array1, T[] array2) {
        if (array1.length != array2.length) {
            return false;
        }

        Map<T, Integer> elementCountMap = new HashMap<T, Integer>();

        for (T t1 : array1) {
            int count = elementCountMap.getOrDefault(t1, 0);
            elementCountMap.put(t1, count + 1);
        }

        for (T t2 : array2) {
            int count = elementCountMap.getOrDefault(t2, 0);
            elementCountMap.put(t2, count - 1);
        }

        for (Integer elementCount : elementCountMap.values()) {
            if (elementCount != 0) {
                return false;
            }
        }

        return true;
    }
}
