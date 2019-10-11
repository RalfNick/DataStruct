package algorithm.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * DESCRIPTION
 * 使用栈练习树
 *
 * @author lixin
 * @create 2019-07-25 上午8:41
 **/
public class StackTree {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 二叉树的锯齿形层次遍历 - 103
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        zigzagLevelOrder(result, root, 0);
        return result;
    }

    private static void zigzagLevelOrder(List<List<Integer>> list, TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        if (list.size() == depth) {
            list.add(new ArrayList<>());
        }
        if (depth % 2 == 0) {
            list.get(depth).add(root.val);
        } else {
            list.get(depth).add(0, root.val);
        }
        zigzagLevelOrder(list, root.left, depth + 1);
        zigzagLevelOrder(list, root.right, depth + 1);
    }

    /**
     * 二叉搜索树迭代器 - 173
     * 实现一个二叉搜索树迭代器。你将使用二叉搜索树的根节点初始化迭代器。
     * 调用 next() 将返回二叉搜索树中的下一个最小的数。
     */
    public static class BSTIterator {

        private Stack<Integer> mIteratorStack = new Stack<>();

        public BSTIterator(TreeNode root) {
            push(root);
        }

        private void push(TreeNode root) {
            if (root == null) {
                return;
            }
            Stack<TreeNode> stack = new Stack<>();
            TreeNode cur = root;
            while (!stack.isEmpty() || cur != null) {
                while (cur != null) {
                    stack.push(cur);
                    cur = cur.right;
                }
                cur = stack.pop();
                mIteratorStack.push(cur.val);
                cur = cur.left;
            }
        }

        private void push1(TreeNode root) {
            if (root == null) {
                return;
            }
            push1(root.right);
            mIteratorStack.push(root.val);
            push1(root.left);
        }

        /**
         * @return the next smallest number
         */
        public int next() {
            if (hasNext()) {
                return mIteratorStack.pop();
            }
            throw new NullPointerException("there is no element!");
        }

        /**
         * @return whether we have a next smallest number
         */
        public boolean hasNext() {
            return !mIteratorStack.isEmpty();
        }
    }
}
