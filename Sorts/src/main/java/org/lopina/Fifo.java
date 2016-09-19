package org.lopina;

public interface Fifo<T> extends HeadObservable<T>
{
    void offer(T elem);
    T poll();
}
