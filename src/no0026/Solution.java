package no0026;


/**
 * 26. 删除排序数组中的重复项
 *      给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 *      不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 * 示例1:
 * 给定数组 nums = [1,1,2],
 * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
 * 你不需要考虑数组中超出新长度后面的元素。
 *
 * 示例2:
 * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
 * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
 * 你不需要考虑数组中超出新长度后面的元素。
 *
 */

/**
 *  todo
 *      双指针方法解决
 */
public class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        int index = 0;
        for(int movePoint = 1; movePoint < nums.length; movePoint++){
            if(nums[index] != nums[movePoint]){
                // 不相等 交换下一个位置放后一个不等元素
                nums[++index] = nums[movePoint];
            }
            // 相等的话 movepoint ++ 往下走（循环中已经写了）
        }
        // 返回不等元素个数
        return index;
    }

    public static void main(String[] args){
        Solution solution = new Solution();
        System.out.println(solution.removeDuplicates(new int[]{1,1,2,2,3,3,4,4,5,5}));
    }
}
