package list.arraylist;

import list.RIterator;
import list.RList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2018-11-25 下午4:35
 **/
public class RArrayListTest {

    RList<Integer> list;

    @Before
    public void init() {
        list = new RArrayList<>(10);

        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
    }

    @Test
    public void size() {
        Assert.assertEquals(10, list.size());
    }

    @Test
    public void isEmpty() {

        Assert.assertFalse(list.isEmpty());

        RList<Integer> tempList = new RArrayList<>();
        Assert.assertTrue(tempList.isEmpty());
    }

    @Test
    public void contain() {

        Assert.assertTrue(list.contains(5));
        Assert.assertFalse(list.contains(10));
    }

    @Test
    public void toArray() {
        Object[] integers = list.toArray();
        Assert.assertEquals(10, integers.length);

        for (int i = 0; i < integers.length; i++) {
            Assert.assertEquals(i, integers[i]);
        }
    }

    @Test
    public void get() {

        for (int i = 0; i < list.size(); i++) {
            Assert.assertEquals(i, list.indexOf(i));
        }

        for (int i = 0; i < list.size(); i++) {
            Assert.assertEquals(i, list.get(i).intValue());
        }

    }

    @Test
    public void add() {

        Assert.assertTrue(list.add(10));
        Object[] integers = list.toArray();
        for (int i = 0; i < integers.length; i++) {
            Assert.assertEquals(i, integers[i]);
        }
        Assert.assertTrue(list.contains(10));
        Assert.assertEquals(11, list.size());

        list.add(3, 30);
        integers = list.toArray();
        for (int i = 0; i < integers.length; i++) {
            if (i == 3) {
                Assert.assertEquals(30, integers[i]);
            } else if (i > 3) {
                Assert.assertEquals(i - 1, integers[i]);
            } else {
                Assert.assertEquals(i, integers[i]);
            }
        }
    }

    @Test
    public void remove() {

        for (int i = 0; i < 3; i++) {
            list.removeByIndex(0);
        }

        Assert.assertEquals(7, list.size());

        for (int i = 0; i < list.size(); i++) {
            Assert.assertEquals(i + 3, list.get(i).intValue());
        }

        Assert.assertTrue(list.remove(8));
        Assert.assertEquals(6, list.size());
        Assert.assertEquals(9, list.get(5).intValue());
    }

    @Test
    public void set() {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, i + 1);
            Assert.assertEquals(i + 1, list.get(i).intValue());
        }
    }

    @Test
    public void clear() {
        list.clear();
        Assert.assertTrue(list.isEmpty());
    }

    @Test
    public void iterator() {

        RIterator<Integer> iterator = list.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Assert.assertEquals(i++, iterator.next().intValue());
        }
    }

}
