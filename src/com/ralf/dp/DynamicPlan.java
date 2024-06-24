package com.ralf.dp;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DynamicPlan {

    /**
     * 96. 不同的二叉搜索树
     * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
     * <a href="https://leetcode.cn/problems/unique-binary-search-trees/?envType=list&envId=Lkxop8fK">Leet Code</a>
     */
    static int numTrees(int n) {
        if (n < 1) return 0;
        int[] G = new int[n + 1];
        G[0] = 1;
        G[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                G[i] += G[j - 1] * G[i - j];
            }
        }
        return G[n];
    }

    /**
     * 70. 爬楼梯
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * <a href="https://leetcode.cn/problems/climbing-stairs/description/?envType=list&envId=Lkxop8fK">Leet Code</a>
     */
    static int climbStairs(int n) {
        if (n < 1) {
            return 0;
        }
        int a, b = 0, r = 1;
        for (int i = 0; i < n; i++) {
            a = b;
            b = r;
            r = a + b;
        }
        return r;
    }

    static int climbStairs1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
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
     * 746. 使用最小花费爬楼梯
     */
    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length + 1];
        dp[0] = dp[1] = 0;
        for (int i = 2; i <= cost.length; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[cost.length];
    }

    public int minCostClimbingStairs2(int[] cost) {
        int pre = 0;
        int cur = 0;
        for (int i = 2; i <= cost.length; i++) {
            int temp = Math.min(cur + cost[i - 1], pre + cost[i - 2]);
            pre = cur;
            cur = temp;
        }
        return cur;
    }

    /**
     * 509. 斐波那契数
     * 斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
     * F(0) = 0，F(1) = 1
     * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
     * 给定 n ，请计算 F(n) 。
     * <a href="https://leetcode.cn/problems/fibonacci-number/description/?envType=study-plan-v2&envId=dynamic-programming">Leet Code</a>
     */
    static int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int a = 0;
        int b = 1;
        int res = 1;
        for (int i = 2; i <= n; i++) {
            res = a + b;
            a = b;
            b = res;
        }
        return res;
    }

    /**
     * 121. 买卖股票的最佳时机
     * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
     * <a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/description/?envType=list&envId=Lkxop8fK">Leet Code</a>
     */
    static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int dp_i_0 = 0;
        int dp_i_1 = Integer.MIN_VALUE;
        for (int price : prices) {
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + price);
            dp_i_1 = Math.max(dp_i_1, -price);
        }
        return dp_i_0;
    }

    /**
     * 122. 买卖股票的最佳时机 II
     * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
     * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
     * 返回 你能获得的 最大 利润 。
     * <a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/description/?envType=list&envId=Lkxop8fK">Leet Code</a>
     */
    static int maxProfitII(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int dp_i_0 = 0;
        int dp_i_1 = Integer.MIN_VALUE;
        for (int price : prices) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + price);
            dp_i_1 = Math.max(dp_i_1, temp - price);
        }
        return dp_i_0;
    }

    /**
     * 714. 买卖股票的最佳时机含手续费
     * 给定一个整数数组 prices，其中 prices[i]表示第 i 天的股票价格 ；整数 fee 代表了交易股票的手续费用。
     * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
     * 返回获得利润的最大值。
     * <a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/description/">Leet Code</a>
     */
    static int maxProfitWithFee(int[] prices, int fee) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int dp_i_0 = 0;
        int dp_i_1 = Integer.MIN_VALUE;
        for (int price : prices) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + price);
            dp_i_1 = Math.max(dp_i_1, temp - price - fee);
        }
        return dp_i_0;
    }

    /**
     * 309. 买卖股票的最佳时机含冷冻期
     * 给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格 。​
     * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
     * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
     * <a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-cooldown/description/?envType=list&envId=Lkxop8fK">Leet Code</a>
     */
    static int maxProfitWithFreezing(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int dp_i_0 = 0;
        int dp_i_1 = Integer.MIN_VALUE;
        int dp_pre = 0;
        for (int price : prices) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + price);
            dp_i_1 = Math.max(dp_i_1, dp_pre - price);
            dp_pre = temp;
        }
        return dp_i_0;
    }

    /**
     * 123. 买卖股票的最佳时机 III
     * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * <a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iii/description/?envType=list&envId=Lkxop8f">Leet Code</a>
     */
    static int maxProfitIII(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int d_1_0 = 0;
        int d_1_1 = Integer.MIN_VALUE;
        int d_2_0 = 0;
        int d_2_1 = Integer.MIN_VALUE;
        for (int price : prices) {
            d_2_0 = Math.max(d_2_0, d_2_1 + price);
            d_2_1 = Math.max(d_2_1, d_1_0 - price);
            d_1_0 = Math.max(d_1_0, d_1_1 + price);
            d_1_1 = Math.max(d_1_1, -price);
        }
        return d_2_0;
    }

    /**
     * 188. 买卖股票的最佳时机 IV
     * 给你一个整数数组 prices 和一个整数 k ，其中 prices[i] 是某支给定的股票在第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。也就是说，你最多可以买 k 次，卖 k 次。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * <a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iv/description/?envType=list&envId=Lkxop8fK">Leet Code</a>
     */
    static int maxProfitIV(int[] prices, int k) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int length = prices.length;
        if (k == 1) {
            return maxProfit(prices);
        } else if (k > length / 2) {
            return maxProfitII(prices);
        }
        int[][][] dp = new int[length][k + 1][2];
        // k = 0 base case
        for (int i = 0; i < length; i++) {
            dp[i][0][0] = 0;
            dp[i][0][1] = Integer.MIN_VALUE;
        }
        for (int i = 0; i < length; i++) {
            for (int j = k; j >= 1; j--) {
                if (i == 0) {
                    dp[i][j][0] = 0;
                    dp[i][j][1] = -prices[i];
                    continue;
                }
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }
        return dp[length - 1][k][0];
    }

    static int maxProfitIV2(int[] prices, int k) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int length = prices.length;
        k = Math.min(length / 2, k);
        int[][] state = new int[k + 1][length];
        for (int i = 1; i <= k; i++) {
            int tempMax = -prices[0];
            for (int j = 1; j < length; j++) {
                state[i][j] = Math.max(state[i][j - 1], tempMax + prices[j]);
                tempMax = Math.max(tempMax, state[i - 1][j - 1] - prices[j]);
            }
        }
        return state[k][length - 1];
    }


    /**
     * 322. 零钱兑换
     * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
     * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
     * 你可以认为每种硬币的数量是无限的。
     * <a href="https://leetcode.cn/problems/coin-change/?envType=list&envId=Lkxop8fK">Leet Code</a>
     */
    static int coinChange(int[] coins, int amount) {
        int[] arr = new int[amount + 1];
        Arrays.fill(arr, amount + 1);
        arr[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    arr[i] = Math.min(arr[i], arr[i - coin] + 1);
                }
            }
        }
        return arr[amount] > amount ? -1 : arr[amount];
    }

    /**
     * 518. 零钱兑换 II
     * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
     * 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
     * 假设每一种面额的硬币有无限个。
     * <a href="https://leetcode.cn/problems/coin-change-ii/description/?envType=list&envId=Lkxop8fK">Leet Code</a>
     */
    static int change(int amount, int[] coins) {
        int[] arr = new int[amount + 1];
        arr[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                arr[i] += arr[i - coin];
            }
        }
        return arr[amount];
    }

    /**
     * 377. 组合总和 Ⅳ
     * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。
     * <a href="https://leetcode.cn/problems/combination-sum-iv/solutions/740581/zu-he-zong-he-iv-by-leetcode-solution-q8zv/?envType=list&envId=Lkxop8fK">Leet Code</a>
     */
    static int combinationSum4(int[] nums, int target) {
        int[] arr = new int[target + 1];
        arr[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (num <= i) {
                    arr[i] += arr[i - num];
                }
            }
        }
        return arr[target];
    }

    /**
     * 62. 不同路径
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
     * 问总共有多少条不同的路径？
     * <a href="https://leetcode.cn/problems/unique-paths/description/?envType=list&envId=Lkxop8fK">Leet Code</a>
     * <a href="https://leetcode.cn/problems/combination-sum-iv/solutions/124393/xi-wang-yong-yi-chong-gui-lu-gao-ding-bei-bao-wen-/?envType=list&envId=Lkxop8fK">解法</a>
     */
    public static int uniquePaths(int m, int n) {
        int[][] paths = new int[m][n];
        for (int i = 0; i < m; i++) paths[i][0] = 1;
        for (int i = 0; i < n; i++) paths[0][i] = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                paths[i][j] = paths[i - 1][j] + paths[i][j - 1];
            }
        }
        return paths[m - 1][n - 1];
    }

    /**
     * 63. 不同路径 II
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish”）。
     * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
     * 网格中的障碍物和空位置分别用 1 和 0 来表示。
     * <a href="https://leetcode.cn/problems/unique-paths-ii/description/">Leet Code</a>
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length < 1 || obstacleGrid[0].length < 1) {
            return 0;
        }
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        final int[][] paths = new int[m][n];
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                break;
            }
            paths[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 1) {
                break;
            }
            paths[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    continue;
                }
                if (obstacleGrid[i - 1][j] == 0) {
                    paths[i][j] += paths[i - 1][j];
                }
                if (obstacleGrid[i][j - 1] == 0) {
                    paths[i][j] += paths[i][j - 1];
                }
            }
        }
        return paths[m - 1][n - 1];
    }

    /**
     * 64. 最小路径和
     * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
     * 说明：每次只能向下或者向右移动一步。
     * <a href="https://leetcode.cn/problems/minimum-path-sum/description/">Leet Code</a>
     */
    static int minPathSum(int[][] grid) {
        if (grid == null || grid.length < 1 || grid[0].length < 1) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int[][] state = new int[m][n];
        int sum = 0;
        for (int i = 0; i < m; i++) {
            sum += grid[i][0];
            state[i][0] = sum;
        }
        sum = 0;
        for (int i = 0; i < n; i++) {
            sum += grid[0][i];
            state[0][i] = sum;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                state[i][j] = Math.min(state[i - 1][j], state[i][j - 1]) + grid[i][j];
            }
        }
        return state[m - 1][n - 1];
    }

    /**
     * 198. 打家劫舍
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     * <a href="https://leetcode.cn/problems/house-robber/description/?envType=list&envId=Lkxop8fK">Leet Code</a>
     */
    static int rob(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int length = nums.length;
        int[] sum = new int[length + 1];
        sum[0] = 0;
        sum[1] = nums[0];
        for (int i = 2; i <= length; i++) {
            sum[i] = Math.max(sum[i - 1], sum[i - 2] + nums[i - 1]);
        }
        return sum[length];
    }

    static int rob2(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int pre = 0;
        int cur = 0;
        for (int num : nums) {
            int temp = cur;
            cur = Math.max(pre + num, cur);
            pre = temp;
        }
        return cur;
    }

    /**
     * 213. 打家劫舍 II
     * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
     * <a href="https://leetcode.cn/problems/house-robber-ii/description/?envType=list&envId=Lkxop8fK">Leet Code</a>
     */
    static int robII(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int length = nums.length;
        int[] nums1 = Arrays.copyOfRange(nums, 0, length - 1);
        int[] nums2 = Arrays.copyOfRange(nums, 1, length);
        return Math.max(rob2(nums1), rob2(nums2));
    }

    /**
     * 53. 最大子数组和
     * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * 子数组 是数组中的一个连续部分。
     * <a href="https://leetcode.cn/problems/maximum-subarray/description/">Leet Code</a>
     */
    static int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] > 0) {
                dp[i] = dp[i - 1] + nums[i];
            } else {
                dp[i] = nums[i];
            }
        }
        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(dp[i], max);
        }
        return max;
    }

    static int maxSubArray2(int[] nums) {
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

    /**
     * 5. 最长回文子串
     * 给你一个字符串 s，找到 s 中最长的回文子串。
     * 如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。
     * <a href="https://leetcode.cn/problems/longest-palindromic-substring/description/">Leet Code</a>
     */
    static String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    /**
     * 516. 最长回文子序列
     */
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int n = s.length();
        char[] chars = s.toCharArray();
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (chars[i] == chars[j]) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }

    /**
     * 279. 完全平方数
     * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
     * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
     * <a href="https://leetcode.cn/problems/perfect-squares/?envType=list&envId=Lkxop8fK">Leet Code</a>
     */
    public int numSquares(int n) {
        int[] res = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                min = Math.min(min, res[i - j * j]);
            }
            res[i] = min + 1;
        }
        return res[n];
    }

    /**
     * 221. 最大正方形
     * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
     * <a href="https://leetcode.cn/problems/maximal-square/description/?envType=study-plan-v2&envId=dynamic-programming">Leet Code</a>
     */
    static int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int maxSlide = 0;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                }
                maxSlide = Math.max(maxSlide, dp[i][j]);
            }
        }
        return maxSlide * maxSlide;
    }

    /**
     * 1143. 最长公共子序列
     * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
     * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
     * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
     * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
     * <a href="https://leetcode.cn/problems/longest-common-subsequence/description/?envType=study-plan-v2&envId=dynamic-programming">Leet Code</a>
     */
    static int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text1.isEmpty() || text2 == null || text2.isEmpty()) {
            return 0;
        }
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            char ch1 = text1.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                char ch2 = text2.charAt(j - 1);
                if (ch1 == ch2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 最长公共子序列 求字符串而不是长度
     */
    static String longestCommonSubsequenceII(String str1, String str2) {
        if (str1 == null || str1.isEmpty() || str2 == null || str2.isEmpty()) {
            return "";
        }
        int m = str1.length();
        int n = str2.length();
        int index = 0;
        int maxLength = 0;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            char ch1 = str1.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                char ch2 = str2.charAt(j - 1);
                if (ch1 == ch2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = 0;
                }
                if (dp[i][j] > maxLength) {
                    index = i - 1;
                    maxLength = dp[i][j];
                }
            }
        }
        return str1.substring(index - maxLength + 1, index + 1);
    }

    /**
     * 0-1 背包问题
     * 有一个容量为 N 的背包，要用这个背包装下物品的价值最大，这些物品有两个属性：体积 w 和价值 v。
     * 定义一个二维数组 dp 存储最大价值，其中 dp[i][j] 表示前 i 件物品体积不超过 j 的情况下能达到的最大价值。设第 i 件物品体积为 w，价值为 v，根据第 i 件物品是否添加到背包中，可以分两种情况讨论：
     * 第 i 件物品没添加到背包，总体积不超过 j 的前 i 件物品的最大价值就是总体积不超过 j 的前 i-1 件物品的最大价值，dp[i][j] = dp[i-1][j]。
     * 第 i 件物品添加到背包中，dp[i][j] = dp[i-1][j-w] + v。
     * 第 i 件物品可添加也可以不添加，取决于哪种情况下最大价值更大。因此，0-1 背包的状态转移方程为：
     * dp[i][j] = max(dp[i-1][j], dp[i-1][j-w] + v)
     *
     * @param W       最大体积
     * @param N       个数
     * @param weights 重量
     * @param values  价值
     */
    static int knapsack(int W, int N, int[] weights, int[] values) {
        int[][] dp = new int[N + 1][W + 1];
        for (int i = 1; i <= N; i++) {
            int weight = weights[i - 1];
            int v = values[i - 1];
            for (int j = 1; j <= W; j++) {
                if (j >= weight) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight] + v);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[N][W];
    }

    static int knapsack2(int W, int N, int[] weights, int[] values) {
        int[] dp = new int[W + 1];
        for (int i = 1; i <= N; i++) {
            int weight = weights[i - 1];
            int v = values[i - 1];
            for (int j = W; j >= 1; j--) {
                if (j >= weight) {
                    dp[j] = Math.max(dp[j], dp[j - weight] + v);
                }
            }
        }
        return dp[W];
    }

    /**
     * 494. 目标和
     * 给你一个非负整数数组 nums 和一个整数 target 。
     * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
     * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
     * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
     * <a href="https://leetcode.cn/problems/target-sum/description/">Leet Code</a>
     */
    static int findTargetSumWays(int[] nums, int target) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int diff = sum - target;
        if (diff < 0 || diff % 2 != 0) {
            return 0;
        }
        int neg = diff / 2;
        int n = nums.length;
        // 方法一：二维数组
