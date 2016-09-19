package org.lopina;

public interface Lifo<T> extends HeadObservable<T>
{
    void push(T elem);
    T pop();
}
