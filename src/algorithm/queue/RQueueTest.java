package algorithm.queue;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-07-29 上午8:33
 **/
public class RQueueTest {

    @Test
    public void testZigZagLevelTest() {

        RQueue.TreeNode treeNode1 = new RQueue.TreeNode(3);
        RQueue.TreeNode treeNode2 = new RQueue.TreeNode(9);
        RQueue.TreeNode treeNode3 = new RQueue.TreeNode(20);
        RQueue.TreeNode treeNode4 = new RQueue.TreeNode(15);
        RQueue.TreeNode treeNode5 = new RQueue.TreeNode(7);

        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode3.left = treeNode4;
        treeNode3.right = treeNode5;

        List<List<Integer>> result = RQueue.zigzagLevelOrder(treeNode1);
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
    public void testLeastInterval() {
        // tasks = ["A","A","A","B","B","B"], n = 2
        String[] tasks = {"A", "A", "A", "B", "B", "B"};
        int result = RQueue.leastInterval(tasks, 2);
        Assert.assertEquals(8, result);
    }
}
