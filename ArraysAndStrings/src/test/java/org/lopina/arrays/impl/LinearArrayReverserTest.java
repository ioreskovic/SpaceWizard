package org.lopina.arrays.impl;

import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.lopina.arrays.ArrayReverser;
import org.lopina.arrays.ArrayReverserTest;

@RunWith(BlockJUnit4ClassRunner.class)
public class LinearArrayReverserTest extends ArrayReverserTest {

    @Override
    protected <T> ArrayReverser<T> getArrayReverser() {
        return new LinearArrayReverser<T>();
    }
}
