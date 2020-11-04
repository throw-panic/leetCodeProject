package no0006;

/**
 * 6. Z 字形变换
 * <p>
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 * <p>
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * <p>
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 * 请你实现这个将字符串进行指定行数变换的函数：
 * string convert(string s, int numRows);
 * <p>
 * 示例 1:
 * 输入: s = "LEETCODEISHIRING", numRows = 3
 * 输出: "LCIRETOESIIGEDHN"
 * <p>
 * 示例 2:
 * 输入: s = "LEETCODEISHIRING", numRows = 4
 * 输出: "LDREOEIIECIHNTSG"
 * <p>
 * 解释:
 * L     D     R
 * E   O E   I I
 * E C   I H   N
 * T     S     G
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 思路：
 *
 * 我们可以使用 Math.min(numRows, s.length()) 个列表 来表示 Z 字形图案中的非空行。
 * 从左到右迭代 ss，将每个字符添加到合适的行。可以使用当前行和当前方向这两个变量对合适的行进行跟踪。
 * 只有当我们向上移动到最上面的行或向下移动到最下面的行时，当前方向才会发生改变。
 *
 */

public class Solution {
    public String convert(String s, int numRows) {

        if (numRows == 1) return s;     // 一行的时候

        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++)
            rows.add(new StringBuilder());  // 行集合 list

        int curRow = 0;
        boolean goingDown = false;  // Z 变换转移方向 标识

        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1) {     // 到顶或者到底 要变换方向呀~~
                goingDown = !goingDown;
            }
            curRow += goingDown ? 1 : -1;
        }

        // 横向输出字符串
        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) {
            ret.append(row);
        }
        return ret.toString();
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.convert("LEETCODEISHIRING", 3));
    }

}
