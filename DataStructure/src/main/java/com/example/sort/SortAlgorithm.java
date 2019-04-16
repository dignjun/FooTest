package com.example.sort;

/**
 * 排序算法
 *
 * @author DINGJUN
 * @date 2019.04.16
 */
public class SortAlgorithm {
    public static void main(String[] args) {
        Integer[] arr = {1, 5, 3, 6, 1, 8, 2, 9};
        insertionSort(arr);
        print(arr);
    }

    /**
     * 插入排序
     * @param t
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void insertionSort(T[] t) {
        int j;
        for (int p = 1; p < t.length; p++) {
            T tmp = t[p];
            for (j = p; j > 0 && tmp.compareTo(t[j - 1]) < 0; j--) {
                t[j] = t[j - 1];
            }
            t[j] = tmp;
        }
    }

    // 打印
    public static <T> void print(T[] t) {
        for (T tmp: t) {
            System.out.println(tmp);
        }
    }
}











































