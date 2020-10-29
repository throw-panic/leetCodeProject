package no0003;


/**
 * 3. 无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * <p>
 * 示例 2:
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1.
 * <p>
 * 示例 3:
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 */

import java.util.HashMap;
import java.util.Map;

/**
 *  思路：     todo: 经典的滑动窗口问题（再多看看，多理解）
 *
 *  这道题主要用到思路是：滑动窗口
 * 什么是滑动窗口？
 * 其实就是一个队列,比如例题中的 abcabcbb，进入这个队列（窗口）为 abc 满足题目要求，当再进入 a，
 * 队列变成了 abca，这时候不满足要求。所以，我们要移动这个队列！
 *
 * 如何移动？
 * 我们只要把队列的左边的元素移出就行了，直到满足题目要求！一直维持这样的队列，找出队列出现最长的长度时候，求出解！
 *
 * 时间复杂度：O(n)
 *
 */

public class Solution {
    public int lengthOfLongestSubstring(String s) {
        // 边界
        if (s == null || s.length() == 0) {
            return 0;
        }

        // 基本参数
        int beginIndex = 0; // 滑动窗口左边起点值（i: 为滑动窗口右边终点值）
        // 结果（无重复字符串长度）
        int maxResult = 1;
        Map<Character, Integer> map = new HashMap<>();

        // 加入初始第一个字符
        map.put(s.charAt(0), 0);
        // 遍历
        for (int i = 1; i < s.length(); i++) {
            Integer index = map.get(s.charAt(i));
            // map 中已经存在这个字符串
            if(index != null && index >= beginIndex){
//            if(map.containsKey(s.charAt(i))){ // 这么写是错误的; index >= beginIndex要有
                // 已经出现重复字符，滑动窗口 左节点向右移动一位
                beginIndex = index + 1;
            }else {
                // 没有重复，更新滑动窗口的大小(无重复字符串长度)
                maxResult = Math.max(maxResult, i - beginIndex +1);
            }

            // 加入字符到 map
            map.put(s.charAt(i), i);
        }
        return maxResult;
    }

    // testCase
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(solution.lengthOfLongestSubstring("bbbbbb"));
    }
}
