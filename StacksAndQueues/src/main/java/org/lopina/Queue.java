package org.lopina;

import java.util.NoSuchElementException;

public class Queue<T> implements Fifo<T>, SizeAware
{
    private interface QueueNode<E> {
        E elem();
        QueueNode<E> next();
        QueueNode<E> withNext(QueueNode<E> node);
        boolean isEmpty();
    }

    private static class DataNode<E> implements QueueNode<E> {

        E elem;
        QueueNode<E> next;

        public DataNode(E elem)
        {
            this.elem = elem;
            this.next = new EmptyNode<>();
        }

        public DataNode(E elem, DataNode<E> next)
        {
            this.elem = elem;
            this.next = next;
        }

        @Override
        public E elem()
        {
            return elem;
        }

        @Override
        public QueueNode<E> next()
        {
            return next;
        }

        @Override
        public QueueNode<E> withNext(QueueNode<E> node)
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

    private static class EmptyNode<E> implements QueueNode<E> {

        @Override
        public E elem()
        {
            throw new NoSuchElementException();
        }

        @Override
        public QueueNode<E> next()
        {
            throw new NoSuchElementException();
        }

        @Override
        public QueueNode<E> withNext(QueueNode<E> node)
        {
            return node;
        }

        @Override
        public boolean isEmpty()
        {
            return true;
        }
    }

    private QueueNode<T> queueNode;
    private QueueNode<T> lastNode;
    private int size;

    public Queue()
    {
        this.queueNode = new EmptyNode<>();
        this.lastNode = this.queueNode;
        this.size = 0;
    }

    @Override
    public void offer(T elem)
    {
        DataNode<T> newLastNode = new DataNode<T>(elem);

        if (isEmpty()) {
            this.queueNode = newLastNode;
            this.lastNode = newLastNode;
        } else {
            this.lastNode.withNext(newLastNode);
            this.lastNode = newLastNode;
        }

        size++;
    }

    @Override
    public T poll()
    {
        T data = this.queueNode.elem();
        this.queueNode = this.queueNode.next();
        size--;
        return data;
    }

    @Override
    public T first()
    {
        return this.queueNode.elem();
    }

    @Override
    public int size()
    {
        return size;
    }

    @Override
    public boolean isEmpty()
    {
        return this.queueNode.isEmpty();
    }
}
