package no0016;

/**
 *  16. 最接近的三数之和
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，
 * 使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 *
 * 示例：
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 *
 * 提示：
 * 3 <= nums.length <= 10^3
 * -10^3 <= nums[i] <= 10^3
 * -10^4 <= target <= 10^4
 *
 */


import java.util.Arrays;

/**
 * todo:
 *      基于排序 + 双指针实现
 *      时间复杂度 O(N^2)
 */
public class Solution {
    public int threeSumClosest(int[] nums, int target) {
        // 先排序 再双指针
        Arrays.sort(nums);
        // 三个数之和
        int result = nums[0] + nums[1] + nums[2];

        // 遍历
        for(int i = 0; i < nums.length; i++){
            int start = i + 1;
            int end = nums.length - 1;
            // 双指针，第二重遍历
            while(start < end){
                int sum = nums[i] + nums[start] + nums[end];
                if(Math.abs(target - result) > Math.abs(target - sum)){
                    // 更新 result，取距离 target 最接近的
                    result = sum;
                }
                // 更新指针位置
                if(target > sum){
                    start++;
                }else if(target < sum){
                    end--;
                }else {
                    return sum;
                }
            }
        }
        return result;
    }

    public static void main(String[] args){
        Solution solution = new Solution();
        System.out.println(solution.threeSumClosest(new int[]{-1,2,1,-4}, 1));
    }
}
