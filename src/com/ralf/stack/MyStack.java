package com.ralf.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class MyStack {

    /**
     * 有效的括号 - 20
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     * 有效字符串需满足：
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 注意空字符串可被认为是有效字符串。
     * <a href="https://leetcode.cn/problems/valid-parentheses/description/">Leet Code</a>
     */
    static boolean isValid(String s) {
        if (s == null || s.length() < 1) {
            return false;
        }
        Deque<Character> stack = new ArrayDeque<>();
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
     * 496. 下一个更大元素 I
     * nums1 中数字 x 的 下一个更大元素 是指 x 在 nums2 中对应位置 右侧 的 第一个 比 x 大的元素。
     * 给你两个 没有重复元素 的数组 nums1 和 nums2 ，下标从 0 开始计数，其中nums1 是 nums2 的子集。
     * 对于每个 0 <= i < nums1.length ，找出满足 nums1[i] == nums2[j] 的下标 j ，并且在 nums2 确定 nums2[j] 的 下一个更大元素 。如果不存在下一个更大元素，那么本次查询的答案是 -1 。
     * 返回一个长度为 nums1.length 的数组 ans 作为答案，满足 ans[i] 是如上所述的 下一个更大元素 。
     * <a href="https://leetcode.cn/problems/next-greater-element-i/description/">Leet Code</a>
     */
    static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length < 1) {
            return new int[0];
        }
        int[] next = new int[nums1.length];
        Arrays.fill(next, -1);
        for (int i = 0; i < nums1.length; i++) {
            int val = nums1[i];
            int index = -1;
            for (int j = 0; j < nums2.length; j++) {
                if (nums2[j] == val) {
                    index = j;
                    break;
                }
            }
            if (index == -1) {
                continue;
            }
            for (int j = index + 1; j < nums2.length; j++) {
                if (nums2[j] > val) {
                    next[i] = nums2[j];
                    break;
                }
            }
        }
        return next;
    }

    static int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length < 1) {
            return new int[0];
        }
        int[] ans = new int[nums1.length];
        Deque<Integer> stack = new ArrayDeque<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = nums2.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums2[i]) {
                stack.pop();
            }
            map.put(nums2[i], stack.isEmpty() ? -1 : stack.peek());
            stack.push(nums2[i]);
        }
        for (int i = 0; i < nums1.length; i++) {
            ans[i] = map.get(nums1[i]);
        }
        return ans;
    }

    /**
     * 503. 下一个更大元素 II
     * 给定一个循环数组 nums （ nums[nums.length - 1] 的下一个元素是 nums[0] ），返回 nums 中每个元素的 下一个更大元素 。
     * 数字 x 的 下一个更大的元素 是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1 。
     * <a href="https://leetcode.cn/problems/next-greater-element-ii/">Leet Code</a>
     */
    static int[] nextGreaterElementII(int[] nums) {
        if (nums == null || nums.length < 1) {
            return new int[0];
        }
        int[] ans = new int[nums.length];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 2 * nums.length - 1; i >= 0; i--) {
            int k = i % nums.length;
            while (!stack.isEmpty() && stack.peek() <= nums[k]) {
                stack.pop();
            }
            ans[k] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[k]);
        }
        return ans;
    }

    /**
     * 556. 下一个更大元素 III
     * 给你一个正整数 n ，请你找出符合条件的最小整数，其由重新排列 n 中存在的每位数字组成，并且其值大于 n 。如果不存在这样的正整数，则返回 -1 。
     * 注意 ，返回的整数应当是一个 32 位整数 ，如果存在满足题意的答案，但不是 32 位整数 ，同样返回 -1 。
     * <a href="https://leetcode.cn/problems/next-greater-element-iii/description/">Leet Code</a>
     */
    static int nextGreaterElementIII(int n) {
        char[] chars = String.valueOf(n).toCharArray();
        if (chars.length < 2) {
            return -1;
        }
        int i = chars.length - 2;
        while (i >= 0 && chars[i] >= chars[i + 1]) {
            i--;
        }
        if (i < 0) {
            return -1;
        }
        int j = chars.length - 1;
        while (j > i && chars[j] <= chars[i]) {
            j--;
        }
        swap(chars, i, j);
        revert(chars, i + 1);
        long val = Long.parseLong(new String(chars));
        return val > Integer.MAX_VALUE ? -1 : (int) val;
    }

    private static void revert(char[] arr, int begin) {
        int i = begin;
        int j = arr.length - 1;
        while (i < j) {
            swap(arr, i, j);
            i++;
            j--;
        }
    }

    private static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 739. 每日温度
     * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，
     * 其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
     * <a href="https://leetcode.cn/problems/daily-temperatures/description/">Leet Code</a>
     */
    static public int[] dailyTemperatures(int[] temperatures) {
        Deque<Integer> stack = new ArrayDeque<>();
        int[] res = new int[temperatures.length];
        for (int i = temperatures.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && temperatures[stack.peek()] <= temperatures[i]) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? 0 : stack.peek() - i + 1;
            stack.push(i);
        }
        return res;
    }

    /**
     * 路径简化 - 71
     * 给你一个字符串 path ，表示指向某一文件或目录的 Unix 风格 绝对路径 （以 '/' 开头），请你将其转化为更加简洁的规范路径。
     * 在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..） 表示将目录切换到上一级（指向父目录）；
     * 两者都可以是复杂相对路径的组成部分。任意多个连续的斜杠（即，'//'）都被视为单个斜杠 '/' 。 对于此问题，任何其他格式的点（例如，'...'）均被视为文件/目录名称。
     * 请注意，返回的 规范路径 必须遵循下述格式：
     * 始终以斜杠 '/' 开头。
     * 两个目录名之间必须只有一个斜杠 '/' 。
     * 最后一个目录名（如果存在）不能 以 '/' 结尾。
     * 此外，路径仅包含从根目录到目标文件或目录的路径上的目录（即，不含 '.' 或 '..'）。
     * 返回简化后得到的 规范路径 。
     * <a href="https://leetcode.cn/problems/simplify-path/description/">Leet Code</a>
     */
    public static String simplifyPath(String path) {
        if (path == null || path.length() < 1) {
            return path;
        }
        String[] paths = path.split("/");
        Deque<String> stack = new ArrayDeque<>();
        for (String str : paths) {
            if (str.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (str.length() > 0 && !".".equals(str)) {
                stack.push(str);
            }
        }
        if (stack.isEmpty()) {
            return "/";
        }
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append("/").append(stack.pollLast());
        }
        return result.toString();
    }

    /**
     * 84. 柱状图中最大的矩形
     * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
     * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
     * <a href="https://leetcode.cn/problems/largest-rectangle-in-histogram/description/">Leet Code</a>
     */
    static int largestRectangleArea(int[] heights) {
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
     * 42. 接雨水
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
     * <a href="https://leetcode.cn/problems/trapping-rain-water/description/">Leet Code</a>
     *
     * <a href="https://leetcode.cn/problems/trapping-rain-water/solutions/48255/bao-li-jie-fa-yi-kong-jian-huan-shi-jian-zhi-zhen-/">题解</a>
     */
    static int trap(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }
        int len = height.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int result = 0;
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int w = i - 1 - stack.peek();
                int h = Math.min(height[i], height[stack.peek()]) - height[top];
                result = result + w * h;
            }
            stack.push(i);
        }
        return result;
    }

    /**
     * 移掉K位数字 - 402
     * 给你一个以字符串表示的非负整数 num 和一个整数 k ，移除这个数中的 k 位数字，使得剩下的数字最小。请你以字符串形式返回这个最小的数字。
     * <a href="https://leetcode.cn/problems/remove-k-digits/description/">Leet Code</a>
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
     * 132模式 - 456
     * 给定一个整数序列：a1, a2, ..., an，一个132模式的子序列 ai, aj, ak 
     * 被定义为：当 i < j < k 时，ai < ak < aj。设计一个算法，当给定有 n 个数字的序列时，
     * <p>
     * 验证这个序列中是否含有132模式的子序列。
     * <p>
     * 输入: [3, 1, 4, 2]
     * 输出: True
     * 解释: 序列中有 1 个132模式的子序列： [1, 4, 2].
     * <a href=" https://leetcode.cn/problems/132-pattern/description">Leet Code</a>/
     */
    public static boolean find132pattern(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }
        int len = nums.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int val = Integer.MIN_VALUE;
        for (int i = len - 1; i >= 0; i++) {
            if (nums[i] < val) {
                return true;
            }
            while (!stack.isEmpty() && stack.peek() < nums[i]) {
                val = Math.max(val, stack.pop());
            }
            stack.push(nums[i]);
        }
        return false;
    }

    /**
     * 316. 去除重复字母
     * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
     * <a href="https://leetcode.cn/problems/remove-duplicate-letters/description/">Leet Code</a>/
     */
    public static String removeDuplicateLetters(String s) {
        boolean[] vis = new boolean[26];
        int[] num = new int[26];
        for (int i = 0; i < s.length(); i++) {
            num[s.charAt(i) - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!vis[ch - 'a']) {
                while (sb.length() > 0 && sb.charAt(sb.length() - 1) > ch) {
                    if (num[sb.charAt(sb.length() - 1) - 'a'] > 0) {
                        vis[sb.charAt(sb.length() - 1) - 'a'] = false;
                        sb.deleteCharAt(sb.length() - 1);
                    } else {
                        break;
                    }
                }
                vis[ch - 'a'] = true;
                sb.append(ch);
            }
            num[ch - 'a'] -= 1;
        }
        return sb.toString();
    }

}
