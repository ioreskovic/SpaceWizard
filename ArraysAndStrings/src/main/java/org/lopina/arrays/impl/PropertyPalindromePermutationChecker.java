package org.lopina.arrays.impl;

import org.lopina.arrays.PalindromePermutationChecker;

import java.util.HashMap;
import java.util.Map;

public class PropertyPalindromePermutationChecker<T> implements PalindromePermutationChecker<T>
{
    @Override
    public boolean hasPalindromePermutation(T[] input)
    {
        Map<T, Boolean> pppMap = new HashMap<>();

        for (T t : input) {
            Boolean currentValue = pppMap.getOrDefault(t, Boolean.FALSE);
            pppMap.put(t, !currentValue);
        }

        int trueCount = 0;

        for (Boolean b : pppMap.values()) {
            if (Boolean.TRUE.equals(b)) {
                trueCount++;
            }

            if (trueCount > 1) {
                return false;
            }
        }

        return true;
    }
}
