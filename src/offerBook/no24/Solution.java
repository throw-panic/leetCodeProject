package offerBook.no24;

import common.ListNode;

public class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;

        while(curr != null){
            ListNode temp = curr.next;
            curr.next = pre;
            pre = curr;
            // 往下走一位
            curr = temp;
        }

        // pre 已经移动到尾节点  此时的 pre 就是新节点的头节点
        return pre;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode head = solution.reverseList(ListNode.create(1, 2, 3, 4, 5));
        // 输出链表
        System.out.println(head);
        // 打印链表
        ListNode.printListNode(head);
    }
}
