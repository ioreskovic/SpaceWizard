package org.lopina.tree;

public interface Tree<T extends Comparable<T>>
{
    T elem();
    int size();
    int height();
    boolean isEmpty();
    boolean nonEmpty();
    boolean isBalanced();
    boolean isSearchable();
    Tree<T> child(int index);

    boolean contains(T elem);
    Tree<T> add(T elem);
    Tree<T> remove(T elem);
    T min();
    T max();
    T apply(int n);
    T predecessor();
    T successor();
    T ancestor(T a, T b);
}
