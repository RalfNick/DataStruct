package algorithm.tree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-08-06 下午8:00
 **/
public class RTreeTest {

    private RTree.TreeNode node1;

    @Before
    public void init() {
        // [3,9,20,null,null,15,7]
        node1 = new RTree.TreeNode(3);
        RTree.TreeNode node2 = new RTree.TreeNode(9);
        RTree.TreeNode node3 = new RTree.TreeNode(20);
        RTree.TreeNode node4 = new RTree.TreeNode(15);
        RTree.TreeNode node5 = new RTree.TreeNode(7);

        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;
    }

    @Test
    public void testMaxDepth() {
        // [3,9,20,null,null,15,7]
        int maxDepth = RTree.maxDepth(node1);
        Assert.assertEquals(3, maxDepth);
    }

    @Test
    public void testMinDepth() {
        int minDepth = RTree.minDepth(node1);
        Assert.assertEquals(2, minDepth);

        RTree.TreeNode node6 = new RTree.TreeNode(1);
        RTree.TreeNode node7 = new RTree.TreeNode(2);
        node6.left = node7;
        minDepth = RTree.minDepth(node6);
        Assert.assertEquals(1, minDepth);
    }

    @Test
    public void testNumberOfNode() {
        int num = RTree.numOfTreeNode(node1);
        Assert.assertEquals(5, num);
    }

    @Test
    public void testNumberOfChildNode() {
        int num = RTree.numOfChildNode(node1);
        Assert.assertEquals(3, num);
    }

    @Test
    public void testNumOfkLevelTreeNode() {
        int num = RTree.numOfkLevelTreeNode(node1, 1);
        Assert.assertEquals(1, num);
        num = RTree.numOfkLevelTreeNode(node1, 2);
        Assert.assertEquals(2, num);
        num = RTree.numOfkLevelTreeNode(node1, 3);
        Assert.assertEquals(2, num);
    }

    @Test
    public void testPreOrder() {
//        List<Integer> list = RTree.preOrder(node1);
        List<Integer> list = RTree.preOrderIteration(node1);
        Assert.assertEquals(5, list.size());
        Assert.assertEquals(3, list.get(0).intValue());
        Assert.assertEquals(9, list.get(1).intValue());
        Assert.assertEquals(20, list.get(2).intValue());
        Assert.assertEquals(15, list.get(3).intValue());
        Assert.assertEquals(7, list.get(4).intValue());
    }

    @Test
    public void testInOrder() {
//        List<Integer> list = RTree.inOrder(node1);
        List<Integer> list = RTree.inOrderIteration(node1);
        Assert.assertEquals(5, list.size());
        Assert.assertEquals(9, list.get(0).intValue());
        Assert.assertEquals(3, list.get(1).intValue());
        Assert.assertEquals(15, list.get(2).intValue());
        Assert.assertEquals(20, list.get(3).intValue());
        Assert.assertEquals(7, list.get(4).intValue());
    }

    @Test
    public void testPostOrder() {
//        List<Integer> list = RTree.postOrder(node1);
        List<Integer> list = RTree.postOrderIteration(node1);
        Assert.assertEquals(5, list.size());
        Assert.assertEquals(9, list.get(0).intValue());
        Assert.assertEquals(15, list.get(1).intValue());
        Assert.assertEquals(7, list.get(2).intValue());
        Assert.assertEquals(20, list.get(3).intValue());
        Assert.assertEquals(3, list.get(4).intValue());
    }

    @Test
    public void testIsValidBST() {
        RTree.TreeNode root = new RTree.TreeNode(2);
        RTree.TreeNode node2 = new RTree.TreeNode(1);
        RTree.TreeNode node3 = new RTree.TreeNode(3);
        root.left = node2;
        root.right = node3;

        Assert.assertTrue(RTree.isValidBST(root));

        RTree.TreeNode root1 = new RTree.TreeNode(5);
        RTree.TreeNode node4 = new RTree.TreeNode(1);
        RTree.TreeNode node5 = new RTree.TreeNode(4);
        RTree.TreeNode node6 = new RTree.TreeNode(3);
        RTree.TreeNode node7 = new RTree.TreeNode(6);
        root1.left = node4;
        root1.right = node5;
        node5.left = node6;
        node5.right = node7;

        Assert.assertFalse(RTree.isValidBST(root1));
    }

