package org.lopina;

public abstract class Deque<T> implements Fifo<T>, Lifo<T>
{
    public abstract void append(T elem);
    public abstract void prepend(T elem);
    public abstract T first();
    public abstract T last();
    public abstract T head();
    public abstract T tail();

    @Override
    public void offer(T elem)
    {
        append(elem);
    }

    @Override
    public T poll()
    {
        return first();
    }

    @Override
    public void push(T elem)
    {
        prepend(elem);
    }

    @Override
    public T pop()
    {
        return first();
    }
}
