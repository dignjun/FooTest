package com.example.leetcode;

/**
 * 两个非空链表求和
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * @author DINGJUN
 * @date 2019.03.27
 */
public class SumLinkedList {

    /**
     * 链表求和
     *
     * @param l1 链表1
     * @param l2 链表2
     * @return 求和之后的链表
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1;
        ListNode q = l2;
        ListNode curr = dummyHead;
        int carry = 0;
        while (l1 != null || l2 !=null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if(p != null) p = p.next;
            if(q != null) q = q.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        if(carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }

    /**
     * 节点
     */
    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int n) {
            val = n;
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode(4);
        ListNode l3 = new ListNode(3);
        l1.next = l2;
        l2.next = l3;

        ListNode l4 = new ListNode(5);
        ListNode l5 = new ListNode(6);
        ListNode l6 = new ListNode(4);
        l4.next = l5;
        l5.next = l6;

        ListNode listNode = addTwoNumbers(l1, l4);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
