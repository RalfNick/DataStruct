package com.ralf.dp;

import java.util.Arrays;

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
        int temp = 0;
        int cur = 0;
        for (int num : nums) {
            temp = cur;
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
}
