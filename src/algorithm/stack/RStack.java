package algorithm.stack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import util.StringUtil;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-07-13 下午9:23
 **/
public class RStack {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 有效的括号 - 20
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     * 有效字符串需满足：
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 注意空字符串可被认为是有效字符串。
     *
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
        if (s == null || s.length() < 1) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else if (ch == ')') {
                if (stack.isEmpty() || stack.peek() != '(') {
                    return false;
                }
                stack.pop();
            } else if (ch == ']') {
                if (stack.isEmpty() || stack.peek() != '[') {
                    return false;
                }
                stack.pop();
            } else if (ch == '}') {
                if (stack.isEmpty() || stack.peek() != '{') {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    /**
     * 路径简化 - 71
     *
     * @param path 路径字符串
     * @return
     */
    public static String simplifyPath(String path) {
        if (path == null || path.length() < 1) {
            return path;
        }
        String[] strs = path.split("/");
        Stack<String> stack = new Stack<>();
        Set<String> set = new HashSet<>();
        set.add("");
        set.add(".");
        set.add("..");
        for (String str : strs) {
            if (str.equals("..") && !stack.isEmpty()) {
                stack.pop();
            } else if (!set.contains(str)) {
                stack.push(str);
            }
        }
        if (stack.isEmpty()) {
            return "/";
        }
        String result = "";
        for (String str : stack) {
            result = result + "/" + str;
        }
        return result;
    }

    /**
     * 柱状图中最大的矩形 - 84
     *
     * @param heights 矩形数组
     * @return
     */
    public static int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length < 1) {
            return 0;
        }
        int[] leftIndex = new int[heights.length];
        int[] rightIndex = new int[heights.length];
        for (int i = 1; i < heights.length; i++) {
            int temp = i - 1;
            while (temp > 0 && heights[temp] > heights[i]) temp--;
            leftIndex[i] = temp;
        }
        for (int i = heights.length - 2; i > 0; i--) {
            int temp = i + 1;
            while (temp < heights.length && heights[temp] > heights[i]) temp++;
            rightIndex[i] = temp;
        }
        int result = 0;
        for (int i = 0; i < heights.length; i++) {
            result = Math.max(result, heights[i] * (rightIndex[i] - leftIndex[i] - 1));
        }
        return result;
    }

    public static int largestRectangleArea1(int[] heights) {
        if (heights == null || heights.length < 1) {
            return 0;
        }
        if (heights.length == 1) {
            return heights[0];
        }
        Deque<Integer> stack = new ArrayDeque<>();
        int result = 0;
        for (int i = 0; i <= heights.length; i++) {
            int h = i == heights.length ? 0 : heights[i];
            if (stack.isEmpty() || h >= heights[stack.peek()]) {
                stack.push(i);
            } else {
                int index = stack.pop();
                result = Math.max(result, heights[index] * (stack.isEmpty() ? i : (i - 1 - stack.peek())));
                i--;
            }
        }
        return result;
    }

    public static int largestRectangleArea2(int[] heights) {
        if (heights == null || heights.length < 1) {
            return 0;
        }
        if (heights.length == 1) {
            return heights[0];
        }
        Deque<Integer> stack = new ArrayDeque<>();
        int result = 0;
        for (int i = 0; i <= heights.length; i++) {
            int h = i == heights.length ? 0 : heights[i];
            while (!stack.isEmpty() && h < heights[stack.peek()]) {
                int index = stack.pop();
                result = Math.max(result, heights[index] * (stack.isEmpty() ? i : (i - 1 - stack.peek())));
            }
            stack.push(i);
        }
        return result;
    }

