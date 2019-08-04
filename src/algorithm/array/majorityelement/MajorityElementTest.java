package algorithm.array.majorityelement;

import org.junit.Assert;
import org.junit.Test;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-04-23 上午7:50
 **/
public class MajorityElementTest {

    @Test
    public void test1() {
        int[] arr = {3, 2, 3};
        Assert.assertEquals(3, MajorityElement.majorityElement(arr));
    }

    @Test
    public void test2() {
        int[] arr = {2, 2, 1, 1, 1, 2, 2};
        Assert.assertEquals(2, MajorityElement.majorityElement(arr));
    }
}
