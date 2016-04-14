package org.lopina.arrays.impl;

import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.lopina.arrays.PermutationCheckerTest;

@RunWith(BlockJUnit4ClassRunner.class)
public class MapBasedPermutationCheckerTest extends PermutationCheckerTest {

    @Override
    protected <T> MapBasedPermutationChecker<T> getPermutationChecker() {
        return new MapBasedPermutationChecker<>();
    }
}
