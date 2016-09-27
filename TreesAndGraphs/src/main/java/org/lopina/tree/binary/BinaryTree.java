package org.lopina.tree.binary;

import org.lopina.tree.Tree;

import java.util.*;

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

    @Override
    public T successor(T elem)
    {
        return successorForwardFind(elem, this, new ArrayDeque<BinaryTree<T>>());
    }

    private T successorForwardFind(T x, BinaryTree<T> t, Deque<BinaryTree<T>> p)
    {
        if (t.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            int cmp = x.compareTo(t.elem());
            if (cmp < 0) {
                p.push(t);
                return successorForwardFind(x, t.left(), p);
            } else if (cmp > 0) {
                p.push(t);
                return successorForwardFind(x, t.right(), p);
            } else if (t.right().nonEmpty()) {
                return t.successor();
            } else {
                return successorBackwardReturn(t, p);
            }
        }
    }

    private T successorBackwardReturn(BinaryTree<T> t, Deque<BinaryTree<T>> p)
    {
        if (p.isEmpty()) {
            throw new NoSuchElementException("No successor");
        } else {
            BinaryTree<T> parent = p.pop();
            if (t == parent.right()) {
                return successorBackwardReturn(parent, p);
            } else {
                return parent.elem();
            }
        }
    }

    @Override
    public T predecessor(T elem)
    {
        return predecessorForwardFind(elem, this, new ArrayDeque<>());
    }

    private T predecessorForwardFind(T x, BinaryTree<T> t, Deque<BinaryTree<T>> p)
    {
        if (t.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            int cmp = x.compareTo(t.elem());

            if (cmp < 0) {
                p.push(t);
                return predecessorForwardFind(x, t.left(), p);
            } else if (cmp > 0) {
                p.push(t);
                return predecessorForwardFind(x, t.right(), p);
            } else if (t.left().nonEmpty()) {
                return t.predecessor();
            } else {
                return predecessorBackwardReturn(t, p);
            }
        }
    }

    protected T predecessorBackwardReturn(BinaryTree<T> t, Deque<BinaryTree<T>> p) {
        if (p.isEmpty()) {
            throw new NoSuchElementException("No predecessor");
        } else {
            BinaryTree<T> parent = p.pop();

            if (t == parent.left()) {
                return predecessorBackwardReturn(parent, p);
            } else {
                return parent.elem();
            }
        }
    }

    @Override
    public T nthMin(int n)
    {
        return apply(n);
    }

    @Override
    public T nthMax(int n)
    {
        return apply(size() - n - 1);
    }

    @Override
    public Iterable<T> pathBetween(T a, T b)
    {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            int aCmp = a.compareTo(elem());
            int bCmp = b.compareTo(elem());

            if (aCmp < 0 && bCmp < 0) {
                return left().pathBetween(a, b);
            } else if (aCmp > 0 && bCmp > 0) {
                return right().pathBetween(a, b);
            } else {
                ArrayDeque<T> result = new ArrayDeque<>();
                result.add(elem());

                if (aCmp < 0 && bCmp > 0) {
                    left().pathPrepend(a, result);
                    right().pathAppend(b, result);
                } else if (bCmp < 0 && aCmp > 0) {
                    left().pathAppend(b, result);
                    right().pathPrepend(a, result);
                }

                return result;
            }
        }
    }

    public abstract Set<Deque<T>> bstSequences();

    private void pathPrepend(T x, ArrayDeque<T> result)
    {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            int cmp = x.compareTo(elem());

            result.offerFirst(elem());

            if (cmp < 0) {
                left().pathPrepend(x, result);
            } else if (cmp > 0) {
                right().pathPrepend(x, result);
            } else {
                return;
            }
        }
    }

    private void pathAppend(T x, ArrayDeque<T> result)
    {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            int cmp = x.compareTo(elem());

            result.offerLast(elem());

            if (cmp < 0) {
                left().pathAppend(x, result);
            } else if (cmp > 0) {
                right().pathAppend(x, result);
            } else {
                return;
            }
        }
    }

    public static <E> Set<Deque<E>> weave(Deque<E> xs, Deque<E> ys) {
        Set<Deque<E>> results = new HashSet<>();
        weave(xs, ys, new ArrayDeque<E>(), results);

        return results;
    }

    private static <E> void weave(Deque<E> xs, Deque<E> ys, ArrayDeque<E> prefix, Set<Deque<E>> results)
    {
        if (xs.isEmpty() && ys.isEmpty()) {
            results.add(prefix);
        } else {
            if (!xs.isEmpty()) {
                ArrayDeque<E> xs1 = new ArrayDeque<>(xs);
                ArrayDeque<E> ys1 = new ArrayDeque<>(ys);
                ArrayDeque<E> prefix1 = new ArrayDeque<>(prefix);
                E x = xs1.pollFirst();
                prefix1.offerLast(x);
                weave(xs1, ys1, prefix1, results);
            }

            if (!ys.isEmpty()) {
                ArrayDeque<E> xs2 = new ArrayDeque<>(xs);
                ArrayDeque<E> ys2 = new ArrayDeque<>(ys);
                ArrayDeque<E> prefix2 = new ArrayDeque<>(prefix);
                E y = ys2.pollFirst();
                prefix2.offerLast(y);
                weave(xs2, ys2, prefix2, results);
            }
        }
    }

    @Override
    public T random()
    {
        Random randGen = new Random(System.currentTimeMillis());
        int n = randGen.nextInt(size());
        return apply(n);
    }

    public static List<Iterable<Integer>> pathSum(BinaryTree<Integer> t, int n)
    {
        List<Iterable<Integer>> pathSum = new ArrayList<>();
        return pathSum(t, n, new SumSeq());
    }

    private static List<Iterable<Integer>> pathSum(BinaryTree<Integer> t, Integer n, SumSeq path)
    {
        if (t.size() == 1) {
            if (n.equals(path.sum + t.elem()) && !path.seq().isEmpty()) {
                List<Iterable<Integer>> pathSumInitial = new ArrayList<>();
                pathSumInitial.add(path.copy().append(t.elem()).seq());
                return pathSumInitial;
            } else {
                return new ArrayList<>();
            }
        } else if (t.isEmpty()) {
            if (n.equals(path.sum) && !path.seq().isEmpty()) {
                List<Iterable<Integer>> pathSumInitial = new ArrayList<>();
                pathSumInitial.add(path.copy().seq());
                return pathSumInitial;
            } else {
                return new ArrayList<>();
            }
        } else {
            List<Iterable<Integer>> pathSumComposite = new ArrayList<>();
            pathSumComposite.addAll(pathSum(t.left(), n, new SumSeq()));
            pathSumComposite.addAll(pathSum(t.right(), n, new SumSeq()));
            pathSumComposite.addAll(pathSum(t.left(), n, path.copy().append(t.elem())));
            pathSumComposite.addAll(pathSum(t.right(), n, path.copy().append(t.elem())));

            return pathSumComposite;
        }
    }

    static class SumSeq {
        private ArrayDeque<Integer> seq;
        private Integer sum;

        private SumSeq(ArrayDeque<Integer> seq, Integer sum) {
            this.seq = new ArrayDeque<>(seq);
            this.sum = sum;
        }

        SumSeq() {
            this.seq = new ArrayDeque<>();
            this.sum = 0;
        }

        SumSeq append(Integer elem) {
            seq.offerLast(elem);
            sum = sum + elem;
            return this;
        }

        public Integer sum()
        {
            return sum;
        }

        public ArrayDeque<Integer> seq()
        {
            return seq;
        }

        public SumSeq copy() {
            return new SumSeq(seq, sum);
        }
    }
}
