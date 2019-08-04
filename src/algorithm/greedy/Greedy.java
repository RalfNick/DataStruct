package algorithm.greedy;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-06-17 下午4:33
 **/
public class Greedy {

    /**
     * 移除 k 位数字，剩下结果最小
     *
     * @param num 数字字符串
     * @param k   需要移除的长度 k
     * @return
     */
    public static String removeKDigits(String num, int k) {
        if (k < 1) {
            return num;
        }
        if (num == null || num.length() < 1 || num.length() <= k) {
            return "0";
        }
        char[] stack = new char[num.length()];
        int top = 0;
        int len = num.length() - k;
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            while (top > 0 && stack[top - 1] > c && k > 0) {
                top--;
                k--;
            }
            stack[top++] = c;
        }
        int index = 0;
        while (index < len && stack[index] == '0') {
            index++;
        }
        return index == len ? "0" : new String(stack, index, len - index);
    }
}
