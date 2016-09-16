package org.lopina;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class LinkedList<T>
{
    private Node<T> root;

    private int size = 0;

    public T head() {
        if (root == null) {
            return null;
        } else {
            return root.data;
        }
    }

    public int size() {
        return (root == null ? 0 : size);
    }

    public Node<T> root() {
        return this.root;
    }

    public static class Node<T> {
        Node<T> next;
        T data;

        private Node(T data) {
            this.data = data;
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;

            Node<?> node = (Node<?>) o;

            return data.equals(node.data);

        }
    }

    public void append(T data) {
        Node<T> endNode = new Node<T>(data);
        if (root == null) {
            root = endNode;
        } else {
            Node curr = root;

            while (curr.next != null) {
                curr = curr.next;
            }

            curr.next = endNode;
        }
        size++;
    }

    public void concat(LinkedList<T> that) {
        if (this.root == null) {
            this.root = that.root;
        } else {
            Node last = this.root;

            while (last.next != null) {
                last = last.next;
            }

            last.next = that.root;
        }

        this.size = this.size + that.size;
    }

    public void prepend(T data) {
        Node<T> startNode = new Node<T>(data);

        if (root != null) {
            startNode.next = root;
        }

        root = startNode;
        size++;
    }

    public void delete(T data) {
        Node<T> curr = root;

        if (curr == null) {
            return;
        }

        if (curr.data.equals(data)) {
            root = curr.next;
            size --;
        } else {
            while (curr.next != null) {
                if (curr.next.data.equals(data)) {
                    curr.next = curr.next.next;
                    size--;
                    return;
                }
                curr = curr.next;
            }
        }
    }

    public void distinct() {
        Set<T> seenElements = new HashSet<>();

        Node<T> prev = null;
        Node<T> curr = root;

        while (curr != null) {
            if (seenElements.contains(curr.data)) {
                prev.next = curr.next;
                size--;
            } else {
                seenElements.add(curr.data);
                prev = curr;
            }
            curr = curr.next;
        }
    }

    public void distinctSlow() {
        Node<T> curr = root;

        while (curr != null) {
            Node<T> prev = curr;
            Node<T> runr = curr.next;

            while (runr != null) {
                if (runr.data.equals(curr.data)) {
                    prev.next = runr.next;
                    size--;
                } else {
                    prev = runr;
                }
                runr = runr.next;
            }

            curr = curr.next;
        }
    }

    public T nthToLast(int n) {
        if (n < 0 || n >= size) {
            throw new IllegalArgumentException("Must be a non negative number in range [0, size - 1]");
        }

        Node<T> curr = root;
        Node<T> runr = curr;

        for (int i = 0; i <= n; i++) {
            runr = runr.next;
        }

        while (runr != null) {
            runr = runr.next;
            curr = curr.next;
        }

        return curr.data;
    }

    public void partitionAround(T value, Comparator<T> ord) {
        if (size < 2) {
            return;
        }

        Node<T> tempHead = new Node<T>(value);
        tempHead.next = root;

        Node<T> prev = tempHead;
        Node<T> curr = tempHead.next;

        while (curr != null) {
            if (ord.compare(curr.data, tempHead.data) < 0) {
                prev.next = curr.next;
                curr.next = tempHead.next;
                tempHead.next = curr;
                curr = prev.next;
            } else {
                prev = curr;
                curr = curr.next;
            }
            root = tempHead.next;
        }

        root = tempHead.next;
    }

    public LinkedList<T> partition(T value, Comparator<T> ord) {
        LinkedList<T> preList = new LinkedList<>();
        LinkedList<T> postList = new LinkedList<>();

        Node<T> curr = root;

        while (curr != null) {
            if (ord.compare(curr.data, value) < 0) {
                preList.prepend(curr.data);
            } else {
                postList.prepend(curr.data);
            }

            curr = curr.next;
        }

        preList.concat(postList);

        return preList;
    }

    public boolean isPalindrome() {
        return this.equals(this.reverse());
    }

    public LinkedList<T> reverse() {
        LinkedList<T> reversed = new LinkedList<>();

        Node<T> curr = root;

        while (curr != null) {
            reversed.prepend(curr.data);
            curr = curr.next;
        }

        return reversed;
    }

    public void reversed() {
        if (size < 2) {
            return;
        }

        Node<T> prev = null;
        Node<T> curr = root;

        while (curr != null) {
            Node<T> tempPrev = curr;
            Node<T> tempCurr = curr.next;
            curr.next = prev;
            curr = tempCurr;
            prev = tempPrev;
        }

        root = prev;
    }

    public Node<T> loopStart() {
        Node<T> slow = root;
        Node<T> fast = root;

        while (fast != null  && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                break;
            }
        }

        if (fast == null || fast.next == null) {
            return null;
        }

        Node<T> iLoop = fast;
        Node<T> oLoop = root;

        while (iLoop != oLoop) {
            iLoop = iLoop.next;
            oLoop = oLoop.next;
        }

        return oLoop;
    }

    @Override
    public String toString()
    {
        Node<T> curr = root;
        StringBuilder stringBuilder = new StringBuilder("ROOT->");
        while (curr != null) {
            stringBuilder.append("(").append(curr.data).append(")->");
            curr = curr.next;
        }

        stringBuilder.append("NIL");

        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        LinkedList<?> that = (LinkedList<?>) o;

        if (this.size != that.size) return false;

        Node<?> thisCurr = this.root;
        Node<?> thatCurr = that.root;

        while (thisCurr != null && thatCurr != null) {
            if (!thisCurr.data.equals(thatCurr.data)) {
                break;
            }

            thisCurr = thisCurr.next;
            thatCurr = thatCurr.next;
        }

        return thisCurr == null && thatCurr == null;
    }

    public static LinkedList<Integer> sumLeastSignificant(LinkedList<Integer> a, LinkedList<Integer> b) {
        Node<Integer> aNode = a.root;
        Node<Integer> bNode = b.root;

        LinkedList<Integer> r = new LinkedList<>();

        int cValue = 0;

        while (aNode != null || bNode != null) {
            int aValue = 0;
            int bValue = 0;

            if (aNode != null) {
                aValue = aNode.data;
                aNode = aNode.next;
            }

            if (bNode != null) {
                bValue = bNode.data;
                bNode = bNode.next;
            }

            int dValue = aValue + bValue + cValue;
            cValue = dValue / 10;
            dValue = dValue % 10;

            r.append(dValue);
        }

        if (cValue > 0) {
            r.append(cValue);
        }

        return r;
    }

    public static LinkedList<Integer> sumMostSignificant(LinkedList<Integer> a, LinkedList<Integer> b) {
        return sumLeastSignificant(a.reverse(), b.reverse());
    }

    public static <T> boolean areIntersected(LinkedList<T> a, LinkedList<T> b) {
        Node<T> aPrev = null;
        Node<T> bPrev = null;
        Node<T> aCurr = a.root;
        Node<T> bCurr = b.root;

        while (aCurr != null) {
            aPrev = aCurr;
            aCurr = aCurr.next;
        }

        while (bCurr != null) {
            bPrev = bCurr;
            bCurr = bCurr.next;
        }

        return aPrev == bPrev;
    }

    public Node<T> nThNode(int n) {
        if (n < 0 || n >= size) {
            throw new IllegalArgumentException();
        }

        Node<T> node = root;

        for (int i = 0; i < n; i++) {
            node = node.next;
        }

        return node;
    }

    public static <T> T intersection(LinkedList<T> a, LinkedList<T> b) {
        int aSize = a.size;
        int bSize = b.size;

        if (aSize == 0 || bSize == 0) {
            return null;
        }

        Node<T> aNode = a.root;
        Node<T> bNode = b.root;

        if (aSize > bSize) {
            aNode = a.nThNode(aSize - bSize);
        } else if (bSize > aSize) {
            bNode = b.nThNode(bSize - aSize);
        }

        while (aNode != bNode) {
            aNode = aNode.next;
            bNode = bNode.next;
        }

        if (aNode != null) {
            return aNode.data;
        } else {
            return null;
        }
    }
}
