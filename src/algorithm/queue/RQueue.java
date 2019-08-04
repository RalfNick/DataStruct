package algorithm.queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-07-29 上午8:22
 **/
public class RQueue {

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
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.push(root);
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> row = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.removeFirst();
                row.add(node.val);
                if (count % 2 == 1) {
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                } else {
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                }
            }
            count++;
            result.add(row);
        }
        return result;
    }
}
