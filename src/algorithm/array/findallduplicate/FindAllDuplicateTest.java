package algorithm.array.findallduplicate;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-05-16 下午8:58
 **/
public class FindAllDuplicateTest {

    @Test
    public void test() {
        int[] arr = {4, 3, 2, 7, 8, 2, 3, 1};
        List<Integer> list = FindAllDuplicate.findAllDuplicate(arr);
        Assert.assertTrue(list.contains(3));
        Assert.assertTrue(list.contains(2));
    }
}
