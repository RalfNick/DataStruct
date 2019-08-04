package algorithm.greedy;

import org.junit.Assert;
import org.junit.Test;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-06-17 下午4:43
 **/
public class GreedyTest {

    @Test
    public void removeDigits() {

        String digits = Greedy.removeKDigits("1432219", 3);
        Assert.assertEquals("1219", digits);
    }
}
