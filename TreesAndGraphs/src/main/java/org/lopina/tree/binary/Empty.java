package org.lopina.tree.binary;

import java.util.NoSuchElementException;

public class Empty<T extends Comparable<T>> extends BinaryTree<T>
{
    @Override
    public T elem()
    {
        throw new NoSuchElementException("Empty has no element data");
    }

    @Override
    public int size()
    {
        return 0;
    }

    @Override
    public int height()
    {
        return 0;
    }

    @Override
    public boolean isEmpty()
    {
        return true;
    }

    @Override
    public boolean isBalanced()
    {
        return true;
    }

    @Override
    public boolean isSearchable()
    {
        return true;
    }

    @Override
    public boolean contains(T elem)
    {
        return false;
    }

    @Override
    public BinaryTree<T> child(int index)
    {
        throw new NoSuchElementException("Empty has no children");
    }

    @Override
    public Leaf<T> add(T elem)
    {
        return BinaryTree.leaf(elem);
    }

    @Override
    public Empty<T> remove(T elem)
    {
        return this;
    }

    @Override
    public T min()
    {
        throw new NoSuchElementException("Empty tree has no min element");
    }

    @Override
    public T max()
    {
        throw new NoSuchElementException("Empty tree has no max element");
    }

    @Override
    public T apply(int n)
    {
        throw new NoSuchElementException("Empty tree has no " + n + ". element");
    }

    @Override
    public T predecessor()
    {
        throw new NoSuchElementException("Empty tree has no predecessor element");
    }

    @Override
    public T successor()
    {
        throw new NoSuchElementException("Empty tree has no successor element");
    }

    @Override
    public String toString()
    {
        return "E()";
    }
}
