package list.linkedlist;

import list.RIterator;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2018-12-23 下午6:01
 **/
public class SLinkedListTest {

    @Test
    public void testSize() {
        SLinkedList<Integer> linkedList = new SLinkedList<>();
        Assert.assertTrue(linkedList.isEmpty());
        Assert.assertEquals(0, linkedList.size());

        for (int i = 0; i < 5; i++) {
            linkedList.add(i);
            Assert.assertEquals(i + 1, linkedList.size());
        }
    }

    @Test
    public void testContain() {

        SLinkedList<Integer> linkedList = new SLinkedList<>();
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
        SLinkedList<Integer> linkedList = new SLinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.add(i);
        }

        for (int i = 0; i < 5; i++) {
            Assert.assertEquals(i, linkedList.indexOf(i));
        }

        // 空和一个元素
        SLinkedList<Integer> linkedList1 = new SLinkedList<>();
        Assert.assertEquals(-1, linkedList1.indexOf(0));
        linkedList1.add(0);
        Assert.assertEquals(0, linkedList1.indexOf(0));
        Assert.assertEquals(-1, linkedList1.indexOf(1));
    }

    @Test
    public void testGet() {
        SLinkedList<Integer> linkedList = new SLinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.add(i);
        }

        for (int i = 0; i < 5; i++) {
            Assert.assertEquals(i, linkedList.get(i).intValue());
        }

        // 空和一个元素
        SLinkedList<Integer> linkedList1 = new SLinkedList<>();
        linkedList1.add(0);
        Assert.assertEquals(0, linkedList1.indexOf(0));
    }

    @Test
    public void testClear() {
        SLinkedList<Integer> linkedList = new SLinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.add(i);
        }
        linkedList.clear();
        Assert.assertEquals(0, linkedList.size());
        Assert.assertTrue(linkedList.isEmpty());
    }

    @Test
    public void testSet() {

        SLinkedList<Integer> linkedList = new SLinkedList<>();
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

        SLinkedList<Integer> linkedList = new SLinkedList<>();
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
        SLinkedList<Integer> linkedList = new SLinkedList<>();
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

        linkedList.add(13, 13);
        Assert.assertEquals(14, linkedList.size());

        Object[] integers = linkedList.toArray();
        System.out.println(Arrays.toString(integers));

        // 空链表插入
        SLinkedList<Integer> linkedList1 = new SLinkedList<>();
        linkedList1.add(0, 14);
        Assert.assertEquals(1, linkedList1.size());
        Assert.assertEquals(14, linkedList1.get(0).intValue());
    }

    @Test
    public void testRemoveLast() {
        SLinkedList<Integer> linkedList = new SLinkedList<>();
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
        SLinkedList<Integer> linkedList = new SLinkedList<>();
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
        SLinkedList<Integer> linkedList = new SLinkedList<>();
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

        SLinkedList<Integer> linkedList = new SLinkedList<>();
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

    /******************************************练习测试*****************************************/
    /**
     * 把该链表逆置
     * 例如链表为 3->7->10 , 逆置后变为  10->7->3
     */
    @Test
    public void reverse() {
        SLinkedList<Integer> linkedList = new SLinkedList<>();
        linkedList.add(3);
        linkedList.add(7);
        linkedList.add(10);
        linkedList.add(12);
        Assert.assertEquals(3, linkedList.get(0).intValue());
        Assert.assertEquals(7, linkedList.get(1).intValue());
        Assert.assertEquals(10, linkedList.get(2).intValue());
        Assert.assertEquals(12, linkedList.get(3).intValue());

        linkedList.reverse();
        Assert.assertEquals(12, linkedList.get(0).intValue());
        Assert.assertEquals(10, linkedList.get(1).intValue());
        Assert.assertEquals(7, linkedList.get(2).intValue());
        Assert.assertEquals(3, linkedList.get(3).intValue());
    }

    /**
     * 删除一个单链表的前半部分
     * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
     * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
     */
    @Test
    public void removeFirstHalf() {
        SLinkedList<Integer> linkedList = new SLinkedList<>();
        linkedList.add(2);
        linkedList.add(5);
        linkedList.add(7);
        linkedList.add(8);
        linkedList.add(9);

        linkedList.removeFirstHalf();
        Assert.assertEquals(3, linkedList.size());

        Assert.assertEquals(7, linkedList.get(0).intValue());
        Assert.assertEquals(8, linkedList.get(1).intValue());
        Assert.assertEquals(9, linkedList.get(2).intValue());
    }

    /**
     * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
     */
    @Test
    public void remove() {
        SLinkedList<Integer> linkedList = new SLinkedList<>();
        linkedList.add(2);
        linkedList.add(5);
        linkedList.add(7);
        linkedList.add(8);
        linkedList.add(9);

        linkedList.remove(0, 4);
        Assert.assertFalse(linkedList.isEmpty());
        Assert.assertEquals(1, linkedList.size());
        Assert.assertEquals(9, linkedList.get(0).intValue());

        SLinkedList<Integer> linkedList1 = new SLinkedList<>();
        linkedList1.add(2);
        linkedList1.add(5);
        linkedList1.add(7);
        linkedList1.add(8);
        linkedList1.add(9);
        linkedList1.add(12);
        linkedList1.add(18);

        linkedList1.remove(2, 4);
        Assert.assertFalse(linkedList1.isEmpty());
        Assert.assertEquals(3, linkedList1.size());
        Assert.assertEquals(2, linkedList1.get(0).intValue());
        Assert.assertEquals(5, linkedList1.get(1).intValue());
        Assert.assertEquals(18, linkedList1.get(2).intValue());
    }

    /**
     * 假定当前链表和list均包含已升序排列的整数
     * 从当前链表中取出那些list所指定的元素
     * 例如当前链表 = 11->101->201->301->401->501->601->701
     * listB = 1->3->4->6
     * 返回的结果应该是[101,301,401,601]
     */
    @Test
    public void getElementsTest() {
        SLinkedList<Integer> linkedList1 = new SLinkedList<>();
        linkedList1.add(11);
        linkedList1.add(101);
        linkedList1.add(201);
        linkedList1.add(301);
        linkedList1.add(401);
        linkedList1.add(501);
        linkedList1.add(601);
        linkedList1.add(701);

        SLinkedList<Integer> linkedList2 = new SLinkedList<>();
        linkedList2.add(1);
        linkedList2.add(3);
        linkedList2.add(4);
        linkedList2.add(6);

        Object[] elements = linkedList1.getElements(linkedList2);
        Assert.assertEquals(4, elements.length);
        Assert.assertEquals(101, elements[0]);
        Assert.assertEquals(301, elements[1]);
        Assert.assertEquals(401, elements[2]);
        Assert.assertEquals(601, elements[3]);
    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 从当前链表中中删除在list中出现的元素
     */
    @Test
    public void subtract() {
        SLinkedList<Integer> linkedList1 = new SLinkedList<>();
        linkedList1.add(1);
        linkedList1.add(2);
        linkedList1.add(4);
        linkedList1.add(5);
        linkedList1.add(7);
        linkedList1.add(8);
        linkedList1.add(9);
        linkedList1.add(10);
        Assert.assertEquals(8, linkedList1.size());

        SLinkedList<Integer> linkedList2 = new SLinkedList<>();
        linkedList2.add(1);
        linkedList2.add(4);
        linkedList2.add(7);
        linkedList2.add(8);
        linkedList2.add(20);

        linkedList1.subtract(linkedList2);
        Assert.assertEquals(4, linkedList1.size());
    }

    /**
     * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
     */
    @Test
    public void removeDuplicateValues() {

        SLinkedList<Integer> linkedList1 = new SLinkedList<>();
        linkedList1.add(0);
        linkedList1.add(0);
        linkedList1.add(1);
        linkedList1.add(2);
        linkedList1.add(2);
        linkedList1.add(3);
        linkedList1.add(4);
        linkedList1.add(4);
        linkedList1.add(4);
        linkedList1.add(5);
        linkedList1.add(6);
        linkedList1.add(7);
        linkedList1.add(8);
        linkedList1.add(9);
        linkedList1.add(9);

        Assert.assertEquals(15, linkedList1.size());

        linkedList1.removeDuplicateValues();
        Assert.assertEquals(10, linkedList1.size());
        for (int i = 0; i < 10; i++) {
            Assert.assertEquals(i, linkedList1.get(i).intValue());
        }
    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
     */
    @Test
    public void removeRange() {
        SLinkedList<Integer> linkedList1 = new SLinkedList<>();
        linkedList1.add(0);
        linkedList1.add(0);
        linkedList1.add(1);
        linkedList1.add(2);
        linkedList1.add(2);
        linkedList1.add(3);
        linkedList1.add(4);
        linkedList1.add(4);
        linkedList1.add(4);
        linkedList1.add(5);
        linkedList1.add(6);
        linkedList1.add(7);
        linkedList1.add(8);
        linkedList1.add(9);
        linkedList1.add(9);

//        linkedList1.removeRange(1,6);
//        Assert.assertEquals(6,linkedList1.size());
//        Assert.assertEquals(0, linkedList1.get(0).intValue());
//        Assert.assertEquals(0, linkedList1.get(1).intValue());
//        Assert.assertEquals(7, linkedList1.get(2).intValue());
//        Assert.assertEquals(8, linkedList1.get(3).intValue());
//        Assert.assertEquals(9, linkedList1.get(4).intValue());
//        Assert.assertEquals(9, linkedList1.get(5).intValue());

//        linkedList1.removeRange(1, 10);
//        Assert.assertEquals(2, linkedList1.size());
//        Assert.assertEquals(0, linkedList1.get(0).intValue());
//        Assert.assertEquals(0, linkedList1.get(1).intValue());

//        linkedList1.removeRange(0, 4);
//        Assert.assertEquals(6, linkedList1.size());
//        Assert.assertEquals(5, linkedList1.get(0).intValue());
//        Assert.assertEquals(6, linkedList1.get(1).intValue());
//        Assert.assertEquals(7, linkedList1.get(2).intValue());
//        Assert.assertEquals(8, linkedList1.get(3).intValue());
//        Assert.assertEquals(9, linkedList1.get(4).intValue());
//        Assert.assertEquals(9, linkedList1.get(5).intValue());

        linkedList1.removeRange(9, 9);
        Assert.assertEquals(13, linkedList1.size());
    }

    /**
     * 123456789876543
     * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
     * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
     */
    @Test
    public void intersection() {
        SLinkedList<Integer> linkedList1 = new SLinkedList<>();
        linkedList1.add(1);
        linkedList1.add(2);
        linkedList1.add(4);
        linkedList1.add(5);

        SLinkedList<Integer> linkedList2 = new SLinkedList<>();
        linkedList2.add(7);
        linkedList2.add(8);
        linkedList2.add(9);
        linkedList2.add(10);

        SLinkedList<Integer> integerRLinkedList1 = linkedList1.intersection(linkedList2);
        Assert.assertEquals(8, integerRLinkedList1.size());
        System.out.println(Arrays.toString(integerRLinkedList1.toArray()));

        SLinkedList<Integer> integerRLinkedList2 = linkedList2.intersection(linkedList1);
        Assert.assertEquals(8, integerRLinkedList2.size());
        System.out.println(Arrays.toString(integerRLinkedList2.toArray()));

        SLinkedList<Integer> linkedList3 = new SLinkedList<>();
        linkedList3.add(1);
        linkedList3.add(2);
        linkedList3.add(4);
        linkedList3.add(5);
        linkedList3.add(7);
        linkedList3.add(9);
        linkedList3.add(16);
        linkedList3.add(17);

        SLinkedList<Integer> linkedList4 = new SLinkedList<>();
        linkedList4.add(1);
        linkedList4.add(3);
        linkedList4.add(7);
        linkedList4.add(8);
        linkedList4.add(9);
        linkedList4.add(10);
        linkedList4.add(17);

        SLinkedList<Integer> integerRLinkedList3 = linkedList3.intersection(linkedList4);
        Assert.assertEquals(11, integerRLinkedList3.size());
        System.out.println(Arrays.toString(integerRLinkedList3.toArray()));

        SLinkedList<Integer> integerRLinkedList4 = linkedList4.intersection(linkedList3);
        Assert.assertEquals(11, integerRLinkedList4.size());
        System.out.println(Arrays.toString(integerRLinkedList4.toArray()));
    }

    /**
     * 获取中间节点，假设不知道长度（快慢指针）
     *
     * @return
     */
    @Test
    public void getMiddle() {

        SLinkedList<Integer> linkedList1 = new SLinkedList<>();
        linkedList1.add(1);
        linkedList1.add(2);
        linkedList1.add(4);
        linkedList1.add(5);
        Assert.assertEquals(4,linkedList1.getMiddle().intValue());

        SLinkedList<Integer> linkedList2 = new SLinkedList<>();
        linkedList2.add(6);
        linkedList2.add(7);
        linkedList2.add(8);
        linkedList2.add(9);
        linkedList2.add(10);
        Assert.assertEquals(8,linkedList2.getMiddle().intValue());
    }

    /**
     * 如何判断一个字符串是否是回文字符串的问题
     */
    @Test
    public void isPalindrome() {
        SLinkedList<Integer> linkedList1 = new SLinkedList<>();
        linkedList1.add(1);
        linkedList1.add(2);
        linkedList1.add(2);
        linkedList1.add(1);

        Assert.assertTrue(linkedList1.isPalindrome());
        System.out.println(Arrays.toString(linkedList1.toArray()));

        SLinkedList<Integer> linkedList2 = new SLinkedList<>();
        linkedList2.add(1);
        linkedList2.add(2);
        linkedList2.add(3);
        linkedList2.add(2);
        linkedList2.add(1);

        Assert.assertTrue(linkedList2.isPalindrome());
        System.out.println(Arrays.toString(linkedList2.toArray()));
    }
}
