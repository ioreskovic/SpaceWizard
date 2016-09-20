package org.lopina;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.NoSuchElementException;

@RunWith(BlockJUnit4ClassRunner.class)
public class MinStackTest
{
    @Test(expected = NoSuchElementException.class)
    public void minOfEmptyStackShouldThrowException() throws Exception
    {
        MinStack<Integer> stack = new MinStack<>();
        stack.min();
    }

    @Test
    public void minAfterPushingOntoEmptyStackShouldYieldThatElement() throws Exception
    {
        MinStack<Integer> stack = new MinStack<>();
        Integer elem = 10;
        stack.push(elem);
        Assert.assertEquals(elem, stack.min());
    }

    @Test
    public void pushingMultipleSameElementsShouldYieldThatElementAsMin() throws Exception
    {
        MinStack<Integer> stack = new MinStack<>();
        Integer elem = 10;

        for (int i = 0; i < 10; i++)
        {
            stack.push(elem);
            Assert.assertEquals(elem, stack.min());
        }
    }

    @Test
    public void minAfterPoppingOneOfMultipleSameElementsShouldBeThatElement() throws Exception {
        MinStack<Integer> stack = new MinStack<>();
        Integer elem = 10;

        for (int i = 0; i < 10; i++)
        {
            stack.push(elem);
            Assert.assertEquals(elem, stack.min());
        }

        for (int i = 0; i < 9; i++)
        {
            Assert.assertEquals(elem, stack.min());
            stack.pop();
        }
    }

    @Test
    public void minShouldProduceMinElementInStack() throws Exception {
        MinStack<Integer> stack = new MinStack<>();

        stack.push(10);
        Assert.assertEquals(Integer.valueOf(10), stack.min());
        stack.push(5);
        Assert.assertEquals(Integer.valueOf(5), stack.min());
        stack.push(4);
        Assert.assertEquals(Integer.valueOf(4), stack.min());
        stack.push(9);
        Assert.assertEquals(Integer.valueOf(4), stack.min());
        stack.push(8);
        Assert.assertEquals(Integer.valueOf(4), stack.min());
        stack.push(3);
        Assert.assertEquals(Integer.valueOf(3), stack.min());
        stack.push(2);
        Assert.assertEquals(Integer.valueOf(2), stack.min());
        stack.push(7);
        Assert.assertEquals(Integer.valueOf(2), stack.min());
        stack.push(6);
        Assert.assertEquals(Integer.valueOf(2), stack.min());
        stack.push(1);
        Assert.assertEquals(Integer.valueOf(1), stack.min());
        stack.push(0);
        Assert.assertEquals(Integer.valueOf(0), stack.min());

        stack.pop();
        Assert.assertEquals(Integer.valueOf(1), stack.min());
        stack.pop();
        Assert.assertEquals(Integer.valueOf(2), stack.min());
        stack.pop();
        Assert.assertEquals(Integer.valueOf(2), stack.min());
        stack.pop();
        Assert.assertEquals(Integer.valueOf(2), stack.min());
        stack.pop();
        Assert.assertEquals(Integer.valueOf(3), stack.min());
        stack.pop();
        Assert.assertEquals(Integer.valueOf(4), stack.min());
        stack.pop();
        Assert.assertEquals(Integer.valueOf(4), stack.min());
        stack.pop();
        Assert.assertEquals(Integer.valueOf(4), stack.min());
        stack.pop();
        Assert.assertEquals(Integer.valueOf(5), stack.min());
        stack.pop();
        Assert.assertEquals(Integer.valueOf(10), stack.min());
    }
}
