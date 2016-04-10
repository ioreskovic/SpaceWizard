package org.lopina.arrays.impl;

import org.lopina.arrays.ElementReplacer;

public class LinearElementReplacer<T> implements ElementReplacer<T> {

    @Override
    public void replace(T[] input, T replacee, T[] replacement) {
        int length = input.length;
        int replaceeCount = getReplaceeCount(input, replacee);
        int offsetSize = (replacement.length - 1) * replaceeCount;

        int i = length - 1;
        int j = i - offsetSize;

        while (i >= 0) {
            if (!replacee.equals(input[j])) {
                input[i--] = input[j--];
            } else {
                for (int k = replacement.length - 1; k >= 0; k--) {
                    input[i--] = replacement[k];
                }
                j--;
            }
        }
    }

    private static <T> int getReplaceeCount(T[] input, T replacee) {
        int count = 0;

        for (int i = 0; i < input.length; i++) {
            if (replacee.equals(input[i])) {
                count++;
            }
        }

        return count;
    }
}
