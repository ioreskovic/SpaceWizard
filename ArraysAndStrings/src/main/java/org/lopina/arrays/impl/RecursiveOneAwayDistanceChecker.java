package org.lopina.arrays.impl;

import org.lopina.arrays.EditDistance;
import org.lopina.arrays.OneAwayDistanceChecker;

public class RecursiveOneAwayDistanceChecker<T> implements OneAwayDistanceChecker<T>
{
    private final EditDistance<T> editDistanceCalculator;

    public RecursiveOneAwayDistanceChecker(EditDistance<T> editDistanceCalculator)
    {
        this.editDistanceCalculator = editDistanceCalculator;
    }

    @Override
    public boolean isOneAway(T[] xs, T[] ys)
    {
        return this.editDistanceCalculator.editDistance(xs, ys) <= 1;
    }
}
