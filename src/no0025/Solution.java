package no0025;


/**
 * 25. K 个一组翻转链表
 *      给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 *      k 是一个正整数，它的值小于或等于链表的长度。
 *      如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 */

import common.ListNode;

/**
 * 示例：
 *
 * 给你这个链表：1->2->3->4->5
 *
 * 当 k= 2 时，应当返回: 2->1->4->3->5
 * 当 k= 3 时，应当返回: 3->2->1->4->5
 *
 */
public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        // head 节点前面一个虚拟节点
        ListNode hair = new ListNode(0);
        hair.next = head;

        // head 节点的前一个节点（刚开始也就是 hair 节点，后续需要不断的移动，
        // 所以定义一个新的 pre 节点出来, hair 要用来 return 的
        ListNode pre = hair;

        // 递归整条链表
        while(head != null){
            // 不断截取出 k 长度的小链表，定义一个 tail 节点，检查大链表的长度是不是还够 k 长
            ListNode tail = pre.next; // 头节点往下走 看看够不够长呀
            for(int i = 1; i < k; i++){ // k = 1 是不用反转的，k=2 是反转长度为 2 的小链表...
                tail = tail.next;
                if(tail == null) {
                    return hair.next;   // 链表不够长了，直接返回链表，不需要继续翻转了
                }
            }

            // 够长，那就把这一段 长度为 k 的小链表翻转一下
            // 先保存 长度为 k 的小链表后面的 剩下的节点的头节点 restHead
            ListNode restHead = tail.next;
            // 翻转 小链表
            // 传入 头尾节点 head、tail，返回翻转后的 头节点和尾节点到数组中
            // 先切断小链表的尾巴
            tail.next = null;
            ListNode[] reverse = myReverse(head, tail);

            // 翻转后 小链表的 新头节点和尾节点
            head = reverse[0];  // 翻转后的小链表头
            tail = reverse[1];  // 翻转后的小链表的尾

            // 小链表 头尾 重新接回 原链表
            pre.next = head;
            tail.next = restHead; // 尾巴接上
            // pre 后移到下一个子链表头节点的 前一位，也就是 前一个小链表的 tail 节点；
            pre = tail;
            // head 改变为下一段小链表的 头节点
            head = tail.next;
        }
        return hair.next;
    }

    // 给出头尾节点  翻转链表
    public ListNode[] myReverse(ListNode head, ListNode tail){
        // 链表的头节点的 前一个节点
        ListNode pre = null;
        ListNode curr = head;

        while(curr != null){
            ListNode temp = curr.next;
            curr.next = pre;
            pre = curr;

            curr = temp; // 转完了, 往下走一位
        }
        // 整个链表翻转过程 不过是指针指向的变化，带过来头尾节点，返回不就是尾节点变头节点，头节点变尾节点吗
        return new ListNode[]{tail, head};
    }

    public static void main(String[] args) {
        no0025.Solution solution = new Solution();
        ListNode head = solution.reverseKGroup(ListNode.create(1, 2, 3, 4, 5), 2);
        // 打印节点
        System.out.println(head);
        // 打印链表
        ListNode.printListNode(head);
    }
}
	