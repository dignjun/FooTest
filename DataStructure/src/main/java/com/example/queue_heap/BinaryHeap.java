package com.example.queue_heap;

/**
 * 堆结构/优先队列
 *
 * @author DINGJUN
 * @date 2019.04.12
 */
public class BinaryHeap<T extends Comparable<? extends T>> {
    public BinaryHeap(){}
    public BinaryHeap(int capacity) {}
    public BinaryHeap(T[] items) {}
    public void insert(T x){}
    public T findMin(){return null;}
    public T deleteMin(){return null;}
    public boolean isEmpty(){return false;}
    public void makeEmpty(){}
    private static final int DEFAULT_CAPACITY = 10;
    private int currentSize;
    private T[] array;
    private void percolateDown(int hole){}
    private void buildheap(){}
    private void enlargeArray(int newSize){}
}
































