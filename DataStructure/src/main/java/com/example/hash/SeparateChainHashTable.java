package com.example.hash;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 分离链表法
 *
 * @author DINGJUN
 * @date 2019.04.01
 */
public class SeparateChainHashTable<T> {

    // constructor
    public SeparateChainHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    public SeparateChainHashTable(int size) {
        theLists = new LinkedList[nextPrime(size)];
        for (int i = 0; i < theLists.length; i++) {
            theLists[i] = new LinkedList<>();
        }
    }

    // method
    // insert
    public void insert(T t) {
        List<T> whichList = theLists[myhash(t)];
        if (!whichList.contains(t)) {
            whichList.add(t);
        }
        // Rehash
        if (++currentSize > theLists.length) {
            rehash();
        }
    }

    // remove
    public void remove(T t) {
        List<T> whichList = theLists[myhash(t)];
        if (whichList.contains(t)) {
            whichList.remove(t);
            currentSize--;
        }
    }

    // contains
    public boolean contains(T t) {
        List<T> whichList = theLists[myhash(t)];
        return whichList.contains(t);
    }

    public void makeEmpty() {
        for (int i = 0; i < theLists.length; i++) {
            theLists[i].clear();
        }
        currentSize = 0;
    }

    // field
    private static final int DEFAULT_TABLE_SIZE = 101;
    private List<T>[] theLists;
    private int currentSize;

    private void rehash() {
        List<T>[] oldList = theLists;
        // create new double-size empty table
        theLists = new List[nextPrime(2 * theLists.length)];
        for (int j = 0; j< theLists.length; j ++) {
            theLists[j] = new LinkedList<>();
        }
        // copy
        currentSize = 0;
        for (int i = 0; i < oldList.length; i++){
            for (T item: oldList[i])
                insert(item);
        }
    }

    private int myhash(T t) {
        int hashVal = t.hashCode();
        hashVal %= theLists.length;
        if (hashVal < 0)
            hashVal += theLists.length;
        return hashVal;
    }

    // static method
    private static int nextPrime(int n) {
        return -1;
    }

    private static boolean isPrime(int n) {
        return false;
    }
}




























