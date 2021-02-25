package no0023;


import common.ListNode;

import java.util.List;

/**
 * https://leetcode-cn.com/problems/merge-k-sorted-lists/
 * 23. 合并K个升序链表
 *      给你一个链表数组，每个链表都已经按升序排列。
 *      请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * todo:
 *      思路，对于多个数组元素的合并，两两合并。
 *      时间复杂度 O(N * logk)
 */
public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0){
            return null;
        }

        int length = lists.length;
        while (length > 1 ){    // 合并到只剩下一条链表为止

            // 起点位置
            int start = 0;
            if((length & 1) == 1){  // 不被整除，说明有一个不能被两两合并，就跳过第一个。
                start = 1;
            }

            // 开始两两合并
            for(int i = start; i < length; i += 2){
                // 合并
                lists[start] = merge2Lists(lists[i], lists[i+1]);
                start++;
            }
            // 设置新的长度(新一轮两两合并)
            length = start;
        }
        // 返回最终的一条链表
        return lists[0];
    }

    public ListNode merge2Lists(ListNode l1, ListNode l2){
        ListNode preHead = new ListNode(0);
        // 比较
        ListNode current = preHead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                current.next = l1;  // 指向 l1 当前头节点
                l1 = l1.next;
            } else {
                current.next = l2;  // 指向 l2 当前头节点
                l2 = l2.next;
            }
            // 后移一个节点
            current = current.next;
        }
        // 当有一条链表为空的时候，补全上来
        current.next = l1 != null ? l1 : l2;
        return preHead.next;
    }
}
