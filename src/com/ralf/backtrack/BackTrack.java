package com.ralf.backtrack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * 回溯
 */
public class BackTrack {

    /**
     * 46. 全排列
     * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
     * <a href="https://leetcode.cn/problems/permutations/description/?envType=list&envId=hU60vjRS">Leet Code</a>
     */
    static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 1) {
            return result;
        }
        Deque<Integer> path = new ArrayDeque<>(nums.length);
        boolean[] visited = new boolean[nums.length];
        permute(nums, visited, path, result);
        return result;
    }

    private static void permute(int[] nums, boolean[] visited, Deque<Integer> path, List<List<Integer>> result) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            path.add(nums[i]);
            permute(nums, visited, path, result);
            visited[i] = false;
            path.remove(nums[i]);
        }
    }

    /**
     * 47. 全排列II
     * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
     * <a href="https://leetcode.cn/problems/permutations/description/?envType=list&envId=hU60vjRS">Leet Code</a>
     */
    static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 1) {
            return result;
        }
        Arrays.sort(nums);
        List<Integer> path = new ArrayList<>(nums.length);
        boolean[] visited = new boolean[nums.length];
        permuteUnique(nums, nums.length, visited, path, result);
        return result;
    }

    private static void permuteUnique(int[] nums, int len, boolean[] visited, List<Integer> path, List<List<Integer>> result) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < len; i++) {
            if (visited[i]) {
                continue;
            }
            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                continue;
            }
            visited[i] = true;
            path.add(nums[i]);
            permuteUnique(nums, len, visited, path, result);
            visited[i] = false;
            path.remove(path.size() - 1);
        }
    }

    /**
     * 39. 组合总和
     * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
     * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
     * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
     * <a href="https://leetcode.cn/problems/combination-sum/description/">Leet Code</a>
     */
    static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null || candidates.length < 1) {
            return result;
        }
        Arrays.sort(candidates);
        List<Integer> path = new ArrayList<>(candidates.length);
        combinationSum(candidates, candidates.length, 0, target, path, result);
        return result;
    }

    private static void combinationSum(int[] candidates, int len, int begin, int target, List<Integer> path, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = begin; i < len; i++) {
            if (target - candidates[i] < 0) {
                break;
            }
            path.add(candidates[i]);
            combinationSum(candidates, len, i, target - candidates[i], path, result);
            path.remove(path.size() - 1);
        }
    }

    /**
     * 40. 组合总和 II
     * 给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * candidates 中的每个数字在每个组合中只能使用 一次 。
     * 注意：解集不能包含重复的组合。
     * <a href="https://leetcode.cn/problems/combination-sum-ii/description/">Leet Code</a>
     */
    static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null || candidates.length < 1) {
            return result;
        }
        Arrays.sort(candidates);
        List<Integer> path = new ArrayList<>(candidates.length);
        combinationSum2(candidates, candidates.length, 0, target, path, result);
        return result;
    }

    private static void combinationSum2(int[] candidates, int len, int begin, int target, List<Integer> path, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = begin; i < len; i++) {
            if (target - candidates[i] < 0) {
                break;
            }
            if (i > begin && candidates[i] == candidates[i - 1]) {
                continue;
            }
            path.add(candidates[i]);
            combinationSum2(candidates, len, i + 1, target - candidates[i], path, result);
            path.remove(path.size() - 1);
        }
    }

    /**
     * 77. 组合
     * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
     * 你可以按 任何顺序 返回答案。
     * <a href="https://leetcode.cn/problems/combinations/description/">Leet Code</a>
     */
    static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (k <= 0 || n < k) {
            return result;
        }
        List<Integer> path = new ArrayList<>(k);
        combine(n, 1, k, path, result);
        return result;
    }

    static void combine(int n, int begin, int k, List<Integer> path, List<List<Integer>> result) {
        if (k == path.size()) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = begin; i <= n; i++) {
            path.add(i);
            combine(n, i + 1, k, path, result);
            path.remove(path.size() - 1);
        }
    }

    /**
     * 78. 子集
     * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
     * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
     * <a href="https://leetcode.cn/problems/subsets/">Leet Code</a>
     */
    static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums == null || nums.length < 1) {
            return list;
        }
        subsets(nums, 0, nums.length, new ArrayList<>(), list);
        return list;
    }

    private static void subsets(int[] nums, int index, int length, ArrayList<Integer> path, List<List<Integer>> list) {
        list.add(new ArrayList<>(path));
        if (index >= length) {
            return;
        }
        for (int i = index; i < length; i++) {
            path.add(nums[i]);
            subsets(nums, i + 1, length, path, list);
            path.remove(path.size() - 1);
        }
    }

    /**
     * 90. 子集 II
     * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
     * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
     * <a href="https://leetcode.cn/problems/subsets-ii/description/">Leet Code</a>
     */
    static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums == null || nums.length < 1) {
            return list;
        }
        Arrays.sort(nums);
        subsetsWithDup(nums, 0, nums.length, new ArrayList<>(), list);
        return list;
    }

    private static void subsetsWithDup(int[] nums, int index, int length, ArrayList<Integer> path, List<List<Integer>> list) {
        list.add(new ArrayList<>(path));
        for (int i = index; i < length; i++) {
            if (i > index && nums[i] == nums[i - 1]) {
                continue;
            }
            path.add(nums[i]);
            subsetsWithDup(nums, i + 1, length, path, list);
            path.remove(path.size() - 1);
        }
    }

    /**
     * 51. N 皇后
     * 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
     * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
     * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
     * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
     * <a href="https://leetcode.cn/problems/n-queens/description/">Leet Code</a>
     */
    private static int[] sRow;

    static List<List<String>> solveNQueens(int n) {
        sRow = new int[n];
        Arrays.fill(sRow, -1);
        List<List<String>> list = new ArrayList<>();
        solveNQueens(list, 0, n);
        return list;
    }

    private static void solveNQueens(List<List<String>> list, int row, int n) {
        if (row == n) {
            list.add(printQueue(sRow));
            return;
        }
        for (int column = 0; column < n; column++) {
            if (!isValid(row, column, sRow, n)) {
                continue;
            }
            sRow[row] = column;
            solveNQueens(list, row + 1, n);
        }
    }

    private static boolean isValid(int row, int column, int[] rows, int n) {
        int leftUp = column - 1;
        int rightUp = column + 1;
        for (int i = row - 1; i >= 0; i--) {
            if (rows[i] == column) return false;
            if (leftUp >= 0 && rows[i] == leftUp) return false;
            if (rightUp < n && rows[i] == rightUp) return false;
            leftUp--;
            rightUp++;
        }
        return true;
    }

    private static List<String> printQueue(int[] rows) {
        List<String> result = new ArrayList<>();
        int n = rows.length;
        for (int row : rows) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < n; j++) {
                stringBuilder.append(row == j ? "Q" : ".");
            }
            result.add(stringBuilder.toString());
        }
        return result;
    }

    /**
     * 93. 复原 IP 地址
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> list = new ArrayList<>();
        if (s == null || s.length() < 4 || s.length() > 12) {
            return list;
        }
        Deque<String> path = new ArrayDeque<>(4);
        restoreIpAddressesDfs(s, 0, s.length(), 0, path, list);
        return list;
    }

    private void restoreIpAddressesDfs(String s, int begin, int length, int count, Deque<String> path, List<String> list) {
        if (begin == length) {
            if (count == 4) {
                list.add(String.join(".", path));
            }
            return;
        }
        if (length - begin < (4 - count) || length - begin > 3 * (4 - count)) {
            return;
        }
        for (int i = 0; i < 3; i++) {
            if (begin + i >= length) {
                break;
            }
            int ip = string2Ip(s, begin, begin + i);
            if (ip == -1) {
                continue;
            }
            path.add(ip + "");
            restoreIpAddressesDfs(s, begin + i + 1, length, count + 1, path, list);
            path.removeLast();
        }
    }

    private int string2Ip(String s, int left, int right) {
        int len = right - left + 1;
        if (len > 1 && s.charAt(left) == '0') {
            return -1;
        }
        int res = 0;
        for (int i = left; i <= right; i++) {
            res = res * 10 + (s.charAt(i) - '0');
        }
        if (res > 255) {
            return -1;
        }
        return res;
    }
}