    /**
     * 逆波兰表达式求值 - 150
     * 根据逆波兰表示法，求表达式的值。
     * 有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
     * 整数除法只保留整数部分。
     * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
     *
     * @param tokens
     * @return
     */
    public static int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length < 1) {
            throw new NullPointerException("the tokens is null");
        }
        Stack<String> stack = new Stack<>();
        Set<String> set = new HashSet<>();
        set.add("+");
        set.add("-");
        set.add("*");
        set.add("/");
        for (int i = 0; i < tokens.length; ) {
            while (i < tokens.length && !set.contains(tokens[i])) {
                stack.push(tokens[i]);
                i++;
            }
            if (i == tokens.length) {
                return Integer.parseInt(stack.pop());
            } else {
                int result = 0;
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                if (tokens[i].equals("+")) {
                    result = num1 + num2;
                } else if (tokens[i].equals("-")) {
                    result = num1 - num2;
                } else if (tokens[i].equals("*")) {
                    result = num1 * num2;
                } else if (tokens[i].equals("/")) {
                    result = num1 / num2;
                } else {
                    result = num2;
                }
                stack.push(String.valueOf(result));
                i++;
            }
        }
        return Integer.parseInt(stack.pop());
    }

    /**
     * 字符串解码 - 394
     * s = "3[a]2[bc]", 返回 "aaabcbc".
     * s = "3[a2[c]]", 返回 "accaccacc".
     * s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".
     *
     * @param s
     * @return
     */
    public static String decodeString(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        String[] strings = s.split("");
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].equals("]")) {
                String temp = "";
                while (!stack.isEmpty() && !StringUtil.isNumberic(stack.peek())) {
                    temp = stack.pop() + temp;
                }
                String cur = "";
                if (!stack.isEmpty()) {
                    int size = Integer.parseInt(stack.pop());
                    for (int j = 0; j < size; j++) {
                        cur += temp;
                    }
                }
                stack.push(cur);
            } else if (strings[i].equals("[")) {
                continue;
            } else {
                stack.push(strings[i]);
            }
        }
        String result = "";
        while (!stack.isEmpty() && StringUtil.check(stack.peek())) {
            result = stack.pop() + result;
        }
        return result;
    }

    /**
     * 132模式 - 456
     * 给定一个整数序列：a1, a2, ..., an，一个132模式的子序列 ai, aj, ak 
     * 被定义为：当 i < j < k 时，ai < ak < aj。设计一个算法，当给定有 n 个数字的序列时，
     * <p>
     * 验证这个序列中是否含有132模式的子序列。
     * <p>
     * 输入: [3, 1, 4, 2]
     * 输出: True
     * 解释: 序列中有 1 个132模式的子序列： [1, 4, 2].
     *
     * @param nums int 数组
     * @return
     */
    public static boolean find132pattern(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }
        for (int i = nums.length - 1; i > 0; i--) {
            int j = i - 1;
            while (j > 0 && nums[j] <= nums[i]) {
                j--;
            }
            if (j > 0) {
                int k = j - 1;
                while (k > 0 && nums[k] >= nums[i]) {
                    k--;
                }
                if (k >= 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean find132pattern1(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        int val = Integer.MIN_VALUE;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] < val) {
                return true;
            } else {
                while (!stack.isEmpty() && stack.peek() < nums[i]) {
                    val = Math.max(val, stack.pop());
                }
                stack.push(nums[i]);
            }
        }
        return false;
    }

    /**
     * 下一个更大元素 I - 496
     * 给定两个没有重复元素的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。找到 nums1 中每个元素在 nums2 中的下一个比其大的值。
     * nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出-1。
     * 示例 1:
     * 输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
     * 输出: [-1,3,-1]
     *
     * @param nums1 数组1
     * @param nums2 数组2
     * @return
     */
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length < 1) {
            return nums1;
        }
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            boolean isFound = false;
            result[i] = -1;
            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]) {
                    isFound = true;
                }
                if (isFound && nums2[j] > nums1[i]) {
                    result[i] = nums2[j];
                    break;
                }
            }
        }
        return result;
    }

    public static int[] nextGreaterElement1(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length < 1) {
            return nums1;
        }
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            while (!stack.isEmpty() && nums2[i] > stack.peek()) {
                map.put(stack.pop(), nums2[i]);
            }
            stack.push(nums2[i]);
        }

        while (!stack.isEmpty()) {
            map.put(stack.pop(), -1);
        }
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = map.get(nums1[i]);
        }
        return result;
    }

    /**
     * 下一个更大元素 II - 503
     * 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。
     * 数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，
     * 这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
     * 输入: [1,2,1]
     * 输出: [2,-1,2]
     * 解释: 第一个 1 的下一个更大的数是 2；
     * 数字 2 找不到下一个更大的数；
     * 第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
     *
     * @param nums 数组
     * @return
     */
    public static int[] nextGreaterElements(int[] nums) {
        if (nums == null || nums.length < 1) {
            return null;
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            stack.push(nums[i]);
        }
        int[] result = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            result[i] = -1;
            while (!stack.isEmpty() && stack.peek() <= nums[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                result[i] = stack.peek();
            }
            stack.push(nums[i]);
        }
        return result;
    }

    /**
     * 移掉K位数字 - 402
     *
     * @param num 数字串
     * @param k   k位
     * @return
     */
    public static String removeKdigits(String num, int k) {
        if (num == null || num.length() < 1 || k == 0) {
            return num;
        }
        if (k >= num.length()) {
            return "0";
        }
        char[] chars = num.toCharArray();
        char[] stack = new char[chars.length];
        int top = 0;
        for (char ch : chars) {
            while (top > 0 && k > 0 && stack[top - 1] > ch) {
                k--;
                top--;
            }
            stack[top++] = ch;
        }
        for (int i = 0; i < k; i++) {
            top--;
        }
        int position = 0;
        while (position < top && stack[position] == '0') {
            position++;
        }
        return position == top ? "0" : new String(stack, position, top - position);
    }

    /**
     * 去除重复字母 - 316
     * 给定一个仅包含小写字母的字符串，去除字符串中重复的字母，使得每个字母只出现一次。
     * 需保证返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
     * 输入: "bcabc"
     * 输出: "abc"
     *
     * @param s 输入字符
     * @return
     */
    public static String removeDuplicateLetters(String s) {
        int[] chs = new int[26];
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chs[chars[i] - 'a']++;
        }
        int position = 0;
        for (int i = 0; i < chars.length; i++) {
            if (s.charAt(i) < s.charAt(position)) {
                position = i;
            }
            chs[s.charAt(i) - 'a']--;
            if (chs[s.charAt(i) - 'a'] == 0) {
                break;
            }
        }
        if (s.length() == 0) {
            return "";
        }
        String cur = String.valueOf(s.charAt(position));
        return cur + removeDuplicateLetters(s.substring(position + 1).replaceAll(cur, ""));
    }

    /**
     * 每日温度 - 739
     * 根据每日 气温 列表，请重新生成一个列表，对应位置的输入是你需要再等待多久温度才会升高超过该日的天数。
     * 如果之后都不会升高，请在该位置用 0 来代替。
     * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，
     * 你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
     *
     * @param arr 每日温度
     * @return
     */
    public static int[] dailyTemperatures(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] > arr[i]) {
                    result[i] = j - i;
                    break;
                }
            }
        }
        return result;
    }

    public static int[] dailyTemperatures1(int[] arr) {
        if (arr == null) {
            return null;
        }
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[i] >= arr[stack.peek()]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);
        }
        return result;
    }

    /**
     * 比较含退格的字符串 - 844
     * 给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。
     * 1 <= S.length <= 200
     * 1 <= T.length <= 200
     * S 和 T 只含有小写字母以及字符 '#'。
     *
     * @param S
     * @param T
     * @return
     */
    public static boolean backspaceCompare(String S, String T) {
        Stack<Character> sStack = new Stack<>();
        Stack<Character> tStack = new Stack<>();

        for (Character s : S.toCharArray()) {
            if (s == '#' && !sStack.isEmpty()) {
                sStack.pop();
            } else {
                sStack.push(s);
            }
        }
        for (Character s : T.toCharArray()) {
            if (s == '#' && !tStack.isEmpty()) {
                tStack.pop();
            } else {
                tStack.push(s);
            }
        }
        if (sStack.isEmpty() && tStack.isEmpty()) {
            return true;
        } else if (sStack.isEmpty() && !tStack.isEmpty()) {
            return false;
        } else if (!sStack.isEmpty() && tStack.isEmpty()) {
            return false;
        }
        if (sStack.size() != tStack.size()) {
            return false;
        }
        while (!sStack.isEmpty()) {
            if (sStack.peek() != tStack.peek()) {
                return false;
            }
            sStack.pop();
            tStack.pop();
        }
        return true;
    }

    /**
     * 括号的分数 - 856
     * 给定一个平衡括号字符串 S，按下述规则计算该字符串的分数：
     * () 得 1 分。
     * AB 得 A + B 分，其中 A 和 B 是平衡括号字符串。
     * (A) 得 2 * A 分，其中 A 是平衡括号字符串。
     *
     * @param s
     * @return
     */
    public static int scoreOfParentheses(String s) {
        Stack<String> stack = new Stack<>();
        String[] str = s.split("");
        for (String c : str) {
            if (c.equals(")")) {
                int temp = 0;
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    temp += Integer.parseInt(stack.pop());
                }
                temp = temp == 0 ? 1 : temp * 2;
                stack.pop();
                stack.push(String.valueOf(temp));

            } else {
                stack.push(c);
            }
        }
        int result = 0;
        while (!stack.isEmpty()) {
            result += Integer.parseInt(stack.pop());
        }
        return result;
    }

    /**
     * 使括号有效的最少添加 - 921
     * 给定一个由 '(' 和 ')' 括号组成的字符串 S，我们需要添加最少的括号（ '(' 或是 ')'，可以在任何位置），以使得到的括号字符串有效。
     * 从形式上讲，只有满足下面几点之一，括号字符串才是有效的：
     * 它是一个空字符串，或者
     * 它可以被写成 AB （A 与 B 连接）, 其中 A 和 B 都是有效字符串，或者
     * 它可以被写作 (A)，其中 A 是有效字符串。
     * 给定一个括号字符串，返回为使结果字符串有效而必须添加的最少括号数。
     *
     * @param
     * @return
     */
    public static int minAddToMakeValid(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        Stack<String> stack = new Stack<>();
        int result = 0;
        for (String cur : s.split("")) {
            if (cur.equals(")")) {
                if (stack.isEmpty()) {
                    result++;
                } else {
                    stack.pop();
                }
            } else {
                stack.push(cur);
            }
        }
        return result + stack.size();
    }

    /**
     * 验证栈序列 - 946
     * 给定 pushed 和 popped 两个序列，只有当它们可能是在最初空栈上进行的
     * 推入 push 和弹出 pop 操作序列的结果时，返回 true；否则，返回 false 。
     * 示例 1：
     * 输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
     * 输出：true
     *
     * @param pushed
     * @param popped
     * @return
     */
    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed == null || pushed.length < 1
                || popped == null || popped.length < 1) {
            return false;
        } else if (pushed.length != popped.length) {
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        int j = 0;
        while (j < pushed.length && popped[0] != pushed[j]) {
            stack.push(pushed[j++]);
        }
        j++;
        if (j >= pushed.length && stack.isEmpty()) {
            return false;
        }
        for (int i = 1; i < popped.length; i++) {
            if (!stack.contains(popped[i])) {
                if (j >= pushed.length) {
                    return false;
                } else if (pushed[j] != popped[i]) {
                    return false;
                } else {
                    j++;
                }
            } else if (stack.contains(popped[i]) && stack.peek() == popped[i]) {
                stack.pop();
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 删除最外层的括号 - 1021
     *
     * @param s
     * @return
     */
    public static String removeOuterParentheses(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        Stack<String> stack = new Stack<>();
        StringBuilder builder = new StringBuilder();
        for (String cur : s.split("")) {
            if (cur.equals(")")) {
                stack.pop();
                if (!stack.isEmpty()) {
                    builder.append(cur);
                }
            } else {
                if (!stack.isEmpty()) {
                    builder.append(cur);
                }
                stack.push(cur);
            }
        }
        return builder.toString();
    }

    /**
     * 链表中的下一个更大节点 - 1019
     * 输入：[2,1,5]
     * 输出：[5,5,0]
     *
     * @param head 头指针
     * @return
     */
    public static int[] nextLargerNodes(ListNode head) {
        if (head == null) {
            return null;
        }
        List<Integer> list = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            ListNode temp = node.next;
            while (temp != null && temp.val <= node.val) {
                temp = temp.next;
            }
            list.add(temp == null ? 0 : temp.val);
            node = node.next;
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }
}
