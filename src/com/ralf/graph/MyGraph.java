package com.ralf.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MyGraph {

    /**
     * 现有n个编译项，编号为0 ~ n-1。给定一个二维数组，表示编译项之间有依赖关系。如[0, 1]表示1依赖于0。
     */
    public List<Integer> haveCircularDependency(int n, int[][] prerequisites) {
        int[] inDegree = new int[n];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            int a = prerequisite[0];
            int b = prerequisite[1];
            if (!map.containsKey(b)) {
                map.put(b, new ArrayList<>());
            }
            map.get(b).add(a);
            inDegree[a]++;
        }
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        List<Integer> list = new ArrayList<>();
        if (queue.isEmpty()) {
            return list;
        }
        while (!queue.isEmpty()) {
            int index = queue.poll();
            list.add(index);
            List<Integer> dependency = map.get(index);
            if (dependency != null) {
                for (int temp : dependency) {
                    inDegree[temp]--;
                    if (inDegree[temp] == 0) {
                        queue.offer(temp);
                    }
                }
            }
        }
        return list.size() == n ? list : new ArrayList<>();
    }

    /**
     * 207. 课程表
     */
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] inDegree = new int[numCourses];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            int a = prerequisite[0];
            int b = prerequisite[1];
            if (!map.containsKey(b)) {
                map.put(b, new ArrayList<>());
            }
            map.get(b).add(a);
            inDegree[a]++;
        }
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        if (queue.isEmpty()) {
            return false;
        }
        int count = 0;
        while (!queue.isEmpty()) {
            int index = queue.poll();
            count++;
            List<Integer> list = map.get(index);
            if (list != null) {
                for (int temp : list) {
                    inDegree[temp]--;
                    if (inDegree[temp] == 0) {
                        queue.offer(temp);
                    }
                }
            }
        }
        return count == numCourses;
    }

    public static void main(String[] args) {
        int[][] temp = {{0, 1}};
        boolean res = canFinish(2, temp);
        System.out.printf("res =" + res);

        int[][] temp1 = {{1, 0}};
        boolean res1 = canFinish(2, temp1);
        System.out.printf("res1 =" + res1);
    }
}
