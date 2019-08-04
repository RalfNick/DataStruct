package algorithm.stack;

import org.junit.Assert;
import org.junit.Test;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-07-13 下午9:23
 **/
public class RStackTest {

    @Test
    public void testIsValid() {
        String s1 = "()";
        String s2 = "()[]{}";
        String s3 = "(]";
        String s4 = "([)]";

        Assert.assertTrue(RStack.isValid(s1));
        Assert.assertTrue(RStack.isValid(s2));
        Assert.assertFalse(RStack.isValid(s3));
        Assert.assertFalse(RStack.isValid(s4));
    }

    @Test
    public void testSimplifyPath() {
        String s1 = "/home/";
        String s2 = "/../";
        String s3 = "/home//foo/";
        String s4 = "/a/./b/../../c/";
        String s5 = "/a/../../b/../c//.//";
        String s6 = "/a//b////c/d//././/..";

        String result1 = RStack.simplifyPath(s1);
        String result2 = RStack.simplifyPath(s2);
        String result3 = RStack.simplifyPath(s3);
        String result4 = RStack.simplifyPath(s4);
        String result5 = RStack.simplifyPath(s5);
        String result6 = RStack.simplifyPath(s6);

        Assert.assertEquals("/home", result1);
        Assert.assertEquals("/", result2);
        Assert.assertEquals("/home/foo", result3);
        Assert.assertEquals("/c", result4);
        Assert.assertEquals("/c", result5);
        Assert.assertEquals("/a/b/c", result6);
    }

    @Test
    public void testLargestRectangleArea() {
        // 输入: [2,1,5,6,2,3]
        // 输出: 10
        int[] arr = {2, 1, 5, 6, 2, 3};
        int result = RStack.largestRectangleArea(arr);
        Assert.assertEquals(10, result);

        Assert.assertEquals(10, RStack.largestRectangleArea1(arr));

        Assert.assertEquals(10, RStack.largestRectangleArea2(arr));
    }

    @Test
    public void testEvalRPN() {
        // ["2", "1", "+", "3", "*"]
        String[] s1 = {"2", "1", "+", "3", "*"};
        String[] s2 = {"4", "13", "5", "/", "+"};
        String[] s3 = {"10", "6", "9", "3", "+", "-11", "*", "/",
                "*", "17", "+", "5", "+"};
        int result1 = RStack.evalRPN(s1);
        Assert.assertEquals(9, result1);

        int result2 = RStack.evalRPN(s2);
        Assert.assertEquals(6, result2);

        int result3 = RStack.evalRPN(s3);
        Assert.assertEquals(22, result3);
    }

    @Test
    public void testDecodeString() {
        // s = "3[a]2[bc]", 返回 "aaabcbc".
        // s = "3[a2[c]]", 返回 "accaccacc".
        // s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".

        String s1 = "3[a]2[bc]";
        String s2 = "3[a2[c]]";
        String s3 = "2[abc]3[cd]ef";
        Assert.assertEquals("aaabcbc", RStack.decodeString(s1));
        Assert.assertEquals("accaccacc", RStack.decodeString(s2));
        Assert.assertEquals("abcabccdcdcdef", RStack.decodeString(s3));

    }

    @Test
    public void find132pattern() {
        int[] arr1 = {1, 2, 3, 4};
        int[] arr2 = {3, 1, 4, 2};
        int[] arr3 = {-1, 3, 2, 0};

        Assert.assertFalse(RStack.find132pattern(arr1));
        Assert.assertTrue(RStack.find132pattern(arr2));
        Assert.assertTrue(RStack.find132pattern(arr3));

        Assert.assertFalse(RStack.find132pattern1(arr1));
        Assert.assertTrue(RStack.find132pattern1(arr2));
        Assert.assertTrue(RStack.find132pattern1(arr3));
    }

    @Test
    public void testNextGreaterElement() {
        //nums1 = [4,1,2], nums2 = [1,3,4,2]. result = [-1,3,-1]
        int[] nums1 = {4, 1, 2};
        int[] nums2 = {1, 3, 4, 2};
        // 方法一
        int[] result = RStack.nextGreaterElement(nums1, nums2);
        Assert.assertArrayEquals(new int[]{-1, 3, -1}, result);

        // 方法二
        int[] result1 = RStack.nextGreaterElement1(nums1, nums2);
        Assert.assertArrayEquals(new int[]{-1, 3, -1}, result1);
    }

    @Test
    public void testNextGreaterElements() {
        // 输入: [1,2,1]
        // 输出: [2,-1,2]
        int[] nums = {1, 2, 1};
        int[] result = RStack.nextGreaterElements(nums);
        Assert.assertArrayEquals(new int[]{2, -1, 2}, result);
    }

