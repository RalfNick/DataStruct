package queue;

import org.junit.Assert;
import org.junit.Test;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-02-10 上午10:11
 **/
public class JosephusTest {

    @Test
    public void testExecute() {
        Assert.assertEquals("[1, 3, 5, 0, 4, 2, 6]", Josephus.execute(7, 2).toString());
    }
}
