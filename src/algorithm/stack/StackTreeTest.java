package algorithm.stack;

import algorithm.queue.RQueue;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-07-25 上午9:09
 **/
public class StackTreeTest {

    @Test
    public void testZigZagLevelTest() {

        StackTree.TreeNode treeNode1 = new StackTree.TreeNode(3);
        StackTree.TreeNode treeNode2 = new StackTree.TreeNode(9);
        StackTree.TreeNode treeNode3 = new StackTree.TreeNode(20);
        StackTree.TreeNode treeNode4 = new StackTree.TreeNode(15);
        StackTree.TreeNode treeNode5 = new StackTree.TreeNode(7);

        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode3.left = treeNode4;
        treeNode3.right = treeNode5;

        List<List<Integer>> result = StackTree.zigzagLevelOrder(treeNode1);
        Assert.assertTrue(!result.isEmpty());
        Assert.assertEquals(3, result.size());
        // 每行数量
        Assert.assertEquals(1, result.get(0).size());
        Assert.assertEquals(2, result.get(1).size());
        Assert.assertEquals(2, result.get(2).size());

        // 第一行
        Assert.assertEquals(3, result.get(0).get(0).intValue());

        // 第二行
        Assert.assertEquals(20, result.get(1).get(0).intValue());
        Assert.assertEquals(9, result.get(1).get(1).intValue());
        // 第三行
        Assert.assertEquals(15, result.get(2).get(0).intValue());
        Assert.assertEquals(7, result.get(2).get(1).intValue());

    }
    
    @Test
    public void testBSTIterator() {
        StackTree.TreeNode treeNode1 = new StackTree.TreeNode(7);
        StackTree.TreeNode treeNode2 = new StackTree.TreeNode(3);
        StackTree.TreeNode treeNode3 = new StackTree.TreeNode(9);
        StackTree.TreeNode treeNode4 = new StackTree.TreeNode(15);
        StackTree.TreeNode treeNode5 = new StackTree.TreeNode(20);

        treeNode1.left = treeNode2;
        treeNode1.right = treeNode4;
        treeNode4.left = treeNode3;
        treeNode4.right = treeNode5;

        StackTree.BSTIterator iterator = new StackTree.BSTIterator(treeNode1);
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(3, iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(7, iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(9, iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(15, iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(20, iterator.next());
        Assert.assertFalse(iterator.hasNext());
    }
}
