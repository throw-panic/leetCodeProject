# LeetCode 代码总结复习


- 一、数据结构
    - （一）链表
    - （二）二叉树
    - （三）栈和队列
    - （四）
    
    
- 二、算法
    - （一）动态规划
        - 斐波那契
        - 凑领钱问题
        - 编辑距离
        - 高楼扔鸡蛋
        - 股票买卖问题
        - 打家劫舍问题
        - 博弈问题
        - 贪心算法（特殊的动态规划）
        - KMP 算法（字符串匹配算法）
        - 敲键盘问题
        - 最长递增子序列
        - 最长子序列
        - 最长回文串子序列
        - 最长公共子序列



## 一、数据结构

### （一）、链表（10）

160. 相交链表

编写一个程序，找到两个单链表相交的起始节点。

```java

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

 // 思路  LA + LB = LB + LA
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode l1 = headA;
        ListNode l2 = headB;

        while(l1 != l2){
            l1 = (l1 == null) ? headB : l1.next;
            l2 = (l2 == null) ? headA : l2.next;
        }
        return l1;
    }
}

```

206. 反转链表

反转一个单链表。

输入: 1->2->3->4->5->NULL;    输出: 5->4->3->2->1->NULL

```java

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;

        // 递归转换指针方向
        while(cur != null){     // 递归结束点在于 pre节点变为新链表的头节点newHead
            // 存一个临时节点
            ListNode temp = cur.next;

            // 转换指针方向，cur节点指向前一个节点pre
            cur.next = pre;
            // pre 节点往下递归到cur 节点
            pre = cur;
            // cur 节点递归到下一个节点 原链表的 cur.next 节点（即为temp存的那个节点）
            cur = temp;
        }
        // 返回新链表的头节点
        return pre;
    }
}

```


21. 归并两个有序链表

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 边界
        if(l1 == null){
            return l2;
        }
        if(l2 == null){
            return l1;
        }
        
        // merge
        if(l1.val < l2.val){
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }else{
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}

```

83. 删除排序链表中的重复元素

给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。

示例 1:
输入: 1->1->2
输出: 1->2


示例 2:
输入: 1->1->2->3->3
输出: 1->2->3


```java

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// 最好理解的做法
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        // 取出当前节点
        ListNode curr = head;
        while(curr != null && curr.next != null){
            // 如果当前节点值和下一个节点值相同
            if(curr.val == curr.next.val){
                curr.next = curr.next.next;         // 更新 curr.next
            }else{
                // 当前节点和下一个节点值不相同
                // 链表递归往下走一位
                curr =curr.next;                    // 更新 curr
            }
        }
        // 返回剪裁后的 head 节点
        return head;
    }
}

```


19. 删除链表的倒数第N个节点

给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。

示例：

给定一个链表: 1->2->3->4->5, 和 n = 2.

当删除了倒数第二个节点后，链表变为 1->2->3->5.

```java

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

 // 找倒数第 n 个节点，双指针： 快指针先走n步（题目默认链表长度大于等于n，不然倒数第n个数直接不存在了兄弟！！！）
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        ListNode slow = head;

        // fast 先走 n 步
        while(n > 0){
            fast = fast.next;
            n--;
        }

        // 考虑一下边界：链表长度为 n，返回倒数第 n 个数, 并删掉，即删掉了链表的头节点（剩下链表表头变为 head.next）
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
}


```

24. 交换链表中的相邻结点

示例:

给定 1->2->3->4, 你应该返回 2->1->4->3.


```java

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

 // 递归的解法
class Solution {
    public ListNode swapPairs(ListNode head) {
        // 考虑边界
        if(head == null || head.next == null){
            return head;
        }
        // if()已经判断第二个节点存在，定义第二个节点为新链表的头节点(即原链表的第二个节点)
        ListNode second = head.next;
        // 改变指针的指向
        head.next = swapPairs(second.next);    // 此时的 second.next 还是原链表的顺序（对于第一组而言 second.next 是第三个节点）
        // 第二个节点指向当时的前一个节点
        second.next = head;
        
        return second;

    }
}

```


445.    链表求和

示例：

输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
输出：7 -> 8 -> 0 -> 7

```java

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// 链表求和

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> l1Stack = buildStack(l1);
        Stack<Integer> l2Stack = buildStack(l2);

        // 求链表之和
        int carry = 0;  // 进位
        ListNode temp = new ListNode(-1);   // 建一个临时节点（倒叙求和后，反向输出链表）
        while(!l1Stack.isEmpty() || !l2Stack.isEmpty() || carry != 0){
            // 取出 x,y 注意空栈异常
            int x = (l1Stack.isEmpty() == true) ? 0 : l1Stack.pop();
            int y = (l2Stack.isEmpty() == true) ? 0 : l2Stack.pop();
            int sum = x + y + carry;

            ListNode node = new ListNode(sum % 10);     // 取余; 从后往前拿到新链表的每一个节点
            node.next = temp.next;   // 指针指向，当前节点指针指向下一节点（新链表的最后一个节点（第一个求出的node）指向空节点）
            temp.next = node;        // temp.next 指向已经求出来的前一个节点
            // 更新 进位 carry
            carry = sum / 10;   // 取商
        }

        // 返回新链表
        return temp.next;   // temp.next 是最后一个求出的 node 节点，即为新链表的头节点
    }

    public Stack<Integer> buildStack(ListNode head){
        Stack<Integer> stack = new Stack<>();
        while(head != null){
            stack.push(head.val);
            head = head.next;
        }
        return stack;
    }
}

```


234. 回文链表

请判断一个链表是否为回文链表。
(回文：中点的左右两侧对应位置相等)

示例 1:

输入: 1->2
输出: false

示例 2:

输入: 1->2->2->1
输出: true


```java

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */


// 回文链表：前半段和后半段是否相等;  注意：不是判断链表有没有环
// 两种思路代码都要会写

// 思路一： 链表切成两半（快慢指针），翻转后半部分，比对两个链表是否相同即可（思路二的代码更好写，思路一更考验对链表的学习和熟悉）

class Solution {
    public boolean isPalindrome(ListNode head) {
        // 边界
        if(head == null || head.next == null){
            return true;
        }

        // 快慢指针
        ListNode slow = head;
        ListNode fast = head.next;
        while(fast != null && fast.next != null){       // 节点数 > 2 情况（node数 <= 1 在 if 边界已经考虑，
                                                        // node 个数 == 2 时，跳过while, 直接采用上述的 slow、fast 计算）
            fast = fast.next.next;
            slow = slow.next;
        }
        // 遍历结束
        if(fast != null){   // 说明链表节点数为偶数, 切断点往后移动一位（因为切断节点属于前半段链表,
                            // 下一位属于后半段链表的头节点）; 奇数节点数直接切断即可;
            slow = slow.next;
        }
        cutListNode(head, slow);
        return isEqual(head, reverseListNode(slow));
    }

    // 切成两半
    public void cutListNode(ListNode head, ListNode slow){
        // 找到后半段节点的头节点的前一个节点（因为node.next 要切断）
        while(head.next != slow){
            head = head.next;
        }
        // 找到了 slow 节点的前一个节点，直接切断
        head.next = null;
    }

