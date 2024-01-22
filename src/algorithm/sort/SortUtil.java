package algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-09-18 下午9:14
 **/
public class SortUtil {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 合并区间 - 56
     *
     * @param intervals 区间数组
     * @return
     */
    public static int[][] merge(int[][] intervals) {
        List<int[]> list = new ArrayList<>();
        if (intervals == null || intervals.length < 1) {
            return list.toArray(new int[0][]);
        }
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        for (int i = 0; i < intervals.length; i++) {
            int left = intervals[i][0];
            int right = intervals[i][1];
            while (i < intervals.length - 1 && right >= intervals[i + 1][0]) {
                i++;
                right = Math.max(right, intervals[i][1]);
            }
            list.add(new int[]{left, right});
        }
        return list.toArray(new int[list.size()][]);
    }

    /**
     * 对链表进行插入排序 - 147
     *
     * @param head 链表
     * @return
     */
    public static ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        ListNode cur = dummy;
        while (head != null) {
            ListNode next = head.next;
            if (head.val < cur.val) {
                ListNode temp = dummy.next;
                ListNode pre = dummy;
                while (temp != null && temp.val <= head.val) {
                    pre = temp;
                    temp = temp.next;
                }
                pre.next = head;
                head.next = temp;
            } else {
                cur.next = head;
                cur = cur.next;
            }
            head = next;
        }
        return dummy.next;
    }

    /**
     * 排序链表 - 148
     * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
     *
     * @param head 头结点
     * @return
     */
    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        ListNode pre = null;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            pre = slow;
            slow = slow.next;
        }
        pre.next = null;
        ListNode start = sortList(head);
        ListNode end = sortList(slow);
        return mergeList(start, end);
    }

    private static ListNode mergeList(ListNode head1, ListNode head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (head1 != null || head2 != null) {
            if (head1 != null && head2 != null) {
                if (head1.val < head2.val) {
                    cur.next = head1;
                    head1 = head1.next;
                } else {
                    cur.next = head2;
                    head2 = head2.next;
                }
            } else {
                if (head1 == null) {
                    cur.next = head2;
                    head2 = head2.next;
                } else {
                    cur.next = head1;
                    head1 = head1.next;
                }
            }
            cur = cur.next;
        }
        return dummy.next;
    }
}
