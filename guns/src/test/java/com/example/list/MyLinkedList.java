package com.example.list;

import java.util.*;
import java.util.function.Consumer;

/**
 * LinkedList实现
 * 数据结构是双向链表
 *
 * @author DINGJUN
 * @date 2019.03.21
 */
public class MyLinkedList<E> implements Iterable<E> {
    private int theSize;
    private int modCount = 0;
    private Node<E> beginMarker;
    private Node<E> endMarker;

    /**
     * 节点内部类
     *
     * @param <E> 存储元素的类型
     */
    private static class Node<E> {
        public E data;
        public Node<E> prev;
        public Node<E> next;

        public Node(E d, Node<E> p, Node<E> n) {
            data = d;
            prev = p;
            next = n;
        }
    }

    /**
     * 构造
     */
    public MyLinkedList() {
        doClear();
    }

    /**
     * 清空集合
     */
    public void clear() {
        doClear();
    }

    /**
     * 清空集合
     */
    private void doClear() {
        beginMarker = new Node<>(null, null, null);
        endMarker = new Node<>(null, beginMarker, null);
        beginMarker.next = endMarker;
        theSize = 0;
        modCount++;
    }

    /**
     * 集合大小
     *
     * @return
     */
    public int size() {
        return theSize;
    }

    /**
     * 判断是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean add(E x) {
        add(size(), x);
        return true;
    }

    public void add(int index, E x) {
        addBefore(getNode(index, 0, size()), x);
    }

    /**
     * 获取一个元素
     *
     * @param index 元素位置
     * @return
     */
    public E get(int index) {
        return getNode(index).data;
    }

    /**
     * 高位添加元素
     *
     * @param p
     * @param x
     */
    private void addBefore(Node<E> p, E x) {
        Node<E> newNode = new Node<>(x, p.prev, p);
        newNode.prev.next = newNode;
        p.prev = newNode;
        theSize++;
        modCount++;
    }

    /**
     * 删除一个元素
     *
     * @param index 元素索引
     * @return
     */
    public E remove(int index) {
        return remove(getNode(index));
    }

    /**
     * 删除一个元素
     *
     * @param p 删除的元素节点
     * @return
     */
    private E remove(Node<E> p) {
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;
        modCount++;
        return p.data;
    }

    /**
     * 获取一个节点
     *
     * @param index 获取索引处的值
     * @return 节点
     */
    private Node<E> getNode(int index) {
        return getNode(index, 0, size() - 1);
    }

    /**
     * 获取一个节点
     *
     * @param index 查找的索引位置
     * @param lower 有效的地位索引
     * @param upper 有效的高位索引
     * @return 节点
     */
    private Node<E> getNode(int index, int lower, int upper) {
        Node<E> p;
        if (index < lower || index > upper) {
            throw new IndexOutOfBoundsException();
        }
        if (index < size() / 2) {
            p = beginMarker.next;
            for (int i = 0; i < index; i++) {
                p = p.next;
            }
        } else {
            p = endMarker;
            for (int i = size(); i > index; i--) {
                p = p.prev;
            }
        }
        return p;
    }

    /**
     * 迭代器类
     */
    private class LinkedListIterator implements java.util.Iterator<E> {
        private Node<E> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        @Override
        public boolean hasNext() {
            return current != endMarker;
        }

        @Override
        public E next() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if (!hasNext())
                throw new NoSuchElementException();
            E nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }

        @Override
        public void remove() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if (!okToRemove)
                throw new IllegalStateException();
            MyLinkedList.this.remove(current.prev);
            expectedModCount++;
            okToRemove = false;
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {

        }
    }


    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    @Override
    public void forEach(Consumer<? super E> action) {

    }

    @Override
    public Spliterator<E> spliterator() {
        return null;
    }


    public static void main(String[] args) {
        MyLinkedList<String> list = new MyLinkedList<String>();
        list.add("张三");
        list.add("李四");
        list.add("王武");
        System.out.println(list);
        for (String str :
                list) {
            System.out.println(str);
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }
}
