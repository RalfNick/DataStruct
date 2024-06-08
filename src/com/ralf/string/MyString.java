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
}
