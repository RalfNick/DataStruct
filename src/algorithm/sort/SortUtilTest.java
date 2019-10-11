package algorithm.sort;

import org.junit.Assert;
import org.junit.Test;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-09-18 下午9:14
 **/
public class SortUtilTest {

    @Test
    public void testMerge() {
        // 输入: [[1,3],[2,6],[8,10],[15,18]]
        // 输出: [[1,6],[8,10],[15,18]]
        int[][] merge = SortUtil.merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}});
        Assert.assertEquals(3, merge.length);
        Assert.assertArrayEquals(new int[]{1, 6}, merge[0]);
        Assert.assertArrayEquals(new int[]{8, 10}, merge[1]);
        Assert.assertArrayEquals(new int[]{15, 18}, merge[2]);
        // 输入: [[1,4],[4,5]]
        // 输出: [[1,5]]
        int[][] merge1 = SortUtil.merge(new int[][]{{1, 4}, {4, 5}});
        Assert.assertEquals(1, merge1.length);
        Assert.assertArrayEquals(new int[]{1, 5}, merge1[0]);
    }


    @Test
    public void testInsertionSortList() {
        SortUtil.ListNode node1 = new SortUtil.ListNode(4);
        SortUtil.ListNode node2 = new SortUtil.ListNode(2);
        SortUtil.ListNode node3 = new SortUtil.ListNode(1);
        SortUtil.ListNode node4 = new SortUtil.ListNode(3);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        SortUtil.ListNode node = SortUtil.insertionSortList(node1);
        Assert.assertEquals(1, node.val);
        Assert.assertEquals(2, node.next.val);
        Assert.assertEquals(3, node.next.next.val);
        Assert.assertEquals(4, node.next.next.next.val);
    }

    @Test
    public void testSortList() {
        SortUtil.ListNode node1 = new SortUtil.ListNode(4);
        SortUtil.ListNode node2 = new SortUtil.ListNode(2);
        SortUtil.ListNode node3 = new SortUtil.ListNode(1);
        SortUtil.ListNode node4 = new SortUtil.ListNode(3);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        SortUtil.ListNode node = SortUtil.sortList(node1);
        Assert.assertEquals(1, node.val);
        Assert.assertEquals(2, node.next.val);
        Assert.assertEquals(3, node.next.next.val);
        Assert.assertEquals(4, node.next.next.next.val);


        SortUtil.ListNode node5 = new SortUtil.ListNode(-1);
        SortUtil.ListNode node6 = new SortUtil.ListNode(5);
        SortUtil.ListNode node7 = new SortUtil.ListNode(3);
        SortUtil.ListNode node8 = new SortUtil.ListNode(4);
        SortUtil.ListNode node9 = new SortUtil.ListNode(0);

        node5.next = node6;
        node6.next = node7;
        node7.next = node8;
        node8.next = node9;

        SortUtil.ListNode nod = SortUtil.sortList(node5);
        Assert.assertEquals(-1, nod.val);
        Assert.assertEquals(0, nod.next.val);
        Assert.assertEquals(3, nod.next.next.val);
        Assert.assertEquals(4, nod.next.next.next.val);
        Assert.assertEquals(5, nod.next.next.next.next.val);
    }
}