    @Test
    public void testLevelOrder() {
        List<List<Integer>> list = RTree.levelOrder(node1);
        Assert.assertEquals(3, list.size());
        Assert.assertEquals(3, list.get(0).get(0).intValue());
        Assert.assertEquals(9, list.get(1).get(0).intValue());
        Assert.assertEquals(20, list.get(1).get(1).intValue());
        Assert.assertEquals(15, list.get(2).get(0).intValue());
        Assert.assertEquals(7, list.get(2).get(1).intValue());
    }

    @Test
    public void testZigzagLevelOrder() {
//        List<List<Integer>> list = RTree.zigzagLevelOrder(node1);
        List<List<Integer>> list = RTree.zigzagLevelOrder1(node1);
        Assert.assertEquals(3, list.size());
        Assert.assertEquals(3, list.get(0).get(0).intValue());
        Assert.assertEquals(20, list.get(1).get(0).intValue());
        Assert.assertEquals(9, list.get(1).get(1).intValue());
        Assert.assertEquals(15, list.get(2).get(0).intValue());
        Assert.assertEquals(7, list.get(2).get(1).intValue());
    }

    @Test
    public void testBuildTree() {
        // 前序遍历 preOrder = [3,9,20,15,7]
        // 中序遍历 inOrder = [9,3,15,20,7]
        int[] preOrder = {3, 9, 20, 15, 7};
        int[] inOrder = {9, 3, 15, 20, 7};
        RTree.TreeNode root = RTree.buildTree(preOrder, inOrder);
        Assert.assertEquals(3, root.val);
        Assert.assertEquals(9, root.left.val);
        Assert.assertEquals(20, root.right.val);
        Assert.assertEquals(15, root.right.left.val);
        Assert.assertEquals(7, root.right.right.val);

        // 1 2 4 6 7 8 3 5
        // 4 7 6 8 2 1 3 5
        int[] preOrder1 = {1, 2, 4, 6, 7, 8, 3, 5};
        int[] inOrder1 = {4, 7, 6, 8, 2, 1, 3, 5};
        RTree.TreeNode root1 = RTree.buildTree(preOrder1, inOrder1);
        Assert.assertEquals(1, root1.val);
        Assert.assertEquals(2, root1.left.val);
        Assert.assertEquals(3, root1.right.val);
        Assert.assertEquals(5, root1.right.right.val);
        Assert.assertEquals(4, root1.left.left.val);
        Assert.assertEquals(6, root1.left.left.right.val);
        Assert.assertEquals(7, root1.left.left.right.left.val);
        Assert.assertEquals(8, root1.left.left.right.right.val);
    }

    @Test
    public void testBuildTree1() {
        // 中序遍历 inOrder = [9,3,15,20,7]
        // 后序遍历 postorder = [9,15,7,20,3]
        int[] inOrder = {9, 3, 15, 20, 7};
        int[] postOrder = {9, 15, 7, 20, 3};
        RTree.TreeNode root = RTree.buildTree1(inOrder, postOrder);
        Assert.assertEquals(3, root.val);
        Assert.assertEquals(9, root.left.val);
        Assert.assertEquals(20, root.right.val);
        Assert.assertEquals(15, root.right.left.val);
        Assert.assertEquals(7, root.right.right.val);
    }

    @Test
    public void testLevelOrderBottom() {
        List<List<Integer>> list = RTree.levelOrderBottom(node1);
        Assert.assertEquals(3, list.size());
        Assert.assertEquals(15, list.get(0).get(0).intValue());
        Assert.assertEquals(7, list.get(0).get(1).intValue());
        Assert.assertEquals(9, list.get(1).get(0).intValue());
        Assert.assertEquals(20, list.get(1).get(1).intValue());
        Assert.assertEquals(3, list.get(2).get(0).intValue());
    }

    @Test
    public void testSortedArrayToBST() {
        int[] arr = {-10, -3, 0, 5, 9};
//        RTree.TreeNode root = RTree.sortedArrayToBST(arr);
        RTree.TreeNode root = RTree.sortedBST(arr);
        Assert.assertEquals(0, root.val);
        Assert.assertEquals(-3, root.left.val);
        Assert.assertEquals(9, root.right.val);
        Assert.assertEquals(5, root.right.left.val);
        Assert.assertEquals(-10, root.left.left.val);
    }

