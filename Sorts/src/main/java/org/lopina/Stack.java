package org.lopina;

import java.util.NoSuchElementException;

public class Stack<T> implements Lifo<T>, SizeAware
{
    private StackNode<T> stackNode;
    private int size;

    public Stack()
    {
        this.stackNode = new EmptyNode<>();
        this.size = 0;
    }

    @Override
    public T first()
    {
        return stackNode.elem();
    }

    @Override
    public int size()
    {
        return size;
    }

    @Override
    public boolean isEmpty()
    {
        return stackNode.isEmpty();
    }

    private interface StackNode<E> {
        E elem();
        StackNode<E> next();
        StackNode<E> withNext(StackNode<E> node);
        boolean isEmpty();

    }

    private static class DataNode<E> implements StackNode<E> {

        public DataNode(E elem)
        {
            this.elem = elem;
            this.next = new EmptyNode<>();
        }

        public DataNode(E elem, StackNode<E> next)
        {
            this.elem = elem;
            this.next = next;
        }

        E elem;
        StackNode<E> next;

        @Override
        public E elem()
        {
            return elem;
        }

        @Override
        public StackNode<E> next()
        {
            return next;
        }

        @Override
        public StackNode<E> withNext(StackNode<E> node)
        {
            this.next = node;
            return this;
        }

        @Override
        public boolean isEmpty()
        {
            return false;
        }
    }

    private static class EmptyNode<E> implements StackNode<E> {

        @Override
        public E elem()
        {
            throw new NoSuchElementException("Empty stack node");
        }

        @Override
        public StackNode<E> next()
        {
            throw new NoSuchElementException("Empty stack node");
        }

        @Override
        public StackNode<E> withNext(StackNode<E> node)
        {
            return node;
        }

        @Override
        public boolean isEmpty()
        {
            return true;
        }
    }

    @Override
    public void push(T elem)
    {
        DataNode<T> dataNode = new DataNode<>(elem, stackNode);
        stackNode = dataNode.withNext(stackNode);
        size++;
    }

    @Override
    public T pop()
    {
        T data = stackNode.elem();
        stackNode = stackNode.next();
        size--;
        return data;
    }
}
