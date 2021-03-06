# Three Sum （三数之和）

**LeetCode 15**

- [英文版](https://leetcode.com/problems/3sum/)

- [中文版](https://leetcode-cn.com/problems/3sum/)

## 题目

给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。

注意：答案中不可以包含重复的三元组。

例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，

满足要求的三元组集合为：
[
  [-1, 0, 1],
  [-1, -1, 2]
]

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
