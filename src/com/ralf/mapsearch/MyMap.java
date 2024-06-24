package com.ralf.mapsearch;

public class MyMap {

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length < 1 || grid[0] == null || grid[0].length < 1) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int maxArea = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    maxArea = Math.max(maxArea, getArea(grid, i, j, m, n));
                }
            }
        }
        return maxArea;
    }

    private int getArea(int[][] grid, int i, int j, int m, int n) {
        if (i < 0 || i == m || j < 0 || j == n || grid[i][j] == '0') {
            return 0;
        }
        grid[i][j] = '0';
        int area = 1;
        area += getArea(grid, i - 1, j, m, n);
        area += getArea(grid, i + 1, j, m, n);
        area += getArea(grid, i, j - 1, m, n);
        area += getArea(grid, i, j + 1, m, n);
        return area;
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length < 1 || grid[0].length < 1) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    numIslands(grid, i, j, m, n);
                    count++;
                }
            }
        }
        return count;
    }

    private void numIslands(char[][] grid, int i, int j, int m, int n) {
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        numIslands(grid, i + 1, j, m, n);
        numIslands(grid, i - 1, j, m, n);
        numIslands(grid, i, j + 1, m, n);
        numIslands(grid, i, j - 1, m, n);
    }

}
