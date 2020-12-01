package common;

/**
 * 实现链表 node、val、next、
 */
public class ListNode {

    public int val;

    public ListNode next;

    public ListNode(int x) {
        val = x;
        next = null;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                '}';
    }

    /**
     * 构建链表
     *
     * 例如：
     *      ListNode.create(1, 2, 3, 4, 5)
     *      1->2->3->4->5
     *
     */
    public static ListNode create(int... args) {    // 构建链表
        if (args.length == 0) {
            return null;
        }
        ListNode head = new ListNode(0);
        ListNode current = head;
        for (int arg : args) {
            current.next = new ListNode(arg);
            current = current.next;
        }
        return head.next;
    }

}
