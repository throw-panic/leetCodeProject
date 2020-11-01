package no0005;


/**
 *5. 最长回文子串
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 *
 * 示例 2:
 * 输入: "cbbd"
 * 输出: "bb"
 *
 */

// todo: 动态规划
public class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();

        // dp[i][j]: 表示起点为 i, 终点为 j 的字符串,
        // 是否为回文字符串的结果为 dp[i][j] ---> true/false
        boolean[][] dp = new boolean[n][n];

        // 回文串
        String result = "";

        // 遍历状态
        for (int l = 0; l < n; ++l) {       // l 表示字符串 起点和终点之间的距离差
            for (int i = 0; i + l < n; ++i) {   // 字符换起点
                int j = i + l;                  // 字符串终点
                if (l == 0) {
                    // (1) 长度为 1 的字符串
                    dp[i][j] = true;
                } else if (l == 1) {
                    // (2) 长度为 2 的字符串
                    dp[i][j] = (s.charAt(i) == s.charAt(j));
                } else {
                    // (3) l为其他长度 l > 2
                    dp[i][j] = (s.charAt(i) == s.charAt(j) &&
                            dp[i + 1][j - 1]);  // 起点 往后走一位；终点往前走一位；
                }

                if (dp[i][j]){      // && l + 1 > result.length()) {
                    // 回文串连起来
                    result = s.substring(i, j + 1);
                    /**
                     *     api 说明： substring(int beginIndex, int endIndex)
                     *     beginIndex -- 起始索引（包括）, 索引从 0 开始。
                     *     endIndex -- 结束索引（不包括）。
                     *
                     **/
                }
            }
        }
        return result;
    }

    public static void main(String[] args){
        Solution solution = new Solution();
        String test = "babad";
        System.out.println("字符串" + test + "的回文字符串为: ");
        System.out.println(solution.longestPalindrome(test));
    }
}

/*
    时间复杂度： O(N^2)
    空间复杂度： O(N)
 */