    // 翻转链表
    public ListNode reverseListNode(ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        ListNode pre = new ListNode(-1);
        ListNode cur = head;
        while(cur != null){
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        // 此时的 pre 应该是原链表最后一个节点，新链表头节点
        return pre;
    }

    // 判断两个链表是否相等
    public boolean isEqual(ListNode l1, ListNode l2){
        while(l1 != null && l2 != null){
            if(l1.val != l2.val){
                return false;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        return true;
    }
    
}




// 思路二：把链表用数组装起来，判断数组是否回文即可，时间空间复杂 O(n))

class Solution {
    public boolean isPalindrome(ListNode head) {
        List<Integer> arrayList = new ArrayList<>();
        while(head != null){
            arrayList.add(head.val);
            head = head.next;
        }
        // 判断数组前后是否一致
        int start = 0;
        int end = arrayList.size() - 1;     // 数组越界问题要注意呀
        while(start < end){
            // if(arrayList.get(start) != arrayList.get(end)){      // 注意不能用 "!="  当值 Integer 超过 -128~127以外时，结果不对的
            //     // 直接返回 不是回文
            //     return false;
            // }

            /*  (补充java常量池知识点)
在-128~127的Integer值并且以Integer x = value;的方式赋值的Integer值在进行==和equals比较时，都会返回true，因为Java里面对处在在-128~127之间的Integer值，用的是原生数据类型int，此时调用的是Integer.valueOf()方法，会在内存里供重用，也就是说这之间的Integer值进行==比较时只是进行int原生数据类型的数值比较，而超出-128~127的范围，进行==比较时是进行地址及数值比较。
            */
            if(!arrayList.get(start).equals(arrayList.get(end))){
                // 直接返回 不是回文
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}


```


725. 分隔链表

给定一个头结点为 root 的链表, 编写一个函数以将链表分隔为 k 个连续的部分。每部分的长度应该尽可能的相等: 任意两部分的长度差距不能超过 1，也就是说可能有些部分为 null。这k个部分应该按照在链表中出现的顺序进行输出，并且排在前面的部分的长度应该大于或等于后面的长度。返回一个符合上述规则的链表的列表。

示例 1：

输入: 
root = [1, 2, 3], k = 5
输出: [[1],[2],[3],[],[]]

解释:
输入输出各部分都应该是链表，而不是数组。


示例 2：

输入: 
root = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], k = 3

输出: [[1, 2, 3, 4], [5, 6, 7], [8, 9, 10]]

解释:
输入被分成了几个连续的部分，并且每部分的长度相差不超过1.前面部分的长度大于等于后面部分的长度。


```java

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

class Solution {
    public ListNode[] splitListToParts(ListNode root, int k) {
        // 统计链表节点数
        int Num = 0;
        ListNode curr = root;       // 这一步不能少，不然回不到头节点了！！！
        while(curr != null){
            Num++; // 统计链表节点数
            curr = curr.next;
        }

        // 分隔后的子链表长度(循环中再考虑：长度要不要加一，因为不一定是刚好都一样长)；   分隔后的子链表个数为 k
        int size = Num / k;     // 向 0 取整
        // 补充余数
        int carry = Num % k;  // 待补充到长度+1链表中的余数 
        
        // 子链表数组集合
        ListNode[] result = new ListNode[k];      // k 条链表  

        curr = root;    // 重新回到头节点
        // 递归塞到集合中
        for(int i = 0; curr != null && i < k; i++){
            result[i] = curr;   // 只塞每一个小链表是我头节点即可
            // 切断链表
            int curSize = size + (carry-- > 0 ? 1 : 0);
            for(int j = 0; j < curSize - 1; j++){     // 要减 1; 递归次数少一次因为已经给了头节点为 curr 节点了
                curr = curr.next;
            }
            ListNode temp = curr.next;
            curr.next = null;   // 切断
            curr = temp;         // 往下递归呗~
        }

        return result;
    }
}


```

328. 链表元素按奇偶聚集

给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。

示例 1:

输入: 1->2->3->4->5->NULL
输出: 1->3->5->2->4->NULL
示例 2:

输入: 2->1->3->5->6->4->7->NULL 
输出: 2->3->6->7->1->5->4->NULL


```java


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode oddEvenList(ListNode head) {
        // 边界
        if(head == null || head.next == null){
            return head;
        }

        // 奇偶子链表表头
        // 奇
        ListNode odd = head;
        // 偶
        ListNode even = head.next;

        // 偶数链表的头节点
        ListNode evenHead = even;   // 后面要拼接到奇数链表的尾部

        while(even != null && even.next != null){         // 递归的主线 奇数节点不为空
            // 奇数
            odd.next = odd.next.next;       // 原链表跳两位
            // 递归
            odd = odd.next;

            // 偶数
            even.next = even.next.next;
            // 递归
            even = even.next;
        }
        // 连上两个子节点
        odd.next = evenHead;
        
        // 返回修改后的原链表的头节点（即为新链表的头节点）
        return head;
    }
}


```


### （二）、二叉树（31）


#### 递归

104. 树的高度

给定一个二叉树，找出其最大深度。二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。


```java

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }
}


```


110. 平衡树

平衡树左右子树高度差都小于等于 1


```java

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    private boolean result = true;

    public boolean isBalanced(TreeNode root) {
        // 求深度
        maxDepth(root);
        return result;
    }

    public int maxDepth(TreeNode root){
        if(root == null){
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        if(Math.abs(left - right) > 1){
            result = false;
        }
        return 1 + Math.max(left, right);
    }
}


```

543. 两节点的最长路径(二叉树的直径)


给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。


```java

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    private int max = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return max;
    }
    public int depth(TreeNode root){
        if(root == null){
            return 0;
        }
        int left = depth(root.left);
        int right = depth(root.right);
        // 针对任一父节点的 left, right
        max = Math.max(max, left + right);
        return 1 + Math.max(left, right);
    }
}

```


226. 翻转二叉树


```java

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

 // 翻转二叉树
 
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root == null){
            return null;
        }

        // 扫描到尾部
        invertTree(root.left);
        invertTree(root.right);
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        return root;
    }
}


```


617. 归并两棵树

给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。

示例 1:

输入: 
	Tree 1                     Tree 2                  
          1                     2                           
         / \                   / \                            
        3   2                 1   3                        
       /                       \   \                      
      5                        4   7                  
输出: 
合并后的树:
	     3
	    / \
	   4   5
	  / \   \ 
	 5   4   7


```java

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        // 边界条件考虑
        if(t1 == null && t2 == null){
            return null;
        }
        if(t1 == null){
            return t2;
        }
        if(t2 == null){
            return t1;
        }
        // 造一个根节点root，或者是合并后的节点node, 出来呀
        TreeNode root = new TreeNode(t1.val + t2.val);
        // 左右递归
        root.left = mergeTrees(t1.left, t2.left);
        root.right = mergeTrees(t1.right, t2.right);
        
        return root;
    }
}


```

112. 判断路径和是否等于一个数

给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。


示例: 
给定如下二叉树，以及目标和 sum = 22，

              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。


```java

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null){
            return false;
        }
        // 确保已经到达叶子节点,且值相同
        if(root.left == null && root.right == null ){
            return root.val == sum;
        }
        
        // 递归
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}


```


437. 路径总和

给定一个二叉树，它的每个结点都存放着一个整数值。找出路径和等于给定数值的路径总数。路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。


示例：

root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8


        10
        /  \
        5   -3
       / \    \
      3   2   11
     / \   \
    3  -2   1


返回 3。和等于 8 的路径有:

1.  5 -> 3
2.  5 -> 2 -> 1
3.  -3 -> 11


```java

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */


// 注意：考虑从根节点开始,和非根节点开始这两种情况;

class Solution {

    public int pathSum(TreeNode root, int sum) {
        // 规避掉空指针异常
        if(root == null){
            return 0;
        }
        // left和right 分别为起点时，不需要减掉 root.val
        int result = starWithRoot(root, sum) + pathSum(root.right, sum) + pathSum(root.left, sum);
        return result;
    }

    public int starWithRoot(TreeNode root, int sum){
        if(root == null){
            return 0;
        }
        int num = 0;

        // 不一定要求为叶子节点
        // if(root.left == null && root.right == null && sum == root.val){
        if(sum == root.val){
            num++;
        }
        // 还没到 0 , 继续往下找，减掉 value
        num += starWithRoot(root.left, sum - root.val) + starWithRoot(root.right, sum - root.val);
        return num;
    }
}


```

572. 子树

给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。


```java

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        // 递归边界
        if(s == null){
            return false;
        }
        // 注意递归别写错了
        return isSubtreeWithRoot(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
        // 错误写法(错误的写法，会漏掉s.left 下面的节点为子节点的根节点的情况，比如以 s.left.left 为根节点的子节点)
        // return isSubtreeWithRoot(s, t) || isSubtreeWithRoot(s.left, t) || isSubtreeWithRoot(s.right, t);

    }
    public boolean isSubtreeWithRoot(TreeNode s, TreeNode t){
        // 均为空
        if(s == null && t == null)
            return true;
        // 两者之一为空, 另一不为空, 则不匹配
        if(s == null || t == null)
            return false;
        // 节点值不相等
        if(s.val != t.val)
            return false;
        // 往下递归
        return isSubtreeWithRoot(s.left, t.left) && isSubtreeWithRoot(s.right, t.right);
    }
}

