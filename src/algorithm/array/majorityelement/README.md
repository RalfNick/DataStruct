# Majority Element（求众数）

**LeetCode 169**

- [英文版](https://leetcode.com/problems/majority-element/)

- [中文版](https://leetcode-cn.com/problems/majority-element/)

## 题目

给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。

你可以假设数组是非空的，并且给定的数组总是存在众数。

```
示例 1:

输入: [3,2,3]
输出: 3

示例 2:

输入: [2,2,1,1,1,2,2]
输出: 2
```

## 思路

<details>
<summary>点击展开</summary>

思路：假设第一个元素是众数，从第二个元素开始遍历，元素与暂时的众数相等，计数加 1，不等则减 1；
如果这是计数为 0，说明众数应该改变，下一个元素可能是众数，所以将众数改为下一个元素，重复这个过程。


所以时间复杂度为 O(n)

</details>

## 代码实现
| C | Java |
| :--: | :--: |
| 🤔 | 🤔 |
