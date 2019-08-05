package algorithm.queue;

import java.util.*;

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

    /**
     * 任务调度器 - 621
     * 给定一个用字符数组表示的 CPU 需要执行的任务列表。其中包含使用大写的 A - Z 字母表示的26 种不同种类的任务。任务可以以任意顺序执行，
     * 并且每个任务都可以在 1 个单位时间内执行完。CPU 在任何一个单位时间内都可以执行一个任务，或者在待命状态。
     * 输入: tasks = ["A","A","A","B","B","B"], n = 2
     * 输出: 8
     * 执行顺序: A -> B -> (待命) -> A -> B -> (待命) -> A -> B.
     *
     * @param tasks 任务数组
     * @param n     任务种类
     * @return
     */
    public static int leastInterval(String[] tasks, int n) {
        class Task {
            int value;
            String name;

            public Task(int value, String name) {
                this.value = value;
                this.name = name;
            }

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
        Map<String, Task> map = new HashMap<>();
        for (int i = 0; i < tasks.length; i++) {
            Task task = map.get(tasks[i]);
            if (task == null) {
                task = new Task(1, tasks[i]);
                map.put(tasks[i], task);
            } else {
                task.setValue(task.getValue() + 1);
            }
        }
        PriorityQueue<Task> queue = new PriorityQueue<>(n, Comparator.comparingInt(Task::getValue));
        for (Map.Entry<String, Task> entry : map.entrySet()) {
            queue.offer(entry.getValue());
        }
        int times = 0;
        while (!queue.isEmpty()) {
            int i = 0;
            List<Task> list = new ArrayList<>();
            while (i < n) {
                if (!queue.isEmpty()) {
                    Task task = queue.poll();
                    int count = task.getValue();
                    times++;
                    if (count > 1) {
                        --count;
                        task.setValue(count);
                        list.add(task);
                    }
                }
                if (queue.isEmpty() && list.isEmpty()) {
                    break;
                }
                i++;
            }
            if (!list.isEmpty()) {
                for (Task task : list) {
                    queue.offer(task);
                }
            }
            if (queue.isEmpty() && list.isEmpty()) {
                break;
            }
            times++;
        }
        return times;
    }
}
