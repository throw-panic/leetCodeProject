package no0020;

import java.util.Stack;

/**
 * 20. 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *      左括号必须用相同类型的右括号闭合。
 *      左括号必须以正确的顺序闭合。
 *      注意空字符串可被认为是有效字符串。
 *
 * 示例 1:
 *
 * 输入: "()"
 * 输出: true
 * 示例 2:
 *
 * 输入: "()[]{}"
 * 输出: true
 */

public class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        // 遍历字符串
        for(char c : s.toCharArray()){
            if(c == '(' || c == '[' || c == '{'){
                // 括号的左半边： 压栈
                stack.push(c);
            }else{
                // 括号的右半边：匹配栈中字符是否一致
                if(stack.isEmpty()){
                    // 栈为空, 直接返回不匹配
                    return false;
                }
                char cStack = stack.pop();

                boolean b1 = c == ')' && cStack == '(' ; // 易错点： 匹配关系的两个字符是不相等的哈，例如 '(' 和 ')' 不等
                boolean b2 = c == ']' && cStack == '[' ;
                boolean b3 = c == '}' && cStack == '{' ;

                if(b1 || b2 || b3){     // 有一个为 true, 既匹配
                    // 继续往下找
                    continue;
                }else{
                    // 全部都是 false, 不匹配
                    return false;
                }
            }
        }
        // true 的话 应该是刚好匹配完
        return stack.isEmpty();
    }

    public static void main(String[] args){
        no0020.Solution solution = new Solution();
        System.out.println(solution.isValid("()()(){}[]"));
        System.out.println(solution.isValid("()((){}[]"));
        System.out.println(solution.isValid("()((){}[])"));
    }
}
