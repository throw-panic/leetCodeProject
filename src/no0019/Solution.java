package no0019;

import common.ListNode;

/**
 * 19. 删除链表的倒数第N个节点
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 示例：
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 *
 */
public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        ListNode slow = head;

        // fast 先走 n 步
        while(n > 0){
            fast = fast.next;
            n--;
        }

        // 考虑一下边界：链表长度为 n，返回倒数第 n 个数, 并删掉，
        // 即删掉了链表的头节点（剩下链表表头变为 head.next）
        if(fast == null){
            // head 节点被删掉了
            return head.next;
        }

        // 同步走 fast.next == null 时候结束，此时 slow 节点位于倒数第 n 节点前一个节点
        while(fast.next != null){
            fast =fast.next;
            slow = slow.next;
        }
        // 拿掉倒数第 n 个节点
        slow.next = slow.next.next;
        return head;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        // 返回输出链表头节点
        System.out.println(solution.removeNthFromEnd(ListNode.create(1, 2, 3, 4, 5), 5));
    }
}
