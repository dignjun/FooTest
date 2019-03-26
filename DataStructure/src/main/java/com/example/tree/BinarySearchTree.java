package com.example.tree;

import java.nio.BufferUnderflowException;
import java.util.Stack;

/**
 * 二叉查找树设计
 * <p>
 * Created by DINGJUN on 2019.03.25.
 */
public class BinarySearchTree<T extends Comparable<? super T>> {

    private BinaryNode<T> root;

    public BinarySearchTree() {
        root = null;
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(T x) {
        return contains(x, root);
    }

    public T findMin() {
        if (isEmpty())
            throw new BufferUnderflowException();
        return findMin(root).element;
    }

    public T findMax() {
        if (isEmpty())
            throw new BufferUnderflowException();
        return findMax(root).element;
    }

    public void insert(T x) {
        root = insert(x, root);
    }

    public void remove(T x) {
        root = remove(x, root);
    }

    public void printTree() {

    }

    /**
     * 树T中存在含有项X的节点,返回true,否在,返回false
     *
     * @param x 节点
     * @param t 树
     * @return
     */
    private boolean contains(T x, BinaryNode<T> t) {
        if (t == null)
            return false;
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0) {
            return contains(x, t.left);
        } else if (compareResult > 0) {
            return contains(x, t.right);
        } else {
            return true; // match
        }
    }

    /**
     * 查找最小节点(递归实现)
     *
     * @param t
     * @return
     */
    private BinaryNode<T> findMin(BinaryNode<T> t) {
        if (t == null)
            return null;
        else if (t.left == null)
            return t;
        return findMin(t.left);
    }

    /**
     * 查找最大节点(循环实现)
     *
     * @param t
     * @return
     */
    private BinaryNode<T> findMax(BinaryNode<T> t) {
        if (t != null)
            while (t.right != null)
                t = t.right;
        return t;
    }

    /**
     * 将元素插入到二叉查找树中.
     *
     * @param x 节点元素
     * @param t 二叉查找树
     * @return
     */
    private BinaryNode<T> insert(T x, BinaryNode<T> t) {
        if (t == null) {
            return new BinaryNode<T>(x, null, null);
        }
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0) {
            t.left = insert(x, t.left);
        } else if (compareResult > 0) {
            t.right = insert(x, t.right);
        } else {
            ; // Duplicate ; do nothing
        }
        return t;
    }

    /**
     * 删除二叉树中的元素
     *
     * @param x 元素节点
     * @param t 二叉查找树
     * @return
     */
    private BinaryNode<T> remove(T x, BinaryNode<T> t) {
        if (t == null)
            return t;
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0) {
            t.left = remove(x, t.left);
        } else if (compareResult > 0) {
            t.right = remove(x, t.right);
        } else if (t.left != null && t.right != null) { // tow children
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        } else { // one children
            t = (t.left != null) ? t.left : t.right;
        }
        return t;
    }

    private void printTree(BinaryNode<T> t) {
        System.out.println("BinaryTree list");
    }


    /**
     * 树节点静态内部类
     *
     * @param <T>
     */
    private static class BinaryNode<T> {
        // field
        T element;
        BinaryNode<T> left;
        BinaryNode<T> right;

        // Constructors
        BinaryNode(T theElement) {
            this(theElement, null, null);
        }

        // Constructors
        BinaryNode(T theElement, BinaryNode<T> lt, BinaryNode<T> rt) {
            element = theElement;
            left = lt;
            right = rt;
        }
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(6);
        tree.insert(3);
        tree.insert(5);
        tree.insert(8);
        tree.insert(1);
        tree.insert(7);
        tree.print1(tree.root);
        System.out.println("-----");
        tree.print2(tree.root);
        System.out.println("-----");
        tree.print3(tree.root);
        System.out.println("-----");
        tree.print4(tree.root);
        System.out.println("-----");
        tree.print5(tree.root);
        System.out.println("-----");
        tree.print6(tree.root);
    }

    // 前序遍历--递归例程
    private void print1(BinaryNode<T> t) {
        if (t != null) {
            System.out.println(t.element);
            print1(t.left);
            print1(t.right);
        }
    }

    // 前序遍历--循环例程
    private void print2(BinaryNode<T> t) {
        Stack<BinaryNode> stack = new Stack<>();
        while (true) {
            while (t != null) {
                System.out.println(t.element);
                stack.push(t);
                t = t.left;
            }
            if (stack.isEmpty())
                break;
            t = stack.pop();
            t = t.right;
        }
    }


    // 中序遍历--递归例程
    private void print3(BinaryNode<T> t) {
        if (null != t) {
            print3(t.left);
            System.out.println(t.element);
            print3(t.right);
        }
    }

    // 中序遍历--循环例程
    private void print4(BinaryNode<T> t) {
        Stack<BinaryNode> stack = new Stack<>();
        while (true) {
            while (null != t) {
                stack.push(t);
                t = t.left;
            }
            if (stack.isEmpty())
                break;
            t = stack.pop();
            System.out.println(t.element);
            t = t.right;
        }
    }


    // 后续遍历--递归例程
    private void print5(BinaryNode<T> t) {
        if (null != t) {
            print5(t.left);
            print5(t.right);
            System.out.println(t.element);
        }
    }

    // 后续遍历--循环例程
    private void print6(BinaryNode<T> t) {
        Stack<BinaryNode> stack = new Stack<>();
        while (true) {
            if (t != null) {
                stack.push(t);
                t = t.left;
            } else {
                if (stack.isEmpty()) return;

                if (null == stack.lastElement().element) {
                    t = stack.pop();
                    System.out.print(t.element + "\t");
                    while (t == stack.lastElement().right) {
                        System.out.print(stack.lastElement().element + "\t");
                        t = stack.pop();
                        if (stack.isEmpty()) {
                            break;
                        }
                    }
                }

                if (!stack.isEmpty())
                    t = stack.lastElement().right;
                else
                    t = null;
            }
        }
    }
}
































































