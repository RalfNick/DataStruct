package tree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * DESCRIPTION
 *
 * @author lixin
 * @create 2019-03-05 下午10:58
 **/
public class BinarySearchTreeTest {

    private BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();

    @Before
    public void build() {
        binarySearchTree.insert(5);
        binarySearchTree.insert(3);
        binarySearchTree.insert(7);
        binarySearchTree.insert(1);
        binarySearchTree.insert(4);
        binarySearchTree.insert(2);
        binarySearchTree.insert(8);
        binarySearchTree.insert(6);
    }

    @Test
    public void contains() {
        Assert.assertTrue(binarySearchTree.contains(3));
        Assert.assertTrue(binarySearchTree.contains(7));
        Assert.assertTrue(binarySearchTree.contains(1));
        Assert.assertTrue(binarySearchTree.contains(4));
        Assert.assertTrue(binarySearchTree.contains(2));
        Assert.assertTrue(binarySearchTree.contains(8));
        Assert.assertTrue(binarySearchTree.contains(6));
        Assert.assertFalse(binarySearchTree.contains(9));
        Assert.assertFalse(binarySearchTree.contains(10));
    }

    @Test
    public void remove() {
//        Assert.assertTrue( binarySearchTree.remove(7));
//        Assert.assertFalse( binarySearchTree.contains(7));
//        Assert.assertTrue( binarySearchTree.remove(6));
//        Assert.assertFalse( binarySearchTree.contains(6));
        Assert.assertTrue( binarySearchTree.remove(3));
        Assert.assertFalse( binarySearchTree.contains(3));
    }

    @Test
    public void findMin() {
        Assert.assertEquals(1,binarySearchTree.findMin().intValue());
    }

    @Test
    public void findMax() {
        Assert.assertEquals(8, binarySearchTree.findMax().intValue());
    }
}
