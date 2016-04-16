package org.lopina.sort.impl;

import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.lopina.sort.Sort;
import org.lopina.sort.SortTest;
import org.lopina.sort.Sorts;

@RunWith(BlockJUnit4ClassRunner.class)
public class ShellSortTest extends SortTest {

    @Override
    protected <T> Sort<T> getSort() {
        return Sorts.shell(ShellSort.StepStrategy.SEDGEWICK);
    }
}