```

101. 树的对称


```java


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root == null){
            return true;
        }
        // 判断左右节点是不是相等？
        return isSymmetricFunc(root.left, root.right);
    }

    public boolean isSymmetricFunc(TreeNode t1, TreeNode t2){
        if(t1 == null && t2 == null){
            return true;
        }
        if(t1 == null || t2 == null){
            return false;
        }

        // // 逆向思维
        // if(t1.val != t2.val){
        //     return false;
        // }
        // // 绝对的对称
        // return isSymmetricFunc(t1.left, t2.right) && isSymmetricFunc(t1.right, t2.left);

        // 或者换一种写法：正向思维（更高效，if 判断减少了递归次数）
        if(t1.val == t2.val){
            return isSymmetricFunc(t1.left, t2.right) && isSymmetricFunc(t1.right, t2.left);
        }
        // 不相等的话
        return false;
    }
}

```


111. 最小路径

树的根节点到叶子节点的最小路径长度

```java

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */


// 思考：只需要分别计算其左右子树的最小叶子节点深度。这样就将一个大问题转化为了小问题，可以递归地解决该问题。

class Solution {
    public int minDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        // 到达叶节点
        if(root.left == null && root. right == null){
            return 1;
        }
        
        // 左右子节点的最短路径
        int minLR = Integer.MAX_VALUE;
        // 左非空,左边往下找
        if(root.left != null){
            minLR = Math.min(minDepth(root.left), minLR);
        }
        // 右非空，右边往下找
        if(root.right != null){
            minLR = Math.min(minDepth(root.right), minLR);
        }
        return minLR + 1;
    }
}

```

404. 统计左叶子节点的和

```java

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    private int sum = 0;
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null){   
            return 0;
        }
        // root 节点是根节点,不属于左叶节点

        // 递归找出来  所有的叶节点
        int left = sumOfLeftLeaves(root.left);
        int right = sumOfLeftLeaves(root.right);        // 不能省略，会漏算 right.left

        // 拿左叶节点
        if(isLeftLeaf(root.left)){
            sum = sum + root.left.val;
        }
        return sum;
    }

    public boolean isLeftLeaf(TreeNode root){
        if(root == null){
            return false;
        }
        if(root.left == null && root.right == null){
            return true;
        }
        return false;
    }
}

```

687. 最长同值长度

相同节点值的最大路径长度。（两个节点之间的路径长度由它们之间的边数表示。）


示例 1:

输入:

              5
             / \
            4   5
           / \   \
          1   1   5
输出:
2

示例 2:
输入:

              1
             / \
            4   5
           / \   \
          4   4   5
输出:
2


```java


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    int ans;
    public int longestUnivaluePath(TreeNode root) {
        ans = 0;
        findLonggestPath(root);
        return ans;
    }

    public int findLonggestPath(TreeNode root){     // 深度遍历递归
        // 边界条件限定
        if(root == null){
            return 0;
        }

        // 遍历两边
        int left = findLonggestPath(root.left);
        int right = findLonggestPath(root.right);
        
        // 单侧最长路径
        int singleSide = 0;
        // 包含根节点的路径
        if(root.left != null && root.val == root.left.val && root.right != null && root.right.val == root.val){
            ans = Math.max(ans, left + right + 2);
        }

        // 仅仅是单侧节点的路径
        // 左
        if(root.left != null && root.val == root.left.val){
            singleSide = Math.max(singleSide, left + 1);
        }
        if(root.right != null && root.val == root.right.val){
            singleSide = Math.max(singleSide, right + 1);
        }

        // 结果
        ans = Math.max(singleSide, ans);

        return singleSide;  // 深度遍历递归，别返回错了！！！
    }
}

```

337. 间隔遍历（打家劫舍：二叉树类型）

```java

class Solution {
    public int rob(TreeNode root) {
        if(root == null){
            return 0;
        }

        // 第一种情况：偷根节点
        int withRoot = root.val;
        // 左节点不为空
        if(root.left != null){
            withRoot += rob(root.left.left) + rob(root.left.right);
        }
        // 右节点也不为空
        if(root.right != null){
            withRoot += rob(root.right.left) + rob(root.right.right);
        }

        // 第二种情况：不偷根节点
        int notRoot = rob(root.left) + rob(root.right);

        // 取最大的
        int max = Math.max(withRoot, notRoot);
        return max;
    }
}

```


671. 找出二叉树中第二小的节点


给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一个。给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。


示例 1:
输入: 
    2
   / \
  2   5
     / \
    5   7

输出: 5
说明: 最小的值是 2 ，第二小的值是 5 。

示例 2:
输入: 
    2
   / \
  2   2

输出: -1
说明: 最小的值是 2, 但是不存在第二小的值



```java

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {

    int first = Integer.MAX_VALUE;
    int second = Integer.MAX_VALUE;
    int count = 0;

    public int findSecondMinimumValue(TreeNode root) {
        helpFind(root);
        return count == 0 ? -1 : second;
    }

    public void helpFind(TreeNode root){

        // if(root == null || root.left == null || root.right == null){
        if(root == null){
            return;
        }

        // 根节点 < first < second
        if(root.val < first){
            second = first;
            first = root.val;
        }else if(root.val <= second && root.val > first){   // 看题目要求，此处可以取等号
        // first < 根节点 < second
            // 必然存在最小和第二小的节点了
            count++;
            // 第二小节点
            second = root.val;
        }

        helpFind(root.left);
        helpFind(root.right);
    }
}

```


#### 层次遍历


637. 二叉树层平均值

给定一个非空二叉树, 返回一个由每层节点平均值组成的数组。


```java

// /**
//  * Definition for a binary tree node.
//  * public class TreeNode {
//  *     int val;
//  *     TreeNode left;
//  *     TreeNode right;
//  *     TreeNode(int x) { val = x; }
//  * }
//  */
class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> averageList = new ArrayList<>();   // 用来存节点均值
        if(root == null){
            return averageList;
        }

        Queue<TreeNode> queue = new LinkedList<>();   // 用来存节点（计算完一层加一层进来）
        // 加入根节点
        queue.add(root);

        while(!queue.isEmpty()){        // 队列非空
            int cnt = queue.size();
            double sum = 0; // 层中节点数值之和
            for(int i = 0; i < cnt; i++){
                TreeNode node = queue.poll();
                sum += node.val;   // 合到 sum

                // 往下找，加到 queue
                if(node.left != null){ // 注意：这里不能用 root，否则无法实现往下递归，要用不断替换的 TreeNode 查找
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }
            }

            averageList.add(sum/cnt);       // 均值存起来
        }
        return averageList;
    }
}

```

513. 找二叉树左下角的值


示例 1:
输入:

    2
   / \
  1   3

输出:
1
 

示例 2:
输入:

        1
       / \
      2   3
     /   / \
    4   5   6
       /
      7

输出:
7


```java

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int findBottomLeftValue(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode node = new TreeNode(-1);
        while(!queue.isEmpty()){
            node = queue.poll();
            
            if(node.right != null){
                queue.add(node.right);
            }
            if(node.left != null){
                queue.add(node.left);   // 后进后出
            }
        }
        return node.val;        // node是在 while循环到 empty 最后一个出来的，所以应该要是最后一个加入队列中
        // 层次遍历到最底一层，取后进后出的 node.left 结点
        
    }
}

```


#### 前后中序遍历   

0. 递归方法的前中后序遍历（无 leetcode 对应）

 - 前序遍历： 根 -- 左 -- 右
 - 中序遍历： 左 -- 根 -- 右
 - 后序遍历： 左 -- 右 -- 根


```java

// 前序遍历
void dfs(TreeNode root) {
    visit(root);
    dfs(root.left);
    dfs(root.right);
}

// 中序遍历
void dfs(TreeNode root) {
    dfs(root.left);
    visit(root);
    dfs(root.right);
}

// 后序遍历
void dfs(TreeNode root) {
    dfs(root.left);
    dfs(root.right);
    visit(root);
}

```

144. 二叉树的前序遍历（非递归方法）


```java

 // 非递归实现
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {

        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()){        // 根 --> 左 --> 右
            TreeNode node = stack.pop();
            // 非空校验
            if(node == null){
                continue;
            }

            // 根节点
            res.add(node.val);
            // 右节点（先进后出来）
            stack.push(node.right);        // 注意：这里有可能压栈压入空节点（null），前面要做集合加入节点值时要做控制
            stack.push(node.left);
        }
        return res;
    }
}

