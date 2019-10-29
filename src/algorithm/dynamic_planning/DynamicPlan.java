package algorithm.dynamic_planning;

import java.util.Arrays;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-09-25 下午9:38
 **/
public class DynamicPlan {

    /**
     * 最大子序和 - 53
     *
     * @param nums 序列数组
     * @return
     */
    public static int maxSubArray(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int sum = 0;
        int max = nums[0];
        for (int i : nums) {
            if (sum > 0) {
                sum += i;
            } else {
                sum = i;
            }
            max = Math.max(max, sum);
        }
        return max;
    }

    /************************************** 股票问题 **************************************/
    // https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/solution/yi-ge-fang-fa-tuan-mie-6-dao-gu-piao-wen-ti-by-l-2/

    /**
     * 卖股票的最佳时机 - 121 (一次交易)
     *
     * @param prices 每天股票价格
     * @return
     */
    public static int maxProfit1(int[] prices) {
        if (prices == null || prices.length < 1) {
            return 0;
        }
        int d_i_0 = 0;
        int d_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < prices.length; i++) {
            d_i_0 = Math.max(d_i_0, d_i_1 + prices[i]);
            d_i_1 = Math.max(d_i_1, -prices[i]);
        }
        return d_i_0;
    }

    /**
     * 买卖股票的最佳时机 II - 122 （尽可能多的交易）
     *
     * @param prices 每天股票价格
     * @return
     */
    public static int maxProfit2(int[] prices) {
        if (prices == null || prices.length < 1) {
            return 0;
        }
        int d_i_0 = 0;
        int d_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < prices.length; i++) {
            int temp = d_i_0;
            d_i_0 = Math.max(d_i_0, d_i_1 + prices[i]);
            d_i_1 = Math.max(d_i_1, temp - prices[i]);
        }
        return d_i_0;
    }

    /**
     * 最佳买卖股票时机含冷冻期 -309 (带有冷冻期)
     *
     * @param prices 股票价格
     * @return
     */
    public static int maxProfit3(int[] prices) {
        if (prices == null || prices.length < 1) {
            return 0;
        }
        int d_i_0 = 0;
        int d_i_1 = Integer.MIN_VALUE;
        int d_pre_0 = 0;
        for (int i = 0; i < prices.length; i++) {
            int temp = d_i_0;
            d_i_0 = Math.max(d_i_0, d_i_1 + prices[i]);
            d_i_1 = Math.max(d_i_1, d_pre_0 - prices[i]);
            d_pre_0 = temp;
        }
        return d_i_0;
    }

    /**
     * 买卖股票的最佳时机含手续费 - 714
     *
     * @param prices 股票价格
     * @return
     */
    public static int maxProfit4(int[] prices, int fee) {
        if (prices == null || prices.length < 1) {
            return 0;
        }
        int d_i_0 = 0;
        int d_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < prices.length; i++) {
            int temp = d_i_0;
            d_i_0 = Math.max(d_i_0, d_i_1 + prices[i]);
            d_i_1 = Math.max(d_i_1, temp - prices[i] - fee);
        }
        return d_i_0;
    }

    /**
     * 买卖股票的最佳时机 IV - 188 (最多交易 k 次) 此种方法有待验证
     *
     * @param prices 股票价格
     * @param k      最多交易次数
     * @return
     */
    public static int maxProfit5(int[] prices, int k) {
        if (prices == null || prices.length < 1 || k < 1) {
            return 0;
        }
        int length = prices.length;
        if (k == 1) {
            return maxProfit1(prices);
        } else if (k > length / 2) {
            return maxProfit2(prices);
        }
        int[][][] d_k_s = new int[length][k + 1][2];
        for (int i = 0; i < length; i++) {
            for (int j = k; j >= 1; j--) {
                if (i == 0) {
                    d_k_s[i][j][0] = d_k_s[i][0][0] = 0;
                    d_k_s[i][j][1] = d_k_s[i][0][1] = Integer.MIN_VALUE;
                    continue;
                }
                d_k_s[i][j][0] = Math.max(d_k_s[i - 1][j][0], d_k_s[i - 1][j][1] + prices[i]);
                d_k_s[i][j][1] = Math.max(d_k_s[i - 1][j][1], d_k_s[i - 1][j - 1][0] - prices[i]);
            }
        }
        return d_k_s[length - 1][k][0];
    }

    /**
     * 买卖股票的最佳时机 IV - 188 (最多交易 k 次)
     *
     * @param prices 股票价格
     * @param k      最多交易次数
     * @return
     */
    public static int maxProfit55(int[] prices, int k) {
        if (prices == null || prices.length < 1 || k < 1) {
            return 0;
        }
        int n = prices.length;
        if (k == 1) {
            return maxProfit1(prices);
        } else if (k > n / 2) {
            return maxProfit2(prices);
        }
        int[][] state = new int[k + 1][n];
        for (int i = 1; i <= k; i++) {
            int tempMax = -prices[0];
            for (int j = 1; j < n; j++) {
                state[i][j] = Math.max(state[i][j - 1], tempMax + prices[j]);
                tempMax = Math.max(tempMax, state[i - 1][j - 1] - prices[j]);
            }
        }
        return state[k][n - 1];
    }

    /**
     * 买卖股票的最佳时机 III - 123 （交易 2 次）可以上面方法令 k = 2
     *
     * @param prices 股票价格
     * @return
     */
    public static int maxProfit6(int[] prices) {
        if (prices == null || prices.length < 1) {
            return 0;
        }
        int d_1_0 = 0;
        int d_1_1 = Integer.MIN_VALUE;
        int d_2_0 = 0;
        int d_2_1 = Integer.MIN_VALUE;
        for (int i = 0; i < prices.length; i++) {
            d_2_0 = Math.max(d_2_0, d_2_1 + prices[i]);
            d_2_1 = Math.max(d_2_1, d_1_0 - prices[i]);
            d_1_0 = Math.max(d_1_0, d_1_1 + prices[i]);
            d_1_1 = Math.max(d_1_1, -prices[i]);
        }
        return d_2_0;
    }

    /*******************************************股票问题**********************************************/


    /**
     * 最长上升子序列 - 300
     * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
     *
     * @param nums 无需数组
     * @return
     */
    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }
        int[] arr = new int[nums.length];
        Arrays.fill(arr, 1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    arr[i] = Math.max(arr[i], arr[j] + 1);
                }
            }
        }
        int max = 1;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, arr[i]);
        }
        return max;
    }

    /**
     * 假设我们有几种不同币值的硬币 v1，v2，……，vn（单位是...1 元、3 元、5 元)支付 9 元所需要的最少硬币
     * https://www.cnblogs.com/snowInPluto/p/5992846.html
     *
     * @param money 支付总额
     * @return
     */
    public static int miniCoin(int[] coins, int money) {
        if (money < 1) {
            return 0;
        }
        int[] d = new int[money + 1];
        miniCoin(coins, money, 1, d);
        return d[money] == Integer.MAX_VALUE ? -1 : d[money];
    }

    private static void miniCoin(int[] coins, int money, int cur, int[] d) {
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            if (cur >= coin && d[cur - coin] + 1 < min) {
                min = d[cur - coin] + 1;
            }
        }
        d[cur] = min;
        if (cur < money) {
            miniCoin(coins, money, cur + 1, d);
        }
    }

    public static int miniCoin2(int[] coins, int money) {
        if (money < 1 || coins == null || coins.length < 1) {
            return -1;
        }
        int[] arr = new int[money + 1];
        Arrays.fill(arr, money + 1);
        arr[0] = 0;
        for (int i = 1; i <= money; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    arr[i] = Math.min(arr[i], arr[i - coin] + 1);
                }
            }
        }
        return arr[money] > money ? -1 : arr[money];
    }

    /**
     * 爬楼梯 - 70
     *
     * @param n 阶梯总数
     * @return 有多少种走法
     */
    public static int climbStairs(int n) {
        if (n < 1) {
            return 0;
        }
        return climbStairs(0, n);
    }

    private static int climbStairs(int i, int n) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        return climbStairs(i + 1, n) + climbStairs(i + 2, n);
    }

    public static int climbStairs1(int n) {
        if (n < 1) {
            return 0;
        }
        int[] arr = new int[n + 1];
        return climbStairs(arr, 0, n);
    }

    private static int climbStairs(int[] arr, int i, int n) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        if (arr[i] > 0) {
            return arr[i];
        }
        arr[i] = climbStairs(arr, i + 1, n) + climbStairs(arr, i + 2, n);
        return arr[i];
    }

    public static int climbStairs2(int n) {
        if (n < 1) {
            return 0;
        }
        int[] arr = new int[n + 1];
        arr[1] = 1;
        arr[2] = 2;
        for (int i = 3; i <= n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr[n];
    }

    /**
     * 最小路径和
     *
     * @param grid 网格
     * @return
     */
    public static int minPathSum(int[][] grid) {
        if (grid == null || grid.length < 1) {
            return 0;
        }
        int col = grid[0].length;
        int row = grid.length;
        int[][] state = new int[row][col];
        state[0][0] = grid[0][0];
        int sum = grid[0][0];
        for (int i = 1; i < col; i++) {
            sum += grid[0][i];
            state[0][i] = sum;
        }
        sum = grid[0][0];
        for (int i = 1; i < row; i++) {
            sum += grid[i][0];
            state[i][0] = sum;
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                state[i][j] = grid[i][j] + Math.min(state[i - 1][j], state[i][j - 1]);
            }
        }
        return state[row - 1][col - 1];
    }

    /**
     * 不同的路径 - 62
     *
     * @param m 行
     * @param n 列
     * @return
     */
    public static int uniquePaths(int m, int n) {
        if (m < 1 && n < 1) {
            return 0;
        }
        if (m == 1 || n == 1) {
            return 1;
        }
        int[][] arr = new int[m][n];
        for (int i = 0; i < m; i++) arr[i][0] = 1;
        for (int i = 0; i < n; i++) arr[0][i] = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                arr[i][j] = arr[i - 1][j] + arr[i][j - 1];
            }
        }
        return arr[m - 1][n - 1];
    }

    /**
     * 不同路径 II - 63
     *
     * @param obstacleGrid 带障碍数组
     * @return
     */
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length < 1 || obstacleGrid[0].length < 1) {
            return 0;
        }
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] arr = new int[obstacleGrid.length][n];
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                break;
            }
            arr[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 1) {
                break;
            }
            arr[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    arr[i][j] = 0;
                    continue;
                }
                boolean invalidUp = obstacleGrid[i - 1][j] == 1;
                boolean invalidLeft = obstacleGrid[i][j - 1] == 1;
                if (invalidUp && invalidLeft) {
                    arr[i][j] = 0;
                } else if (invalidLeft) {
                    arr[i][j] = arr[i - 1][j];
                } else if (invalidUp) {
                    arr[i][j] = arr[i][j - 1];
                } else {
                    arr[i][j] = arr[i - 1][j] + arr[i][j - 1];
                }
            }
        }
        return arr[m - 1][n - 1];
    }

    /**
     * 最长回文子串 - 5
     *
     * @param s 字符串
     * @return
     */
    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int maxLen = 0;
        int maxEnd = 0;
        String result;
        String reverse = new StringBuilder(s).reverse().toString();
        int[][] tab = new int[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(i) == reverse.charAt(j)) {
                    if (i == 0 || j == 0) {
                        tab[i][j] = 1;
                    } else {
                        tab[i][j] = tab[i - 1][j - 1] + 1;
                    }
                    if (tab[i][j] > maxLen) {
                        int before = reverse.length() - j - 1;
                        if (before + tab[i][j] - 1 == i) {
                            maxEnd = i;
                            maxLen = tab[i][j];
                        }
                    }
                }
            }
        }
        result = s.substring(maxEnd - maxLen + 1, maxEnd + 1);
        return result;
    }
}
