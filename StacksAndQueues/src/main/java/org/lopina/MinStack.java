package org.lopina;

public class MinStack<T extends Comparable<T>> extends Stack<T>
{

    private Stack<T> minStack;

    public MinStack()
    {
        super();
        this.minStack = new Stack<>();
    }

    public T min() {
        return this.minStack.first();
    }

    @Override
    public void push(T elem)
    {
        super.push(elem);

        if (minStack.isEmpty()) {
            minStack.push(elem);
        } else {
            T currMin = minStack.first();
            if (elem.compareTo(currMin) <= 0) {
                minStack.push(elem);
            }
        }
    }

    @Override
    public T pop()
    {
        T elem = super.pop();

        if (elem.compareTo(minStack.first()) == 0) {
            minStack.pop();
        }

        return elem;
    }
}