    @Test
    public void testHasPathSum() {
        RTree.TreeNode root = new RTree.TreeNode(5);
        RTree.TreeNode node2 = new RTree.TreeNode(4);
        RTree.TreeNode node3 = new RTree.TreeNode(8);
        RTree.TreeNode node4 = new RTree.TreeNode(11);
        RTree.TreeNode node5 = new RTree.TreeNode(13);
        RTree.TreeNode node6 = new RTree.TreeNode(4);
        RTree.TreeNode node7 = new RTree.TreeNode(7);
        RTree.TreeNode node8 = new RTree.TreeNode(2);
        RTree.TreeNode node9 = new RTree.TreeNode(1);

        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node4.left = node7;
        node4.right = node8;
        node3.left = node5;
        node3.right = node6;
        node6.right = node9;

        Assert.assertTrue(RTree.hasPathSum(root, 22));
        Assert.assertFalse(RTree.hasPathSum(root, 19));
        Assert.assertTrue(RTree.hasPathSum(root, 18));
        Assert.assertTrue(RTree.hasPathSum(root, 26));
    }

    @Test
    public void testPathSum() {
        RTree.TreeNode root = new RTree.TreeNode(5);
        RTree.TreeNode node2 = new RTree.TreeNode(4);
        RTree.TreeNode node3 = new RTree.TreeNode(8);
        RTree.TreeNode node4 = new RTree.TreeNode(11);
        RTree.TreeNode node5 = new RTree.TreeNode(13);
        RTree.TreeNode node6 = new RTree.TreeNode(4);
        RTree.TreeNode node7 = new RTree.TreeNode(7);
        RTree.TreeNode node8 = new RTree.TreeNode(2);
        RTree.TreeNode node9 = new RTree.TreeNode(5);
        RTree.TreeNode node10 = new RTree.TreeNode(1);

        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node4.left = node7;
        node4.right = node8;
        node3.left = node5;
        node3.right = node6;
        node6.left = node9;
        node6.right = node10;

        List<List<Integer>> list = RTree.pathSum(root, 22);
        Assert.assertEquals(2, list.size());
        //[5,4,11,2],
        //[5,8,4,5]
        Assert.assertEquals(5, list.get(0).get(0).intValue());
        Assert.assertEquals(4, list.get(0).get(1).intValue());
        Assert.assertEquals(11, list.get(0).get(2).intValue());
        Assert.assertEquals(2, list.get(0).get(3).intValue());

        Assert.assertEquals(5, list.get(1).get(0).intValue());
        Assert.assertEquals(8, list.get(1).get(1).intValue());
        Assert.assertEquals(4, list.get(1).get(2).intValue());
        Assert.assertEquals(5, list.get(1).get(3).intValue());
    }

    @Test
    public void testPathSum3() {
        RTree.TreeNode root = new RTree.TreeNode(10);
        RTree.TreeNode node2 = new RTree.TreeNode(5);
        RTree.TreeNode node3 = new RTree.TreeNode(-3);
        RTree.TreeNode node4 = new RTree.TreeNode(3);
        RTree.TreeNode node5 = new RTree.TreeNode(2);
        RTree.TreeNode node6 = new RTree.TreeNode(3);
        RTree.TreeNode node7 = new RTree.TreeNode(-2);
        RTree.TreeNode node8 = new RTree.TreeNode(1);
        RTree.TreeNode node9 = new RTree.TreeNode(11);

        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node4.left = node6;
        node4.right = node7;
        node5.right = node8;
        node3.right = node9;

        List<List<Integer>> list = RTree.pathSum3(root, 8);
        Assert.assertEquals(3, list.size());
    }

    @Test
    public void testFlatten() {
        RTree.TreeNode root = new RTree.TreeNode(1);
        RTree.TreeNode node2 = new RTree.TreeNode(2);
        RTree.TreeNode node3 = new RTree.TreeNode(3);
        RTree.TreeNode node4 = new RTree.TreeNode(4);
        RTree.TreeNode node5 = new RTree.TreeNode(5);
        RTree.TreeNode node6 = new RTree.TreeNode(6);

        root.left = node2;
        root.right = node5;
        node2.left = node3;
        node2.right = node4;
        node5.right = node6;

        RTree.flatten(root);
        Assert.assertEquals(1, root.val);
        Assert.assertEquals(2, root.right.val);
        Assert.assertEquals(3, root.right.right.val);
        Assert.assertEquals(4, root.right.right.right.val);
        Assert.assertEquals(5, root.right.right.right.right.val);
        Assert.assertEquals(6, root.right.right.right.right.right.val);
    }

