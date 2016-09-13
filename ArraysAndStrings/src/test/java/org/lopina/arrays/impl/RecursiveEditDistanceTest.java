package org.lopina.arrays.impl;

import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.lopina.arrays.EditDistance;
import org.lopina.arrays.EditDistanceTest;

@RunWith(BlockJUnit4ClassRunner.class)
public class RecursiveEditDistanceTest extends EditDistanceTest
{
    @Override
    protected <T> EditDistance<T> getEditDistance()
    {
        return new RecursiveEditDistance<>();
    }
}
