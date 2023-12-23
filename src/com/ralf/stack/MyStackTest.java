package com.ralf.stack;

import org.junit.Assert;
import org.junit.Test;

public class MyStackTest {

    @Test
    public void testSimplifyPath() {
        String s1 = "/home/";
        String s2 = "/../";
        String s3 = "/home//foo/";
        String s4 = "/a/./b/../../c/";
        String s5 = "/a/../../b/../c//.//";
        String s6 = "/a//b////c/d//././/..";

        String result1 = MyStack.simplifyPath(s1);
        String result2 = MyStack.simplifyPath(s2);
        String result3 = MyStack.simplifyPath(s3);
        String result4 = MyStack.simplifyPath(s4);
        String result5 = MyStack.simplifyPath(s5);
        String result6 = MyStack.simplifyPath(s6);

        Assert.assertEquals("/home", result1);
        Assert.assertEquals("/", result2);
        Assert.assertEquals("/home/foo", result3);
        Assert.assertEquals("/c", result4);
        Assert.assertEquals("/c", result5);
        Assert.assertEquals("/a/b/c", result6);
    }

    @Test
    public void testRemoveKdigits() {
        // num = "1432219", k = 3
        String num = "1432219";
        String result = MyStack.removeKdigits(num, 3);
        Assert.assertEquals("1219", result);

        result = MyStack.removeKdigits("10", 2);
        Assert.assertEquals("0", result);

        result = MyStack.removeKdigits("10200", 1);
        Assert.assertEquals("200", result);

        result = MyStack.removeKdigits("10", 1);
        Assert.assertEquals("0", result);

        result = MyStack.removeKdigits("112", 1);
        Assert.assertEquals("11", result);

        result = MyStack.removeKdigits("9999999999991", 8);
        Assert.assertEquals("99991", result);

        result = MyStack.removeKdigits("10001", 4);
        Assert.assertEquals("0", result);
    }
}
