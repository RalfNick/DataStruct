# First Missing Positive（缺失的第一个正数）

**LeetCode 41**

- [英文版](https://leetcode.com/problems/first-missing-positive/)

- [中文版](https://leetcode-cn.com/problems/first-missing-positive/)

## 题目
给定一个未排序的整数数组，找出其中没有出现的最小的正整数。

示例 1:
```
输入: [3,4,-1,1]
输出: 2
```

示例 3:
```
输入: [7,8,9,11,12]
输出: 1
```

说明:

算法的时间复杂度应为O(n)，并且只能使用常数级别的空间。

## 思路

<details>
<summary>点击展开</summary>

思路一：采用比较笨的方法，使用双层遍历，找到两个数的和等于目标值，有一个小优化，就是当遍历到的数值大于目标值时，就结束遍历，因为之后也不会存在这样的两个数
时间复杂度：O(n^2)

</details>

## 代码实现
| C | Java |
| :--: | :--: |
| 🤔 | 🤔 |