//        int[][] dp = new int[n + 1][neg + 1];
//        dp[0][0] =1;
//        for (int i = 0; i < n; i++) {
//            int temp = nums[i];
//            for (int j = 0; j <= neg; j++) {
//                if (j >= temp) {
//                    dp[i][j] += dp[i - 1][j - temp];
//                }
//            }
//        }
//        return dp[n][neg];

        // 方法二：一维数组
        int[] dp = new int[neg + 1];
        dp[0] = 1;
        for (int num : nums) {
            for (int j = neg; j >= num; j--) {
                dp[j] += dp[j - num];
            }
        }
        return dp[neg];
    }

    /**
     * 139. 单词拆分
     * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true。
     * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
     * <a href="https://leetcode.cn/problems/word-break/description/?envType=study-plan-v2&envId=top-interview-150">LeetCode</a>
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.isEmpty() || wordDict == null || wordDict.isEmpty()) {
            return false;
        }
        Set<String> set = new HashSet<>(wordDict);
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }

    /**
     * 1746. 经过一次操作后的最大子数组和
     * 你有一个整数数组 nums。你只能将一个元素 nums[i] 替换为 nums[i] * nums[i]。
     * 返回替换后的最大子数组和。
     */
    public static int maxSumAfterOperation(int[] nums) {
        int sum = 0;
        int dp1 = 0;
        int dp2 = 0;
        for (int num : nums) {
            dp1 = Math.max(dp2 + num * num, dp1 + num);
            dp2 = Math.max(dp2 + num, 0);
            sum = Math.max(sum, dp1);
        }
        return sum;
    }

    /**
     * 718. 最长重复子数组
     * 给两个整数数组 nums1 和 nums2 ，返回 两个数组中 公共的 、长度最长的子数组的长度 。
     */
    public int findLength(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length < 1 || nums2 == null || nums2.length < 1) {
            return 0;
        }
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[][] dp = new int[len1 + 1][len2 + 1];
        int res = 0;
        for (int i = len1 - 1; i >= 0; i--) {
            int num = nums1[i];
            for (int j = len2 - 1; j >= 0; j--) {
                if (num == nums2[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i + 1][j + 1] + 1);
                }
                res = Math.max(dp[i][j], res);
            }
        }
        return res;
    }

    public int findLength2(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length < 1 || nums2 == null || nums2.length < 1) {
            return 0;
        }
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[][] dp = new int[len1 + 1][len2 + 1];
        int res = 0;
        for (int i = 1; i <= len1; i++) {
            int num = nums1[i - 1];
            for (int j = 1; j <= len2; j++) {
                if (num == nums2[j - 1]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
                res = Math.max(dp[i][j], res);
            }
        }
        return res;
    }

    /**
     * 583. 两个字符串的删除操作
     */
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++)
            dp[i][0] = i;
        for (int i = 0; i <= len2; i++)
            dp[0][i] = i;
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + 1;
                }
            }
        }
        return dp[len1][len2];
    }

    /**
     * 72. 编辑距离
     */
    public int minDistance1(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++) dp[i][0] = i;
        for (int i = 0; i <= len2; i++) dp[0][i] = i;
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                }
            }
        }
        return dp[len1][len2];
    }

    /**
     * LCR 161. 连续天数的最高销售额
     */
    public int maxSales(int[] sales) {
        if (sales == null || sales.length < 1) {
            return 0;
        }
        int length = sales.length;
        int[] dp = new int[length + 1];
        dp[0] = sales[0];
        int res = dp[0];
        for (int i = 1; i < length; i++) {
            if (dp[i - 1] > 0) {
                dp[i] = dp[i - 1] + sales[i];
            } else {
                dp[i] = sales[i];
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public int maxSales2(int[] sales) {
        if (sales == null || sales.length < 1) {
            return 0;
        }
        int length = sales.length;
        int res = sales[0];
        int pre = sales[0];
        for (int i = 1; i < length; i++) {
            pre = Math.max(sales[i], pre + sales[i]);
            res = Math.max(res, pre);
        }
        return res;
    }

    /**
     * 152. 乘积最大子数组
     */
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int res = Integer.MIN_VALUE;
        int max = 1;
        int min = 1;
        for (int num : nums) {
            if (num < 0) {
                int temp = min;
                min = max;
                max = temp;
            }
            max = Math.max(num, num * max);
            min = Math.min(num, num * min);
            res = Math.max(res, max);
        }
        return res;
    }

    /**
     * 300. 最长递增子序列
     * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
     * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
     * <a href="https://leetcode.cn/problems/longest-increasing-subsequence/description/">Leet Code</a>
     */
    static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     * 435. 无重叠区间
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length < 1 || intervals[0].length < 1) {
            return 0;
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        int length = intervals.length;
        int count = 1;
        int right = intervals[0][1];
        for (int i = 1; i < length; i++) {
            if (intervals[i][0] >= right) {
                count++;
                right = intervals[i][1];
            }
        }
        return length - count;
    }

    /**
     * 435. 无重叠区间
     */
    public int eraseOverlapIntervals2(int[][] intervals) {
        if (intervals == null || intervals.length < 1 || intervals[0].length < 1) {
            return 0;
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int length = intervals.length;
        int[] dp = new int[length];
        dp[0] = 1;
        int count = 1;
        for (int i = 1; i < length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (intervals[j][1] <= intervals[i][0]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                count = Math.max(count, dp[i]);
            }
        }
        return length - count;
    }


    /**
     * 646. 最长数对链
     */
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] == o2[1] ? o1[0] - o2[0] : o1[1] - o2[1];
            }
        });
        int count = 1;
        int right = pairs[0][1];
        for (int i = 1; i < pairs.length; i++) {
            if (pairs[i][0] > right) {
                count++;
                right = pairs[i][1];
            }
        }
        return count;
    }

    /**
     * 452. 用最少数量的箭引爆气球
     */
    public static int findMinArrowShots(int[][] points) {
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] > o2[1] ? 1 : o1[1] < o2[1] ? -1 : 0;
            }
        });
        int count = 1;
        int right = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > right) {
                count++;
                right = points[i][1];
            }
        }
        return count;
    }

    /**
     * 354. 俄罗斯套娃信封问题
     */
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length < 1 || envelopes[0].length < 1) {
            return 0;
        }
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0];
            }
        });
        int length = envelopes.length;
        int[] dp = new int[length];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }

    /**
     * 面试题 08.13. 堆箱子
     */
    public static int pileBox(int[][] box) {
        Arrays.sort(box, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] == o2[1] ? o1[2] - o2[2] : o1[1] - o2[1];
            }
        });
        int length = box.length;
        int[] dp = new int[length];
        for (int i = 0; i < length; i++) {
            dp[i] = box[i][2];
        }
        int max = dp[0];
        for (int i = 1; i < length; i++) {
            for (int j = 0; j < i; j++) {
                if (box[i][0] > box[j][0] && box[i][1] > box[j][1] && box[i][2] > box[j][2]) {
                    dp[i] = Math.max(dp[i], dp[j] + box[i][2]);
                }
                max = Math.max(dp[i], max);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] arr = {{3, 3, 8}, {3, 6, 5}, {7, 1, 6}};
        int count = pileBox(arr);
        System.out.printf("pileBox count " + count);
    }
}















