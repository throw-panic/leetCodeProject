package no0015;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 三数之和
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
 * 使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 示例：
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * 满足要求的三元组集合为：
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 */

/**
 * todo: 思路【排序+双指针】
 *  1. 特判，对于数组长度 n，如果数组为 null 或者数组长度小于 3，返回 []。
 *  2. 对数组进行排序。
 *  3. 遍历排序后数组：
 *      （1）若 nums[i]>0：因为已经排序好，所以后面不可能有三个数加和等于 0，
 *          直接返回结果；
 *      （2）对于重复元素：跳过，避免出现重复解；
 *      （3）令左指针 L=i+1L=i+1，右指针 R=n-1R=n−1，当 L<RL<R 时，执行循环：
 *            - 当 nums[i]+nums[L]+nums[R]==0，执行循环，判断左界和右界是否和下一位置重复，
 *              去除重复解。并同时将 L,R 移到下一位置，寻找新的解
 *            - 若和大于 0，说明 nums[R] 太大，R 左移
 *            - 若和小于 0，说明 nums[L] 太小，L 右移
 *
 */
public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        //排序
        Arrays.sort(nums);
        //双指针
        int len = nums.length;
        // 遍历数组
        for (int i = 0; i < len; ++i) {
            if (nums[i] > 0)    // 最小的都大于 0  ---> 直接返回
                return lists;
            if (i > 0 && nums[i] == nums[i - 1])    // 相同的数跳过，避免重复计算
                continue;   // i > 0 防止越界

            // 匹配点
            int curr = nums[i];
            int L = i + 1, R = len - 1;

            // 匹配 curr 这个点
            while (L < R) {
                int tmp = curr + nums[L] + nums[R];
                if (tmp == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(curr);
                    list.add(nums[L]);
                    list.add(nums[R]);
                    lists.add(list);
                    while (L < R && nums[L + 1] == nums[L])
                        // 去重复
                        ++L;
                    while (L < R && nums[R - 1] == nums[R])
                        // 去重复
                        --R;
                    ++L;
                    --R;
                } else if (tmp < 0) {
                    ++L;
                } else {    // temp > 0
                    --R;
                }
            }
        }
        return lists;
    }

    public static void main(String[] args){
        no0015.Solution solution = new no0015.Solution();
        System.out.println(solution.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }
}
