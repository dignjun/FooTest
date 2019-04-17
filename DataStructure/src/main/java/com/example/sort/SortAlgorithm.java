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
        System.out.println("----");
        shellSort(arr);
        print(arr);
        System.out.println("----");
        Integer[] arr1 = {3,2,5,17,4,8};
        bubbleSort(arr1);
        print(arr1);
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

    /**
     * 希尔排序
     *
     * @param t
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void shellSort(T[] t){
        int j;
        for (int gap = t.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < t.length; i ++) {
                T tmp = t[i];
                for (j = i; j >= gap && tmp.compareTo(t[j - gap]) < 0; j-= gap) {
                    t[j] = t[j - gap];
                }
                t[j] = tmp;
            }
        }
    }



    private static int leftChild(int i) {
        return 2 * i + 1;
    }
    private static <T extends Comparable<? super T>> void percDown(T[] a, int i, int n) {
        int child=0;
        T tmp;
        for (tmp = a[i]; leftChild(i) < n; i = child) {
            child = leftChild(i);
            if(child != n -1 && a[child].compareTo(a[child + 1]) < 0)
                child ++;
            if (tmp.compareTo(a[child]) < 0)
                a[i] = a[child];
            else
                break;
        }
        a[i] = tmp;
    }
    public static <T extends Comparable<? super T>> void heapSort(T[] t) {
        for (int i = t.length/2 -1; i >= 0; i--) // buildHeap
            percDown(t, i, t.length);
        for (int i = t.length - 1; i > 0; i --) {
            // swapReferences(t, 0, i) // deleteMax
            percDown(t, 0, i);
        }
    }


    public static <T extends Comparable<? super T>> void merge(T[] a, T[] tmpArray, int leftPos, int rightPos, int rightEnd) {
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;
        // Main loop
        while(leftPos <= leftEnd && rightPos <= rightEnd)
            if (a[leftPos].compareTo(a[rightPos]) <= 0)
                tmpArray[tmpPos++] = a[leftPos++];
            else
                tmpArray[tmpPos++] = a[rightPos++];
        while (leftPos <= leftEnd) // copy rest of first half
            tmpArray[tmpPos++] = a[leftPos++];
        while (rightPos <= rightEnd) // copy rest of right half
            tmpArray[tmpPos++] = a[rightPos++];

        // copy tmpArray back
        for (int i = 0; i < numElements; i++, rightEnd--)
            a[rightEnd] = tmpArray[rightEnd];
    }


    /**
     * 冒泡
     * @param a
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void bubbleSort(T[] a) {
        for (int i=0; i < a.length; i++) {
            for (int j=0; j < a.length-1; j++) {
                if(a[j].compareTo(a[j+1]) > 0) {
                    T tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = tmp;
                }
            }
        }
    }




    // 打印
    public static <T> void print(T[] t) {
        for (T tmp: t) {
            System.out.println(tmp);
        }
    }
}











































