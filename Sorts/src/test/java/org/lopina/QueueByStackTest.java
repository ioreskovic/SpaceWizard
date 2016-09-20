package org.lopina;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.NoSuchElementException;

@RunWith(BlockJUnit4ClassRunner.class)
public class QueueByStackTest
{
    @Test
    public void queueShouldBeCreatedEmpty() throws Exception
    {
        QueueByStack<Integer> stack = new QueueByStack<>();
        Assert.assertEquals(0, stack.size());
        Assert.assertTrue(stack.isEmpty());
    }

    @Test(expected = NoSuchElementException.class)
    public void checkingHeadOnEmptyQueueShouldThrowException() throws Exception
    {
        QueueByStack<Integer> stack = new QueueByStack<>();
        stack.first();
    }

    @Test(expected = NoSuchElementException.class)
    public void extractingHeadOnEmptyQueueShouldThrowException() throws Exception
    {
        QueueByStack<Integer> stack = new QueueByStack<>();
        stack.poll();
    }

    @Test
    public void pushingOntoEmptyQueueShouldProduceStackOfSizeOneWithThatElementInTheEnd() throws Exception
    {
        QueueByStack<Integer> stack = new QueueByStack<>();
        Integer elem = 42;
        stack.offer(elem);
        Assert.assertEquals(1, stack.size());
        Assert.assertEquals(elem, stack.first());
        Assert.assertFalse(stack.isEmpty());
    }

    @Test
    public void poppingFromSingletonQueueShouldProduceEmptyStack() throws Exception
    {
        QueueByStack<Integer> stack = new QueueByStack<>();
        Integer elem = 42;
        stack.offer(elem);
        Integer poppedElem = stack.poll();
        Assert.assertEquals(elem, poppedElem);
        Assert.assertEquals(0, stack.size());
        Assert.assertTrue(stack.isEmpty());
    }

    @Test
    public void pushingElementsShouldIncreaseSizeAccordingly() throws Exception
    {
        QueueByStack<Integer> stack = new QueueByStack<>();

        int oldSize = stack.size();

        for (int i = 0; i < 10; i++)
        {
            Assert.assertEquals(oldSize, stack.size());
            stack.offer(i);
            Assert.assertEquals(oldSize + 1, stack.size());
            Assert.assertFalse(stack.isEmpty());
            oldSize = stack.size();
        }
    }

    @Test
    public void queueShouldStoreElementsInFifoOrder() throws Exception
    {
        QueueByStack<Integer> stack = new QueueByStack<>();
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
