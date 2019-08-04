package algorithm.array.threesum;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-04-23 上午7:50
 **/
public class ThreeSumTest {

    @Test
    public void test1() {
        int[] arr = {-1, 0, 1, 2, -1, -4};
        int[] result1 = {-1, -1, 2};
        int[] result2 = {-1, 0, 1};
        List<List<Integer>> resultList = ThreeSum.threeSum(arr);
        Assert.assertEquals(2, resultList.size());
        for (int i = 0; i < 3; i++) {
            Assert.assertEquals(result1[i], resultList.get(0).get(i).intValue());
        }
        for (int i = 0; i < 3; i++) {
            Assert.assertEquals(result2[i], resultList.get(1).get(i).intValue());
        }
    }
}
