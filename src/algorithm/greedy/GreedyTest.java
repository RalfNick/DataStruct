package algorithm.greedy;

import org.junit.Assert;
import org.junit.Test;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-06-17 下午4:43
 **/
public class GreedyTest {

    @Test
    public void removeDigits() {
        String digits = Greedy.removeKDigits("1432219", 3);
        Assert.assertEquals("1219", digits);
    }

    @Test
    public void testMaxProfit() {
        // 输入: [7,1,5,3,6,4]
        // 输出: 7
        Assert.assertEquals(7, Greedy.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
        Assert.assertEquals(7, Greedy.maxProfit1(new int[]{7, 1, 5, 3, 6, 4}));
        // 输入: [1,2,3,4,5]
        // 输出: 4
        Assert.assertEquals(4, Greedy.maxProfit(new int[]{1, 2, 3, 4, 5}));
        Assert.assertEquals(4, Greedy.maxProfit1(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void testMaxProfit1() {
        // 输入: [1, 3, 2, 8, 4, 9]
        // 输出:v8
        Assert.assertEquals(8, Greedy.maxProfit(new int[]{1, 3, 2, 8, 4, 9}, 2));
        // 输入: [1, 3, 6, 8, 4, 9]
        // 输出: 8
        Assert.assertEquals(8, Greedy.maxProfit(new int[]{1, 3, 6, 8, 4, 9}, 2));
    }

    @Test
    public void testCanCompleteCircuit() {
        int[] gas = {1, 2, 3, 4, 5};
        int[] cost = {3, 4, 5, 1, 2};
        Assert.assertEquals(3, Greedy.canCompleteCircuit1(gas, cost));
        Assert.assertEquals(3, Greedy.canCompleteCircuit1(gas, cost));

        int[] gas1 = {2, 3, 4};

        int[] cost1 = {3, 4, 3};
        Assert.assertEquals(-1, Greedy.canCompleteCircuit(gas1, cost1));
        Assert.assertEquals(-1, Greedy.canCompleteCircuit1(gas1, cost1));
    }

    @Test
    public void testRemoveDuplicateLetters() {
        // 输入: "bcabc"
        // 输出: "abc"
        Assert.assertEquals("abc", Greedy.removeDuplicateLetters("bcabc"));
        // 输入: "cbacdcbc"
        // 输出: "acdb"
        Assert.assertEquals("acdb", Greedy.removeDuplicateLetters("cbacdcbc"));
    }

    @Test
    public void testFindContentChildren() {
        // 输入: [1,2,3], [1,1]
        // 输出: 1
        Assert.assertEquals(1, Greedy.findContentChildren(new int[]{1, 2, 3}, new int[]{1, 1}));
        Assert.assertEquals(1, Greedy.findContentChildren1(new int[]{1, 2, 3}, new int[]{1, 1}));
        // 输入: [1,2], [1,2,3]
        // 输出: 2
        Assert.assertEquals(2, Greedy.findContentChildren(new int[]{1, 2}, new int[]{1, 2, 3}));
        Assert.assertEquals(2, Greedy.findContentChildren1(new int[]{1, 2}, new int[]{1, 2, 3}));
    }

    @Test
    public void testEraseOverlapIntervals() {
        // 输入: [ [1,2], [2,3], [3,4], [1,3] ]
        // 输出: 1
        Assert.assertEquals(1, Greedy.eraseOverlapIntervals(new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 3}}));
    }

    @Test

    public void testMinAddToMakeValid() {
        // 输入："())"
        // 输出：1
        Assert.assertEquals(1, Greedy.minAddToMakeValid("())"));

        // 输入："((("
        // 输出：3
        Assert.assertEquals(3, Greedy.minAddToMakeValid("((("));

        // 输入："()"
        // 输出：0
        Assert.assertEquals(0, Greedy.minAddToMakeValid("()"));

        // 输入："()))(("
        // 输出：4
        Assert.assertEquals(4, Greedy.minAddToMakeValid("()))(("));
    }

    @Test
    public void testLeastInterval() {
        // 输入: tasks = ["A","A","A","B","B","B"], n = 2
        // 输出: 8
        Assert.assertEquals(8, Greedy.leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 2));
    }

    @Test
    public void testCandy() {
        // 输入: [1,0,2]
        //v输出: 5
        Assert.assertEquals(5, Greedy.candy(new int[]{1, 0, 2}));

        // 输入: [1,2,2]
        // 输出: 4
        Assert.assertEquals(4, Greedy.candy(new int[]{1, 2, 2}));
    }
}
