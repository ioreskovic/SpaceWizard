package org.lopina.arrays.impl;

import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.lopina.arrays.ElementReplacerTest;

@RunWith(BlockJUnit4ClassRunner.class)
public class LinearElementReplacerTest extends ElementReplacerTest {

    @Override
    protected <T> LinearElementReplacer<T> getElementReplacer() {
        return new LinearElementReplacer<>();
    }
}