```

145. 二叉树的后序遍历(非递归)


```java


// 非递归实现
// 后序遍历 : 左 ---> 右 ---> 根
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()){
            TreeNode node = stack.pop();

            if(node == null){
                continue;
            }
            // 求解：左--右--根 ===》 reverse 根 -- 右 -- 左
            // 根 -- 右 -- 左
            list.add(node.val);
            // 先进后出
            stack.push(node.left);
            stack.push(node.right);
        }

        // 注意 reverse 一下
        Collections.reverse(list);
        return list;
    }
}


```


94. 二叉树的中序遍历


```java

// 非递归求解思路
// 中序遍历 : 左节点 ---> 根节点 ---> 右节点
class Solution {
    List<Integer> result = new ArrayList<Integer>();
    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;

        while(curr != null || !stack.isEmpty()){
            while(curr != null){
                // 先进后出，相当于先加入父节点，再加入 left 节点（出栈 left ---> root ---）
                stack.push(curr);
                curr = curr.left;      // 找到左节点最后加（后进先出）
            }
            
            TreeNode node = stack.pop();
            result.add(node.val);
            curr = node.right;  // 这个父节点有没有右节点
        }
    return result;
    }
}


```


#### 二叉查找树（BST）


BST： left.val <= root.val <= right.val


给定一个二叉搜索树，同时给定最小边界L 和最大边界 R。通过修剪二叉搜索树，使得所有节点的值在[L, R]中 (R>=L) 。你可能需要改变树的根节点，所以结果应当返回修剪好的二叉搜索树的新的根节点。


```java

class Solution {
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if(root == null){
            return null;
        }
        // (1) root 节点不在区间内
        // root 节点在区间左边
        if(root.val < L){
            return trimBST(root.right, L, R);
        }
        // root 节点在区间右边
        if(root.val > R){
            return trimBST(root.left, L, R);
        }

        // (2) root 节点在区间内
        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);

        return root;
    }
}

```


230. 二叉搜索树中第K小的元素


给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。


思路：中序遍历，遍历到k, 查找即可


```java


class Solution {
    List<Integer> list = new ArrayList<>();
    public int kthSmallest(TreeNode root, int k) {
        // 中序遍历
        midFind(list, root);
        for(int result : list){
            if(k > 1){
                k--;
            }else{
                return result;
            }
        }
        return -1;
    }

    public void midFind(List<Integer> list, TreeNode root){     // 递归查找就可以了
        if(root == null){
            return;
        }
        // 左节点 --> 根节点 --> 右节点
        midFind(list, root.left);
        list.add(root.val);
        midFind(list, root.right);
        return;
    }
}


```

0. 把二叉查找树每个节点的值都加上比它大的节点的值

Input: The root of a Binary Search Tree like this:

              5
            /   \
           2     13

Output: The root of a Greater Tree like this:

             18
            /   \
          20     13


```java

// 思路： 先遍历右子树。

class Solution{

    private int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        traver(root);
        return root;
    }

    private void traver(TreeNode node) {
        if (node == null) return;
        // 先遍历右子树
        traver(node.right);
        sum += node.val;
        node.val = sum;
        traver(node.left);
    }
}


```


235. 二叉搜索树的最近公共祖先


给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。

```java

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null){
            return null;
        }
        // root 节点在两节点区间的右边
        if(root.val > p.val && root.val > q.val){
            // 最近公共祖先在 root.left
            return lowestCommonAncestor(root.left, p, q);
        }

        // root 节点在两节点区间的左边
        if(root.val < p.val && root.val < q.val){
            // 最近公共祖先在 root.left
            return lowestCommonAncestor(root.right, p, q);
        }
        return root;
    }
}

```


236. 二叉树的最近公共祖先


```java

// 注意：这不是二叉搜索树（区别一下 235 题），只是普通的二叉树！
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root== p || root == q){
            // 直接返回root, 即为二叉搜索树的最近公共节点
            return root;
        }
        // 深度遍历
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if(left == null){
            // 左节点为空，返回右节点
            return right;
        }else if(right == null){
            // 左节点不为空，右节点为空，返回左节点
            return left;
        }else{
            // 左右节点都不为空，返回 root 节点
            return root;
        }
    }
}

```


108. 将有序数组转换为二叉搜索树


```java

class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        // 中序遍历的逆过程
        return toReverseBST(nums, 0, nums.length - 1);
    }

    public TreeNode toReverseBST(int[] nums, int startIndex, int endIndex){
        if(startIndex > endIndex){
            return null;
        }
        int midIndex = (startIndex + endIndex) / 2;
        TreeNode root = new TreeNode(nums[midIndex]);
        // 左分支
        root.left = toReverseBST(nums, startIndex, midIndex-1);
        // 右分支
        root.right = toReverseBST(nums, midIndex + 1, endIndex);

        return root;
    }
}

```


109. 有序链表转换二叉搜索树

给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。


```java


class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        // 链表为空
        if(head == null){
            return null;
        }
        // 链表就剩下一个节点
        if(head.next == null){
            return new TreeNode(head.val);
        }
        
        // 中间节点的前一个节点（要用到这个节点去断开链表，所以不能直接只取中间节点！！！）
        ListNode preMid = findPreMid(head);
        // 链表中间节点
        ListNode Mid = preMid.next;
        // 把链表断掉！！！
        preMid.next = null;
        
        TreeNode root = new TreeNode(Mid.val);
        // 递归就好
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(Mid.next);
        
        return root;
    }
    
    public ListNode findPreMid(ListNode head){
        ListNode slow = head;
        // (fast 的这两个定义都是对的，无非链表奇数偶数个节点的差异，结果一样的)
        // ListNode fast = head.next;
        ListNode fast = head;
        ListNode preMidNode = head;
        
        while(fast != null && fast.next != null){
            
            preMidNode = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        return preMidNode;
    }
}

```

653. 两数之和 IV - 输入 BST

给定一个二叉搜索树和一个目标结果，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。


case1

输入: 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 9

输出: True


case2

输入: 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 28

输出: False


```java

 // 思路：先中序遍历, 再双指针查找！
class Solution {

    List<Integer> list = new ArrayList<>();

    public boolean findTarget(TreeNode root, int k) {
        toBST(root);
        int i = 0;
        int j = list.size() - 1;    // 防止数组越界
        while(i < j){
            int sum = list.get(i) + list.get(j);
            if(k > sum){
                i++;
            }else if(k < sum){
                j--;
            }else if(k == sum){
                return true;
            }
        }
        return false;
    }

    // 中序遍历: 左--根--右
    public void toBST(TreeNode root){
        if(root == null){
            return;
        }
        toBST(root.left);
        list.add(root.val);
        toBST(root.right);
    }
}

```


530. 二叉搜索树的最小绝对差

给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。


```java

// 思路：找到最小的差值的绝对值，对于二叉搜索树，这两个值应该出现在相邻节点中;(考虑：边中序遍历边求解得到这个差值)
class Solution {
    // 最小绝对差值
    private int minDiff = Integer.MAX_VALUE;
    // 前一个节点值（根节点是没有前一个节点值的，他的前一个节点值初始化为 null）
    TreeNode preNode = null;

    public int getMinimumDifference(TreeNode root) {
        // 中序遍历, 同时求解这个差值
        midOrder(root);
        return minDiff;
    }

    public void midOrder(TreeNode root){
        if(root == null){
            return;
        }
        midOrder(root.left);
        if(preNode != null){
            minDiff = Math.min(minDiff, Math.abs(root.val - preNode.val));
        }
        // 更新前一节点
        preNode = root;
        midOrder(root.right);
    }
}

```

501. 二叉搜索树中的众数


给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。

- 假定 BST 有如下定义：
    - 结点左子树中所含结点的值小于等于当前结点的值
    - 结点右子树中所含结点的值大于等于当前结点的值
    - 左子树和右子树都是二叉搜索树
    
 
```java

 // 思考：题目要求返回出现次数最多的节点值的 集合; 二叉搜索树中序遍历拿到这些出现最多的“众数”集合
class Solution {

