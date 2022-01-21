package ru.ramprox;

import java.util.Collection;

public interface CustomList<T> extends Iterable<T> {
    void add(T item);
    void add(int index, T item);
    boolean addAll(int index, Collection<? extends T> collection);
    boolean remove(T item);
    T remove(int index);
    T get(int index);
    T set(int index, T item);
    int size();
}
