package com.ralf.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MyTree {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 144. 二叉树的前序遍历
     * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
     * <a href="https://leetcode.cn/problems/binary-tree-preorder-traversal/description/?envType=list&envId=TBxUr5QH">Leet Code</a>
     */
    List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        preorderTraversal(root, list);
        return list;
    }

    private static void preorderTraversal(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        list.add(node.val);
        preorderTraversal(node.left, list);
        preorderTraversal(node.right, list);
    }

    private static List<Integer> preOrderIteration(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            list.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return list;
    }

    /**
     * 94. 二叉树的中序遍历
     * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
     * <a href="https://leetcode.cn/problems/binary-tree-preorder-traversal/description/?envType=list&envId=TBxUr5QH">Leet Code</a>
     */
    List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        inorderTraversal(root, list);
        return list;
    }

    private static void inorderTraversal(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.left, list);
        list.add(node.val);
        inorderTraversal(node.right, list);
    }

    private static List<Integer> inorderIteration(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            list.add(node.val);
            node = node.right;
        }
        return list;
    }

    /**
     * 145. 二叉树的后序遍历
     * 给你一棵二叉树的根节点 root ，返回其节点值的 后序遍历 。
     * <a href="https://leetcode.cn/problems/binary-tree-postorder-traversal/description/?envType=list&envId=TBxUr5QH">Leet Code</a>
     */
    List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        postorderTraversal(root, list);
        return list;
    }

    private static void postorderTraversal(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        postorderTraversal(node.left, list);
        postorderTraversal(node.right, list);
        list.add(node.val);
    }

    private static List<Integer> postorderIteration(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        TreeNode pre = null;
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            if ((node.left == null && node.right == null) || (pre != null && (pre == node.left || pre == node.right))) {
                list.add(node.val);
                pre = node;
                stack.pop();
            } else {
                if (node.right != null) {
                    stack.push(node.right);
                }
                if (node.left != null) {
                    stack.push(node.left);
                }
            }
        }
        return list;
    }

    /**
     * 102. 二叉树的层序遍历
     * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
     * <a href="https://leetcode.cn/problems/binary-tree-level-order-traversal/submissions/497368972/?envType=list&envId=TBxUr5QH">Leet Code</a>
     */
    List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            list.add(level);
        }
        Collections.reverse(list);
        return list;
    }

    /**
     * 107. 二叉树的层序遍历 II
     * 给你二叉树的根节点 root ，返回其节点值 自底向上的层序遍历 。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
     * <a href="https://leetcode.cn/problems/binary-tree-level-order-traversal-ii/description/?envType=list&envId=TBxUr5QH">Leet Code</a>
     */
    List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            list.add(level);
        }
        return list;
    }

    /**
     * 662. 二叉树最大宽度
     * 给你一棵二叉树的根节点 root ，返回树的 最大宽度 。
     * 树的 最大宽度 是所有层中最大的 宽度 。
     * 每一层的 宽度 被定义为该层最左和最右的非空节点（即，两个端点）之间的长度。将这个二叉树视作与满二叉树结构相同，两端点间会出现一些延伸到这一层的 null 节点，这些 null 节点也计入长度。
     * 题目数据保证答案将会在  32 位 带符号整数范围内。
     * <a href="https://leetcode.cn/problems/maximum-width-of-binary-tree/description/?envType=list&envId=TBxUr5QH">Leet Code</a>
     */
    private static class Pair<F, S> {
        F first;
        S second;

        Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }

        static <F, S> Pair<F, S> of(F first, S second) {
            return new Pair<>(first, second);
        }
    }

    int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int width = 0;
        LinkedList<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.offer(Pair.of(root, 1));
        while (!queue.isEmpty()) {
            int size = queue.size();
            int first = 0;
            int last = 0;
            for (int i = 0; i < size; i++) {
                Pair<TreeNode, Integer> item = queue.poll();
                TreeNode node = item.first;
                int index = item.second;
                if (first == 0 && node != null) {
                    first = index;
                }
                if (node != null) {
                    last = index;
                }
                if (node.left != null) {
                    queue.offer(Pair.of(node.left, index * 2));
                }
                if (node.right != null) {
                    queue.offer(Pair.of(node.right, index * 2 + 1));
                }
            }
            width = Math.max(width, last - first + 1);
        }
        return width;
    }

    int widthOfBinaryTree2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Map<Integer, Integer> levelMin = new HashMap<>();
        return widthOfBinaryTree2(root, 1, 1, levelMin);
    }

    private int widthOfBinaryTree2(TreeNode root, int depth, int index, Map<Integer, Integer> levelMin) {
        if (root == null) {
            return 0;
        }
        levelMin.putIfAbsent(depth, index);
        int left = widthOfBinaryTree2(root.left, depth + 1, index * 2, levelMin);
        int right = widthOfBinaryTree2(root.right, depth + 1, index * 2 + 1, levelMin);
        return Math.max(index - levelMin.get(depth) + 1, Math.max(left, right));
    }

    /**
     * 103. 二叉树的锯齿形层序遍历
     * 给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
     * <a href="https://leetcode.cn/problems/binary-tree-zigzag-level-order-traversal/description/?envType=list&envId=TBxUr5QH">Leet Code</a>
     */
    static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean isOdd = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (isOdd) {
                    level.add(node.val);
                } else {
                    level.add(0, node.val);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            isOdd = !isOdd;
            list.add(level);
        }
        return list;
    }

    static List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        zigzagLevelOrder2(root, list, 0);
        return list;
    }

    private static void zigzagLevelOrder2(TreeNode root, List<List<Integer>> list, int n) {
        if (root == null) {
            return;
        }
        if (list.size() == n) {
            list.add(new ArrayList<>());
        }
        if (n % 2 == 0) {
            list.get(n).add(root.val);
        } else {
            list.get(n).add(0, root.val);
        }
        zigzagLevelOrder2(root.left, list, n + 1);
        zigzagLevelOrder2(root.right, list, n + 1);
    }

    /**
     * 199. 二叉树的右视图
     * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
     * <a href="https://leetcode.cn/problems/binary-tree-right-side-view/description/?envType=list&envId=TBxUr5QH">Leet Code</a>
     */
    static List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == size - 1) {
                    list.add(node.val);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return list;
    }

    static List<Integer> rightSideView2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        rightSideView2(root, list, 0);
        return list;
    }

    private static void rightSideView2(TreeNode root, List<Integer> list, int level) {
        if (root == null) {
            return;
        }
        if (list.size() == level) {
            list.add(root.val);
        }
        rightSideView2(root.right, list, level + 1);
        rightSideView2(root.left, list, level + 1);
    }


    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public List<Node> children;
    }

    /**
     * 116. 填充每个节点的下一个右侧节点指针
     * 给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
     * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
     * 初始状态下，所有 next 指针都被设置为 NULL。
     * <a href="https://leetcode.cn/problems/populating-next-right-pointers-in-each-node/description/?envType=list&envId=TBxUr5QH">Leet Code</a>
     */
    static Node connect(Node root) {
        if (root == null) {
            return root;
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            Node pre = null;
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (pre != null) {
                    pre.next = node;
                }
                pre = node;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return root;
    }

    static Node connect1(Node root) {
        if (root == null) {
            return root;
        }
        Node l = root.left;
        Node r = root.right;
        while (l != null) {
            l.next = r;
            l = l.right;
            r = r.left;
        }
        connect1(root.left);
        connect1(root.right);
        return root;
    }

    /**
     * 117. 填充每个节点的下一个右侧节点指针 II
     * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL 。
     * 初始状态下，所有 next 指针都被设置为 NULL 。
     * <a href="https://leetcode.cn/problems/populating-next-right-pointers-in-each-node-ii/description/?envType=list&envId=TBxUr5QH">Leet Code</a>
     */
    static Node connectII(Node root) {
        if (root == null) {
            return root;
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            Node pre = null;
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (pre != null) {
                    pre.next = node;
                }
                pre = node;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return root;
    }

    /**
     * 100. 相同的树
     * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
     * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
     * <a href="https://leetcode.cn/problems/same-tree/description/?envType=list&envId=TBxUr5QH">Leet Code</a>
     */
    static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null) {
            return false;
        }
        if (q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /**
     * 101. 对称二叉树
     * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
     * <a href="https://leetcode.cn/problems/symmetric-tree/description/?envType=list&envId=TBxUr5QH">Leet Code</a>
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return false;
        }
        return isSymmetric(root.left, root.right);
    }

    private static boolean isSymmetric(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null) {
            return false;
        }
        if (node2 == null) {
            return false;
        }
        if (node1.val != node2.val) {
            return false;
        }
        boolean inner = isSymmetric(node1.right, node2.left);
        boolean outer = isSymmetric(node1.left, node2.right);
        return inner && outer;
    }

    /**
     * 226. 翻转二叉树
     * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
     * <a href="https://leetcode.cn/problems/invert-binary-tree/description/?envType=list&envId=TBxUr5QH">Leet Code</a>
     */
    static TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode l = root.left;
        TreeNode r = root.right;
        root.left = invertTree(r);
        root.right = invertTree(l);
        return root;
    }

    /**
     * 104. 二叉树的最大深度
     * 给定一个二叉树 root ，返回其最大深度。
     * 二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数
     * <a href="https://leetcode.cn/problems/maximum-depth-of-binary-tree/description/?envType=list&envId=TBxUr5QH">Leet Code</a>
     */
    static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }

    /**
     * 111. 二叉树的最小深度
     * 给定一个二叉树，找出其最小深度。
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
     * 说明：叶子节点是指没有子节点的节点。
     * <a href="https://leetcode.cn/problems/minimum-depth-of-binary-tree/description/?envType=list&envId=TBxUr5QH">Leet Code</a>
     */
    static int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        if (root.left == null) {
            return right + 1;
        }
        if (root.right == null) {
            return left + 1;
        }
        return Math.min(left, right) + 1;
    }

    /**
     * LCR 176. 判断是否为平衡二叉树
     * 输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
     * <a href="https://leetcode.cn/problems/ping-heng-er-cha-shu-lcof/?envType=list&envId=TBxUr5QH">Leet Code</a>
     */
    static boolean isBalanced(TreeNode root) {
        if (root == null) {
            return false;
        }
        return recur(root) != -1;
    }

    private static int recur(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = recur(root.left);
        if (left == -1) {
            return -1;
        }
        int right = recur(root.right);
        if (right == -1) {
            return -1;
        }
        return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : -1;
    }

    /**
     * LCR 175. 计算二叉树的深度
     * 某公司架构以二叉树形式记录，请返回该公司的层级数。
     * <a href="https://leetcode.cn/problems/er-cha-shu-de-shen-du-lcof/">Leet Code</a>
     */
    static int calculateDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return calculateDepth(root, 1);
    }

    private static int calculateDepth(TreeNode root, int level) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int left = calculateDepth(root.left, level + 1);
        int right = calculateDepth(root.right, level + 1);
        return Math.max(left, right) + 1;
    }

    /**
     * 112. 路径总和
     * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。如果存在，返回 true ；否则，返回 false 。
     * 叶子节点 是指没有子节点的节点
     * <a href="https://leetcode.cn/problems/path-sum/description/?envType=list&envId=TBxUr5QH">Leet Code</a>
     */
    static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return targetSum == 0;
        }
        if (root.left == null && root.right == null && targetSum == root.val) {
            return true;
        }
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    /**
     * 113. 路径总和 II
     * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
     * 叶子节点 是指没有子节点的节点。
     * <a href="https://leetcode.cn/problems/path-sum-ii/description/?envType=list&envId=TBxUr5QH">Leet Code</a>
     */
    static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        pathSum(root, targetSum, new ArrayList<>(), list);
        return list;
    }

    private static void pathSum(TreeNode root, int targetSum, List<Integer> path, List<List<Integer>> list) {
        if (root == null) {
            return;
        }
        path.add(root.val);
        if (root.left == null && root.right == null && targetSum == root.val) {
            list.add(new ArrayList<>(path));
        }
        pathSum(root.left, targetSum - root.val, path, list);
        pathSum(root.right, targetSum - root.val, path, list);
        path.remove(path.size() - 1);
    }

    /**
     * 打印所有路径和
     */
    static List<Integer> pathSum2(TreeNode root) {
        List<Integer> path = new ArrayList<>();
        if (root == null) {
            return path;
        }
        pathSum2(root, 0, path);
        return path;
    }

    private static void pathSum2(TreeNode root, int sum, List<Integer> path) {
        if (root == null) {
            return;
        }
        sum += root.val;
        if (root.left == null && root.right == null) {
            path.add(sum);
        }
        pathSum2(root.left, sum, path);
        pathSum2(root.right, sum, path);
    }

    /**
     * 437. 路径总和 III
     */
    public int pathSumIII(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        long res = pathSumDfs(root, targetSum);
        res += pathSumIII(root.left, targetSum);
        res += pathSumIII(root.right, targetSum);
        return (int) res;
    }

    public long pathSumDfs(TreeNode root, long targetSum) {
        long count = 0;
        if (root == null) {
            return 0;
        }
        if (root.val == targetSum) {
            count++;
        }
        count += pathSumDfs(root.left, targetSum - root.val);
        count += pathSumDfs(root.right, targetSum - root.val);
        return count;
    }

    private static int maxPathSum = Integer.MIN_VALUE;

    /**
     * 124. 二叉树中的最大路径和
     * 二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
     * 路径和 是路径中各节点值的总和。
     * <a href="https://leetcode.cn/problems/binary-tree-maximum-path-sum/?envType=list&envId=Lkxop8fK">Leet Code</a>
     */
    static int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        maxPathSumOfTree(root);
        return maxPathSum;
    }

    private static int maxPathSumOfTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int maxLeft = Math.max(maxPathSumOfTree(root.left), 0);
        int maxRight = Math.max(maxPathSumOfTree(root.right), 0);
        maxPathSum = Math.max(maxPathSum, maxRight + maxLeft + root.val);
        return Math.max(maxRight, maxLeft) + root.val;
    }

    /**
     * 96. 不同的二叉搜索树
     * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
     * <a href="https://leetcode.cn/problems/unique-binary-search-trees/description/?envType=list&envId=TBxUr5QH">Leet Code</a>
     */
    static int numTrees(int n) {
        if (n < 1) {
            return 0;
        }
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
     * LCR 193. 二叉搜索树的最近公共祖先
     * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     * 例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
     * <a href="https://leetcode.cn/problems/er-cha-sou-suo-shu-de-zui-jin-gong-gong-zu-xian-lcof/description/?envType=list&envId=TBxUr5QH">Leet Code</a>
     */
    static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root) {
            return root;
        }
        if ((p.val - root.val) * (q.val - root.val) > 0) {
            if (p.val - root.val > 0) {
                return lowestCommonAncestor(root.right, p, q);
            } else {
                return lowestCommonAncestor(root.left, p, q);
            }
        }
        return root;
    }

    static TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root) {
            return root;
        }
        if (p.val > q.val) {
            TreeNode temp = p;
            p = q;
            q = temp;
        }
        TreeNode node = root;
        while (node != null) {
            if (node.val < p.val) {
                node = node.right;
            } else if (node.val > q.val) {
                node = node.left;
            } else {
                break;
            }
        }
        return node;
    }

    /**
     * 236. 二叉树的最近公共祖先
     * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     * <a href="https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/description/?envType=list&envId=TBxUr5QH">Leet Code</a>
     */
    static TreeNode lowestCommonAncestorII(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root) {
            return root;
        }
        TreeNode l = lowestCommonAncestorII(root.left, p, q);
        TreeNode r = lowestCommonAncestorII(root.right, p, q);
        if (l != null && r != null) {
            return root;
        }
        return l != null ? l : r;
    }

    /**
     * 108. 将有序数组转换为二叉搜索树
     * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
     * 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
     * <a href="https://leetcode.cn/problems/convert-sorted-array-to-binary-search-tree/description/?envType=list&envId=TBxUr5QH">Leet Code</a>
     */
    static TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length < 1) {
            return null;
        }
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    private static TreeNode sortedArrayToBST(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) >> 1;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(nums, start, mid - 1);
        root.right = sortedArrayToBST(nums, mid + 1, end);
        return root;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 109. 有序链表转换二叉搜索树
     * 给定一个单链表的头节点  head ，其中的元素 按升序排序 ，将其转换为高度平衡的二叉搜索树。
     * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差不超过 1。
     * <a href="https://leetcode.cn/problems/convert-sorted-list-to-binary-search-tree/description/">Leet Code</a>
     */
    static TreeNode sortedListToBSTII(ListNode head) {
        if (head == null) {
            return null;
        }
        return sortedListToBSTII(head, null);
    }

    static TreeNode sortedListToBSTII(ListNode head, ListNode end) {
        if (head == end) {
            return null;
        }
        ListNode mid = findMid(head, end);
        TreeNode root = new TreeNode(mid.val);
        root.left = sortedListToBSTII(head, mid);
        root.right = sortedListToBSTII(mid.next, end);
        return root;
    }

    private static ListNode findMid(ListNode head, ListNode end) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != end && fast.next != end) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    private static int count = 0;
    private static int result = 0;

    /**
     * 230. 二叉搜索树中第K小的元素
     * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
     * <a href="https://leetcode.cn/problems/kth-smallest-element-in-a-bst/description/">Leet Code</a>
     */
    static int kthSmallest(TreeNode root, int k) {
        if (root == null) {
            return -1;
        }
        count = 0;
        result = 0;
        kthSmallestDFS(root, k);
        return result;
    }

    private static void kthSmallestDFS(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        kthSmallestDFS(root.left, k);
        count++;
        if (count == k) {
            result = root.val;
            return;
        }
        kthSmallestDFS(root.right, k);
    }

    /**
     * 从前序与中序遍历序列构造二叉树 - 105
     * 前序遍历 preOrder = [3,9,20,15,7]
     * 中序遍历 inOrder = [9,3,15,20,7]
     * <p>
     * 先序：1 2 4 6 7 8 3 5
     * 中序：4 7 6 8 2 1 3 5
     * <a href="https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/">Leet Code</a>
     */
    static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(preorder, 0, 0, inorder.length, map);
    }

    private static TreeNode buildTree(int[] preorder, int pStart, int inStart, int inEnd, Map<Integer, Integer> map) {
        if (inStart >= inEnd) {
            return null;
        }
        int val = preorder[pStart];
        int index = map.get(val);
        TreeNode root = new TreeNode(val);
        root.left = buildTree(preorder, pStart + 1, inStart, index, map);
        root.right = buildTree(preorder, pStart + index - inStart + 1, index + 1, inEnd, map);
        return root;
    }

    /**
     * 1008. 前序遍历构造二叉搜索树
     * 给定一个整数数组，它表示BST(即 二叉搜索树 )的 先序遍历 ，构造树并返回其根。
     * 保证 对于给定的测试用例，总是有可能找到具有给定需求的二叉搜索树。
     * 二叉搜索树 是一棵二叉树，其中每个节点， Node.left 的任何后代的值 严格小于 Node.val , Node.right 的任何后代的值 严格大于 Node.val。
     * 二叉树的 前序遍历 首先显示节点的值，然后遍历Node.left，最后遍历Node.right。
     * <a href="https://leetcode.cn/problems/construct-binary-search-tree-from-preorder-traversal/">Leet Code</a>
     */
    static TreeNode bstFromPreorder(int[] preorder) {
        if (preorder == null || preorder.length < 1) {
            return null;
        }
        int[] inorder = new int[preorder.length];
        System.arraycopy(preorder, 0, inorder, 0, preorder.length);
        Arrays.sort(inorder);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(preorder, 0, 0, inorder.length, map);
    }

    /**
     * 530. 二叉搜索树的最小绝对差
     * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
     * 差值是一个正数，其数值等于两值之差的绝对值。
     * <a href="https://leetcode.cn/problems/minimum-absolute-difference-in-bst/description/?envType=study-plan-v2&envId=top-interview-150">LeetCode</a>
     */
    static int res;
    static int pre;

    static int getMinimumDifference(TreeNode root) {
        res = Integer.MAX_VALUE;
        pre = -1;
        return 0;
    }

    private static void getMinimumDifferenceInternal(TreeNode root) {
        if (root == null) {
            return;
        }
        getMinimumDifferenceInternal(root.left);
        if (pre == -1) {
            pre = root.val;
        } else {
            res = Math.min(root.val - pre, res);
            pre = root.val;
        }
        getMinimumDifferenceInternal(root.right);
    }

    /**
     * 98. 验证二叉搜索树
     * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
     * <a href="https://leetcode.cn/problems/validate-binary-search-tree/description/?envType=study-plan-v2&envId=top-interview-150">LeetCode</a>
     */
    static boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean isValidBST(TreeNode root, long left, long right) {
        if (root == null) {
            return true;
        }
        if (root.val <= left || root.val >= right) {
            return false;
        }
        return isValidBST(root.left, left, root.val) && isValidBST(root.right, root.val, right);
    }

    /**
     * 501. 二叉搜索树中的众数
     * 给你一个含重复值的二叉搜索树（BST）的根节点 root ，找出并返回 BST 中的所有 众数（即，出现频率最高的元素）。
     * 如果树中有不止一个众数，可以按 任意顺序 返回。
     * <a href="https://leetcode.cn/problems/find-mode-in-binary-search-tree/">LeetCode</a>
     */
    private static TreeNode findModeOfPre = null;
    private static int findModeOfCount = 0;
    private static int findModeOfMax = 0;

    static int[] findMode(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<Integer> res = new ArrayList<>();
        findModeDfs(root, res);
        int[] arr = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            arr[i] = res.get(i);
        }
        return arr;
    }

    private static void findModeDfs(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        findModeDfs(root.left, res);
        if (findModeOfPre == null) {
            findModeOfCount = 1;
        } else {
            if (findModeOfPre.val == root.val) {
                findModeOfCount++;
            } else {
                findModeOfCount = 1;
            }
        }
        if (findModeOfCount == findModeOfMax) {
            res.add(root.val);
        } else if (findModeOfCount > findModeOfMax) {
            findModeOfMax = findModeOfCount;
            res.clear();
            res.add(root.val);
        }
        findModeOfPre = root;
        findModeDfs(root.right, res);
    }

    /**
     * LCR 153. 二叉树中和为目标值的路径
     * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
     * 叶子节点 是指没有子节点的节点
     */
    public List<List<Integer>> pathTarget(TreeNode root, int target) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        pathTargetDfs(root, target, new ArrayList<>(), list);
        return list;
    }

    private void pathTargetDfs(TreeNode root, int target, List<Integer> path, List<List<Integer>> list) {
        if (root == null) {
            return;
        }
        path.add(root.val);
        if (root.left == null && root.right == null && root.val == target) {
            list.add(new ArrayList<>(path));
        }
        pathTargetDfs(root.left, target - root.val, path, list);
        pathTargetDfs(root.right, target - root.val, path, list);
        path.remove(path.size() - 1);
    }

    /**
     * 257. 二叉树的所有路径
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        binaryTreePathsDfs(root, list, "");
        return list;
    }

    private void binaryTreePathsDfs(TreeNode root, List<String> list, String path) {
        if (root == null) {
            return;
        }
        StringBuilder builder = new StringBuilder(path);
        builder.append(root.val);
        if (root.left == null && root.right == null) {
            list.add(builder.toString());
        } else {
            builder.append("->");
            binaryTreePathsDfs(root.left, list, builder.toString());
            binaryTreePathsDfs(root.right, list, builder.toString());
        }
    }

    /**
     * 559. N 叉树的最大深度
     */
    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        if (root.children == null) {
            return 1;
        }
        int maxChildDepth = 0;
        for (Node node : root.children) {
            int depth = maxDepth(node);
            maxChildDepth = Math.max(maxChildDepth, depth);
        }
        return maxChildDepth + 1;
    }

    /**
     * 543. 二叉树的直径
     */
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] res = new int[1];
        diameterOfBinaryTree(root, res);
        return res[0] - 1;
    }

    private int diameterOfBinaryTree(TreeNode root, int[] res) {
        if (root == null) {
            return 0;
        }
        int l = diameterOfBinaryTree(root.left, res);
        int r = diameterOfBinaryTree(root.right, res);
        res[0] = Math.max(res[0], l + r + 1);
        return Math.max(l, r) + 1;
    }

    /**
     * 1522. N 叉树的直径
     * 给定一棵 N 叉树 的根节点 root ，计算这棵树的直径长度。
     * N 叉树的直径指的是树中任意两个节点间路径中 最长 路径的长度。这条路径可能经过根节点，也可能不经过根节点。
     */
    public int diameter(Node root) {
        if (root == null) {
            return 0;
        }
        int[] res = new int[1];
        diameterDfs(root, res, 0);
        return res[0];
    }

    public int diameterDfs(Node root, int[] res, int depth) {
        if (root.children == null || root.children.isEmpty()) {
            return depth;
        }
        int maxDepth1 = depth, maxDepth2 = 0;
        for (Node node : root.children) {
            int temp = diameterDfs(node, res, depth + 1);
            if (temp > maxDepth1) {
                maxDepth2 = maxDepth1;
                maxDepth1 = temp;
            } else if (temp > maxDepth2) {
                maxDepth2 = temp;
            }
            int distance = maxDepth1 + maxDepth2 - 2 * depth;
            res[0] = Math.max(res[0], distance);
        }
        return maxDepth1;
    }

    /**
     * JZ36 二叉搜索树与双向链表
     * <a href="https://www.nowcoder.com/practice/947f6eb80d944a84850b0538bf0ec3a5?tpId=13">牛客</a>
     */
    public TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null) {
            return null;
        }
        TreeNode[] pre = new TreeNode[1];
        TreeNode root = pRootOfTree;
        ConvertDfs(pRootOfTree, pre);
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    public void ConvertDfs(TreeNode root, TreeNode[] pre) {
        if (root == null) {
            return;
        }
        ConvertDfs(root.left, pre);
        root.left = pre[0];
        if (pre[0] != null) {
            pre[0].right = root;
        }
        pre[0] = root;
        ConvertDfs(root.right, pre);
    }

    /**
     * 426. 将二叉搜索树转化为排序的双向链表
     * 将一个 二叉搜索树 就地转化为一个 已排序的双向循环链表 。
     * 对于双向循环列表，你可以将左右孩子指针作为双向循环链表的前驱和后继指针，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。
     * 特别地，我们希望可以 就地 完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中最小元素的指针。
     */
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        Node[] pre = new Node[2];
        treeToDoublyListDfs(root, pre);
        Node head = pre[1];
        Node last = pre[0];
        head.left = last;
        last.right = head;
        return head;
    }

    private void treeToDoublyListDfs(Node root, Node[] pre) {
        if (root == null) {
            return;
        }
        treeToDoublyListDfs(root.left, pre);
        if (pre[0] != null) {
            pre[0].right = root;
        } else {
            pre[1] = root;
        }
        root.left = pre[0];
        pre[0] = root;
        treeToDoublyListDfs(root.right, pre);
    }

    /**
     * BM35 判断是不是完全二叉树
     * <a href="https://www.nowcoder.com/practice/8daa4dff9e36409abba2adbe413d6fae?tpId=295&tqId=2299105&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj">牛客</a>
     */
    public boolean isCompleteTree(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean notComplete = false;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                notComplete = true;
                continue;
            }
            if (notComplete) {
                return false;
            }
            queue.offer(node.left);
            queue.offer(node.right);
        }
        return true;
    }

    /**
     * 129. 求根节点到叶节点数字之和
     * <a href="https://leetcode.cn/problems/sum-root-to-leaf-numbers/description/">Leet Code</a>
     */
    public int sumNumbers(TreeNode root) {
        return sumNumbersDfs(root, 0);
    }

    private int sumNumbersDfs(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        sum = sum * 10 + root.val;
        if (root.left == null && root.right == null) {
            return sum;
        }
        return sumNumbersDfs(root.left, sum) + sumNumbersDfs(root.right, sum);
    }

    /**
     * 173. 二叉搜索树迭代器
     * <a href="https://leetcode.cn/problems/binary-search-tree-iterator/description/">Leet Code</a>
     */
    class BSTIterator {

        private List<Integer> list = new ArrayList<>();
        private int index = 0;

        public BSTIterator(TreeNode root) {
            inOrder(root);
        }

        private void inOrder(TreeNode root) {
            if (root == null) {
                return;
            }
            inOrder(root.left);
            list.add(root.val);
            inOrder(root.right);
        }

        public int next() {
            return list.get(index);
        }

        public boolean hasNext() {
            return index < list.size();
        }
    }

    class BSTIterator2 {

        private TreeNode cur = null;
        private final Deque<TreeNode> queue = new LinkedList<>();

        public BSTIterator2(TreeNode root) {
            cur = root;
        }

        public int next() {
            while (cur != null) {
                queue.push(cur);
                cur = cur.left;
            }
            TreeNode node = queue.pop();
            int result = node.val;
            cur = node.right;
            return result;
        }

        public boolean hasNext() {
            return cur != null || !queue.isEmpty();
        }
    }
}


















