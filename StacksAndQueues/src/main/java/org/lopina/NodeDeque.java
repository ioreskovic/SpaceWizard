package org.lopina;

import java.util.NoSuchElementException;

public class NodeDeque<T> extends Deque<T> implements SizeAware
{
    private DequeNode<T> head;
    private DequeNode<T> tail;
    private int size;

    public NodeDeque()
    {
        this.head = new EmptyNode<>();
        this.tail = new EmptyNode<>();
        this.size = 0;
    }

    @Override
    public void append(T elem)
    {
        DataNode<T> node = new DataNode<T>(elem);
        node.withPrev(tail);
        if (size > 0) {
            tail.withNext(node);
        } else {
            head = node;
        }
        tail = node;
        this.size++;
    }

    @Override
    public void prepend(T elem)
    {
        DataNode<T> node = new DataNode<T>(elem);
        node.withNext(head);
        if (size > 0) {
            head.withPrev(node);
        } else {
            tail = node;
        }
        head = node;
        this.size++;
    }

    @Override
    public T first()
    {
        T elem = head();

        this.head = head.next();
        if (!this.head.isEmpty()) {
            this.head.prev().withNext(new EmptyNode<T>());
            this.head.withPrev(new EmptyNode<T>());
        } else {
            this.tail = new EmptyNode<>();
        }

        this.size--;
        return elem;
    }

    @Override
    public T last()
    {
        T elem = tail();

        this.tail = tail.prev();
        if (!this.tail.isEmpty()) {
            this.tail.next().withPrev(new EmptyNode<T>());
            this.tail.withNext(new EmptyNode<T>());
        } else {
            this.head = new EmptyNode<>();
        }

        this.size--;
        return elem;
    }

    @Override
    public T head()
    {
        return head.elem();
    }

    @Override
    public T tail()
    {
        return tail.elem();
    }

    @Override
    public int size()
    {
        return size;
    }

    @Override
    public boolean isEmpty()
    {
        return size != 0;
    }

    private interface DequeNode<E> {
        E elem();
        DequeNode<E> prev();
        DequeNode<E> next();
        DequeNode<E> withPrev(DequeNode<E> prev);
        DequeNode<E> withNext(DequeNode<E> next);
        boolean isEmpty();
    }

    private static class DataNode<E> implements DequeNode<E> {
        E elem;
        DequeNode<E> prev;
        DequeNode<E> next;

        public DataNode(E elem)
        {
            this.elem = elem;
            this.prev = new EmptyNode<>();
            this.next = new EmptyNode<>();
        }

        public DataNode(E elem, DequeNode<E> prev, DequeNode<E> next)
        {
            this.elem = elem;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public E elem()
        {
            return elem;
        }

        @Override
        public DequeNode<E> prev()
        {
            return prev;
        }

        @Override
        public DequeNode<E> next()
        {
            return next;
        }

        @Override
        public DequeNode<E> withPrev(DequeNode<E> prev)
        {
            this.prev = prev;
            return this;
        }

        @Override
        public DequeNode<E> withNext(DequeNode<E> next)
        {
            this.next = next;
            return this;
        }

        @Override
        public boolean isEmpty()
        {
            return false;
        }
    }

    private static class EmptyNode<E> implements DequeNode<E> {

        @Override
        public E elem()
        {
            throw new NoSuchElementException();
        }

        @Override
        public DequeNode<E> prev()
        {
            throw new NoSuchElementException();
        }

        @Override
        public DequeNode<E> next()
        {
            throw new NoSuchElementException();
        }

        @Override
        public DequeNode<E> withPrev(DequeNode<E> prev)
        {
            return prev;
        }

        @Override
        public DequeNode<E> withNext(DequeNode<E> next)
        {
            return next;
        }

        @Override
        public boolean isEmpty()
        {
            return true;
        }
    }
}
