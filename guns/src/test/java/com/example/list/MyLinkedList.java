package com.example.list;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * LinkedList实现
 * @author DINGJUN
 * @date 2019.03.21
 */
public class MyLinkedList<E> implements Iterable<E>{
    private int theSize;
    private int modCount = 0;
    private Node<E> beginMarker;
    private Node<E> endMarker;

    private static class Node<E> {

    }
    public MyLinkedList(){
//        doClear();
    }
    public void clear(){

    }
    public int size(){
        return theSize;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super E> action) {

    }

    @Override
    public Spliterator<E> spliterator() {
        return null;
    }
}