    @Test
    public void removeKdigits() {
        //num = "1432219", k = 3
        String num = "1432219";
        String result = RStack.removeKdigits(num, 3);
        Assert.assertEquals("1219", result);

        result = RStack.removeKdigits("10", 2);
        Assert.assertEquals("0", result);

        result = RStack.removeKdigits("10200", 1);
        Assert.assertEquals("200", result);
    }

    @Test
    public void testRemoveDuplicateLetters() {
        String s1 = "bcabc";
        String s2 = "cbacdcbc";

        Assert.assertEquals("abc", RStack.removeDuplicateLetters(s1));
        Assert.assertEquals("acdb", RStack.removeDuplicateLetters(s2));
    }

    @Test
    public void testDailyTemperatures() {
        // temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，
        // 输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] result = {1, 1, 4, 2, 1, 1, 0, 0};
        Assert.assertArrayEquals(result, RStack.dailyTemperatures(temperatures));

        Assert.assertArrayEquals(result, RStack.dailyTemperatures1(temperatures));
    }


    @Test
    public void testBackspaceCompare() {
        //S = "ab#c", T = "ad#c"
        String s1 = "ab#c";
        String s2 = "ad#c";
        Assert.assertTrue(RStack.backspaceCompare(s1, s2));

        // S = "ab##", T = "c#d#"
        String s3 = "ab##";
        String s4 = "c#d#";
        Assert.assertTrue(RStack.backspaceCompare(s3, s4));

        // S = "a##c", T = "#a#c"
        String s5 = "a##c";
        String s6 = "#a#c";
        Assert.assertTrue(RStack.backspaceCompare(s5, s6));

        // S = "a#c", T = "b"
        String s7 = "a#c";
        String s8 = "b";
        Assert.assertFalse(RStack.backspaceCompare(s7, s8));
    }

    @Test
    public void testScoreOfParentheses() {
        String s1 = "(()(()))";
        int result1 = RStack.scoreOfParentheses(s1);
        Assert.assertEquals(6, result1);

        String s2 = "()";
        int result2 = RStack.scoreOfParentheses(s2);
        Assert.assertEquals(1, result2);

        String s3 = "(())";
        int result3 = RStack.scoreOfParentheses(s3);
        Assert.assertEquals(2, result3);

        String s4 = "()()";
        int result4 = RStack.scoreOfParentheses(s4);
        Assert.assertEquals(2, result4);
    }

    @Test
    public void testMinAddToMakeValid() {
        String s1 = "())";
        int result1 = RStack.minAddToMakeValid(s1);
        Assert.assertEquals(1, result1);

        String s2 = "(((";
        int result2 = RStack.minAddToMakeValid(s2);
        Assert.assertEquals(3, result2);

        String s3 = "()";
        int result3 = RStack.minAddToMakeValid(s3);
        Assert.assertEquals(0, result3);

        String s4 = "()))((";
        int result4 = RStack.minAddToMakeValid(s4);
        Assert.assertEquals(4, result4);

    }

    @Test
    public void testValidateStackSequences() {
        // pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
        // pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
        int[] push = {1, 2, 3, 4, 5};
        int[] pop1 = {4, 5, 3, 2, 1};
        int[] pop2 = {4, 3, 5, 1, 2};
        Assert.assertTrue(RStack.validateStackSequences(push, pop1));
        Assert.assertFalse(RStack.validateStackSequences(push, pop2));
    }

    @Test
    public void testRemoveOuterParentheses() {
        String s1 = "(()())(())";
        String s2 = "()()()";
        String result1 = RStack.removeOuterParentheses(s1);
        Assert.assertEquals(s2, result1);

        String s3 = "(()())(())(()(()))";
        String s4 = "()()()()(())";
        String result2 = RStack.removeOuterParentheses(s3);
        Assert.assertEquals(s4, result2);

        String s5 = "()()";
        String s6 = "";
        String result3 = RStack.removeOuterParentheses(s5);
        Assert.assertEquals(s6, result3);
    }

    @Test
    public void testNextLargerNodes() {
        RStack.ListNode node1 = new RStack.ListNode(2);
        RStack.ListNode node2 = new RStack.ListNode(7);
        RStack.ListNode node3 = new RStack.ListNode(4);
        RStack.ListNode node4 = new RStack.ListNode(3);
        RStack.ListNode node5 = new RStack.ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        int[] result = RStack.nextLargerNodes(node1);
        Assert.assertArrayEquals(new int[]{7, 0, 5, 5, 0}, result);
    }
}
