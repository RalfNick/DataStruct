package algorithm.string;

import org.junit.Assert;
import org.junit.Test;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-10-26 上午10:41
 **/
public class StringAlgorithmTest {

    @Test
    public void tesLengthOfLongestSubstring() {
        Assert.assertEquals(3, StringAlgorithm.lengthOfLongestSubstring("abcabcbb"));
        Assert.assertEquals(1, StringAlgorithm.lengthOfLongestSubstring("bbbbb"));
        Assert.assertEquals(3, StringAlgorithm.lengthOfLongestSubstring("pwwkew"));

        Assert.assertEquals(3, StringAlgorithm.lengthOfLongestSubstring1("abcabcbb"));
        Assert.assertEquals(1, StringAlgorithm.lengthOfLongestSubstring1("bbbbb"));
        Assert.assertEquals(3, StringAlgorithm.lengthOfLongestSubstring1("pwwkew"));

    }
}
