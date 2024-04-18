package org.example;

import java.util.Iterator;

public interface MyListInterface <T> extends Iterable<T>{
    T get(int index);

    void add(T t);
    void addByIndex(T t, int index);

    void remove(T t);

    void removeByIndex(int index);

    void addAll(MyListInterface<T> myList);

    int size();

    void clear();

    Iterator<T> iterator();
}
