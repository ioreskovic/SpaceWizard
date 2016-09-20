package org.lopina;

public class SortStack<T extends Comparable<T>> implements Lifo<T>, SizeAware
{
    private Stack<T> s1;
    private Stack<T> s2;

    public SortStack()
    {
        this.s1 = new Stack<>();
        this.s2 = new Stack<>();
    }

    @Override
    public void push(T elem)
    {
        if (s1.isEmpty()) {
            s1.push(elem);
        } else {
            while (!s1.isEmpty() && s1.first().compareTo(elem) < 0) {
                s2.push(s1.pop());
            }
            s1.push(elem);
            while (!s2.isEmpty()) {
                s1.push(s2.pop());
            }
        }
    }

    @Override
    public T pop()
    {
        return s1.pop();
    }

    @Override
    public T first()
    {
        return s1.first();
    }

    @Override
    public int size()
    {
        return s1.size();
    }

    @Override
    public boolean isEmpty()
    {
        return s1.isEmpty();
    }
}
