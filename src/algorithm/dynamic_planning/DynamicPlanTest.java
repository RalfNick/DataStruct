package algorithm.dynamic_planning;

import org.junit.Assert;
import org.junit.Test;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-09-25 下午9:41
 **/
public class DynamicPlanTest {

    @Test
    public void testMaxSubArray() {
        // 输入: [-2,1,-3,4,-1,2,1,-5,4],
        // 输出: 6
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        Assert.assertEquals(6, DynamicPlan.maxSubArray(arr));
    }

    @Test
    public void testMaxProfit1() {
        // 输入: [7,1,5,3,6,4]
        // 输出: 5
        Assert.assertEquals(5, DynamicPlan.maxProfit1(new int[]{7, 1, 5, 3, 6, 4}));

        // 输入: [7,6,4,3,1]
        // 输出: 0
        Assert.assertEquals(0, DynamicPlan.maxProfit1(new int[]{7, 6, 4, 3, 1}));
    }

    @Test
    public void testMaxProfit2() {
        // 输入: [7,1,5,3,6,4]
        // 输出: 7
        Assert.assertEquals(7, DynamicPlan.maxProfit2(new int[]{7, 1, 5, 3, 6, 4}));

        // 输入: [1,2,3,4,5]
        // 输出: 4
        Assert.assertEquals(4, DynamicPlan.maxProfit2(new int[]{1, 2, 3, 4, 5}));

        // 输入: [7,6,4,3,1]
        // 输出: 0
        Assert.assertEquals(0, DynamicPlan.maxProfit2(new int[]{7, 6, 4, 3, 1}));
    }

    @Test
    public void testMaxProfit3() {
        // 输入: [1,2,3,0,2]
        // 输出: 3
        Assert.assertEquals(3, DynamicPlan.maxProfit3(new int[]{1, 2, 3, 0, 2}));
    }

    @Test
    public void testMaxProfit4() {
        // 输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
        // 输出: 8
        Assert.assertEquals(8, DynamicPlan.maxProfit4(new int[]{1, 3, 2, 8, 4, 9}, 2));
    }

    @Test
    public void testMaxProfit5() {
        // 输入: [2,4,1], k = 2
        // 输出: 2
        Assert.assertEquals(2, DynamicPlan.maxProfit5(new int[]{2, 4, 1}, 2));
        Assert.assertEquals(2, DynamicPlan.maxProfit55(new int[]{2, 4, 1}, 2));
        // 输入: [3,2,6,5,0,3], k = 2
        // 输出: 7
        Assert.assertEquals(7, DynamicPlan.maxProfit5(new int[]{3, 2, 6, 5, 0, 3}, 2));
        Assert.assertEquals(7, DynamicPlan.maxProfit55(new int[]{3, 2, 6, 5, 0, 3}, 2));

    }

    @Test
    public void testMaxProfit6() {
        // 输入: [3,3,5,0,0,3,1,4]
        // 输出: 6
        Assert.assertEquals(6, DynamicPlan.maxProfit6(new int[]{3, 3, 5, 0, 0, 3, 1, 4}));
        Assert.assertEquals(6, DynamicPlan.maxProfit55(new int[]{3, 3, 5, 0, 0, 3, 1, 4}, 2));

        // 输入: [1,2,3,4,5]
        // 输出: 4
        Assert.assertEquals(4, DynamicPlan.maxProfit6(new int[]{1, 2, 3, 4, 5}));
        Assert.assertEquals(4, DynamicPlan.maxProfit55(new int[]{1, 2, 3, 4, 5}, 2));

        // 输入: [7,6,4,3,1]
        // 输出: 0
        Assert.assertEquals(0, DynamicPlan.maxProfit6(new int[]{7, 6, 4, 3, 1}));
        Assert.assertEquals(0, DynamicPlan.maxProfit55(new int[]{7, 6, 4, 3, 1}, 2));
    }

    @Test
    public void testLengthOfLIS() {
        int[] arr = {10, 9, 2, 5, 3, 7, 101, 18};
        Assert.assertEquals(4, DynamicPlan.lengthOfLIS(arr));
    }

    @Test
    public void testMiniCoin() {
        int[] coins = {1, 3, 5};
        Assert.assertEquals(3, DynamicPlan.miniCoin(coins, 11));
        Assert.assertEquals(3, DynamicPlan.miniCoin(coins, 9));
        Assert.assertEquals(3, DynamicPlan.miniCoin2(coins, 11));
        Assert.assertEquals(3, DynamicPlan.miniCoin2(coins, 9));
    }

    @Test
    public void testMinPathSum() {
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        Assert.assertEquals(7, DynamicPlan.minPathSum(grid));
    }

    @Test
    public void testUniquePaths() {
        Assert.assertEquals(3, DynamicPlan.uniquePaths(3, 2));
        Assert.assertEquals(28, DynamicPlan.uniquePaths(7, 3));
    }

    @Test
    public void testUniquePathsWithObstacles() {
        int[][] arr = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        Assert.assertEquals(2, DynamicPlan.uniquePathsWithObstacles(arr));
    }
}