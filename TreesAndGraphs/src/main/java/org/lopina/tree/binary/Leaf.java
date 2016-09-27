package org.lopina.tree.binary;

import java.util.*;

public class Leaf<T extends Comparable<T>> extends BinaryTree<T>
{
    private T elem;
    private List<BinaryTree<T>> children;

    public Leaf(T elem)
    {
        this.elem = elem;
        this.children = new ArrayList<>();
        this.children.add(0, BinaryTree.empty());
        this.children.add(1, BinaryTree.empty());
    }

    @Override
    public boolean contains(T elem)
    {
        return this.elem().compareTo(elem) == 0;
    }

    @Override
    public T elem()
    {
        return elem;
    }

    @Override
    public int size()
    {
        return 1;
    }

    @Override
    public int height()
    {
        return 1;
    }

    @Override
    public boolean isEmpty()
    {
        return false;
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
    public BinaryTree<T> child(int index)
    {
        if (index < 0 || index > 1) {
            throw new NoSuchElementException("Binary tree has only two children");
        }

        return children.get(index);
    }

    @Override
    public BinaryTree<T> add(T elem)
    {
        int cmp = elem.compareTo(this.elem);

        if (cmp < 0) {
            return BinaryTree.branch(BinaryTree.leaf(elem), this.elem, this.right());
        } else if (cmp > 0) {
            return BinaryTree.branch(this.left(), this.elem, BinaryTree.leaf(elem));
        } else {
            return this;
        }
    }

    @Override
    public BinaryTree<T> remove(T elem)
    {
        int cmp = elem.compareTo(this.elem);

        if (cmp == 0) {
            return BinaryTree.empty();
        } else {
            return this;
        }
    }

    @Override
    public Set<Deque<T>> bstSequences()
    {
        Deque<T> bstSeq = new ArrayDeque<>();
        bstSeq.add(elem);
        Set<Deque<T>> resultSet = new HashSet<>();
        resultSet.add(bstSeq);
        return resultSet;
    }

    @Override
    public T min()
    {
        return elem;
    }

    @Override
    public T max()
    {
        return elem;
    }

    @Override
    public T apply(int n)
    {
        if (n != 0) {
            throw new NoSuchElementException("Leaf tree has no " + n + ". element");
        }

        return elem;
    }

    @Override
    public T predecessor()
    {
        throw new NoSuchElementException("Leaf tree has no predecessor element");
    }

    @Override
    public T successor()
    {
        throw new NoSuchElementException("Leaf tree has no successor element");
    }

    @Override
    public String toString()
    {
        return "L(" + elem.toString() + ")";
    }
}