    // 当前节点值出现次数
    private int curCnt = 1;
    // “众数”节点值出现次数
    private int maxCnt = 1;
    // 二叉搜索树的 前一个节点
    TreeNode preNode = null;

    public int[] findMode(TreeNode root) {
        // 用来存储“众数”的list（不是存储中序遍历结果的list, 中序遍历结果我们不存！！！）
        List<Integer> maxNumList = new ArrayList<>();
        inOrder(root, maxNumList);

        // 结果数组
        int[] nums = new int[maxNumList.size()];
        int index = 0;
        for(int num : maxNumList){
            nums[index++] = num;
        }
        // 返回结果数组
        return nums;
    }

    public void inOrder(TreeNode root, List<Integer> list){
        if(root == null){
            return;
        }
        // 中序遍历：左--根--右
        inOrder(root.left, list);
        if(preNode != null){        // 前一节点不为空
            if(preNode.val == root.val){
                // 节点值相等，当前节点相等数
                curCnt++;
            }else{
                // 不相等，当前节点相等数，归 1
                curCnt = 1;
            }
        }

        // 是否更新最大出现节点数
        if( curCnt > maxCnt){
            // 更新节点数
            maxCnt = curCnt;
            // list 清空
            list.clear();
            // list 加入这个值为第一个值
            list.add(root.val);
        }else if(curCnt == maxCnt){ // 相同
            // 加上这个节点值
            list.add(root.val);
        }

        // 更新前一节点当前节点
        preNode = root;
        // 中序遍历右节点
        inOrder(root.right, list);

    }
}

```


208. 实现 Trie (前缀树)


实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。


示例:

Trie trie = new Trie();

trie.insert("apple");
trie.search("apple");   // 返回 true
trie.search("app");     // 返回 false
trie.startsWith("app"); // 返回 true
trie.insert("app");   
trie.search("app");     // 返回 true



```java

class Trie {

    private class Node {
        Node[] childs = new Node[26];
        boolean isLeaf;
    }

    private Node root = new Node();

    public Trie() {
    }

    public void insert(String word) {
        insert(word, root);
    }

    private void insert(String word, Node node) {
        if (node == null) return;
        if (word.length() == 0) {
            node.isLeaf = true;
            return;
        }
        int index = indexForChar(word.charAt(0));
        if (node.childs[index] == null) {
            node.childs[index] = new Node();
        }
        insert(word.substring(1), node.childs[index]);
    }

    public boolean search(String word) {
        return search(word, root);
    }

    private boolean search(String word, Node node) {
        if (node == null) return false;
        if (word.length() == 0) return node.isLeaf;
        int index = indexForChar(word.charAt(0));
        return search(word.substring(1), node.childs[index]);
    }

    public boolean startsWith(String prefix) {
        return startWith(prefix, root);
    }

    private boolean startWith(String prefix, Node node) {
        if (node == null) return false;
        if (prefix.length() == 0) return true;
        int index = indexForChar(prefix.charAt(0));
        return startWith(prefix.substring(1), node.childs[index]);
    }

    private int indexForChar(char c) {
        return c - 'a';
    }
}


```


677. 键值映射


实现一个 MapSum 类里的两个方法，insert 和 sum。
对于方法 insert，你将得到一对（字符串，整数）的键值对。字符串表示键，整数表示值。如果键已经存在，那么原来的键值对将被替代成新的键值对。
对于方法 sum，你将得到一个表示前缀的字符串，你需要返回所有以该前缀开头的键的值的总和。


```

示例 1:

输入: insert("apple", 3), 输出: Null
输入: sum("ap"), 输出: 3
输入: insert("app", 2), 输出: Null
输入: sum("ap"), 输出: 5


```


```java


class MapSum {

    private class Node {
        Node[] child = new Node[26];
        int value;
    }

    private Node root = new Node();

    public MapSum() {

    }

    public void insert(String key, int val) {
        insert(key, root, val);
    }

    private void insert(String key, Node node, int val) {
        if (node == null) return;
        if (key.length() == 0) {
            node.value = val;
            return;
        }
        int index = indexForChar(key.charAt(0));
        if (node.child[index] == null) {
            node.child[index] = new Node();
        }
        insert(key.substring(1), node.child[index], val);
    }

    public int sum(String prefix) {
        return sum(prefix, root);
    }

    private int sum(String prefix, Node node) {
        if (node == null) return 0;
        if (prefix.length() != 0) {
            int index = indexForChar(prefix.charAt(0));
            return sum(prefix.substring(1), node.child[index]);
        }
        int sum = node.value;
        for (Node child : node.child) {
            sum += sum(prefix, child);
        }
        return sum;
    }

    private int indexForChar(char c) {
        return c - 'a';
    }
}


```


### 栈和队列(6)

232. 用栈实现队列

使用栈实现队列的下列操作：

push(x) -- 将一个元素放入队列的尾部。
pop() -- 从队列首部移除元素。
peek() -- 返回队列首部的元素。
empty() -- 返回队列是否为空。
 

示例:

MyQueue queue = new MyQueue();

queue.push(1);
queue.push(2);  
queue.peek();  // 返回 1
queue.pop();   // 返回 1
queue.empty(); // 返回 false



```java

class MyQueue {

    // 先建立两个栈
    private Stack<Integer> in = new Stack<>();
    private Stack<Integer> out = new Stack<>();

    /** Initialize your data structure here. */
    public MyQueue() {

    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        // push 到入栈
        in.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */   // todo:返回并删掉
    public int pop() {
        // 写一个方法，入栈转到出栈
        inToOut();
        return out.pop();
    }
    
    /** Get the front element. */           // todo: 返回不删掉
    public int peek() {
        inToOut();
        return out.peek();

    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return in.isEmpty() && out.isEmpty();
    }


    public void inToOut(){
        if(out.isEmpty()){  // out 为空，in栈倒入out栈
            while(!in.isEmpty()){       // 全倒过去
                out.push(in.pop());
            }
        }

        // out 栈不为空，直接返回栈顶就行了,不用做操作
    }
}



```


225. 用队列实现栈

使用队列实现栈的下列操作：

push(x) -- 元素 x 入栈
pop() -- 移除栈顶元素
top() -- 获取栈顶元素
empty() -- 返回栈是否为空



```java

/// 要总结一下: 队列和栈的 api 存在的差异性！！！！
class MyStack {

    // 新建一个队列（底层是链表）
    private Queue<Integer> queue = new LinkedList<>();          // queue.add();  queue.poll()

    /** Initialize your data structure here. */
    public MyStack() {
    
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        // 直接压到队列出口处，先进先出
        int len = queue.size();
        // 压进去
        queue.add(x);
        // 推一圈
        while(len > 0){
            queue.add(queue.poll());
            len--;
        }
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return queue.remove();      // pop 直接 remove
    }
    
    /** Get the top element. */
    public int top() {
        return queue.peek();    // top 直接取 peek
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }
}

```


155. 最小栈


设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。

push(x) —— 将元素 x 推入栈中。
pop() —— 删除栈顶的元素。
top() —— 获取栈顶元素。
getMin() —— 检索栈中的最小元素。


```java

class MinStack {

    private int min = Integer.MAX_VALUE; 
    // 维护两个栈，一个数据栈，一个最小栈
    private Stack<Integer> dataStack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();


    /** initialize your data structure here. */
    public MinStack() {

    }
    
    public void push(int x) {
        dataStack.push(x);
        // 维护一个 minStack
        min = Math.min(min, x);
        minStack.push(min);
    }
    
    public void pop() {         // 删除栈顶的元素，不做返回！（void）
        dataStack.pop();
        // 说明：dataStack 每次压栈一个数，minStack 也会压入一个最小数；
        // 由于dataStack这次压入的数而形成此时的最小数，所以，删掉应该同时删掉！！！
        minStack.pop();

        // minStack 删掉一个数后，更新最小值 min
        min = minStack.isEmpty() ? Integer.MAX_VALUE : minStack.peek();     // 非空，这里 peek 第一个，此处不   再删 minStack
    }
    