    @Test
    public void testSumNumbers() {
        RTree.TreeNode root = new RTree.TreeNode(1);
        RTree.TreeNode node2 = new RTree.TreeNode(2);
        RTree.TreeNode node3 = new RTree.TreeNode(3);
        root.left = node2;
        root.right = node3;
        Assert.assertEquals(25, RTree.sumNumbers(root));

        RTree.TreeNode node4 = new RTree.TreeNode(4);
        RTree.TreeNode node5 = new RTree.TreeNode(9);
        RTree.TreeNode node6 = new RTree.TreeNode(5);
        RTree.TreeNode node7 = new RTree.TreeNode(1);
        RTree.TreeNode node8 = new RTree.TreeNode(0);

        node4.left = node5;
        node4.right = node8;
        node5.left = node6;
        node5.right = node7;

        Assert.assertEquals(1026, RTree.sumNumbers(node4));
    }

    @Test
    public void testMaxPathSum() {
        // 1 2 3
        RTree.TreeNode root = new RTree.TreeNode(1);
        RTree.TreeNode node2 = new RTree.TreeNode(2);
        RTree.TreeNode node3 = new RTree.TreeNode(3);

        root.left = node2;
        root.right = node3;
        Assert.assertEquals(6, RTree.maxPathSum(root));

        // [-10,9,20,null,null,15,7]
        RTree.TreeNode node4 = new RTree.TreeNode(-10);
        RTree.TreeNode node5 = new RTree.TreeNode(9);
        RTree.TreeNode node6 = new RTree.TreeNode(20);
        RTree.TreeNode node7 = new RTree.TreeNode(15);
        RTree.TreeNode node8 = new RTree.TreeNode(7);

        node4.right = node6;
        node4.left = node5;
        node6.left = node7;
        node6.right = node8;

        Assert.assertEquals(42, RTree.maxPathSum(node4));
    }

    @Test
    public void testRightSideView() {
        // 输入: [1,2,3,null,5,null,4]
        // 输出: [1, 3, 4]
        RTree.TreeNode root = new RTree.TreeNode(1);
        RTree.TreeNode node2 = new RTree.TreeNode(2);
        RTree.TreeNode node3 = new RTree.TreeNode(3);
        RTree.TreeNode node4 = new RTree.TreeNode(4);
        RTree.TreeNode node5 = new RTree.TreeNode(5);

        root.left = node2;
        root.right = node3;
        node2.right = node5;
        node3.right = node4;

        Assert.assertEquals(3, RTree.rightSideView(root).size());
        Assert.assertEquals(1, RTree.rightSideView(root).get(0).intValue());
        Assert.assertEquals(3, RTree.rightSideView(root).get(1).intValue());
        Assert.assertEquals(4, RTree.rightSideView(root).get(2).intValue());
    }

    @Test
    public void testKthSmallest() {

        RTree.TreeNode root = new RTree.TreeNode(2);
        RTree.TreeNode node2 = new RTree.TreeNode(1);
        RTree.TreeNode node3 = new RTree.TreeNode(4);
        RTree.TreeNode node4 = new RTree.TreeNode(3);
        RTree.TreeNode node5 = new RTree.TreeNode(5);

        root.left = node2;
        root.right = node3;
        node3.left = node4;
        node3.right = node5;
        Assert.assertEquals(1, RTree.kthSmallest(root, 1));
        Assert.assertEquals(2, RTree.kthSmallest(root, 2));
        Assert.assertEquals(3, RTree.kthSmallest(root, 3));
        Assert.assertEquals(4, RTree.kthSmallest(root, 4));
        Assert.assertEquals(5, RTree.kthSmallest(root, 5));
    }

    @Test
    public void testBinaryTreePaths() {
        RTree.TreeNode root = new RTree.TreeNode(1);
        RTree.TreeNode node2 = new RTree.TreeNode(2);
        RTree.TreeNode node3 = new RTree.TreeNode(3);
        RTree.TreeNode node4 = new RTree.TreeNode(5);

        root.left = node2;
        root.right = node3;
        node2.right = node4;

        List<String> list = RTree.binaryTreePaths(root);
        Assert.assertEquals(2, list.size());
        Assert.assertEquals("1->2->5", list.get(0));
        Assert.assertEquals("1->3", list.get(1));
    }

