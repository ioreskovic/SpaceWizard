package org.lopina.tree.binary;

import org.lopina.tree.Tree;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class BinaryTree<T extends Comparable<T>> implements Tree<T>
{
    public final BinaryTree<T> left() {
        return child(0);
    }

    public final BinaryTree<T> right() {
        return child(1);
    }

    @Override
    public abstract BinaryTree<T> child(int index);

    @Override
    public final boolean nonEmpty()
    {
        return !isEmpty();
    }

    @Override
    public abstract BinaryTree<T> add(T elem);

    @Override
    public abstract BinaryTree<T> remove(T elem);

    public static <E extends Comparable<E>> Empty<E> empty() {
        return new Empty<E>();
    }

    public static <E extends Comparable<E>> Leaf<E> leaf(E elem) {
        return new Leaf<>(elem);
    }

    public static <E extends Comparable<E>> Branch<E> branch(BinaryTree<E> left, E elem, BinaryTree<E> right) {
        return new Branch<>(left, elem, right, left.size() + 1 + right.size());
    }

    public static <E extends Comparable<E>> BinaryTree<E> make(E[] elems) {
        return make(elems, 0, elems.length);
    }

    private static <E extends Comparable<E>> BinaryTree<E> make(E[] elems, int from, int until)
    {
        if (from == until) {
            return BinaryTree.empty();
        } else if (until - from == 1) {
            return BinaryTree.leaf(elems[from]);
        } else {
            int mid = from + (until - from) / 2;
            return BinaryTree.branch(make(elems, from, mid), elems[mid], make(elems, mid + 1, until));
        }
    }

    public List<T> level(int n) {
        List<T> level = new LinkedList<>();
        level(0, n, level);
        return level;
    }

    private void level(int currentLevel, int targetLevel, List<T> level)
    {
        if (currentLevel < targetLevel) {
            left().level(currentLevel + 1, targetLevel, level);
            right().level(currentLevel + 1, targetLevel, level);
        } else if (currentLevel == targetLevel) {
            if (nonEmpty()) {
                level.add(elem());
            }
        }
    }

    @Override
    public T ancestor(T a, T b)
    {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        int aCmp = a.compareTo(this.elem());
        int bCmp = b.compareTo(this.elem());

        if (aCmp < 0 && bCmp < 0) {
            return left().ancestor(a, b);
        } else if (aCmp > 0 && bCmp > 0) {
            return right().ancestor(a, b);
        } else {
            if (contains(a) && contains(b)) {
                return elem();
            } else {
                throw new NoSuchElementException();
            }
        }
    }
}
