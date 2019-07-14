package com.example.queue_heap;

/**
 * 堆结构/优先队列
 *
 * @author DINGJUN
 * @date 2019.04.12
 */
public class BinaryHeap<T extends Comparable<? super T>> {
    public BinaryHeap() {
    }

    public BinaryHeap(int capacity) {
    }

    public BinaryHeap(T[] items) {
        currentSize = items.length;
        array = (T[]) new Comparable[(currentSize + 2) * 11 / 10];
        int i = 1;
        for (T item : items)
            array[i++] = item;
        buildheap();
    }

    public void insert(T x) {
        if (currentSize == array.length - 1) {
            enlargeArray(array.length * 2 + 1);
        }
        // percolate up
        int hole = ++currentSize;
        // 完全二叉树规律表明:对于数组中任一位置i上的元素,左儿子在2i上,右儿子在2i+1上,父亲在i/2上.
        for (array[0] = x; x.compareTo(array[hole / 2]) < 0; hole /= 2) {
            array[hole] = array[hole / 2];
        }
        array[hole] = x;
    }

    public T findMin() {
        return null;
    }

    public T deleteMin() {
        if (isEmpty())
            throw new IndexOutOfBoundsException();
        T minItem = findMin();
        array[1] = array[currentSize--];
        percolateDown(1);
        return minItem;
    }

    public boolean isEmpty() {
        return false;
    }

    public void makeEmpty() {
    }

    private static final int DEFAULT_CAPACITY = 10;
    private int currentSize;
    private T[] array;

    private void percolateDown(int hole) {
        int child = 0;
        T tmp = array[hole];
        for (; hole * 2 <= currentSize; hole = child) {
            child = hole * 2;
            // 堆中存在偶数个元素的时候,将遇到一个节点只有一个儿子的情况.
            if (child != currentSize && array[child + 1].compareTo(array[child]) < 0)
                child++;
            if(array[child].compareTo(tmp) < 0)
                array[hole] = array[child];
            else
                break;
        }
        array[hole] = tmp;
    }

    private void buildheap() {
        for (int i = currentSize/2; i > 0; i--)
            percolateDown(i);
    }

    private void enlargeArray(int newSize) {
    }
}
