    @Test
    public void testSumOfLeftLeaves() {
        Assert.assertEquals(24, RTree.sumOfLeftLeaves(node1));
    }

    @Test
    public void testSearchRange() {
        // Input：{20,8,22,4,12},10,22
        // Output：[12,20,22]
        RTree.TreeNode root = new RTree.TreeNode(20);
        RTree.TreeNode node1 = new RTree.TreeNode(8);
        RTree.TreeNode node2 = new RTree.TreeNode(22);
        RTree.TreeNode node3 = new RTree.TreeNode(4);
        RTree.TreeNode node4 = new RTree.TreeNode(12);

        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;

        List<Integer> list = RTree.searchRange(root, 10, 22);
        Assert.assertEquals(3, list.size());
        Assert.assertEquals(12, list.get(0).intValue());
        Assert.assertEquals(20, list.get(1).intValue());
        Assert.assertEquals(22, list.get(2).intValue());
    }

    @Test
    public void testRobIII() {
        // [3,2,3,null,3,null,1]
        RTree.TreeNode root = new RTree.TreeNode(3);
        RTree.TreeNode node1 = new RTree.TreeNode(2);
        RTree.TreeNode node2 = new RTree.TreeNode(3);
        RTree.TreeNode node3 = new RTree.TreeNode(3);
        RTree.TreeNode node4 = new RTree.TreeNode(1);

        root.left = node1;
        root.right = node2;
        node1.right = node3;
        node2.right = node4;

        int res = RTree.robIII(root);
        Assert.assertEquals(7, res);
    }

    @Test
    public void testFindMode() {
        // [1,null,2,2],
        RTree.TreeNode root = new RTree.TreeNode(1);
        RTree.TreeNode node1 = new RTree.TreeNode(2);
        RTree.TreeNode node2 = new RTree.TreeNode(2);
        root.right = node1;
        node1.left = node2;

        int[] result = RTree.findMode(root);
        Assert.assertEquals(1, result.length);
        Assert.assertEquals(2, result[0]);
    }

    @Test
    public void testLargestValues() {
        RTree.TreeNode root = new RTree.TreeNode(1);
        RTree.TreeNode node1 = new RTree.TreeNode(2);
        RTree.TreeNode node2 = new RTree.TreeNode(3);
        RTree.TreeNode node3 = new RTree.TreeNode(5);
        RTree.TreeNode node4 = new RTree.TreeNode(3);
        RTree.TreeNode node5 = new RTree.TreeNode(9);
        root.left = node2;
        root.right = node1;
        node1.right = node5;
        node2.left = node3;
        node2.right = node4;
        List<Integer> list = RTree.largestValues(root);
        Assert.assertEquals(3, list.size());
        Assert.assertEquals(1, list.get(0).intValue());
        Assert.assertEquals(3, list.get(1).intValue());
        Assert.assertEquals(9, list.get(2).intValue());
    }

    @Test
    public void testBstFromPreorder() {
        // 输入：[8,5,1,7,10,12]
        // 输出：[8,5,10,1,7,null,12]
        int[] arr = {8, 5, 1, 7, 10, 12};
        RTree.TreeNode root = RTree.bstFromPreorder(arr);
        Assert.assertEquals(8, root.val);
        Assert.assertEquals(5, root.left.val);
        Assert.assertEquals(1, root.left.left.val);
        Assert.assertEquals(7, root.left.right.val);
        Assert.assertEquals(10, root.right.val);
        Assert.assertEquals(12, root.right.right.val);
    }

    @Test
    public void testBstFromPreorder1() {
        // 输入：[8,5,1,7,10,12]
        // 输出：[8,5,10,1,7,null,12]
        int[] arr = {8, 5, 1, 7, 10, 12};
        RTree.TreeNode root = RTree.bstFromPreorder1(arr);
        Assert.assertEquals(8, root.val);
        Assert.assertEquals(5, root.left.val);
        Assert.assertEquals(1, root.left.left.val);
        Assert.assertEquals(7, root.left.right.val);
        Assert.assertEquals(10, root.right.val);
        Assert.assertEquals(12, root.right.right.val);
    }

    @Test
    public void numTrees() {
        Assert.assertEquals(5, RTree.numTrees(3));
    }
}

