package org.lopina.tree;

import java.util.List;

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
    T random();
    T nthMin(int n);
    T nthMax(int n);
    T predecessor();
    T predecessor(T elem);
    T successor();
    T successor(T elem);
    T ancestor(T a, T b);
    Iterable<T> pathBetween(T a, T b);
}
