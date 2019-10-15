package algorithm.back_tracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-10-11 下午7:44
 **/
public class BackTracking {

    /**
     * 组合 - 77
     * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
     *
     * @param n 整数1
     * @param k 整数2
     * @return
     */
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (n < 1 || k > n) {
            return result;
        }
        combine(result, k, new ArrayList<>(), 1, n);
        return result;
    }

    private static void combine(List<List<Integer>> result, int k, List<Integer> list, int start, int n) {
        if (list.size() == k) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i <= n; i++) {
            list.add(i);
            combine(result, k, list, i + 1, n);
            list.remove(list.size() - 1);
        }
    }

    /**
     * 组合总和 - 39
     * 给定一个无重复元素的数组 candidates 和一个目标数 target ，
     * 找出 candidates 中所有可以使数字和为 target 的组合。
     *
     * @param candidates 无重复元素数组
     * @param target     目标和
     * @return
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null || candidates.length < 1 || target < 0) {
            return result;
        }
        Arrays.sort(candidates);
        combinationSum(result, target, new ArrayList<>(), candidates, 0);
        return result;
    }

    private static void combinationSum(List<List<Integer>> result, int target, List<Integer> list, int[] candidates, int start) {
        if (0 == target) {
            result.add(new ArrayList<>(list));
        } else if (target > 0) {
            for (int i = start; i < candidates.length; i++) {
                int val = candidates[i];
                if (val > target) {
                    continue;
                }
                list.add(val);
                combinationSum(result, target - val, list, candidates, i);
                list.remove(list.size() - 1);
            }
        }
    }

    /**
     * 组II - 40
     *
     * @param candidates 数组
     * @param target     目标和
     * @return
     */
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (target < 1 || candidates == null || candidates.length < 1) {
            return result;
        }
        Arrays.sort(candidates);
        combineSum2(result, target, new ArrayList<>(), candidates, 0);
        return result;
    }

    private static void combineSum2(List<List<Integer>> result, int target, List<Integer> list, int[] candidates, int start) {
        if (0 == target) {
            result.add(new ArrayList<>(list));
        } else if (target > 0) {
            for (int i = start; i < candidates.length; i++) {
                if (candidates[i] > target || (i > start && candidates[i - 1] == candidates[i])) {
                    continue;
                }
                list.add(candidates[i]);
                combineSum2(result, target - candidates[i], list, candidates, i + 1);
                list.remove(list.size() - 1);
            }
        }
    }

    /**
     * 全排列 - 46
     *
     * @param nums 数组 (数字无重复)
     * @return
     */
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 1) {
            return result;
        }
        int[] visited = new int[nums.length];
        permute(result, new ArrayList<>(), nums, visited);
        return result;
    }

    private static void permute(List<List<Integer>> result, List<Integer> list, int[] nums, int[] visited) {
        if (list.size() == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] == 1) continue;
            visited[i] = 1;
            list.add(nums[i]);
            permute(result, list, nums, visited);
            visited[i] = 0;
            list.remove(list.size() - 1);
        }
    }

    /**
     * 全排列 II - 47
     *
     * @param nums 数组 (数字可能重复)
     * @return
     */
    public static List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 1) {
            return result;
        }
        Arrays.sort(nums);
        int[] visited = new int[nums.length];
        permute2(result, new ArrayList<>(), nums, visited);
        return result;
    }

    private static void permute2(List<List<Integer>> result, List<Integer> list, int[] nums, int[] visited) {
        if (list.size() == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] == 1) continue;
            if (i > 0 && nums[i] == nums[i - 1] && visited[i - 1] == 0) continue;
            visited[i] = 1;
            list.add(nums[i]);
            permute2(result, list, nums, visited);
            visited[i] = 0;
            list.remove(list.size() - 1);
        }
    }

    private static int[] sRow;

    /**
     * N 皇后 - 51
     *
     * @param n 数字n
     * @return
     */
    public static List<List<String>> solveNQueens(int n) {
        sRow = new int[n];
        for (int i = 0; i < n; i++) {
            sRow[i] = -1;
        }
        List<List<String>> list = new ArrayList<>();
        solveNQueens(list, 0, n);
        return list;
    }

    private static void solveNQueens(List<List<String>> list, int row, int n) {
        if (row == n) {
            list.add(printQueues());
            return;
        }
        for (int column = 0; column < n; column++) {
            if (isValid(row, column, n)) {
                sRow[row] = column;
                solveNQueens(list, row + 1, n);
            }
        }
    }

    private static boolean isValid(int row, int column, int n) {
        int leftUp = column - 1;
        int rightUp = column + 1;
        for (int i = row - 1; i >= 0; i--) {
            if (sRow[i] == column) {
                return false;
            }
            if (leftUp >= 0 && sRow[i] == leftUp) {
                return false;
            }
            if (rightUp < n && sRow[i] == rightUp) {
                return false;
            }
            leftUp--;
            rightUp++;
        }
        return true;
    }

    private static List<String> printQueues() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < sRow.length; i++) {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < sRow.length; j++) {
                row.append(sRow[i] == j ? "Q" : ".");
            }
            list.add(row.toString());
        }
        return list;
    }
}
