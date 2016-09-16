package org.lopina.shuffle;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(BlockJUnit4ClassRunner.class)
public abstract class ShuffleTest {
    protected abstract <T> Shuffle<T> getShuffle();

    @Test(expected = NullPointerException.class)
    public void exceptionShouldBeThrownWhenTryingToShuffleNullArray() throws Exception {
        Integer[] input = null;
        Shuffle<Integer> sort = getShuffle();
        sort.shuffle(input);
    }

    @Test(expected = NullPointerException.class)
    public void exceptionShouldBeThrownWhenTryingToShuffleNullList() throws Exception {
        List<Integer> input = null;
        Shuffle<Integer> sort = getShuffle();
        sort.shuffle(input);
    }

    @Test
    public void nothingShouldBeDoneWhenTryingToShuffleEmptyArray() throws Exception {
        Integer[] input = new Integer[0];
        Shuffle<Integer> sort = getShuffle();
        sort.shuffle(input);

        Assert.assertEquals(0, input.length);
    }

    @Test
    public void nothingShouldBeDoneWhenTryingToShuffleEmptyList() throws Exception {
        List<Integer> input = new ArrayList<>();
        Shuffle<Integer> sort = getShuffle();
        sort.shuffle(input);

        Assert.assertEquals(0, input.size());
    }

    @Test
    public void noElementsShouldBeLostWhenTryingToShuffleAnArray() throws Exception {
        Integer[] input = new Integer[]{
                0, 1, 2, 3, 4, 5
        };

        Shuffle<Integer> sort = getShuffle();
        sort.shuffle(input);


    }

    @Test
    public void noElementsShouldBeLostWhenTryingToShuffleAList() throws Exception {
        List<Integer> input = new ArrayList<Integer>() {{
            add(0); add(1); add(2); add(3); add(4); add(5);
        }};

        Shuffle<Integer> sort = getShuffle();
        sort.shuffle(input);
    }

    protected static final <T> void assertNotLost(Map<T, Integer> countMap, List<T> input) {
        int n = input.size();

        for (int i = 0; i < n; i++) {
            T element = input.get(i);

            int count = countMap.getOrDefault(element, 0);

            if (count == 0) {
                Assert.fail();
            }

            countMap.put(element, count - 1);
        }

        boolean allEmpty = countMap.values().stream().allMatch(i -> i == 0);

        Assert.assertTrue(allEmpty);
    }

    protected static final <T> void assertNotLost(Map<T, Integer> countMap, T[] input) {
        int n = input.length;

        for (int i = 0; i < n; i++) {
            T element = input[i];

            int count = countMap.getOrDefault(element, 0);

            if (count == 0) {
                Assert.fail();
            }

            countMap.put(element, count - 1);
        }

        boolean allEmpty = countMap.values().stream().allMatch(i -> i == 0);

        Assert.assertTrue(allEmpty);
    }
}
