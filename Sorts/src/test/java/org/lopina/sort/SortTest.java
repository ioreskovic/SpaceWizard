package org.lopina.sort;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RunWith(BlockJUnit4ClassRunner.class)
public abstract class SortTest {
    protected abstract <T> Sort<T> getSort();

    @Test(expected = NullPointerException.class)
    public void exceptionShouldBeThrownWhenTryingToSortNullArray() throws Exception {
        Integer[] input = null;
        Sort<Integer> sort = getSort();
        sort.sort(input, Comparator.<Integer>naturalOrder());
    }

    @Test(expected = NullPointerException.class)
    public void exceptionShouldBeThrownWhenTryingToSortNullList() throws Exception {
        List<Integer> input = null;
        Sort<Integer> sort = getSort();
        sort.sort(input, Comparator.<Integer>naturalOrder());
    }

    @Test
    public void nothingShouldBeDoneWhenTryingToSortEmptyArray() throws Exception {
        Integer[] input = new Integer[0];
        Sort<Integer> sort = getSort();
        sort.sort(input, Comparator.<Integer>naturalOrder());

        Assert.assertEquals(0, input.length);
    }

    @Test
    public void nothingShouldBeDoneWhenTryingToSortEmptyList() throws Exception {
        List<Integer> input = new ArrayList<>();
        Sort<Integer> sort = getSort();
        sort.sort(input, Comparator.<Integer>naturalOrder());

        Assert.assertEquals(0, input.size());
    }

    @Test
    public void arrayShouldRemainSortedWhenTryingToSortAlreadySortedArray() throws Exception {
        Integer[] input = new Integer[]{
                0, 1, 2, 3, 4, 5
        };
        Sort<Integer> sort = getSort();
        sort.sort(input, Comparator.<Integer>naturalOrder());

        Assert.assertEquals(6, input.length);

        Integer[] expected = new Integer[]{
                0, 1, 2, 3, 4, 5
        };

        Assert.assertArrayEquals(expected, input);
    }

    @Test
    public void listShouldRemainSortedWhenTryingToSortAlreadySortedList() throws Exception {
        List<Integer> input = new ArrayList<Integer>() {{
            add(0); add(1); add(2); add(3); add(4); add(5);
        }};
        Sort<Integer> sort = getSort();
        sort.sort(input, Comparator.<Integer>naturalOrder());

        Assert.assertEquals(6, input.size());

        List<Integer> expected = new ArrayList<Integer>() {{
            add(0); add(1); add(2); add(3); add(4); add(5);
        }};

        Assert.assertEquals(expected, input);
    }

    @Test
    public void arrayShouldBeSortedCorrectlyWhenTryingToSortUnorderedArray() throws Exception {
        Integer[] input = new Integer[]{
                5, 1, 3, 4, 2, 0
        };
        Sort<Integer> sort = getSort();
        sort.sort(input, Comparator.<Integer>naturalOrder());

        Assert.assertEquals(6, input.length);

        Integer[] expected = new Integer[]{
                0, 1, 2, 3, 4, 5
        };

        Assert.assertArrayEquals(expected, input);
        assertOrdered(input, Comparator.<Integer>naturalOrder());
    }

    @Test
    public void listShouldBeSortedCorrectlyWhenTryingToSortUnorderedList() throws Exception {
        List<Integer> input = new ArrayList<Integer>() {{
            add(5); add(1); add(3); add(4); add(2); add(0);
        }};
        Sort<Integer> sort = getSort();
        sort.sort(input, Comparator.<Integer>naturalOrder());

        Assert.assertEquals(6, input.size());

        List<Integer> expected = new ArrayList<Integer>() {{
            add(0); add(1); add(2); add(3); add(4); add(5);
        }};

        Assert.assertEquals(expected, input);
        assertOrdered(input, Comparator.<Integer>naturalOrder());
    }

    protected static final <T> void assertOrdered(T[] input, Comparator<? super T> comparator) {
        int n = input.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                T expectedLEQ = input[i];
                T expectedGEQ = input[j];

                Assert.assertTrue(comparator.compare(expectedLEQ, expectedGEQ) <= 0);
            }
        }
    }

    protected static final <T> void assertOrdered(List<T> input, Comparator<? super T> comparator) {
        int n = input.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                T expectedLEQ = input.get(i);
                T expectedGEQ = input.get(j);

                Assert.assertTrue(comparator.compare(expectedLEQ, expectedGEQ) <= 0);
            }
        }
    }
}
