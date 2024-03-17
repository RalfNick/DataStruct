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
}
