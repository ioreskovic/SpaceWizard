package org.lopina.shuffle.impl;

import org.lopina.shuffle.Shuffle;

import java.util.List;
import java.util.Random;

public class KnuthShuffle<T> extends Shuffle<T> {

    @Override
    public T[] shuffle(T[] input) {
        int n = input.length;
        Random random = getRandom();

        for (int i = 0; i < n; i++) {
            int swapPosition = random.nextInt(i + 1);
            swap(input, i, swapPosition);
        }

        return input;
    }

    @Override
    public List<T> shuffle(List<T> input) {
        int n = input.size();
        Random random = getRandom();

        for (int i = 0; i < n; i++) {
            int swapPosition = random.nextInt(i + 1);
            swap(input, i, swapPosition);
        }

        return input;
    }

    private Random getRandom() {
        return new Random(System.currentTimeMillis());
    }
}
