package org.lopina;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.NoSuchElementException;

@RunWith(BlockJUnit4ClassRunner.class)
public class NodeDequeTest
{
    @Test
    public void creatingDequeShouldInstantiateAsEmpty() throws Exception {
        NodeDeque<Integer> deque = new NodeDeque<>();
        Assert.assertEquals(0, deque.size());
    }

    @Test(expected = NoSuchElementException.class)
    public void callingHeadOnEmptyDequeShouldThrowException() throws Exception {
        NodeDeque<Integer> deque = new NodeDeque<>();
        deque.head();
    }

    @Test(expected = NoSuchElementException.class)
    public void callingTailOnEmptyDequeShouldThrowException() throws Exception {
        NodeDeque<Integer> deque = new NodeDeque<>();
        deque.tail();
    }

    @Test(expected = NoSuchElementException.class)
    public void callingFirstOnEmptyDequeShouldThrowException() throws Exception {
        NodeDeque<Integer> deque = new NodeDeque<>();
        deque.first();
    }

    @Test(expected = NoSuchElementException.class)
    public void callingLastOnEmptyDequeShouldThrowException() throws Exception {
        NodeDeque<Integer> deque = new NodeDeque<>();
        deque.last();
    }

    @Test
    public void prependingToDequeShouldAddElementInTheFront() throws Exception {
        NodeDeque<Integer> deque = new NodeDeque<>();
        int max = 3;
        int min = 0;

        for (int i = max; i >= min; i--) {
            deque.prepend(i);
            Assert.assertEquals(Integer.valueOf(i), deque.head());
            Assert.assertEquals(Integer.valueOf(max), deque.tail());
        }
    }

    @Test
    public void appendingToDequeShouldAddElementInTheEnd() throws Exception {
        NodeDeque<Integer> deque = new NodeDeque<>();
        int max = 3;
        int min = 0;

        for (int i = min; i <= max; i++) {
            deque.append(i);
            Assert.assertEquals(Integer.valueOf(i), deque.tail());
            Assert.assertEquals(Integer.valueOf(min), deque.head());
        }
    }

    @Test
    public void pollingFirstUntilDequeIsEmptyShouldYieldEmptyDeque() throws Exception {
        NodeDeque<Integer> deque = new NodeDeque<>();

        deque.append(3);
        deque.prepend(2);
        deque.append(4);
        deque.append(5);
        deque.prepend(1);

        while (deque.size() > 0) {
            deque.first();
        }

        Assert.assertEquals(0, deque.size());
    }

    @Test
    public void pollingLastUntilDequeIsEmptyShouldYieldEmptyDeque() throws Exception {
        NodeDeque<Integer> deque = new NodeDeque<>();

        deque.prepend(3);
        deque.prepend(2);
        deque.append(4);
        deque.append(5);
        deque.prepend(1);

        while (deque.size() > 0) {
            deque.last();
        }

        Assert.assertEquals(0, deque.size());
    }

}
