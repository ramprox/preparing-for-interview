package ru.ramprox;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class CustomArrayList<T> implements CustomList<T> {

    private Object[] items;

    private int size;

    public CustomArrayList() {
        this(10);
    }

    public CustomArrayList(int initialCapacity) {
        items = new Object[initialCapacity];
    }

    public CustomArrayList(Collection<? extends T> collection) {
        items = new Object[collection.size()];
        addAll(0, collection);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T item) {
        if (size == items.length) {
            items = Arrays.copyOf(items, items.length * 2);
        }
        items[size++] = item;
    }

    @Override
    public void add(int index, T item) {
        if(index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        if (size == items.length) {
            Object[] temp = new Object[items.length * 2];
            System.arraycopy(items, 0, temp, 0, index);
            temp[index] = item;
            System.arraycopy(items, index, temp, index + 1, items.length - index);
            items = temp;
        } else {
            for (int i = size; i > index; i--) {
                items[i] = items[i - 1];
            }
            items[index] = item;
        }
        size++;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> collection) {
        if(collection == null || collection.size() == 0) {
            return false;
        }
        if(index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        if (size + collection.size() > items.length) {
            Object[] temp = new Object[size + collection.size()];
            System.arraycopy(items, 0, temp, 0, index);
            insertCollection(index, collection, temp);
            System.arraycopy(items, index, temp, index + collection.size(), size - index);
            items = temp;
        } else {
            System.arraycopy(items, index, items, index + collection.size(), size - index);
            insertCollection(index, collection, items);
        }
        size += collection.size();
        return true;
    }

    private void insertCollection(int index, Collection<? extends T> collection, Object[] items) {
        int curIndex = index;
        for (T item : collection) {
            items[curIndex++] = item;
        }
    }

    @Override
    public boolean remove(T item) {
        for(int i = 0; i < size; i++) {
            if(items[i].equals(item)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        T result = (T)items[index];
        for(int i = index; i < size - 1; i++) {
            items[i] = items[i + 1];
        }
        items[--size] = null;
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if(index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        return (T)items[index];
    }

    @Override
    @SuppressWarnings("unchecked")
    public T set(int index, T item) {
        if(index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        T previous = (T)items[index];
        items[index] = item;
        return previous;
    }

    @Override
    public Iterator<T> iterator() {
        return new CustomArrayIterator(0);
    }

    private class CustomArrayIterator implements Iterator<T> {

        private int curIndex;

        private CustomArrayIterator(int index) {
            this.curIndex = index;
        }

        @Override
        public boolean hasNext() {
            return curIndex < size;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T next() {
            return (T)items[curIndex++];
        }
    }
}
