package no0017;

/**
 * todo:
 *   17. 电话号码的字母组合
 *      给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 *      给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *      @link {https://assets.leetcode-cn.com/aliyun-lc-upload/original_images/17_telephone_keypad.png}
 *
 * 示例:
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 *
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 基于回溯算法实现。
 *
 * 时间复杂度 O(N^2)
 */
public class Solution {
    // 数字和 字母的映射关系
    private static List<List<String>> MAPPINGS = new ArrayList<>(); // 数组
    static {
        MAPPINGS.add(null); // 0
        MAPPINGS.add(null); // 1
        MAPPINGS.add(Arrays.asList("a", "b", "c")); // 2
        MAPPINGS.add(Arrays.asList("d", "e", "f")); // 3
        MAPPINGS.add(Arrays.asList("g", "h", "i")); // 4
        MAPPINGS.add(Arrays.asList("j", "k", "l")); // 5
        MAPPINGS.add(Arrays.asList("m", "n", "o")); // 6
        MAPPINGS.add(Arrays.asList("p", "q", "r", "s"));    // 7
        MAPPINGS.add(Arrays.asList("t", "u", "v"));         // 8
        MAPPINGS.add(Arrays.asList("w", "x", "y", "z"));    // 9
    }

    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) {
            // 直接返回空 list
            return Collections.emptyList();
        }
        /**
         *      this.方法名称: 用来访问本类的成员方法
         *      this.属性名称: 指的是访问类中的成员变量，
         *                    用来区分成员变量和局部变量（重名问题）
         */
        return this.gen(digits, 0);
    }

    private List<String> gen(String digits, int index) {
        // 获得当前的（curr）
        List<String> mappings = MAPPINGS.get(digits.charAt(index) - '0');
        if (index == digits.length() - 1) {
            // 递归到最后一位，遍历数字字符串结束，直接返回某个数字字符对应的 mappings
            return mappings;
        }

        // 获得后续的（递归 index++ ）
        List<String> others = this.gen(digits, index + 1);

        // 输出字母组合 的集合
        List<String> result = new ArrayList<>();
        // 组合
        for (String mapping : mappings) {
            for (String other : others) {
                // 拼接
                result.add(mapping + other);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.letterCombinations("23"));
    }

}
