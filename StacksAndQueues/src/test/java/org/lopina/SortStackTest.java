package org.lopina;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@RunWith(BlockJUnit4ClassRunner.class)
public class SortStackTest
{
    @Test
    public void creatingNewStackShouldCreateEmptyStack() throws Exception {
        SortStack<Integer> stack = new SortStack<>();
        Assert.assertTrue(stack.isEmpty());
    }

    @Test(expected = NoSuchElementException.class)
    public void poppingFromEmptyStackShouldThrowException() throws Exception {
        SortStack<Integer> stack = new SortStack<>();
        stack.pop();
    }

    @Test
    public void poppingFromSingletonStackShouldReturnSingletonElement() throws Exception {
        SortStack<Integer> stack = new SortStack<>();
        Integer elem = Integer.MAX_VALUE;
        stack.push(elem);
        Assert.assertEquals(elem, stack.pop());
    }

    @Test
    public void poppingAfterPushingUnorderedSequenceShouldProduceOrderedSequence() throws Exception {
        SortStack<Integer> stack = new SortStack<>();
        List<Integer> input = new ArrayList<Integer>(){{
            add(9); add(8); add(7); add(6); add(5); add(4); add(3); add(2); add(1); add(0);
        }};

        input.forEach(stack::push);

        List<Integer> expected = new ArrayList<>(input);
        Collections.sort(expected);

        List<Integer> actual = new ArrayList<>();
        while (!stack.isEmpty()) {
            actual.add(stack.pop());
        }

        Assert.assertEquals(expected, actual);
    }
}