    public int top() {
        return dataStack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}


```


20. 有效的括号


给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

- 有效字符串需满足：
    - 左括号必须用相同类型的右括号闭合。
    - 左括号必须以正确的顺序闭合。
    = 注意空字符串可被认为是有效字符串。


示例1

输入: "()[]{}"      
输出: true

示例2

输入: "([)]"
输出: false


```java


class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        // 遍历字符串
        for(char c : s.toCharArray()){
            if(c == '(' || c == '[' || c == '{'){
                // 括号的左半边： 压栈
                stack.push(c);
            }else{
                // 括号的右半边：匹配栈中字符是否一致
                if(stack.isEmpty()){
                    // 栈为空, 直接返回不匹配
                    return false;
                }
                char cStack = stack.pop();

                boolean b1 = c == ')' && cStack == '(' ; // 易错点： 匹配关系的连个字符是不相等的哈，例如 '(' 和 ')' 不等
                boolean b2 = c == ']' && cStack == '[' ;
                boolean b3 = c == '}' && cStack == '{' ;

                if(b1 || b2 || b3){     // 有一个为 true, 既匹配
                    // 继续往下找
                    continue;
                }else{
                    // 全部都是 false, 都不匹配
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}


```


739. 每日温度

请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。

例如，

给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73];
你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。


```java

// 题目解读： 数组中下一个当前位置数大的数要往后移动多少个位置，没有则返回 0;





```





========================  数据结构待补充  =================================


# 二、算法


## （一）动态规划


动态规划问题，解决具备优子结构的问题。(最优子结构 ==> 原问题最优解)

特点：1.做选择（操作）； 2.状态

### 斐波那契数列

特点： 0,1,1,2,3,5,8,13,21,34...

斐波那契数列严格意义上不是动态规划问题，但是他同样具备最有子结构的特点，故可以采用这种思路解决。

```
方程式：

F(0) = 0; 
F(1) = 1; 
F(2) = 1; 
F(n) = F(n-1) + F(n-2);

问题：

第 n 个斐波那契数列数值是多少？

```

1. 解法一：采用dp数组存下所有子问题的值，最后得到原问题的解。(时间复杂 O(N), 空间复杂 O(N))

```java

class Solution{
    int fib(int N){
        int[] dp = new int[N+1];    // dp table 记录、备忘录
        // 初始化边界
        dp[1] = dp[2] = 1;
        dp[0] = 0;
        
        // 遍历
        for(int i = 3; i <= N; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        
        return dp[N];   // dp[n]表示第n个斐波那契数列的数值。
    }
    
}


```

2. 解法二：dp table 存储了所有的状态，考虑到只用到了前后两个状态, 可以优化空间复杂度。(时间复杂 O(1), 空间复杂 O(N))

```java

class Solution{
    int fib(int N){
        // 边界
        if(N == 1 || N == 2){
            return 1;
        }
        int pre = 1;
        int cur = 1;
        // 循环
        for(int i = 3; i <= N; i++ ){
            int sum = pre + cur;
            pre = cur;
            cur = sum;
        }
        return cur;
    }
}


```


### 凑领钱问题


#### 解法：采用dp 数组存储状态，动态规划求解(leetCode 原题为例)

322. 零钱兑换(凑某个总额，求最少硬币数)--leetcode

给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
如果没有任何一种硬币组合能组成总金额，返回 -1。你可以认为每种硬币的数量是无限的。

```
示例 1：

输入：coins = [1, 2, 5], amount = 11
输出：3 
解释：11 = 5 + 5 + 1

```

```java

class Solution {
    public int coinChange(int[] coins, int amount) {
        // 边界
        if(coins.length == 0){
            return -1;
        }

        // dp[n]的值： 表示的凑成总金额为n所需的最少的硬币个数
        int[] dp = new int[amount+1];
        // 给dp赋初值，最多的硬币数就是全部使用面值1的硬币进行换,所以 dp[amount]的最大值时 amount;
        // 而 amount + 1 是不可能达到的换取数量，于是使用其进行填充;
        Arrays.fill(dp, amount+1);
        // 边界
        dp[0] = 0;
        // 遍历所有的状态
        for(int i = 1; i <= amount; i++){
            for(int j = 0; j < coins.length; j++){
                if(i - coins[j] >= 0){
                    // dp[i]有两种实现的方式：
                    // (1) 一种是包含当前的coins[j], 剩余钱就是 i-coins[j], 要兑换的硬币数是 dp[i-coins[j]] + 1;
                    // (2) 另一种就是不包含当前的coins[j]，要兑换的硬币数是 dp[i];
                    // 选择硬币数量最少的那种 min
                    dp[i] = Math.min(dp[i], dp[i-coins[j]] + 1);
                }
            }
        }

        // 当 dp[amount] == (amount+1) 时，说明还是之前填充进来的原始参数，这个amount值无法兑换，不可达！
        return dp[amount] == (amount+1) ? -1 : dp[amount];
    }
}

// 补充说明：
// 时间复杂度：O(N×amount)。其中 N 为 coins 数组的长度。
// 空间复杂度：O(amount)，dp 数组使用的空间。


```

518. 零钱兑换 II （凑某个总额，求凑的方式（组合方式）有多少种？）--leetcode

给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。 
（*完全背包问题*）

```
示例 1:

输入: amount = 5, coins = [1, 2, 5]
输出: 4
解释: 有四种方式可以凑成总金额:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1
示例 2:

输入: amount = 3, coins = [2]
输出: 0
解释: 只用面额2的硬币不能凑成总金额3。
示例 3:

输入: amount = 10, coins = [10] 
输出: 1

``` 

```java

class Solution {
  public int change(int amount, int[] coins) {
    int[] dp = new int[amount + 1];
    dp[0] = 1;

    for (int coin : coins) {
      for (int x = coin; x < amount + 1; x++) {
        dp[x] += dp[x - coin];
      }
    }
    return dp[amount];
  }
}


// 补充说明：
// 时间复杂度：O(N×amount)。其中 N 为 coins 数组的长度。
// 空间复杂度：O(amount)，dp 数组使用的空间。

```

### 编辑距离

72. 编辑字符串--leetcode

```
给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。

你可以对一个单词进行如下三种操作：
(1)插入一个字符
(2)删除一个字符
(3)替换一个字符

示例 1：

输入：word1 = "horse", word2 = "ros"
输出：3
解释：
horse -> rorse (将 'h' 替换为 'r')
rorse -> rose (删除 'r')
rose -> ros (删除 'e')

```


算法思路：
```

1. dp[i][j] 表示 以i,j 为index 结尾的两个字符串（s1，s2），最少操作 dp[i][j] 次，可使得 s1 -> s2

```
![avatar](pic/动态规划-编辑距离.png)

```
2. 针对 index 为 i,j 的字符 s[i]、s[j] 有三种情况：(s1 -> s2)
    （1） i 位置插入数据 b, i 不变还是 a, j 往前挪一位，操作次数加一；
            dp[i][j] = dp[i][j-1] + 1;
    （2） i 位置删掉数据 a, i 往前挪动（i位置字符用了），j位置不变（j位置字符未用到），操作次数加一；
            dp[i][j] = dp[i-1][j] + 1;
    （3） 替换a 为b，此时，i和j 都用到了（s[i] = s[j]）, 都往前挪一位字符，操作次数加一；
            dp[i][j] = dp[i-1][j-1] + 1;

3. 综上，最少操作次数，应该是取值 （1）、（2）、（3）的最小值 min；

```

代码如下

```java
class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        // 防止越界
        int[][] dp = new int[m + 1][n + 1];
        // 初始化边界(当两个字符串有一个为空串时)
        for(int i = 1; i <= m; i++){
            dp[i][0] = i;
        }
        for(int j = 1; j <=n; j++){
            dp[0][j] = j;
        }

        // 自底向上求解
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                // 字符相等时，直接下一位，操作数没有变化
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];    // 都挪一位
                }else{
                    // 取操作数最少的
                    dp[i][j] = Math.min(dp[i][j-1] + 1, Math.min(dp[i-1][j] + 1, dp[i-1][j-1] + 1));
                }
            }
        }

        return dp[m][n];
    }
}

```


### 高楼扔鸡蛋

887. 鸡蛋掉落--leetcode----------------（待补充）

```

你将获得 K 个鸡蛋，并可以使用一栋从 1 到 N  共有 N 层楼的建筑。
每个蛋的功能都是一样的，如果一个蛋碎了，你就不能再把它掉下去。
你知道存在楼层 F ，满足 0 <= F <= N 任何从高于 F 的楼层落下的鸡蛋都会碎，从 F 楼层或比它低的楼层落下的鸡蛋都不会破。
每次移动，你可以取一个鸡蛋（如果你有完整的鸡蛋）并把它从任一楼层 X 扔下（满足 1 <= X <= N）。
你的目标是确切地知道 F 的值是多少。无论 F 的初始值如何，你确定 F 的值的最小移动次数是多少？

