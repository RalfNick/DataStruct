package com.ralf.string;

import java.util.HashMap;
import java.util.Map;

public class MyString {

    /**
     * 3. 无重复字符的最长子串
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串的长度。
     * <a href="https://leetcode.cn/problems/longest-substring-without-repeating-characters/?envType=list&envId=8iaTBYMk">LeetCode</a>
     */
    static int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        int left = 0;
        int result = 0;
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (map.containsKey(ch)) {
                left = Math.max(map.get(ch) + 1, left);
            }
            result = Math.max(result, i - left + 1);
            map.put(ch, i);
        }
        return result;
    }

    /**
     * LCR 169. 招式拆解 II
     */
    public char dismantlingAction(String arr) {
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = arr.toCharArray();
        for (char ch : chars) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        for (char ch : chars) {
            if (map.get(ch) == 1) {
                return ch;
            }
        }
        return ' ';
    }

    /**
     * 415. 字符串相加
     */
    public String addStrings(String num1, String num2) {
        if (num1.isEmpty()) {
            return num2;
        }
        if (num2.isEmpty()) {
            return num1;
        }
        char[] num1CharArray = num1.toCharArray();
        char[] num2CharArray = num2.toCharArray();
        int len1 = num1CharArray.length;
        int len2 = num2CharArray.length;
        int i = len1 - 1;
        int j = len2 - 1;
        int carry = 0;
        StringBuilder builder = new StringBuilder();
        while (i >= 0 || j >= 0) {
            int x = i >= 0 ? num1CharArray[i] - '0' : 0;
            int y = j >= 0 ? num2CharArray[j] - '0' : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            int result = sum % 10;
            builder.append(result);
            i--;
            j--;
        }
        if (carry > 0) {
            builder.append(carry);
        }

        return builder.reverse().toString();
    }

    /**
     * 344. 反转字符串
     */
    public void reverseString(char[] s) {
        if (s == null || s.length == 0) {
            return;
        }
        int i = 0, j = s.length - 1;
        while (i < j) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            i++;
            j--;
        }
    }

    /**
     * 557. 反转字符串中的单词 III
     */
    public String reverseWords(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        String[] strings = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String str : strings) {
            char[] chars = str.toCharArray();
            if (chars.length == 0) {
                continue;
            }
            int i = 0, j = chars.length - 1;
            while (i < j) {
                char temp = chars[i];
                chars[i] = chars[j];
                chars[j] = temp;
                i++;
                j--;
            }
            sb.append(chars).append(" ");
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    /**
     * 43. 字符串相乘
     */
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        String ans = "0";
        int m = num1.length(), n = num2.length();
        for (int i = n - 1; i >= 0; i--) {
            StringBuilder sb = new StringBuilder();
            for (int j = n - 1; j > i; j--) {
                sb.append("0");
            }
            int add = 0;
            int y = num2.charAt(i) - '0';
            for (int j = m - 1; j >= 0; j--) {
                int x = num1.charAt(j) - '0';
                int sum = x * y + add;
                add = sum / 10;
                sb.append(sum % 10);
            }
            if (add != 0) {
                sb.append(add);
            }
            ans = addTwoNumbers(ans, sb.reverse().toString());
        }
        return ans;
    }

    private String addTwoNumbers(String num1, String num2) {
        if (num1 == null || num1.isEmpty()) {
            return num2;
        }
        if (num2 == null || num2.isEmpty()) {
            return num1;
        }
        int i = num1.length() - 1, j = num2.length() - 1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        while (i >= 0 || j >= 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            sb.append(sum % 10);
            i--;
            j--;
        }
        if (carry > 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }
}
