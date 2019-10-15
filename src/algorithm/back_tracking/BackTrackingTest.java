package algorithm.back_tracking;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-10-11 下午7:44
 **/
public class BackTrackingTest {

    @Test
    public void testCombine() {
        List<List<Integer>> lists = BackTracking.combine(4, 2);
        Assert.assertEquals(6, lists.size());
    }

    @Test
    public void testCombinationSum() {
        // candidates = [2,3,6,7], target = 7,
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        List<List<Integer>> lists = BackTracking.combinationSum(candidates, target);
        Assert.assertEquals(2, lists.size());

        // candidates = [2,3,5], target = 8,
        int[] candidates1 = {2, 3, 5};
        int target1 = 8;
        List<List<Integer>> lists1 = BackTracking.combinationSum(candidates1, target1);
        Assert.assertEquals(3, lists1.size());
    }

    @Test
    public void testCombinationSum2() {
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        List<List<Integer>> lists = BackTracking.combinationSum2(candidates, target);
        Assert.assertEquals(4, lists.size());
    }

    @Test
    public void testSolveNQueens() {
        List<List<String>> lists = BackTracking.solveNQueens(4);
        Assert.assertEquals(2, lists.size());
    }

    @Test
    public void testPermute() {
        int[] arr = {1, 2, 3};
        List<List<Integer>> permute = BackTracking.permute(arr);
        Assert.assertEquals(6, permute.size());
    }

    @Test
    public void testPermute2() {
        int[] arr = {1, 1, 2};
        List<List<Integer>> permute = BackTracking.permute2(arr);
        Assert.assertEquals(3, permute.size());
    }
}
