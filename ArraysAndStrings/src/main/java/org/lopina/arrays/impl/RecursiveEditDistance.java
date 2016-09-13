package org.lopina.arrays.impl;

import org.lopina.arrays.EditDistance;

public class RecursiveEditDistance<T> implements EditDistance<T>
{
    @Override
    public int editDistance(T[] xs, T[] ys)
    {
        return editDistance(xs, 0, ys, 0);
    }

    private int editDistance(T[] xs, int i, T[] ys, int j)
    {
        if (i == xs.length && j == ys.length) {
            return 0;
        } else if (i == xs.length) {
            return ys.length - j;
        } else if (j == ys.length) {
            return xs.length - i;
        } else if (!xs[i].equals(ys[i])) {
            int swapDistance = editDistance(xs, i + 1, ys, j + 1);
            int addDistance = editDistance(xs, i, ys, j + 1);
            int delDistance = editDistance(xs, i + 1, ys, j);

            return 1 + Math.min(swapDistance, Math.min(addDistance, delDistance));
        } else {
            return editDistance(xs, i + 1, ys, j + 1);
        }
    }
}
