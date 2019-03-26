package com.example.tree;

import java.util.Stack;

/**
 * AVL平衡条件树
 *
 * @author DINGJUN
 * @date 2019.03.26
 */
public class AvlTree<T extends Comparable<? super T>> {

    /**
     * return the height of node t, or -1, if null
     *
     * @param t 节点
     * @return 树高
     */
    private int height(AvlNode<T> t) {
        return t == null ? -1 : t.height;
    }

    /**
     * 插入一个元素,和二叉查找树过程类似,区别是最后的平衡过程
     *
     * @param x 元素
     * @param t avl平衡树
     * @return avl平衡树
     */
    private AvlNode<T> insert(T x, AvlNode<T> t) {
        if (t == null)
            return new AvlNode<>(x, null, null);
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0) {
            t.left = insert(x, t.left);
        } else if (compareResult > 0) {
            t.right = insert(x, t.right);
        } else {
            ;// duplicate; do nothing
        }
        return balance(t);
    }

    private static final int ALLOWED_IMBALANCE = 1;

    /**
     * 确保树的平衡
     *
     * @param t 树节点
     * @return 平衡的树节点
     */
    private AvlNode<T> balance(AvlNode<T> t) {
        if (t == null)
            return t;
        if (height(t.left) - height(t.right) > ALLOWED_IMBALANCE) {
            if (height(t.left.left) >= height(t.left.right)) {
                t = rotateWithLeftChild(t);
            } else {
                t = doubleWithLeftChild(t);
            }
        } else if (height(t.right) - height(t.left) > ALLOWED_IMBALANCE) {
            if (height(t.right.right) >= height(t.right.left)) {
                t = rotateWithRightChild(t);
            } else {
                t = doubleWithRightChild(t);
            }
        }

        t.height = Math.max(height(t.left), height(t.right)) + 1;
        return t;
    }

    /**
     * 通过左子树旋转二叉查找树
     * 对AVL二叉树,这是一个单旋转应对情形1, 左左情形.
     * 更新树高,并返回新的根
     * @param k2 根节点的左子树
     * @return 新的根节点
     */
    private AvlNode<T> rotateWithLeftChild(AvlNode<T> k2) {
        AvlNode<T> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), k2.height) + 1;
        return k1;
    }

    private AvlNode<T> doubleWithLeftChild(AvlNode<T> k3) {
        k3.left = rotateWithLeftChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    private AvlNode<T> rotateWithRightChild(AvlNode<T> k2) {
        AvlNode<T> k1 = k2.right;
        k2.right = k1.left;
        k1.left = k2;
        k2.height = Math.max(height(k2.right), height(k2.left)) + 1;
        k1.height = Math.max(height(k1.right), k2.height) + 1;
        return k1;
    }

    private AvlNode<T> doubleWithRightChild(AvlNode<T> k3) {
        k3.right = rotateWithRightChild(k3.right);
        return rotateWithRightChild(k3);
    }

    private AvlNode<T> remove(T x, AvlNode<T> t) {
        if(t == null)
            return t;
        int compareResult = x.compareTo(t.element);
        if(compareResult < 0)
            t.left = remove(x, t.left);
        else if(compareResult > 0)
            t.right = remove(x, t.right);
        else if(t.left != null && t.right != null) {
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        } else {
            t = (t.left != null) ? t.left : t.right;
        }
        return balance(t);
    }

    private AvlNode<T> findMin(AvlNode<T> t) {
        if(t == null)
            return null;
        else if(t.left == null)
            return t;
        return findMin(t.left);
    }

    /**
     * 节点
     *
     * @param <T>
     */
    private static class AvlNode<T> {
        T element; // data
        AvlNode<T> left; // lef child
        AvlNode<T> right; // right child
        int height; // height

        AvlNode(T theElement, AvlNode<T> lt, AvlNode<T> rt) {
            element = theElement;
            left = lt;
            right = rt;
            height = 0;
        }
    }

//    public static void main(String[] args) {
//        AvlTree<Integer> tree = new AvlTree<>();
//        AvlNode<Integer> node = tree.insert(1, null);
//        node = tree.insert(2, node);
//        tree.insert(3, node);
//        System.out.println("avl tree:" + tree);
//        System.out.println("--------------");
//        System.out.println("avl tree:" + tree.print1(tree.););
//
//    }
//
//    //前序遍历-递归方式
//    private void print1(AvlNode<T> t){
//        if(t != null){
//            System.out.println(t.element);
//            print1(t.left);
//            print1(t.right);
//        }
//    }
//    //前序遍历-非递归方式
//    private void print2(AvlNode<T> t){
//        Stack<AvlNode> stack = new Stack<>();
//        while(true) {
//            while(t != null) {
//                System.out.println(t.element);
//                stack.push(t);
//                t = t.left;
//            }
//            if(stack.isEmpty())
//                break;
//            t = stack.pop();
//            t = t.right;
//        }
//    }
}







































