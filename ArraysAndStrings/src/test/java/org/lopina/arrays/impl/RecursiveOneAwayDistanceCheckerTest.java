package org.lopina.arrays.impl;

import org.lopina.arrays.OneAwayDistanceChecker;
import org.lopina.arrays.OneAwayDistanceCheckerTest;

public class RecursiveOneAwayDistanceCheckerTest extends OneAwayDistanceCheckerTest
{
    @Override
    protected <T> OneAwayDistanceChecker<T> getChecker()
    {
        return new RecursiveOneAwayDistanceChecker<T>(new RecursiveEditDistance<T>());
    }
}
