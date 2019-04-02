package com.example.hash;

/**
 * 散列表-线性探测例程
 * Created by DINGJUN on 2019.04.02.
 */
public class QuadraticProbingHashTable<T> {
    public QuadraticProbingHashTable(){
        this(DEFAULT_TABLE_SIZE);
    }
    public QuadraticProbingHashTable(int size){
        allocateArray(size);
        makeEmpty();
    }
    public void makeEmpty(){
        currentSize = 0;
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
    }
    public boolean contains(T t){
        int currentPos = findPos(t);
        return isActive(currentPos);
    }
    public void insert(T t){
        int currentPos = findPos(t);
        if(isActive(currentPos))
            return ;
        array[currentPos] = new HashEntry<>(t, true);
        if(currentSize > array.length / 2)
            rehash();
    }
    public void remove(T t){
        int currentPos = findPos(t);
        if (isActive(currentPos))
            array[currentPos].isActive = false;
    }

    private static class HashEntry<T> {
        public T element; // the element
        public boolean isActive; // false if marked deleted
        public HashEntry(T e, boolean i) {
            element = e;
            isActive = i;
        }
    }

    private static final int DEFAULT_TABLE_SIZE = 11;

    private HashEntry<T>[] array;
    private int currentSize;

    private void allocateArray(int arraySize) {
        array = new HashEntry[nextPrime(arraySize)];
    }
    private boolean isActive(int currentPos){
        return array[currentPos] != null && array[currentPos].isActive;
    }
    private int findPos(T t){
        int offset = 1;
        int currentPos = myhash(t);
        while (array[currentPos] != null && !array[currentPos].element.equals(t)) {
            currentPos += offset;
            offset += 2;
            if (currentPos >= array.length)
                currentPos -= array.length;
        }
        return currentPos;
    }
    private void rehash(){}
    private int myhash(T t){
        return 1;
    }
    private static int nextPrime(int n){
        return -1;
    }
    private static boolean isPrime(int n){
        return false;
    }

}

























