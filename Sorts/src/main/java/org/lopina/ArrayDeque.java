package org.lopina;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayDeque<T> extends Deque<T> implements SizeAware
{
    private int capacity;
    private T[] array;
    private int headIdx;
    private int tailIdx;
    private int size;

    public ArrayDeque()
    {
        this(16);
    }

    public ArrayDeque(int capacity) {
        this.capacity = capacity;
        this.array = (T[]) new Object[this.capacity];
        this.headIdx = 0;
        this.tailIdx = 0;
        this.size = 0;
    }

    @Override
    public void append(T elem)
    {
        if (capIdx(tailIdx + 1, capacity) == capIdx(headIdx, capacity)) {
            throw new IllegalStateException("Full");
        } else if (size > 0) {
            tailIdx = capIdx(tailIdx + 1, capacity);
        }

        array[tailIdx] = elem;
        size++;
        expandIfNeeded();
    }

    @Override
    public void prepend(T elem)
    {
        if (capIdx(headIdx - 1, capacity) == capIdx(tailIdx, capacity)) {
            throw new IllegalStateException("Full");
        } else if (size > 0) {
            headIdx = capIdx(headIdx - 1, capacity);
        }

        array[headIdx] = elem;
        size++;
        expandIfNeeded();
    }

    @Override
    public T first()
    {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        T elem = array[headIdx];

        if (headIdx != tailIdx) {
            array[headIdx] = null;
            headIdx++;
        }

        size--;
        shrinkIfNeeded();
        return elem;
    }

    @Override
    public T last()
    {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        T elem = array[tailIdx];

        if (headIdx != tailIdx) {
            array[tailIdx] = null;
            tailIdx--;
        }

        size--;
        shrinkIfNeeded();
        return elem;
    }

    @Override
    public T head()
    {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        return array[headIdx];
    }

    @Override
    public T tail()
    {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        return array[tailIdx];
    }

    @Override
    public int size()
    {
        return 0;
    }

    public int capacity()
    {
        return capacity;
    }

    @Override
    public boolean isEmpty()
    {
        return size == 0;
    }

    private int capIdx(int idx, int cap) {
        return (idx + cap) % cap;
    }

    private void expandIfNeeded() {
        if (size == capacity) {
            T[] newArray = (T[]) new Object[capacity * 2];
            for (int i = 0; i < size; i++) {
                newArray[i] = array[capIdx(headIdx + i, capacity)];
            }
            array = newArray;
            capacity = newArray.length;
            headIdx = 0;
            tailIdx = size;
        }
    }

    private void shrinkIfNeeded() {
        if (size <= capacity / 4) {
            T[] newArray = (T[]) new Object[capacity / 2];
            for (int i = 0; i < size; i++) {
                newArray[i] = array[capIdx(headIdx + i, capacity)];
            }
            array = newArray;
            capacity = newArray.length;
            headIdx = 0;
            tailIdx = size;
        }
    }

    @Override
    public String toString()
    {
        return Arrays.toString(array);
    }
}
