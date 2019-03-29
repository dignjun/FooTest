package com.example.leetcode;

import java.util.*;

/**
 * 4.寻找两个有序数组的中位数
 * 示例 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * 则中位数是 2.0
 * 示例 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * 则中位数是 (2 + 3)/2 = 2.5
 * @author DINGJUN
 * @date 2019.03.29
 */
public class findMedianSortedArrays {

    public static void main(String[] args) {
        int[] nums1 = {1,2};
        int[] nums2 = {3,4};
        System.out.println(findMedianSortedArrays(nums1, nums2));
        System.out.println("-----------");
        List list = new ArrayList();
        list.add(1);
        list.add("123");
        System.out.println(list);
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        return 0;
    }
}