1. 解读问题：求要想确切知道 F 的值，最坏情况下，最少需要几个鸡蛋？（最坏情况下：无论 F 的初始值如何）

2. 思路：
（1）鸡蛋个数有限 K 个，不能简单的二分查找（否则可能找不到确切的 F 值，鸡蛋就没了）；
（2）鸡蛋只有两种可能性：碎了/没碎（找状态）
    ① 鸡蛋在第 i 层碎了，K-1，则楼层查找区间为下半部分，层高 N 变为 i-1，区间 [1,2,...,N] -> [1,2,...,i-1]
        dp[K][N] = dp[K-1][i-1] + 1;    // 测试次数加一
    ② 鸡蛋在第 i 层没碎，K不变，层高 N-1，则楼层查找区间为上半部分，区间 [1,2,...,N] -> [i+1,i+2,...,N]
        dp[K][N] = dp[K][N-1] + 1;    // 测试次数加一
    ③ 取上述①、②的测试次数最大值（考虑最坏情况下，也能找出层高 F）
（3）dp[K][N]: 楼高为 N, 鸡蛋数为 K, 确切知道 F 值最少需要鸡蛋数量为 dp[K][N]

```








### 股票买卖问题

```
1. 股票买卖问题，动态规划要素整理：

（1）股票可选操作：① 买入 buy；②卖出 sell；③无操作 rest；

（2）状态：① 天数 n (0~n-1)；②交易次数 k (1~k)；③是否持有状态 （0：不持有； 1：持有）；

2. 注意要点：

（1）一笔交易：包含买入和卖出全过程（一次买卖全过程）；
（2）最终受益最大结果应该为 dp[n-1][k][0] : 最大受益应该是股票已经卖出的时候，此时持有状态为 0；
（3）股票价格为 prices[i], 买入后(尚未卖出)受益为 -prices[i],(这个时候应该是亏钱的!)

3. 几种题型：

（1）K = 1,只能进行一笔交易（1笔 = 买入+卖出）
（2）K = +∞,交易笔数不设限；
（3）K = +∞,cooldown, 交易笔数不设限，设有冷冻期；
（4）K = +∞,fee, 交易笔数不设限，每笔交易收取手续费（Notice:手续费无论是买入时收取，或卖出时收取，计算最终结果都一样的）；
（5）K 为有限正整数（ 0< k< +∞），可进行有限笔（K 笔）交易

```

#### **（1）K = 1,只能进行一笔交易（1笔 = 买入+卖出）**

121. 买卖股票的最佳时机

给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
注意：你不能在买入股票前卖出股票。

```

示例 1:

输入: [7,1,5,3,6,4]
输出: 5
解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。

示例 2:

输入: [7,6,4,3,1]
输出: 0
解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。


```

```java

// 方法一 ：dp table 记录所有状态

class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        // 2表示持有、不持有2个状态; 交易次数 k=1，这题不考虑;
        int[][] dp = new int[n][2];     // todo: dp[n][x]: 表示第n+1 天收益
        // 边界
        if(n == 0){                  // 这个判断不能少，否则 dp[0][0] = 0 会越界
            return 0;
        }
        dp[0][0] = 0;           // 第一天未持有收益(没买，收益为0)
        dp[0][1] = -prices[0];  // 第一天持有收益(买了股票，收益为 - prices[0])
        for(int i = 1; i < n; i++){
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1],  -prices[i]);   // 只进行一次交易 -prices[i]
        }
        return dp[n-1][0];
    }
}



// 方法 2 ：只要存两个状态，不需要状态表 dp[][]，优化空间复杂度
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        // 边界(第1天)
        if(n == 0){
            return 0;
        }
        int dp0 = 0;            // 不持有
        int dp1 = -prices[0];   // 持有
        for(int i = 0; i < n ; i++){
            dp0 = Math.max(dp0, dp1 + prices[i]);
            dp1 = Math.max(dp1, -prices[i]);
        }
        return dp0;
    }
}


```


#### *（2）K = +∞,交易笔数不设限；*

122. 买卖股票的最佳时机 II

给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

```java

// 方法 一： dp table 存储状态
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if(n == 0){
            return 0;
        }
        // k 无限，不需要这个状态
        int[][] dp = new int[n][2];
        // 边界，第一天
        dp[0][0] = 0;           // 不持有
        dp[0][1] = -prices[0];  // 持有

        // 遍历状态
        for(int i = 1; i < n; i++){
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i]);
        }

        return dp[n-1][0];
    }
}



// 方法二： 优化空间复杂度；只存三个状态： dp1 持有，dp0 不持有, beforePrfit 之前交易利润总和

class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if(n == 0){
            return 0;
        }
        // 边界，第一天
        int beforeProfit = 0;   // 之前的盈利
        int dp0 = 0;           // 不持有
        int dp1 = -prices[0];  // 持有

        // 遍历状态
        for(int i = 1; i < n; i++){
            dp0 = Math.max(dp0, dp1 + prices[i]);
            dp1 = Math.max(dp1, beforeProfit - prices[i]);
            beforeProfit =dp0;
        }

        return dp0;
    }
}

```


#### *（3）K = +∞,cooldown, 交易笔数不设限，设有冷冻期*

309. 最佳买卖股票时机含冷冻期

给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。

```
示例:

输入: [1,2,3,0,2]
输出: 3 
解释: 对应的交易状态为: [买入, 卖出, 冷冻期（1天）, 买入, 卖出]

```

```java

// 不用 dp table；
// 只需要记录4个状态，① 今天持有 dp1, ② 今天不持有 dp0, 
// ③ 昨天及之前利润总和 dpYesterday, ④ 前天及之前利润总和 dpBeforeYesterday；
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if(n == 0){
            return 0;
        }

        // 边界初始化: 第一天
        int dp0 = 0;
        int dp1 = - prices[0];
        // 前天之前利润总和，因为今天可以交易了，所以是前天，而不是昨天
        int dpBeforeYesterday = 0;
        
        // 遍历状态
        for(int i = 1; i < n; i++){
            int dpYesterday = dp0;  // 昨天之前的累计利润总和
            dp0 = Math.max(dp0, dp1 + prices[i]);
            // dpBeforeYesterday - prices[i] : 今天不持有，说明前天/更前卖了，
            // 因为今天有选择权(不是冷冻期)，不会是昨天卖的
            dp1 = Math.max(dp1, dpBeforeYesterday - prices[i]); 
            // 更新前天
            dpBeforeYesterday = dpYesterday;
        }
        return dp0;
    }
}

```


#### *（4）K = +∞,fee, 交易笔数不设限，每笔交易收取手续费*

714. 买卖股票的最佳时机含手续费

给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
返回获得利润的最大值。
注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。

```
示例 1:

输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
输出: 8
解释: 能够达到的最大利润:  
在此处买入 prices[0] = 1
在此处卖出 prices[3] = 8
在此处买入 prices[4] = 4
在此处卖出 prices[5] = 9
总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.

```


```java

// 手续费 卖出时刻收取
class Solution {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        if(n == 0){
            return 0;
        }
        // 边界
        int dp0 = 0;
        int dp1 = -prices[0];

        // 遍历状态
        for(int i = 0; i < n; i++){
            // 非持有状态(卖出)
            dp0 = Math.max(dp0, dp1 + prices[i] - fee);
            // 持有状态
            dp1 = Math.max(dp1, dp0 - prices[i]);
        }
        return dp0;
    }
}

```

#### *（5）K 为有限正整数（ 0< k< +∞），可进行有限笔（K 笔）交易*

188. 买卖股票的最佳时机 IV
给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

```
示例 1：
输入：k = 2, prices = [2,4,1]
输出：2
解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。

示例 2：
输入：k = 2, prices = [3,2,6,5,0,3]
输出：7
解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
     随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。

```

```java

class Solution {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if(n == 0 || k == 0 || prices == null){
            return 0;
        }

