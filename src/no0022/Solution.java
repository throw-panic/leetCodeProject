package no0022;


import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/generate-parentheses/
 *  题目：
 *      22. 括号生成
 *          数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 * 示例 1：
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：["()"]
 *
 * 注意：1 <= n <= 8
 */

// todo 思路： 回溯算法解决
public class Solution {
    private List<String> results = new ArrayList<>();

    public List<String> generateParenthesis(int n){
        this.backtracking(0, 0, n,"");
        return results;
    }

    private void backtracking(int leftCount, int rightCount, int n, String current){
        if(leftCount == n && rightCount == n){
            results.add(current);
            return;
        }
        /*
        通过跟踪到目前为止放置的左括号和右括号的数目来做，如果左括号数量不大于 n，
        我们可以放一个左括号。如果右括号数量小于左括号的数量，我们可以放一个右括号。
       */
        // 左括号 "("
        if(leftCount < n){
            this.backtracking(leftCount+1, rightCount, n, current + "(");
        }
        // 右括号 ")"
        if(rightCount < leftCount){
            this.backtracking(leftCount, rightCount+1, n, current + ")");
        }
    }

    public static void main(String[] args){
        no0022.Solution solution = new Solution();
        System.out.println(solution.generateParenthesis(3));
    }
}
