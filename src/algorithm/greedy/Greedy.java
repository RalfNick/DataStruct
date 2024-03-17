package algorithm.greedy;

import java.util.*;

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

    /**
     * 买卖股票的最佳时机 II - 122
     *
     * @param prices 股票价格
     * @return
     */
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 1) {
            return 0;
        }
        int profit = 0;
        int i = 0;
        while (i < prices.length) {
            int index = i;
            while (index < prices.length - 1 && prices[index + 1] > prices[index]) {
                index++;
            }
            if (index < prices.length) {
                profit += prices[index] - prices[i];
                i = index + 1;
            } else {
                i++;
            }
        }
        return profit;
    }

    /**
     * 买卖股票的最佳时机 II - 122  方法二
     *
     * @param prices 股票价格
     * @return
     */
    public static int maxProfit1(int[] prices) {
        if (prices == null || prices.length < 1) {
            return 0;
        }
        int profit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i + 1] >= prices[i]) {
                profit += prices[i + 1] - prices[i];
            }
        }
        return profit;
    }

    /**
     * 买卖股票的最佳时机含手续费 - 714
     *
     * @param prices 股票价格
     * @param fee    手续费
     * @return
     */
    public static int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int profit = 0;
        int i = 0;
        while (i < prices.length) {
            int index = i;
            // 找到能赚钱的交易
            while (index < prices.length && prices[index] <= (prices[i] + fee)) {
                index++;
            }
            // 找到 更 赚钱的交易
            while (index < prices.length - 1 && prices[index + 1] > prices[index]) {
                index++;
            }
            if (index < prices.length) {
                profit += (prices[index] - (prices[i] + fee));
                i = index + 1;
            } else {
                i++;
            }
        }
        return profit;
    }

    /**
     * 加油站 - 134 - 方法一
     *
     * @param gas  加油站汽油数量
     * @param cost 汽车消耗汽油量
     * @return
     */
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || cost == null || gas.length != cost.length) {
            return -1;
        }
        int len = gas.length - 1;
        for (int i = 0; i < len; i++) {
            if (cost[i] <= gas[i]) {
                if (canCompleteCircuit(gas, cost, i, len)) {
                    return i;
                }
            }
        }
        return -1;
    }

    private static boolean canCompleteCircuit(int[] gas, int[] cost, int index, int len) {
        int next = index;
        int oil = gas[index];
        while (oil >= cost[next]) {
            int last = next;
            next = next == len - 1 ? 0 : next + 1;
            if (next == index) {
                return true;
            }
            oil = oil - cost[last] + gas[next];
        }
        return false;
    }

    /**
     * 加油站 - 134 - 方法二
     *
     * @param gas  加油站汽油数量
     * @param cost 汽车消耗汽油量
     * @return
     */
    public static int canCompleteCircuit1(int[] gas, int[] cost) {
        if (gas == null || cost == null || gas.length != cost.length) {
            return -1;
        }
        int hasResult = 0;
        int sum = 0;
        int index = 0;
        for (int i = 0; i < gas.length; i++) {
            hasResult += gas[i] - cost[i];
            if (sum > 0) {
                sum += gas[i] - cost[i];
            } else {
                sum = gas[i] - cost[i];
                index = i;
            }
        }
        return hasResult >= 0 ? index : -1;
    }

    /**
     * 去除重复字母 - 316
     *
     * @param s 字符串
     * @return
     */
    public static String removeDuplicateLetters(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        char[] chars = new char[26];
        for (char c : s.toCharArray()) {
            chars[c - 'a']++;
        }
        int index = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch < s.charAt(index)) {
                index = i;
            }
            chars[ch - 'a']--;
            if (chars[ch - 'a'] == 0) {
                break;
            }
        }
        String cur = String.valueOf(s.charAt(index));
        return cur + removeDuplicateLetters(s.substring(index + 1).replaceAll(cur, ""));
    }

    /**
     * 分发饼干 - 455
     * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
     * 对每个孩子 i ，都有一个胃口值 gi ，这是能让孩子们满足胃口的饼干的最小尺寸；
     * 并且每块饼干 j ，都有一个尺寸 sj 。如果 sj >= gi ，我们可以将这个饼干 j 分配给孩子 i ，
     * 这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
     *
     * @param g 孩子的胃口
     * @param s 饼干的尺寸
     * @return
     */
    public static int findContentChildren(int[] g, int[] s) {
        if (g == null || g.length < 1 || s == null || s.length < 1) {
            return 0;
        }
        Arrays.sort(g);
        Arrays.sort(s);
        int num = 0;
        int index = 0;
        for (int i = 0; i < g.length; i++) {
            while (index < s.length && s[index] < g[i]) {
                index++;
            }
            if (index < s.length) {
                num++;
                index++;
            }
        }
        return num;
    }

    public static int findContentChildren1(int[] g, int[] s) {
        if (g == null || g.length < 1 || s == null || s.length < 1) {
            return 0;
        }
        int num = 0;
        Arrays.sort(g);
        Arrays.sort(s);
        for (int i = 0, j = 0; i < g.length && j < s.length; ) {
            if (s[j] >= g[i]) {
                num++;
                i++;
            }
            j++;
        }
        return num;
    }

    /**
     * 无重叠区间 - 435
     * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
     *
     * @param intervals 区间数组
     * @return
     */
    public static int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length < 1) {
            return 0;
        }
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        int end = Integer.MIN_VALUE;
        int count = 0;
        for (int[] interval : intervals) {
            if (interval[0] >= end) {
                end = interval[1];
            } else {
                count++;
            }
        }
        return count;
    }

    /**
     * 使括号有效的最少添加 - 921
     *
     * @param str 字符串
     * @return
     */
    public static int minAddToMakeValid(String str) {
        if (str == null || str.length() < 1) {
            return 0;
        }
        String[] strings = str.split("");
        int count = 1;
        String last = strings[0];
        for (int i = 1; i < strings.length; i++) {
            if (!last.equals(strings[i]) && "(".equals(last)) {
                count--;
            } else {
                count++;
            }
            last = strings[i];
        }
        return count;
    }

    /**
     * 任务调度器 - 621
     *
     * @param tasks
     * @param n
     * @return
     */
    public static int leastInterval(char[] tasks, int n) {
        if (tasks == null || tasks.length < 1) {
            return 0;
        }
        class Task {
            private int value;
            private char name;

            private Task(int value, char name) {
                this.value = value;
                this.name = name;
            }
        }
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : tasks) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        PriorityQueue<Task> queue = new PriorityQueue<>((o1, o2) -> o2.value - o1.value);
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            queue.offer(new Task(entry.getValue(), entry.getKey()));
        }
        int count = 0;
        while (!queue.isEmpty()) {
            int i = 0;
            List<Task> list = new ArrayList<>();
            while (i <= n) {
                if (!queue.isEmpty()) {
                    Task task = queue.poll();
                    task.value -= 1;
                    if (task.value > 0) {
                        list.add(task);
                    }
                }
                count++;
                if (queue.isEmpty() && list.isEmpty()) {
                    break;
                }
                i++;
            }
            if (!list.isEmpty()) {
                for (Task task : list) {
                    queue.offer(task);
                }
            }
        }
        return count;
    }

    /**
     * 分发糖果 - 135
     *
     * @param ratings 每个孩子的评分
     * @return
     */
    public static int candy(int[] ratings) {
        int n = ratings.length;
        int[] left = new int[n];
        for (int i = 0; i < n; i++) {
            if (i > 0 && ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }
        int right = 0, ret = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (i < n - 1 && ratings[i] > ratings[i + 1]) {
                right++;
            } else {
                right = 1;
            }
            ret += Math.max(left[i], right);
        }
        return ret;
    }
}
