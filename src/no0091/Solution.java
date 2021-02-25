package no0091;

/**
 * 题目：
 *      一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
 *          'A' -> 1
 *          'B' -> 2
 *          ...
 *          'Z' -> 26
 * 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。
 * 例如，"111" 可以将 "1" 中的每个 "1" 映射为 "A" ，从而得到 "AAA" ，
 * 或者可以将 "11" 和 "1"（分别为 "K" 和 "A" ）映射为 "KA" 。
 * 注意，"06" 不能映射为 "F" ，因为 "6" 和 "06" 不同。
 * 给你一个只含数字的 非空 字符串 num ，请计算并返回 解码 方法的 总数 。
 *
 *
 * 示例 1：
 * 输入：s = "12"
 * 输出：2
 * 解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
 *
 * 示例 2：
 * 输入：s = "226"
 * 输出：3
 * 解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 *
 * 示例 3：
 * 输入：s = "0"
 * 输出：0
 * 解释：没有字符映射到以 0 开头的数字。含有 0 的有效映射是 'J' -> "10" 和 'T'-> "20" 。
 * 由于没有字符，因此没有有效的方法对此进行解码，因为所有数字都需要映射。
 *
 * 示例 4：
 * 输入：s = "1"
 * 输出：1
 *
 */


/**
 *  todo： 递归解法
 */
public class Solution {
    public int numDecodings(String s) {
        if(s.charAt(0) == '0')
            return 0;
        char[] chars = s.toCharArray();
        return decode(chars, chars.length - 1);
    }

    // 符串转换成字符数组，利用递归函数 decode，从最后一个字符向前递归
    public int decode(char[] chars, int index){
        // 处理第一个字符  只能有一种解法
        if(index <= 0)
            return 1;
        int count = 0;
        char curr = chars[index];
        char pre = chars[index - 1];

        // 当前字符比 “0” 大，则直接利用它之前的字符串所求得的结果
        if(curr > '0'){
            count = decode(chars, index - 1);
        }

        // 由前一个字符和当前字符所构成的数字，值必须要在 1 到 26之间，否则无法进行解码
        if(pre == '1' || pre == '2' && curr <= '6'){
            count += decode(chars, index - 2);
        }
        return count;
    }

    public  static void main(String[] args){
        Solution solution = new Solution();
        System.out.println(solution.numDecodings("111111111111111111111111111111111111111111111"));
    }
}