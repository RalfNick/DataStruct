package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-03-02 下午8:21
 **/
public class BinaryTree<T> {

    public static class TreeNode<T> {
        public T data;
        public TreeNode<T> left;
        public TreeNode<T> right;
    }

    /**
     * 查找最大深度
     *
     * @param root 根节点
     * @return
     */
    public int getMaxDepth(TreeNode<T> root) {
        if (root == null) {
            return 0;
        }
        int leftMax = getMaxDepth(root.left);
        int rightMax = getMaxDepth(root.right);
        return 1 + Math.max(leftMax, rightMax);
    }

    /**
     * 求最小深度
     *
     * @param root 根节点
     * @return
     */
    public int getMinDepth(TreeNode<T> root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        return Math.min(getMinDepth(root.left), getMinDepth(root.right)) + 1;
    }

    /**
     * 获取二叉树节点总数
     *
     * @param root 根节点
     * @return
     */
    public int totalNodes(TreeNode<T> root) {
        if (root == null) {
            return 0;
        }
        int leftNum = totalNodes(root.left);
        int rightNum = totalNodes(root.right);
        return 1 + leftNum + rightNum;
    }

    /**
     * 获取所有叶子节点总数
     *
     * @param root 根节点
     * @return
     */
    public int totalChildNodes(TreeNode<T> root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        return totalChildNodes(root.left) + totalChildNodes(root.right);
    }

    /**
     * 获取第 K 层节点数
     *
     * @param root 根节点
     * @param k    k 层
     * @return
     */
    public int getLevelKNodes(TreeNode<T> root, int k) {
        if (root == null || k < 1) {
            return 0;
        }
        if (k == 1) {
            return 1;
        }
        int left = getLevelKNodes(root.left, k - 1);
        int right = getLevelKNodes(root.right, k - 1);
        return left + right;
    }

    /**
     * 层次遍历，每一层从左向右遍历
     * 要点：
     * <p>
     * 1.利用队列添加每一层节点，进行遍历
     * <p>
     * 2.如果当前节点存在子节点，加到队列尾部，下次循环遍历取出
     *
     * @param root 根节点
     * @return
     */
    public List<List<T>> printTreeNodes(TreeNode<T> root) {
        List<List<T>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode<T>> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            int size = stack.size();
            List<T> row = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode<T> node = stack.pop();
                row.add(node.data);
                if (node.right != null) {
                    stack.push(node.right);
                }
                if (node.left != null) {
                    stack.push(node.left);
                }
            }
            result.add(row);
        }
        return result;
    }

    /**
     * 计算二叉树的最大宽度，通过使用队列计算，每一层的节点数即为每一层的宽度取出最大的一层的宽度
     *
     * @param root 根节点
     * @return
     */
    public int getBinaryTreeWidth(TreeNode<T> root) {
        if (root == null) {
            return 0;
        }
        LinkedList<TreeNode<T>> queue = new LinkedList<>();
        queue.push(root);
        int width = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            if (size > width) {
                width = size;
            }
            for (int i = 0; i < size; i++) {
                TreeNode<T> node = queue.poll();
                if (node.left != null) {
                    queue.push(node.left);
                }
                if (node.right != null) {
                    queue.push(node.right);
                }
            }
        }
        return width;
    }

    /**
     * 判断两个二叉树是否相同
     *
     * @param root1 树 1 根节点
     * @param root2 树 2 根节点
     * @return
     */
    public boolean isSameTreeNode(TreeNode<T> root1, TreeNode<T> root2) {
        if (root1 == null && root2 == null) {
            return true;
        } else if (root1 == null || root2 == null) {
            return false;
        }
        if (!root1.data.equals(root2.data)) {
            return false;
        }
        boolean left = isSameTreeNode(root1.left, root2.left);
        boolean right = isSameTreeNode(root1.right, root2.right);

        return left && right;
    }

    /**
     * 前序遍历
     *
     * @param root 根节点
     * @param list 集合
     * @return
     */
    public void preOrder(List<T> list, TreeNode<T> root) {
        if (root == null) {
            return;
        }
        list.add(root.data);
        preOrder(list, root.left);
        preOrder(list, root.right);
    }

    /**
     * 后序遍历
     *
     * @param root 根节点
     * @param list 集合
     * @return
     */
    public void postOrder(List<T> list, TreeNode<T> root) {
        if (root == null) {
            return;
        }
        postOrder(list, root.left);
        postOrder(list, root.right);
        list.add(root.data);
    }

    /**
     * 中序遍历
     *
     * @param root 根节点
     * @param list 集合
     * @return
     */
    public void inOrder(List<T> list, TreeNode<T> root) {
        if (root == null) {
            return;
        }
        inOrder(list, root.left);
        list.add(root.data);
        inOrder(list, root.right);
    }
}
