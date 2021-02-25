package no0024;

import common.ListNode;

/**
 * 24. 两两交换链表中的节点
 *      给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *      你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 *
 */
public class Solution {
    public ListNode swapPairs(ListNode head) {
        // 考虑边界
        if(head == null || head.next == null){
            return head;
        }
        // if()已经判断第二个节点存在，定义第二个节点为新链表的头节点(即原链表的第二个节点)
        ListNode second = head.next;
        // 改变指针的指向
        head.next = swapPairs(second.next);// 此时的 second.next 还是原链表的顺序（对于第一组而言 second.next 是第三个节点）
        // 第二个节点指向当时的前一个节点
        second.next = head;

        return second;
    }
}
