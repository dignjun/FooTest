package com.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 不同集合添加数据的时间测试
 *
 * @author DINGJUN
 * @date 2019.03.21
 */
public class CollectionTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();
        System.out.println("--------add(i)--------");
        long l1 = System.currentTimeMillis();
        CollectionTest.makeList1(list, 100000);
        long l2 = System.currentTimeMillis();
        System.out.println("list add time:" + (l2 - l1));
        long l3 = System.currentTimeMillis();
        CollectionTest.makeList1(linkedList, 100000);
        long l4 = System.currentTimeMillis();
        System.out.println("linkedList add time:" + (l4 - l3));
        System.out.println("--------add(0, i)--------");
        long l5 = System.currentTimeMillis();
        CollectionTest.makeList2(list, 100000);
        long l6 = System.currentTimeMillis();
        System.out.println("list add time:" + (l6 - l5));
        long l7 = System.currentTimeMillis();
        CollectionTest.makeList2(linkedList, 100000);
        long l8 = System.currentTimeMillis();
        System.out.println("linkedList add time:" + (l8 - l7));

        System.out.println("----------sum--------");
        long ll1 = System.currentTimeMillis();
        sum(list, 100000);
        long ll2 = System.currentTimeMillis();
        System.out.println("list add time:" + (ll2 - ll1));
        long ll3 = System.currentTimeMillis();
        sum(linkedList, 100000);
        long ll4 = System.currentTimeMillis();
        System.out.println("linkedList add time:" + (ll4 - ll3));
    }
    // 尾端添加
    public static void makeList1(List<Integer> list, int n){
        if(list != null) {
            list.clear();
            for (int i=0; i<n; i++) {
                list.add(i);
            }
        }
    }
    /**
     * 前端添加
     * @param list
     * @param n
     */
    public static void makeList2(List<Integer> list, int n){
        if(list != null) {
            list.clear();
            for (int i=0; i<n; i++) {
                list.add(0, i);
            }
        }
    }

    //求和,get(int index)l
    public static int sum(List<Integer> list, Integer n){
        int total = 0;
        for(int i=0; i < n; i++){
            total += list.get(i);
        }
        return total;
    }
}
