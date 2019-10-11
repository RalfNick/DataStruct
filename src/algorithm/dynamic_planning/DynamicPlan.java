package algorithm.dynamic_planning;

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
        return 0;
    }
}
