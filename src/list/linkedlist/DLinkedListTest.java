package list.linkedlist;

import list.RIterator;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-01-03 下午11:21
 **/
public class DLinkedListTest {

    @Test
    public void testSize() {
        DLinkedList<Integer> linkedList = new DLinkedList<>();
        Assert.assertTrue(linkedList.isEmpty());
        Assert.assertEquals(0, linkedList.size());

        for (int i = 0; i < 5; i++) {
            linkedList.add(i);
            Assert.assertEquals(i + 1, linkedList.size());
        }
    }

    @Test
    public void testContain() {

        DLinkedList<Integer> linkedList = new DLinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.add(i);
        }
        Assert.assertTrue(linkedList.contains(0));
        Assert.assertTrue(linkedList.contains(1));
        Assert.assertTrue(linkedList.contains(2));
        Assert.assertTrue(linkedList.contains(3));
        Assert.assertTrue(linkedList.contains(4));
        Assert.assertFalse(linkedList.contains(5));
    }

    @Test
    public void testIndexOf() {
        DLinkedList<Integer> linkedList = new DLinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.add(i);
        }

        for (int i = 0; i < 5; i++) {
            Assert.assertEquals(i, linkedList.indexOf(i));
        }

        // 空和一个元素
        DLinkedList<Integer> linkedList1 = new DLinkedList<>();
        Assert.assertEquals(-1, linkedList1.indexOf(0));
        linkedList1.add(0);
        Assert.assertEquals(0, linkedList1.indexOf(0));
        Assert.assertEquals(-1, linkedList1.indexOf(1));
    }

    @Test
    public void testGet() {
        DLinkedList<Integer> linkedList = new DLinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.add(i);
        }

        for (int i = 0; i < 5; i++) {
            Assert.assertEquals(i, linkedList.get(i).intValue());
        }

        // 空和一个元素
        DLinkedList<Integer> linkedList1 = new DLinkedList<>();
        linkedList1.add(0);
        Assert.assertEquals(0, linkedList1.indexOf(0));
    }

    @Test
    public void testClear() {
        DLinkedList<Integer> linkedList = new DLinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.add(i);
        }
        linkedList.clear();
        Assert.assertEquals(0, linkedList.size());
        Assert.assertTrue(linkedList.isEmpty());
    }

    @Test
    public void testSet() {

        DLinkedList<Integer> linkedList = new DLinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.add(i);
        }
        Assert.assertEquals(0, linkedList.set(0, 5).intValue());
        Assert.assertEquals(1, linkedList.set(1, 4).intValue());
        Assert.assertEquals(2, linkedList.set(2, 3).intValue());
        Assert.assertEquals(3, linkedList.set(3, 2).intValue());
        Assert.assertEquals(4, linkedList.set(4, 1).intValue());

        Assert.assertEquals(5, linkedList.size());

        for (int i = 0; i < 5; i++) {
            Assert.assertEquals(5 - i, linkedList.get(i).intValue());
        }
    }

    @Test
    public void testToArray() {

        DLinkedList<Integer> linkedList = new DLinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.add(i);
        }

        Object[] integers = linkedList.toArray();
        for (int i = 0; i < linkedList.size(); i++) {
            Assert.assertEquals(i, ((Integer) integers[i]).intValue());
        }
    }

    @Test
    public void testAdd() {
        DLinkedList<Integer> linkedList = new DLinkedList<>();
        for (int i = 0; i < 10; i++) {
            boolean result = linkedList.add(i);
            Assert.assertTrue(result);
        }

        linkedList.add(0, 10);
        Assert.assertEquals(11, linkedList.size());
        Assert.assertEquals(10, linkedList.get(0).intValue());

        linkedList.add(1, 11);
        Assert.assertEquals(12, linkedList.size());

        linkedList.add(5, 12);
        Assert.assertEquals(13, linkedList.size());

        linkedList.add(12, 13);
        Assert.assertEquals(14, linkedList.size());

        Object[] integers = linkedList.toArray();
        System.out.println(Arrays.toString(integers));

        // 空链表插入
        DLinkedList<Integer> linkedList1 = new DLinkedList<>();
        linkedList1.add(0, 14);
        Assert.assertEquals(1, linkedList1.size());
        Assert.assertEquals(14, linkedList1.get(0).intValue());
    }

    @Test
    public void testRemoveLast() {
        DLinkedList<Integer> linkedList = new DLinkedList<>();
        for (int i = 0; i < 10; i++) {
            boolean result = linkedList.add(i);
            Assert.assertTrue(result);
        }

        for (int i = 0; i < 10; i++) {
            Assert.assertEquals(9 - i, linkedList.removeLast().intValue());
            Assert.assertEquals(9 - i, linkedList.size());
        }
    }

    @Test
    public void testRemoveByIndex() {
        DLinkedList<Integer> linkedList = new DLinkedList<>();
        for (int i = 0; i < 5; i++) {
            boolean result = linkedList.add(i);
            Assert.assertTrue(result);
        }

        for (int i = 0; i < 5; i++) {
            int result = linkedList.removeByIndex(0);
            Assert.assertEquals(i, result);
            Assert.assertEquals(4 - i, linkedList.size());
        }

        Assert.assertEquals(0, linkedList.size());
        Assert.assertTrue(linkedList.isEmpty());
    }

    @Test
    public void testRemove() {
        DLinkedList<Integer> linkedList = new DLinkedList<>();
        for (int i = 0; i < 5; i++) {
            boolean result = linkedList.add(i);
            Assert.assertTrue(result);
        }
        Assert.assertFalse(linkedList.remove(5));

        Assert.assertTrue(linkedList.remove(0));
        Assert.assertEquals(4, linkedList.size());

        Assert.assertTrue(linkedList.remove(3));
        Assert.assertEquals(3, linkedList.size());

        Assert.assertTrue(linkedList.remove(4));
        Assert.assertEquals(2, linkedList.size());

        Assert.assertTrue(linkedList.remove(1));
        Assert.assertEquals(1, linkedList.size());

        Assert.assertTrue(linkedList.remove(2));
        Assert.assertEquals(0, linkedList.size());
        Assert.assertTrue(linkedList.isEmpty());

    }

    @Test
    public void testIterator() {

        DLinkedList<Integer> linkedList = new DLinkedList<>();
        for (int i = 0; i < 5; i++) {
            boolean result = linkedList.add(i);
            Assert.assertTrue(result);
        }

        RIterator<Integer> iterator = linkedList.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Assert.assertEquals(i, iterator.next().intValue());
            i++;
        }
        Assert.assertEquals(5, linkedList.size());
    }
}
