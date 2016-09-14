package org.lopina;

import java.util.HashSet;
import java.util.Set;

public class LinkedList<T>
{
    private Node root;

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

    private class Node {
        Node next;
        T data;

        private Node(T data) {
            this.data = data;
        }
    }

    public void append(T data) {
        Node endNode = new Node(data);
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

    public void delete(T data) {
        Node curr = root;

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

        Node prev = null;
        Node curr = root;

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
}
