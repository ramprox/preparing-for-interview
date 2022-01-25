package ru.ramprox.list_tasks;

import java.util.Iterator;

public class SimpleLinkedList<T> implements Iterable<T> {

    private Node<T> head;
    private Node<T> tail;

    private int size;

    public void add(T data) {
        Node<T> node = new Node<>(null, data);
        if(head == null) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = tail.next;
        }
        size++;
    }

    public void reverse() {
        if(head == null || head.next == null) {
            return;
        }
        Node<T> previous = head;
        head = previous.next;
        Node<T> next = head.next;
        previous.next = null;
        head.next = previous;
        tail = previous;
        while(next != null) {
            previous = head;
            head = next;
            next = next.next;
            head.next = previous;
        }
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new SimpleIterator();
    }

    private class SimpleIterator implements Iterator<T> {

        private Node<T> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T data = current.data;
            current = current.next;
            return data;
        }
    }

    private static class Node<T> {

        private Node<T> next;
        private T data;

        public Node(Node<T> next, T data) {
            this.next = next;
            this.data = data;
        }
    }
}
