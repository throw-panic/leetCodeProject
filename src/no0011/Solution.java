package no0011;


/**
 *  11. 盛最多水的容器
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的
 * 两个端点分别为 (i, ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 说明：你不能倾斜容器。
 *
 * 原题地址：https://leetcode-cn.com/problems/container-with-most-water/
 *
 *
 * 示例 2：
 * 输入：height = [1,1]
 * 输出：
 *
 * 示例 3：
 * 输入：height = [4,3,2,1,4]
 * 输出：16
 *
 * 示例 4
 * 输入：height = [1,2,1]
 * 输出：2
 *
 */

// todo: 参考解法： https://leetcode-cn.com/problems/container-with-most-water/solution/container-with-most-water-shuang-zhi-zhen-fa-yi-do/

// 双指针解决
public class Solution {
    public int maxArea(int[] height) {
        int start = 0;
        int end = height.length - 1;
        int res = 0;

        // 双指针递归
        while (start < end) {   // 别死循环呀  是不是傻？？？
            // 更新 res
            res = height[start] < height[end]        // 取短边
//                    ? Math.max(res, height[start++] * (end - start))  // 错误写法，start end 要先计算差值，再加加，减减
                    ? Math.max(res, (end - start) * height[start++])    // 短边放弃掉 ++
//                    : Math.max(res, height[end--] * (end - start));
                    : Math.max(res, (end - start) * height[end--]);     // 短边放弃掉 --
        }
        return res;
    }

    public static void main(String[] args){
        Solution solution = new Solution();
        System.out.println(solution.maxArea(new int[]{1,2,1}));
    }
}
