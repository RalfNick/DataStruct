package algorithm.array;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-10-20 下午5:49
 **/
public class ArrayUtil {

    public static int reverse(int x) {
        int reverse = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (reverse > Integer.MAX_VALUE / 10 || (reverse == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (reverse < Integer.MIN_VALUE / 10 || (reverse == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            reverse = reverse * 10 + pop;
        }
        return reverse;
    }

    /**
     * 字符串转换整数 (atoi) - 8
     *
     * @param str
     * @return
     */
    public static int myAtoi(String str) {
        if (str == null || "".equals(str)) {
            return 0;
        }
        int result = 0;
        int index = 0;
        while (index < str.length() && Character.isSpaceChar(str.charAt(index))) {
            index++;
        }
        if (index == str.length()) {
            return 0;
        }
        boolean navigateFlag = (str.charAt(index)) == '-';
        if (str.charAt(index) == '-' || str.charAt(index) == '+') {
            index++;
        }
        for (; index < str.length(); index++) {
            if (str.charAt(index) > '9' || str.charAt(index) < '0') {
                break;
            }
            int temp = str.charAt(index) - '0';
            if (!navigateFlag && result > (Integer.MAX_VALUE - temp) / 10) {
                return Integer.MAX_VALUE;
            } else if (navigateFlag && result < (Integer.MAX_VALUE + temp) / 10) {
                return Integer.MIN_VALUE;
            }
            result = result * 10 + (navigateFlag ? -temp : temp);
        }
        return result;
    }

    public static int maxArea(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }
        int max = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            max = Math.max(max, Math.min(height[left], height[right]) * (right - left));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }
}
