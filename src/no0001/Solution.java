package no0001;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 *  1. 两数之和
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 * 示例:
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 */

public class Solution {

    public int[] twoSum(int[] nums, int target) {

        // 固定容量 hashmap，默认加载因子 0.75，此处设定加载因子为 1.0f，防止 hashmap 扩容消耗更多的内存
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(nums.length, 1.0f);

        // 遍历数组
        for (int i = 0; i < nums.length; i++) {
            // 判断是否有匹配的
            Integer j = map.get(target - nums[i]);
            if (j != null) {
                return new int[]{i, j};
            }
            // 添加到 map 中
            // key -> value （nums[i] -> index）
            map.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(
                Arrays.toString(    // 数组转字符串
                        solution.twoSum(
                                new int[]{2, 7, 11, 15}, 9)));
    }
}
