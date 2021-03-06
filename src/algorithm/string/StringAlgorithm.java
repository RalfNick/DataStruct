package algorithm.string;

import java.util.HashSet;
import java.util.Set;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-06-05 下午5:28
 **/
public class StringAlgorithm {

    /**
     * 找到最大的回文串
     *
     * @param str 输入字符串（以空格分割）
     * @return
     */
    public static String findLongestPalindrome(String str) {
        if (str == null || str.length() < 1) {
            return null;
        }
        String[] strings = str.split(" ");
        if (strings.length < 1) {
            return null;
        }
        int lastIndex = -1;
        int len = 0;
        for (int i = 0; i < strings.length; i++) {
            if (isPalindrome(strings[i]) && strings[i].length() > len) {
                len = strings[i].length();
                lastIndex = i;
            }
        }
        if (lastIndex != -1) {
            return strings[lastIndex];
        }
        return null;
    }

    /**
     * 是否是回文串
     *
     * @param str 输入字符串（121、abba）
     * @return
     */
    public static boolean isPalindrome(String str) {
        if (str == null || str.length() < 1) {
            return false;
        }
        int length = str.length();
        char[] chars = str.toCharArray();
        for (int i = 0; i < length / 2; i++) {
            if (chars[i] != chars[length - 1 - i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String str = "This is racecar and madam, but the number is 121";
        System.out.println(findLongestPalindrome(str));
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        int start = 0;
        int maxLen = 1;
        for (int i = 1; i < s.length(); ) {
            for (int j = start; j < i; j++) {
                if (s.charAt(j) == s.charAt(i)) {
                    start++;
                    i = start;
                    break;
                }
            }
            maxLen = Math.max(maxLen, i - start + 1);
            i++;
        }
        return maxLen;
    }

    public static int lengthOfLongestSubstring1(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        Set<Character> set = new HashSet<>();
        int i = 0, j = 0, len = 0;
        while (i < s.length() && j < s.length()) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                len = Math.max(len, j - i);
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return len;
    }
}