        if(k >= n/2){   // 交易笔数k 超过股票买卖天数的一半，则只要买的有赚就该买入，第二天卖出去
            // 相当于 k 正无穷（这里采用动态规划导致 case 过不了）
            // return KnotRelationMaxProfit(prices);
            // 改用贪心算法
            return greedy(prices);
        }

        // 1~k 笔交易
        int[] buy  = new int[k];
        int[] sell = new int[k];
        // 初始值
        for(int i = 0; i < k; i++){
            buy[i]  = Integer.MIN_VALUE;    // 最小值
            sell[i] = 0;
        }
        // 遍历状态
        for(int i = 0;i < prices.length;i++){
            // 边界值（第一笔交易）
            buy[0]  = Math.max(buy[0], 0 - prices[i]);          // 买入后收益
            sell[0] = Math.max(sell[0], buy[0] + prices[i]);    // 卖出后收益
            // 交易笔数
            for(int j = 1;j < k; j++){
                buy[j]  = Math.max(buy[j], sell[j-1] - prices[i]);  // 买入后。交易笔数 - 1
                sell[j] = Math.max(sell[j], buy[j] + prices[i]);    // 卖出不计算交易笔数
            }
        }
        return sell[k-1];
    }
    
    // 贪心算法
    public int greedy(int[] prices){
        int result = 0;
        for(int i = 0; i < prices.length - 1; i++){ // prices.length - 1 防止 prices[i+1] 越界
            // 交易笔数k 超过股票买卖天数的一半，则只要买的有赚就该买入，第二天卖出去
            if(prices[i] < prices[i+1])
                result += prices[i+1] - prices[i];
        }
        return result;
    }
}

```

#### *(6) 其他*

123. 买卖股票的最佳时机 III

给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

```
示例 1:
输入: [3,3,5,0,0,3,1,4]
输出: 6
解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
     随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。

示例 2:
输入: [1,2,3,4,5]
输出: 4
解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。   
     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。   
     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
```

```java

class Solution {
    public int maxProfit(int[] prices) {
        if(prices==null || prices.length==0) {
            return 0;
        }
        int n = prices.length;
        //定义三维数组，第i天、交易了多少次、当前的买卖状态
        int[][][] dp = new int[n][3][2];
        //初始化第一天，这里的dp[0][2][1]可以不用管，后面也不会用到   
        dp[0][0][0] = 0;
        dp[0][0][1] = -prices[0];
        dp[0][1][0] = 0;
        dp[0][1][1] = -prices[0];
        dp[0][2][0] = 0;
        dp[0][2][1] = -prices[0];
        for(int i=1;i<n;++i) {
            //dp[i][0][0]相当于初始状态，它只能从初始状态转换来
            dp[i][0][0] = dp[i-1][0][0];
            //处理第一次买入、第一次卖出
            dp[i][0][1] = Math.max(dp[i-1][0][1],dp[i-1][0][0]-prices[i]);
            dp[i][1][0] = Math.max(dp[i-1][1][0],dp[i-1][0][1]+prices[i]);
            //处理第二次买入、第二次卖出
            dp[i][1][1] = Math.max(dp[i-1][1][1],dp[i-1][1][0]-prices[i]);
            dp[i][2][0] = Math.max(dp[i-1][2][0],dp[i-1][1][1]+prices[i]);
        }
        //返回最大值；至少做两次交易, 可以只做一次, 所以要进行 max 比较.
        int a = Math.max(dp[n-1][0][0],dp[n-1][0][1]);
        int b = Math.max(dp[n-1][1][0],dp[n-1][1][1]);
        return Math.max(Math.max(a,b),dp[n-1][2][0]);
    }
}

```

### 打家劫舍问题

打家劫舍分为三种情况： 1. 抢劫金额为一个数组；2. 抢劫金额为环形数组；3. 抢劫金额为二叉树；

```
分析(1) ：抢劫金额为一个数组的情况：

dp[x] 表示 从最后一家房子开始抢钱一直抢到第i 家，最多能抢到的钱总额为 dp[x]

```

213. 打家劫舍 II
你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。
同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，能够偷窃到的最高金额。

 ```

示例 1：

输入：nums = [2,3,2]
输出：3
解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。

```

```java

class Solution {
    public int rob(int[] nums) {
        int n = nums.length;

        // dp 数组：dp[x] 表示 从最后一家房子开始抢钱一直抢到第i 家，最多能抢到的钱总额为 dp[x]
        int[] dp = new int[n+2];        // 因为要算到取到 i+2
        // 边界
        if(n == 0 || nums == null){
            return 0;
        }
        // 遍历状态(自底向上，倒叙遍历)
        for(int i = n-1; i >= 0; i--){  // n-1 ~ 0 (数组一共n 个数)
            dp[i] = Math.max(dp[i+1],   // nums[i] 不偷，到下一家偷
                        dp[i+2] + nums[i]);     // nums[i] 偷走，到下下家继续偷
        }
        // 返回第一家，倒叙从最后一家开始偷的呀
        return dp[0];
    }
}


// 只要存 当前状态，下家状态，下下家状态 ----> 所以可以优化空间复杂度

class Solution {
    public int rob(int[] nums) {
        int n = nums.length;

        // 当前位置值 + 之前偷到的总和
        int dp0 = 0;
        // dp1: 到下家后偷到的总额
        int dp1 = 0;
        // dp2 到下下家偷到的总额
        int dp2 = 0;

        // 遍历状态
        for(int i = n-1; i >= 0; i--){
            dp0 = Math.max(dp1,         // 当前这家不偷，到下家偷
                    nums[i] + dp2);     // 当前这家偷完，到下下家继续偷
            // 位置变化： 下下家变成下家，下家变成当前位置
            dp2 = dp1;
            dp1 = dp0;
        }
        return dp0;
    }
}

```


**分析(2) ：抢劫金额为一个环形数组的情况** ：

```
思路：将环形数组  转换为普通数组求解（采用上一题方法即可解答）

有三种情况（间隔抢劫）：
1. 第一家不抢；
2. 最后一家不抢；
3. 第一家和最后一家都不抢；（这是情况 1、2 的特殊情况，已经包含，除非只有唯一一家，以为边界的情况写出）
```

**所以, 只需要选择 第一家不抢、最后一家不抢（情况一、情况二）的最大值即可!**

```java

class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        //  边界条件
        if(n == 0 || nums ==null){
            return 0;
        }else if(n == 1){   // 只有一家，下面两种情况会把这个值漏掉，这一家必须抢下来
            return nums[0];
        }
        // 取两种情况的最大值即可：第一家不抢、最后一家不抢
        return Math.max(robNums(nums, 0, n-2), robNums(nums, 1, n-1));
    }

    public int robNums(int[] nums, int start, int end) {
        // 当前位置值 + 之前偷到的总和
        int dp0 = 0;
        // dp1: 到下家后偷到的总额
        int dp1 = 0;
        // dp2 到下下家偷到的总额
        int dp2 = 0;
        // 遍历状态
        for(int i = end; i >= start; i--){
            dp0 = Math.max(dp1,         // 当前这家不偷，到下家偷
                    nums[i] + dp2);     // 当前这家偷完，到下下家继续偷
            // 位置变化： 下下家变成下家，下家变成当前位置
            dp2 = dp1;
            dp1 = dp0;
        }
        return dp0;
    }
}

```

337. 打家劫舍 III

在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 
除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 
如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。

``` java


// 动态规划解法






// 二叉树解法：分情况递归，累加节点值（1. 要根节点； 2. 不要根节点  两种情况！）
class Solution {
    public int rob(TreeNode root) {
        if(root == null){
            return 0;
        }

        // 第一种情况：偷根节点
        int withRoot = root.val;
        // 左节点不为空
        if(root.left != null){
            withRoot += rob(root.left.left) + rob(root.left.right);
        }
        // 右节点也不为空
        if(root.right != null){
            withRoot += rob(root.right.left) + rob(root.right.right);
        }

        // 第二种情况：不偷根节点
        int notRoot = rob(root.left) + rob(root.right);

        // 取最大的
        int max = Math.max(withRoot, notRoot);
        return max;
    }
}


### 博弈问题



### 贪心算法（特殊的动态规划）



### KMP 算法（字符串匹配算法）




### 敲键盘问题


### 最长子序列


### 最长递增子序列


### 最长回文串子序列


### 最长公共子序列


