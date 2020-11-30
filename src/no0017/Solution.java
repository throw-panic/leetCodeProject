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
import java.util.List;

/**
 * 基于双指针实现。
 *
 * 时间复杂度 O(N^2)
 */
public class Solution {
    // 数字和 字母的映射关系
    private static List<List<String>> MAPPINGS = new ArrayList<>();
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

}
