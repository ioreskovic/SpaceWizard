package org.lopina;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.NoSuchElementException;

@RunWith(BlockJUnit4ClassRunner.class)
public class StackTest
{
    @Test
    public void stackShouldBeCreatedEmpty() throws Exception {
        Stack<Integer> stack = new Stack<>();
        Assert.assertEquals(0, stack.size());
        Assert.assertTrue(stack.isEmpty());
    }

    @Test(expected = NoSuchElementException.class)
    public void checkingHeadOnEmptyStackShouldThrowException() throws Exception {
        Stack<Integer> stack = new Stack<>();
        stack.first();
    }

    @Test(expected = NoSuchElementException.class)
    public void extractingHeadOnEmptyStackShouldThrowException() throws Exception {
        Stack<Integer> stack = new Stack<>();
        stack.pop();
    }

    @Test
    public void pushingOntoEmptyStackShouldProduceStackOfSizeOneWithThatElementOnTop() throws Exception {
        Stack<Integer> stack = new Stack<>();
        Integer elem = 42;
        stack.push(elem);
        Assert.assertEquals(1, stack.size());
        Assert.assertEquals(elem, stack.first());
        Assert.assertFalse(stack.isEmpty());
    }

    @Test
    public void poppingFromSingletonStackShouldProduceEmptyStack() throws Exception {
        Stack<Integer> stack = new Stack<>();
        Integer elem = 42;
        stack.push(elem);
        Integer poppedElem = stack.pop();
        Assert.assertEquals(elem, poppedElem);
        Assert.assertEquals(0, stack.size());
        Assert.assertTrue(stack.isEmpty());
    }

    @Test
    public void pushingElementsShouldIncreaseSizeAccordingly() throws Exception {
        Stack<Integer> stack = new Stack<>();

        int oldSize = stack.size();

        for (int i = 0; i < 10; i++) {
            Assert.assertEquals(oldSize, stack.size());
            stack.push(i);
            Assert.assertEquals(oldSize + 1, stack.size());
            Assert.assertFalse(stack.isEmpty());
            oldSize = stack.size();
        }
    }

    @Test
    public void stackShouldStoreElementsInLifoOrder() throws Exception {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(5);

        Assert.assertEquals(Integer.valueOf(5), stack.pop());
        Assert.assertEquals(Integer.valueOf(3), stack.pop());
        Assert.assertEquals(Integer.valueOf(2), stack.pop());
        Assert.assertEquals(Integer.valueOf(1), stack.pop());
        Assert.assertEquals(Integer.valueOf(1), stack.pop());
    }
}
