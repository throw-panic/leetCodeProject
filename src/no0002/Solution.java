package no0002;


/**
 * 2. 两数相加
 * （链表求和）
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点
 * 只能存储一位数字。如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例：
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */

public class Solution {

    // 合并链表后的头尾节点
    private ListNode head = null;
    private ListNode tail = null;

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 进位
        int extra = 0;

        //递归两个链表
        while (true) {
            // 定义两条链表对应节点之和；进位加到 sum
            int sum = extra;
            // extra 清零（否则会影响下一次进位数）
            extra = 0;

            // 如果都为空节点
            if (l1 == null && l2 == null) {
                if (sum > 0) {
                    // 加上最后一个节点
                    buildNode(sum);
                }
                // 跳出 while 循环
                break;
            }

            // 链表存在 非空（1条/2条）
            if (l1 != null) {
                sum += l1.val;
            }
            if (l2 != null) {
                sum += l2.val;
            }

            // 求和
            if (sum > 9) {
                // 进位更新
                extra = sum / 10;
                sum %= 10;
            }

            // 设置到新的节点上面
            buildNode(sum);

            // 跳到下一个节点
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        return head;
    }

    // 生成链表
    private void buildNode(int sum) {
        // 头节点是否建好
        if (head == null) {
            // 新建链表
            head = tail = new ListNode(sum);
            return;
        }

        // 存在头节点，直接在尾节点继续接上新的节点即可
        tail.next = new ListNode(sum);
        // 尾巴后移动
        tail = tail.next;
    }
}
