package no0018;


/**
 * 18. 四数之和
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素
 * a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 * 注意：答案中不可以包含重复的四元组。
 *
 * 示例：
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 * 满足要求的四元组集合为：
 *
 * [
 *   [-1,  0, 0, 1],
 *   [-2, -1, 1, 2],
 *   [-2,  0, 0, 2]
 * ]
 *
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * todo: 思路： 双指针 求解！
 */
public class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums.length < 4) {
            return Collections.emptyList();
        }

        // 排序
        Arrays.sort(nums);

        // 复合要求的四个数字 组合
        List<List<Integer>> results = new ArrayList<>();

        for (int i = 0; i < nums.length - 3; i++) { // 启始支点 1
            if (i > 0 && nums[i] == nums[i - 1]) {
                // 相同点、跳过；避免重复
                continue;
            }
            for (int j = i + 1; j < nums.length - 2; j++) { // 启始支点 2
                if (j - 1 != i && nums[j] == nums[j - 1]) {
                    continue;
                }
                // 计算当前目标
                int currentTarget = target - nums[i] - nums[j];
                // 双指针遍历（遍历区间 j+1 ~ nums.length-1）
                int left = j + 1, right = nums.length - 1;
                while (left < right) {
                    int sum = nums[left] + nums[right];
                    // 符合条件
                    if (sum == currentTarget) {
                        results.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        // 右移（去重复）
                        do {
                            left++;
                        } while (left < right && nums[left] == nums[left - 1]); // 相同值的跳过
                        // 左移（去重复）
                        do {
                            right--;
                        } while (left < right && nums[right] == nums[right + 1]);
                        continue;
                    }
                    // 过小
                    if (sum < currentTarget) {
                        // 右移
                        do {
                            left++;
                        } while (left < right && nums[left] == nums[left - 1]);
                        continue;
                    }
                    // 过大
                    // 左移
                    do {
                        right--;
                    } while (left < right && nums[right] == nums[right + 1]);
                }
            }
        }

        return results;
    }

    public static void main(String[] args){
        no0018.Solution solution =new Solution();
        System.out.println(solution.fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0));
    }
}
