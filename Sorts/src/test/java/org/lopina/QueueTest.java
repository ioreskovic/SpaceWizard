package org.lopina;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.NoSuchElementException;

@RunWith(BlockJUnit4ClassRunner.class)
public class QueueTest
{
    @Test
    public void queueShouldBeCreatedEmpty() throws Exception {
        Queue<Integer> stack = new Queue<>();
        Assert.assertEquals(0, stack.size());
        Assert.assertTrue(stack.isEmpty());
    }

    @Test(expected = NoSuchElementException.class)
    public void checkingHeadOnEmptyQueueShouldThrowException() throws Exception {
        Queue<Integer> stack = new Queue<>();
        stack.first();
    }

    @Test(expected = NoSuchElementException.class)
    public void extractingHeadOnEmptyQueueShouldThrowException() throws Exception {
        Queue<Integer> stack = new Queue<>();
        stack.poll();
    }

    @Test
    public void pushingOntoEmptyQueueShouldProduceStackOfSizeOneWithThatElementInTheEnd() throws Exception {
        Queue<Integer> stack = new Queue<>();
        Integer elem = 42;
        stack.offer(elem);
        Assert.assertEquals(1, stack.size());
        Assert.assertEquals(elem, stack.first());
        Assert.assertFalse(stack.isEmpty());
    }

    @Test
    public void poppingFromSingletonQueueShouldProduceEmptyStack() throws Exception {
        Queue<Integer> stack = new Queue<>();
        Integer elem = 42;
        stack.offer(elem);
        Integer poppedElem = stack.poll();
        Assert.assertEquals(elem, poppedElem);
        Assert.assertEquals(0, stack.size());
        Assert.assertTrue(stack.isEmpty());
    }

    @Test
    public void pushingElementsShouldIncreaseSizeAccordingly() throws Exception {
        Queue<Integer> stack = new Queue<>();

        int oldSize = stack.size();

        for (int i = 0; i < 10; i++) {
            Assert.assertEquals(oldSize, stack.size());
            stack.offer(i);
            Assert.assertEquals(oldSize + 1, stack.size());
            Assert.assertFalse(stack.isEmpty());
            oldSize = stack.size();
        }
    }

    @Test
    public void queueShouldStoreElementsInFifoOrder() throws Exception {
        Queue<Integer> stack = new Queue<>();
        stack.offer(1);
        stack.offer(1);
        stack.offer(2);
        stack.offer(3);
        stack.offer(5);

        Assert.assertEquals(Integer.valueOf(1), stack.poll());
        Assert.assertEquals(Integer.valueOf(1), stack.poll());
        Assert.assertEquals(Integer.valueOf(2), stack.poll());
        Assert.assertEquals(Integer.valueOf(3), stack.poll());
        Assert.assertEquals(Integer.valueOf(5), stack.poll());
    }
}
