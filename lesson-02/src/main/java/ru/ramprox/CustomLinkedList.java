package ru.ramprox;

import java.util.Collection;
import java.util.Iterator;

public class CustomLinkedList<T> implements CustomList<T> {

    private Node<T> head;
    private Node<T> tail;

    private int size;

    public CustomLinkedList() {
    }

    public CustomLinkedList(Collection<? extends T> collection) {
        addAll(0, collection);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T item) {
        if(head == null) {
            head = tail = new Node<>(null, null, item);
        } else {
            tail.next = new Node<>(null, tail, item);
            tail = tail.next;
        }
        size++;
    }

    @Override
    public void add(int index, T item) {
        if(index > size || index < 0) {
            throw new IndexOutOfBoundsException(String.format("Index %d out of bound", index));
        }
        if(index == 0) {
            addToHead(item);
        } else if(index == size) {
            add(item);
        } else {
            addInMiddle(index, item);
        }
    }

    private void addToHead(T item) {
        Node<T> newNode = new Node<>(head, null, item);
        head.previous = newNode;
        head = newNode;
        size++;
    }

    private void addInMiddle(int index, T item) {
        Node<T> node = getNodeByIndex(index);
        Node<T> newNode = new Node<>(node, node.previous, item);
        node.previous.next = newNode;
        node.previous = newNode;
        size++;
    }

    private Node<T> getNodeByIndex(int index) {
        Node<T> node = head;
        int currentIndex = 0;
        while (currentIndex < index) {
            node = node.next;
            currentIndex++;
        }
        return node;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> collection) {
        if(index > size || index < 0) {
            throw new IndexOutOfBoundsException(String.format("Index %d out of bound", index));
        }
        if(collection.size() == 0) {
            return false;
        }
        if(index == size) {
            collection.forEach(this::add);
        } else if(index == 0) {
            Node<T> curNode = head;
            for(T item : collection) {
                Node<T> newNode = new Node<>(curNode, curNode.previous, item);
                curNode.previous = newNode;
                if(newNode.previous != null) {
                    newNode.previous.next = newNode;
                } else {
                    head = head.previous;
                }
                size++;
            }
        } else {
            Node<T> curNode = getNodeByIndex(index);
            for(T item : collection) {
                Node<T> newNode = new Node<>(curNode, curNode.previous, item);
                curNode.previous = newNode;
                newNode.previous.next = newNode;
                size++;
            }
        }
        return true;
    }

    @Override
    public boolean remove(T item) {
        Node<T> curNode = head;
        while(curNode != null) {
            if(curNode.data.equals(item)) {
                if(curNode == head) {
                    removeFromHead();
                } else if (curNode == tail) {
                    removeFromTail();
                } else {
                    removeFromMiddle(curNode);
                }
                return true;
            }
            curNode = curNode.next;
        }
        return false;
    }

    private T removeFromHead() {
        T data = head.data;
        head = head.next;
        head.previous = head.previous.next = null;
        size--;
        return data;
    }

    private T removeFromTail() {
        T data = tail.data;
        tail = tail.previous;
        tail.next = tail.next.previous = null;
        size--;
        return data;
    }

    private T removeFromMiddle(Node<T> removingNode) {
        T data = removingNode.data;
        removingNode.previous.next = removingNode.next;
        removingNode.next.previous = removingNode.previous;
        removingNode.next = removingNode.previous = null;
        size--;
        return data;
    }

    @Override
    public T remove(int index) {
        if(index >= size || index < 0) {
            throw new IndexOutOfBoundsException(String.format("Index %d out of bound", index));
        }
        T data;
        if(index == 0) {
            data = removeFromHead();
        } else if(index == size - 1) {
            data = removeFromTail();
        } else {
            data = removeFromMiddle(getNodeByIndex(index));
        }
        return data;
    }

    @Override
    public T get(int index) {
        if(index >= size || index < 0) {
            throw new IndexOutOfBoundsException(String.format("Index %d out of bound", index));
        }
        return getNodeByIndex(index).data;
    }

    @Override
    public T set(int index, T item) {
        if(index >= size || index < 0) {
            throw new IndexOutOfBoundsException(String.format("Index %d out of bound", index));
        }
        Node<T> node = getNodeByIndex(index);
        T data = node.data;
        node.data = item;
        return data;
    }

    @Override
    public Iterator<T> iterator() {
        return new CustomListIterator(head);
    }

    private static class Node<T> {
        private Node<T> next;
        private Node<T> previous;
        private T data;

        public Node() {
        }

        public Node(Node<T> next, Node<T> previous, T data) {
            this(next, data);
            this.previous = previous;
        }

        public Node(Node<T> next, T data) {
            this.next = next;
            this.data = data;
        }
    }

    private class CustomListIterator implements Iterator<T> {

        private Node<T> currentNode;

        private CustomListIterator(Node<T> currentNode) {
            this.currentNode = currentNode;
        }

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public T next() {
            T data = currentNode.data;
            currentNode = currentNode.next;
            return data;
        }
    }
}
