package org.lopina.tree.binary;



import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Branch<T extends Comparable<T>> extends BinaryTree<T>
{
    private T elem;
    private List<BinaryTree<T>> children;
    private int size;

    public Branch(BinaryTree<T> left, T elem, BinaryTree<T> right, int size)
    {
        this.elem = elem;
        this.children = new ArrayList<>();
        this.children.add(0, left);
        this.children.add(1, right);
        this.size = size;
    }

    @Override
    public T elem()
    {
        return elem;
    }

    @Override
    public int size()
    {
        return size;
    }

    @Override
    public int height()
    {
        return 1 + Math.max(left().height(), right().height());
    }

    @Override
    public boolean isEmpty()
    {
        return false;
    }

    @Override
    public boolean isBalanced()
    {
        return left().isBalanced() &&
                right().isBalanced() &&
                (Math.abs(left().height() - right().height()) <= 1);
    }

    @Override
    public boolean isSearchable()
    {
        boolean searchable = left().isSearchable() && right().isSearchable();

        if (left().nonEmpty()) {
            searchable = searchable && left().elem().compareTo(elem) < 0;
        }

        if (right().nonEmpty()) {
            searchable = searchable && right().elem().compareTo(elem) > 0;
        }

        return searchable;
    }

    @Override
    public boolean contains(T elem)
    {
        int cmp = elem.compareTo(this.elem);
        if (cmp < 0) {
            return left().contains(elem);
        } else if (cmp > 0) {
            return right().contains(elem);
        } else {
            return true;
        }
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
    public Branch<T> add(T elem)
    {
        int cmp = elem.compareTo(this.elem);

        if (cmp < 0) {
            return BinaryTree.branch(this.left().add(elem), this.elem, this.right());
        } else if (cmp > 0) {
            return BinaryTree.branch(this.left(), this.elem, this.right().add(elem));
        } else {
            return this;
        }
    }

    @Override
    public BinaryTree<T> remove(T elem)
    {
        int cmp = elem.compareTo(this.elem);

        if (cmp < 0) {
            BinaryTree<T> newLeft = left().remove(elem);
            if (newLeft.isEmpty() && right().isEmpty()) {
                return BinaryTree.leaf(this.elem);
            } else {
                return BinaryTree.branch(newLeft, this.elem, right());
            }
        } else if (cmp > 0) {
            BinaryTree<T> newRight = right().remove(elem);
            if (left().isEmpty() && newRight.isEmpty()) {
                return BinaryTree.leaf(this.elem);
            } else {
                return BinaryTree.branch(left(), this.elem, newRight);
            }
        } else {
            if (right().nonEmpty()) {
                T successor = successor();
                BinaryTree<T> newRight = right().remove(successor);
                if (left().isEmpty() && newRight.isEmpty()) {
                    return BinaryTree.leaf(successor);
                } else {
                    return BinaryTree.branch(left(), successor, newRight);
                }
            } else if (left().nonEmpty()) {
                T predecessor = predecessor();
                BinaryTree<T> newLeft = left().remove(predecessor);
                if (newLeft.isEmpty() && right().isEmpty()) {
                    return BinaryTree.leaf(predecessor);
                } else {
                    return BinaryTree.branch(newLeft, predecessor, right());
                }
            } else {
                return BinaryTree.empty();
            }
        }
    }

    @Override
    public T min()
    {
        if (!left().isEmpty()) {
            return left().min();
        } else {
            return elem;
        }
    }

    @Override
    public T max()
    {
        if (!right().isEmpty()) {
            return right().max();
        } else {
            return elem;
        }
    }

    @Override
    public T apply(int n)
    {
        if (n < left().size()) {
            return left().apply(n);
        } else if (n > left().size()) {
            return right().apply(n - left().size() - 1);
        } else {
            return elem;
        }
    }

    @Override
    public T predecessor()
    {
        return left().max();
    }

    @Override
    public T successor()
    {
        return right().min();
    }

    @Override
    public String toString()
    {
        return "{" + left().toString() + "<-B(" + elem.toString() + ")->" + right().toString() + "}";
    }
}
