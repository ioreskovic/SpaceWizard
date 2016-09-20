package org.lopina;

public class QueueByStack<T> implements Fifo<T>, SizeAware
{
    private Stack<T> pushStack;
    private Stack<T> popStack;

    public QueueByStack()
    {
        this.pushStack = new Stack<>();
        this.popStack = new Stack<>();
    }

    @Override
    public void offer(T elem)
    {
        pushStack.push(elem);
    }

    @Override
    public T poll()
    {
        if (popStack.isEmpty()) {
            while (!pushStack.isEmpty()) {
                popStack.push(pushStack.pop());
            }
        }

        return popStack.pop();
    }

    @Override
    public T first()
    {
        if (popStack.isEmpty()) {
            while (!pushStack.isEmpty()) {
                popStack.push(pushStack.pop());
            }
        }

        return popStack.first();
    }

    @Override
    public int size()
    {
        return pushStack.size() + popStack.size();
    }

    @Override
    public boolean isEmpty()
    {
        return pushStack.isEmpty() && popStack.isEmpty();
    }
}
