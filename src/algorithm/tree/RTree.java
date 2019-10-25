package algorithm.tree;

import java.util.*;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-08-06 下午7:59
 **/
public class RTree {

    static class TreeNode {

        public TreeNode(int val) {
            this.val = val;
        }

        int val;
        TreeNode left;
        TreeNode right;
        TreeNode next;
    }

    /**
     * 前序遍历（递归）
     *
     * @param root 根节点
     * @return
     */
    public static List<Integer> preOrder(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        preOrder(root, list);
        return list;
    }

    private static void preOrder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        preOrder(root.left, list);
        preOrder(root.right, list);
    }

    /**
     * 前序遍历（迭代）
     *
     * @param root 根节点
     * @return
     */
    public static List<Integer> preOrderIteration(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
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
     * 中序遍历递归
     *
     * @param root 根节点
     * @return
     */
    public static List<Integer> inOrder(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        inOrder(root, list);
        return list;
    }

    private static void inOrder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inOrder(root.left, list);
        list.add(root.val);
        inOrder(root.right, list);
    }

    /**
     * 中序遍历 - 迭代
     *
     * @param root 根节点
     * @return
     */
    public static List<Integer> inOrderIteration(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            list.add(current.val);
            current = current.right;
        }
        return list;
    }

    /**
     * 后序遍历递归
     *
     * @param root 根节点
     * @return
     */
    public static List<Integer> postOrder(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        postOrder(root, list);
        return list;
    }

    private static void postOrder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        postOrder(root.left, list);
        postOrder(root.right, list);
        list.add(root.val);
    }

    /**
     * 后序遍历 - 迭代
     *
     * @param root 根节点
     * @return
     */
    public static List<Integer> postOrderIteration(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        TreeNode cur;
        TreeNode pre = null;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            cur = stack.peek();
            if (cur.left == null && cur.right == null || (pre != null && (cur.left == pre || cur.right == pre))) {
                list.add(cur.val);
                pre = cur;
                stack.pop();
            } else {
                if (cur.right != null) {
                    stack.push(cur.right);
                }
                if (cur.left != null) {
                    stack.push(cur.left);
                }
            }
        }
        return list;
    }


    /**
     * 二叉树中节点的个数
     *
     * @param root 根节点
     * @return
     */
    public static int numOfTreeNode(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = numOfTreeNode(root.left);
        int right = numOfTreeNode(root.right);

        return left + right + 1;
    }

    /**
     * 叶子节点总数
     *
     * @param root 根节点
     * @return
     */
    public static int numOfChildNode(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int left = numOfChildNode(root.left);
        int right = numOfChildNode(root.right);
        return left + right;
    }

    /**
     * k 层节点总数
     *
     * @param root 根节点
     * @param k    第 k 层
     * @return
     */
    public static int numOfkLevelTreeNode(TreeNode root, int k) {
        if (root == null || k < 1) {
            return 0;
        }
        if (k == 1) {
            return 1;
        }
        int left = numOfkLevelTreeNode(root.left, k - 1);
        int right = numOfkLevelTreeNode(root.right, k - 1);
        return left + right;
    }

    /**
     * 判断是否是相同的二叉树
     *
     * @param t1 树1
     * @param t2 树2
     * @return
     */
    public static boolean isSameTreeNode(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        } else if (t1 == null || t2 == null) {
            return false;
        }
        return t1.val == t2.val
                && isSameTreeNode(t1.left, t2.left)
                && isSameTreeNode(t1.right, t2.right);
    }

    /**
     * 判断是否是镜像二叉树
     *
     * @param t1 树1
     * @param t2 树2
     * @return
     */
    public static boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        } else if (t1 == null || t2 == null) {
            return false;
        }
        return t1.val == t2.val
                && isMirror(t1.left, t2.right)
                && isMirror(t1.right, t2.left);
    }

    /**
     * 翻转二叉树or镜像二叉树
     *
     * @param root 根节点
     * @return
     */
    public static TreeNode mirrorTreeNode(TreeNode root) {
        if (root == null) {
            return null;
        }
        root.left = mirrorTreeNode(root.right);
        root.right = mirrorTreeNode(root.left);
        return root;
    }

    /**
     * 判断是否是平衡二叉树
     *
     * @param node 根节点
     * @return
     */
    public static boolean isBalanced(TreeNode node) {
        return maxDeath2(node) != -1;
    }

    private static int maxDeath2(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = maxDeath2(node.left);
        int right = maxDeath2(node.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }

    /**
     * 验证二叉查找树 - 98
     *
     * @param root 根节点
     * @return
     */
    public static boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left != null && root.val < root.left.val) {
            return false;
        }
        if (root.right != null && root.val > root.right.val) {
            return false;
        }
        return isValidBST(root.left) && isValidBST(root.right);
    }

    /**
     * 二叉树最大深度 - 104
     *
     * @param root 根节点
     * @return
     */
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        return Math.max(left, right) + 1;
    }

    /**
     * 二叉树最小深度 - 111
     *
     * @param root 根节点
     * @return
     */
    public static int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int left = minDepth(root.left);
        int right = minDepth(root.right);

        return Math.min(left, right) + 1;
    }

    /**
     * 二叉树的层次遍历 - 102
     *
     * @param root 根节点
     * @return
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> row = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.removeFirst();
                row.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(row);
        }
        return result;
    }

    /**
     * 二叉树的锯齿形层次遍历 递归 - 103
     *
     * @param root 根节点
     * @return
     */
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        zigzagLevelOrder(root, list, 0);
        return list;
    }

    private static void zigzagLevelOrder(TreeNode root, List<List<Integer>> list, int n) {
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

        zigzagLevelOrder(root.left, list, n + 1);
        zigzagLevelOrder(root.right, list, n + 1);
    }

    /**
     * 二叉树的锯齿形层次遍历 迭代 - 103
     *
     * @param root 根节点
     * @return
     */
    public static List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> row = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.removeFirst();
                if (level % 2 == 0) {
                    row.add(node.val);
                } else {
                    row.add(0, node.val);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            list.add(row);
            level++;
        }
        return list;
    }

    /**
     * 从前序与中序遍历序列构造二叉树 - 105
     * 前序遍历 preOrder = [3,9,20,15,7]
     * 中序遍历 inOrder = [9,3,15,20,7]
     * <p>
     * 先序：1 2 4 6 7 8 3 5
     * 中序：4 7 6 8 2 1 3 5
     *
     * @param preOrder 前序数组
     * @param inOrder  后序数组
     * @return
     */
    public static TreeNode buildTree(int[] preOrder, int[] inOrder) {
        if (preOrder == null || inOrder == null) {
            return null;
        }
        if (preOrder.length != inOrder.length) {
            throw new IllegalArgumentException("length of pre order is not equal the length of in order");
        }
        return getNode(preOrder, inOrder, 0, 0, inOrder.length);
    }

    private static TreeNode getNode(int[] preOrder, int[] inOrder, int preStart, int inStart, int inEnd) {
        if (inStart >= inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preOrder[preStart]);
        int index = getIndex(inOrder, preOrder[preStart]);
        root.left = getNode(preOrder, inOrder, preStart + 1, inStart, index);
        root.right = getNode(preOrder, inOrder, preStart + index - inStart + 1, index + 1, inEnd);
        return root;
    }

    private static int getIndex(int[] arr, int value) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 从中序与后序遍历序列构造二叉树 - 106
     * 中序遍历 inorder = [9,3,15,20,7]
     * 后序遍历 postorder = [9,15,7,20,3]
     *
     * @param inOrder   中序数组
     * @param postOrder 后序数组
     * @return
     */
    public static TreeNode buildTree1(int[] inOrder, int[] postOrder) {
        if (postOrder == null || inOrder == null) {
            return null;
        }
        if (postOrder.length != inOrder.length) {
            throw new IllegalArgumentException("length of pre order is not equal the length of in order");
        }
        return getNode1(postOrder, inOrder, postOrder.length - 1, 0, inOrder.length);
    }

    private static TreeNode getNode1(int[] postOrder, int[] inOrder, int postEnd, int inStart, int inEnd) {
        if (inStart >= inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(postOrder[postEnd]);
        int index = getIndex(inOrder, postOrder[postEnd]);
        root.right = getNode1(postOrder, inOrder, postEnd - 1, index + 1, inEnd);
        root.left = getNode1(postOrder, inOrder, postEnd - (inEnd - index), inStart, index);
        return root;
    }

    /**
     * 二叉树的层次遍历 II - 107
     * 方法一：普通层次遍历之后倒序
     * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        levelOrderBottom(root, list, 0);
        Collections.reverse(list);
        return list;
    }

    private static void levelOrderBottom(TreeNode root, List<List<Integer>> list, int level) {
        if (root == null) {
            return;
        }
        if (level + 1 > list.size()) {
            list.add(new ArrayList<>());
        }
        list.get(level).add(root.val);
        if (root.left != null) levelOrderBottom(root.left, list, level + 1);
        if (root.right != null) levelOrderBottom(root.right, list, level + 1);
    }

    /**
     * 将有序数组转换为二叉搜索树 - 108
     *
     * @param nums 数组
     * @return
     */
    public static TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length < 1) {
            return null;
        }
        int mid = nums.length / 2;
        TreeNode root = new TreeNode(nums[mid]);
        for (int i = mid - 1; i >= 0; i--) {
            insert(root, nums[i]);
        }
        for (int i = nums.length - 1; i >= mid + 1; i--) {
            insert(root, nums[i]);
        }
        return root;
    }

    private static TreeNode insert(TreeNode root, int value) {
        if (root == null) {
            return new TreeNode(value);
        }
        if (value < root.val) {
            root.left = insert(root.left, value);
        } else if (value > root.val) {
            root.right = insert(root.right, value);
        }
        return root;
    }

    public static TreeNode sortedBST(int[] nums) {
        if (nums == null || nums.length < 1) {
            return null;
        }
        return sortedBST(nums, 0, nums.length);
    }

    private static TreeNode sortedBST(int[] nums, int start, int end) {
        if (start >= end) {
            return null;
        }
        int mid = (start + end) >>> 1;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedBST(nums, start, mid);
        root.right = sortedBST(nums, mid + 1, end);
        return root;
    }

    /**
     * 路径总和 - 112
     * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
     * 说明: 叶子节点是指没有子节点的节点。
     *
     * @param root 根节点
     * @param sum  路径总和
     * @return
     */
    public static boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return sum == 0;
        }
        return hasPathSum(root.left, sum - root.val)
                || hasPathSum(root.right, sum - root.val);
    }

    /**
     * 路径总和 II - 113
     *
     * @param root 根节点
     * @param sum  路径总和
     * @return
     */
    public static List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        List<Integer> temp = new ArrayList<>();
        pathSum(root, list, sum, temp);
        return list;
    }

    private static void pathSum(TreeNode root, List<List<Integer>> list, int sum, List<Integer> temp) {
        if (root == null) {
            return;
        }
        temp.add(root.val);
        if (root.left == null && root.right == null && sum == root.val) {
            list.add(new ArrayList<>(temp));
        }
        pathSum(root.left, list, sum - root.val, temp);
        pathSum(root.right, list, sum - root.val, temp);
        temp.remove(temp.size() - 1);
    }

    /**
     * 路径总和 III - 437
     *
     * @param root 根节点
     * @param sum  路径总和
     */
    public static List<List<Integer>> pathSum3(TreeNode root, int sum) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        pathSum3(root, list, sum);
        return list;
    }

    private static void pathSum3(TreeNode root, List<List<Integer>> list, int sum) {
        if (root == null) {
            return;
        }
        paths(root, list, sum, new ArrayList<>());
        paths(root.left, list, sum, new ArrayList<>());
        paths(root.right, list, sum, new ArrayList<>());
    }

    private static void paths(TreeNode root, List<List<Integer>> list, int sum, List<Integer> temp) {
        if (root == null) {
            return;
        }
        temp.add(root.val);
        if (sum == root.val) {
            list.add(new ArrayList<>(temp));
        }
        paths(root.left, list, sum - root.val, temp);
        paths(root.right, list, sum - root.val, temp);
        temp.remove(temp.size() - 1);
    }

    /**
     * 二叉树展开为链表 - 114
     *
     * @param root 根节点
     */
    public static void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        while (root != null) {
            if (root.left == null) {
                root = root.right;
            } else {
                TreeNode pre = root.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                pre.right = root.right;
                root.right = root.left;
                root.left = null;
                root = root.right;
            }
        }
    }

    /**
     * 求根到叶子节点数字之和 - 129
     * 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
     * 例如，从根到叶子节点路径 1->2->3 代表数字 123。
     * 计算从根到叶子节点生成的所有数字之和。
     *
     * @param root 根节点
     * @return
     */
    public static int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        getAllPath(root, list, temp);
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).size(); j++) {
                int cof = (int) Math.pow(10, (list.get(i).size() - 1 - j));
                sum += list.get(i).get(j) * cof;
            }
        }
        return sum;
    }

    private static void getAllPath(TreeNode root, List<List<Integer>> list, List<Integer> temp) {
        if (root == null) {
            return;
        }
        temp.add(root.val);
        if (root.left == null && root.right == null) {
            list.add(new ArrayList<>(temp));
        }
        getAllPath(root.left, list, temp);
        getAllPath(root.right, list, temp);
        temp.remove(temp.size() - 1);
    }

    /**
     * 填充每个节点的下一个右侧节点指针 (完全二叉树)- 116
     * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。
     * 如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
     *
     * @param root 根节点
     * @return
     */
    public static TreeNode connect(TreeNode root) {
        if (root == null) {
            return null;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.removeFirst();
                if (i == size - 1) {
                    node.next = null;
                } else {
                    node.next = queue.peek();
                }
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
     * 递归解法
     *
     * @param root 根节点
     * @return
     */
    public static TreeNode connect1(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode l = root.left;
        TreeNode r = root.right;
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
     * 填充每个节点的下一个右侧节点指针（普通二叉树） II
     * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。
     * 如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
     *
     * @param root
     * @return
     */
    public static TreeNode connectII(TreeNode root) {
        if (root == null) {
            return null;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.removeFirst();
                if (i == size - 1) {
                    node.next = null;
                } else {
                    node.next = queue.peek();
                }
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

    private static int mMaxValue;

    /**
     * 二叉树的最大路径和 - 124
     *
     * @param root 根节点
     * @return
     */
    public static int maxPathSum(TreeNode root) {
        getMaxPathSum(root);
        return mMaxValue;
    }

    private static int getMaxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = Math.max(getMaxPathSum(root.left), 0);
        int right = Math.max(getMaxPathSum(root.right), 0);
        mMaxValue = Math.max(mMaxValue, root.val + left + right);
        return root.val + Math.max(left, right);
    }

    /**
     * 二叉树的右视图 - 199
     *
     * @param root 根节点
     * @return
     */
    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        rightSideView(root, list, 0);
        return list;
    }

    private static void rightSideView(TreeNode root, List<Integer> list, int level) {
        if (root == null) {
            return;
        }
        if (level == list.size()) {
            list.add(root.val);
        }
        rightSideView(root.right, list, level + 1);
        rightSideView(root.left, list, level + 1);
    }

    private static int sCount = 0;
    private static int sResult = 0;

    /**
     * 二叉搜索树中第K小的元素 - 230
     *
     * @param root 根节点
     * @param k    第 k 小的索引
     * @return
     */
    public static int kthSmallest(TreeNode root, int k) {
        if (root == null) {
            return 0;
        }
        sCount = 0;
        sResult = 0;
        inOrderOfKthSmallest(root, k);
        return sResult;
    }

    private static void inOrderOfKthSmallest(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        inOrderOfKthSmallest(root.left, k);
        sCount++;
        if (k == sCount) {
            sResult = root.val;
            return;
        }
        inOrderOfKthSmallest(root.right, k);
    }

    /**
     * 二叉搜索树的最近公共祖先 - 235
     *
     * @param root 根节点
     * @param p    节点一
     * @param q    节点二
     * @return
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root) {
            return root;
        }
        if ((p.val - root.val) * (q.val - root.val) > 0) {
            if (p.val < root.val) {
                lowestCommonAncestor(root.left, p, q);
            } else {
                return lowestCommonAncestor(root.right, p, q);
            }
        }
        return root;
    }

    /**
     * 二叉树的最近公共祖先 - 236
     *
     * @param root 根节点
     * @param p    节点一
     * @param q    节点二
     * @return
     */
    public static TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root) {
            return root;
        }
        TreeNode l = lowestCommonAncestor1(root.left, p, q);
        TreeNode r = lowestCommonAncestor1(root.right, p, q);
        if (l != null && r != null) {
            return root;
        }
        return l != null ? l : r;
    }

    /**
     * 二叉树的所有路径 - 257
     *
     * @param root 根节点
     * @return
     */
    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        binaryTreePaths(root, list, "");
        return list;
    }

    private static void binaryTreePaths(TreeNode root, List<String> list, String string) {
        if (root == null) {
            return;
        }
        string += root.val;
        if (root.left == null && root.right == null) {
            list.add(string);
        } else {
            string += "->";
            binaryTreePaths(root.left, list, string);
            binaryTreePaths(root.right, list, string);
        }
    }

    /**
     * 左叶子之和 - 404
     *
     * @param root 根节点
     * @return
     */
    public static int sumOfLeftLeaves(TreeNode root) {
        int sum = 0;
        if (root == null) {
            return 0;
        }
        if (root.left != null) {
            if (root.left.left == null && root.left.right == null) {
                sum += root.left.val;
            } else {
                sum += sumOfLeftLeaves(root.left);
            }
        }
        if (root.right != null) {
            sum += sumOfLeftLeaves(root.right);
        }
        return sum;
    }

    /**
     * 是否是完全二叉树
     *
     * @param root 根节点
     * @return
     */
    public static boolean isCompleteTreeNode(TreeNode root) {
        if (root == null) {
            return true;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean hasChild = true;
        while (!queue.isEmpty()) {
            TreeNode node = queue.removeFirst();
            if (!hasChild) {
                if (node.left != null || node.right != null) {
                    return false;
                }
            } else {
                if (node.left != null && node.right != null) {
                    hasChild = true;
                    queue.offer(node.left);
                    queue.offer(node.right);
                } else if (node.left == null && node.right != null) {
                    return false;
                } else if (node.left != null) {
                    queue.offer(node.left);
                    hasChild = false;
                } else {
                    hasChild = false;
                }
            }
        }
        return true;

    }

    /**
     * 区间搜索 - lintcode 11
     *
     * @param root 根节点
     * @param k1   第一个值
     * @param k2   第二个值
     * @return
     */
    public static List<Integer> searchRange(TreeNode root, int k1, int k2) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        searchRange(root, k1, k2, list);
        return list;
    }

    private static void searchRange(TreeNode root, int k1, int k2, List<Integer> list) {
        if (root == null) {
            return;
        }
        if (root.val >= k1) {
            searchRange(root.left, k1, k2, list);
        }
        if (root.val >= k1 && root.val <= k2) {
            list.add(root.val);
        }
        if (root.val <= k2) {
            searchRange(root.right, k1, k2, list);
        }
    }

    /**
     * 二叉搜索树序列化 - 449
     *
     * @param root 根节点
     * @return
     */
    public static String serialize(TreeNode root) {
        return new Codec().serialize(root);
    }

    /**
     * 二叉搜索树序反列化 - 449
     *
     * @param data 根节点
     * @return
     */
    public static TreeNode deserialize(String data) {
        return new Codec().deserialize(data);
    }

    private static class Codec {

        /**
         * 二叉搜索树序列化 - 449
         *
         * @param root 根节点
         * @return
         */
        String serialize(TreeNode root) {
            if (root == null) {
                return "";
            }
            StringBuilder builder = new StringBuilder();
            serializePreOrder(root, builder);
            builder.substring(0, builder.length() - 1);
            return builder.toString();
        }

        private void serializePreOrder(TreeNode root, StringBuilder builder) {
            if (root == null) {
                return;
            }
            builder.append(root.val).append(",");
            serializePreOrder(root.left, builder);
            serializePreOrder(root.right, builder);
        }

        /**
         * 二叉搜索树序反列化 - 449
         *
         * @param data 根节点
         * @return
         */
        TreeNode deserialize(String data) {
            if (data == null || "".equals(data)) {
                return null;
            }
            String[] preData = data.split(",");
            return builderTree(preData);
        }

        private TreeNode builderTree(String[] preOrder) {
            if (preOrder == null || preOrder.length < 1) {
                return null;
            }
            TreeNode root = new TreeNode(Integer.valueOf(preOrder[0]));
            for (int i = 1; i < preOrder.length; i++) {
                insert(root, Integer.valueOf(preOrder[i]));
            }
            return root;
        }
    }

    /**
     * 打家劫舍 III - 337 （普通方法 - 层次遍历）
     * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。
     * 这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。
     * 一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
     * 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
     * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
     *
     * @param root 根节点
     * @return
     */
    public static int robIII(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // result 数组 0 - 包含根节点最大值， 1 - 不包含根节点最大值
        int[] result = rob(root);
        return Math.max(result[0], result[1]);
    }

    private static int[] rob(TreeNode root) {
        int[] res = new int[2];
        if (root == null) {
            return res;
        }
        int[] l = rob(root.left);
        int[] r = rob(root.right);
        // 不包含根节点
        res[1] = Math.max(l[0], l[1]) + Math.max(r[0], r[1]);
        // 换成这个也可以，因为不包含根节点，左右节点一定包含（层次遍历，间隔一层求和）
//        res[1] = l[0] + r[0];
        // 包含根节点
        res[0] = root.val + l[1] + r[1];
        return res;
    }

    /**
     * 二叉搜索树中的众数 - 501
     *
     * @param root 根节点
     * @return
     */
    public static int[] findMode(TreeNode root) {
        return new FindMode().findMode(root);
    }

    private static class FindMode {

        private Integer pre = null;
        private int maxCount;
        private int curCount;
        private List<Integer> mList = new ArrayList<>();

        /**
         * 二叉搜索树中的众数 - 501
         *
         * @param root 根节点
         * @return
         */
        private int[] findMode(TreeNode root) {
            if (root == null) {
                return null;
            }
            getMode(root);
            int[] resultArr = new int[mList.size()];
            for (int i = 0; i < mList.size(); i++) {
                resultArr[i] = mList.get(i);
            }
            return resultArr;
        }

        private void getMode(TreeNode root) {
            if (root == null) {
                return;
            }
            getMode(root.left);
            if (pre == null || pre != root.val) {
                curCount = 1;
            } else {
                curCount++;
            }
            if (curCount > maxCount) {
                mList.clear();
                maxCount = curCount;
            }
            if (curCount == maxCount) {
                mList.add(root.val);
                curCount++;
            }
            pre = root.val;
            getMode(root.right);
        }
    }

    /**
     * 在每个树行中找最大值 - 515
     *
     * @param root 根节点
     * @return
     */
    public static List<Integer> largestValues(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int max = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                max = Math.max(max, node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            list.add(max);
        }
        return list;
    }

    /**
     * 二叉树的直径 - 543
     * 给定一棵二叉树，你需要计算它的直径长度。
     * 一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过根结点。
     *
     * @param root 根节点
     * @return
     */
    public static int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] res = new int[1];
        diameterOfBinaryTree(root, res);
        return res[0];
    }

    private static int diameterOfBinaryTree(TreeNode root, int[] res) {
        if (root == null) {
            return 0;
        }
        int leftDepth = diameterOfBinaryTree(root.left, res);
        int rightDepth = diameterOfBinaryTree(root.right, res);
        res[0] = Math.max(leftDepth + rightDepth, res[0]);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    /**
     * 二叉树最大宽度 - 662
     *
     * @param root 根节点
     * @return
     */
    public static int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int maxLength = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            maxLength = Math.max(maxLength, size);
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return maxLength;
    }

    /**
     * 先序遍历构造二叉搜索树 - 1008
     * 返回与给定先序遍历 preorder 相匹配的二叉搜索树（binary search tree）的根结点。
     *
     * @param preorder 前序遍历
     * @return
     */
    public static TreeNode bstFromPreorder(int[] preorder) {
        if (preorder == null || preorder.length < 1) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        for (int i = 1; i < preorder.length; i++) {
            insert(root, preorder[i]);
        }
        return root;
    }

    /**
     * 方法二
     *
     * @param preorder 前序遍历
     * @return
     */
    public static TreeNode bstFromPreorder1(int[] preorder) {
        if (preorder == null || preorder.length < 1) {
            return null;
        }
        int[] index = new int[]{0};
        return bstFromPreorder1(preorder, index, Integer.MAX_VALUE);
    }

    private static TreeNode bstFromPreorder1(int[] preorder, int[] index, int maxValue) {
        if (index[0] >= preorder.length || preorder[index[0]] > maxValue) {
            return null;
        }
        TreeNode treeNode = new TreeNode(preorder[index[0]++]);
        treeNode.left = bstFromPreorder1(preorder, index, treeNode.val);
        treeNode.right = bstFromPreorder1(preorder, index, maxValue);
        return treeNode;
    }

    /**
     * 不同的二叉搜索树 - 96 （动态规划）
     * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
     * 解析： https://leetcode-cn.com/problems/unique-binary-search-trees/solution/bu-tong-de-er-cha-sou-suo-shu-by-leetcode/
     *
     * @param n
     * @return
     */
    public static int numTrees(int n) {
        int[] res = new int[n + 1];
        res[0] = 1;
        res[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            for (int j = 1; j <= i; j++) {
                res[i] += res[j - 1] * res[i - j];
            }
        }
        return res[n];
    }

    /**
     * 不同的二叉搜索树 II - 95 （动态规划）
     * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
     *
     * @param n 二叉树节点最大值
     * @return
     */
    public static List<TreeNode> generateTrees(int n) {
        List<TreeNode> list = new ArrayList<>();
        if (n == 0) {
            return list;
        }
        generateTrees(1, n);
        return list;
    }

    private static List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> treeNodes = new ArrayList<>();
        if (start > end) {
            treeNodes.add(null);
            return treeNodes;
        }
        if (start == end) {
            TreeNode node = new TreeNode(start);
            treeNodes.add(node);
            return treeNodes;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftNodes = generateTrees(start, i - 1);
            List<TreeNode> rightNodes = generateTrees(i + 1, end);
            for (TreeNode l : leftNodes) {
                for (TreeNode r : rightNodes) {
                    TreeNode root = new TreeNode(start);
                    root.left = l;
                    root.right = r;
                    treeNodes.add(root);
                }
            }
        }
        return treeNodes;
    }
}
