package org.lopina;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.NoSuchElementException;

@RunWith(BlockJUnit4ClassRunner.class)
public class ArrayDequeTest
{
    @Test
    public void creatingDequeShouldInstantiateAsEmpty() throws Exception {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        Assert.assertEquals(0, deque.size());
    }

    @Test(expected = NoSuchElementException.class)
    public void callingHeadOnEmptyDequeShouldThrowException() throws Exception {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.head();
    }

    @Test(expected = NoSuchElementException.class)
    public void callingTailOnEmptyDequeShouldThrowException() throws Exception {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.tail();
    }

    @Test(expected = NoSuchElementException.class)
    public void callingFirstOnEmptyDequeShouldThrowException() throws Exception {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.first();
    }

    @Test(expected = NoSuchElementException.class)
    public void callingLastOnEmptyDequeShouldThrowException() throws Exception {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.last();
    }

    @Test
    public void prependingToDequeShouldAddElementInTheFront() throws Exception {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
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
        ArrayDeque<Integer> deque = new ArrayDeque<>();
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
        ArrayDeque<Integer> deque = new ArrayDeque<>();

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
        ArrayDeque<Integer> deque = new ArrayDeque<>();

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

    @Test
    public void appendingFullDequeSilentlyExpandCapacity() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        int previousCapacity = deque.capacity();

        for (int i = 8; i <= 15; i++) {
            deque.append(i);
        }

        for (int i = 7; i >= 0; i--) {
            deque.prepend(i);
        }

        int newCapacity = deque.capacity();

        deque.append(16);

        Assert.assertTrue(newCapacity > previousCapacity);
    }

    @Test
    public void prependingFullDequeShouldSilentlyExpandCapacity() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        int previousCapacity = deque.capacity();

        for (int i = 8; i <= 15; i++) {
            deque.append(i);
        }

        for (int i = 7; i >= 0; i--) {
            deque.prepend(i);
        }

        int newCapacity = deque.capacity();

        deque.prepend(0);

        Assert.assertTrue(newCapacity > previousCapacity);
    }

    @Test
    public void appendingOverCapacityShouldSilentlyExpandCapacity() throws Exception {
        int capacity = 16;
        ArrayDeque<Integer> deque = new ArrayDeque<>(capacity);

        int previousCapacity = deque.capacity();

        for (int i = 0; i < capacity; i++) {
            deque.append(i);
        }

        int newCapacity = deque.capacity();

        deque.append(capacity);

        Assert.assertTrue(newCapacity > previousCapacity);
    }

    @Test
    public void prependingOverCapacityShouldSilentlyExpandCapacity() throws Exception {
        int capacity = 16;
        ArrayDeque<Integer> deque = new ArrayDeque<>(capacity);

        int previousCapacity = deque.capacity();

        for (int i = 16; i > 0; i--) {
            deque.prepend(i);
        }

        int newCapacity = deque.capacity();

        deque.prepend(0);

        Assert.assertTrue(newCapacity > previousCapacity);
    }

    @Test
    public void takingFirstUnderCapacityShouldSilentlyShrinkArray() throws Exception {
        int capacity = 16;
        ArrayDeque<Integer> deque = new ArrayDeque<>(capacity);

        for (int i = 0; i < capacity / 4; i++) {
            if (i % 2 == 0) {
                deque.append(i);
            } else {
                deque.prepend(i);
            }
        }

        deque.prepend(100);

        int currentCapacity = deque.capacity();

        deque.first();

        int newCapacity = deque.capacity();

        Assert.assertTrue(newCapacity < currentCapacity);
    }

    @Test
    public void takingLastUnderCapacityShouldSilentlyShrinkArray() throws Exception {
        int capacity = 16;
        ArrayDeque<Integer> deque = new ArrayDeque<>(capacity);

        for (int i = 0; i < capacity / 4; i++) {
            if (i % 2 == 0) {
                deque.append(i);
            } else {
                deque.prepend(i);
            }
        }

        deque.append(100);

        int currentCapacity = deque.capacity();

        deque.last();

        int newCapacity = deque.capacity();

        Assert.assertTrue(newCapacity < currentCapacity);
    }
}
