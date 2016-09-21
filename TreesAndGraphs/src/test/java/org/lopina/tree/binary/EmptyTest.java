package org.lopina.tree.binary;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.NoSuchElementException;

@RunWith(BlockJUnit4ClassRunner.class)
public class EmptyTest
{
    @Test
    public void creatingEmptyNodeShouldBeEmpty() throws Exception {
        Empty<Integer> empty = BinaryTree.empty();

        Assert.assertTrue(empty.isEmpty());
        Assert.assertFalse(empty.nonEmpty());
        Assert.assertEquals(0, empty.size());
        Assert.assertEquals(0, empty.height());
        Assert.assertFalse(empty.contains(0));
    }

    @Test
    public void removingFromEmptyNodeShouldReturnThatSameEmptyNode() throws Exception {
        Empty<Integer> empty = BinaryTree.empty();
        Empty<Integer> result = empty.remove(0);
        Assert.assertSame(empty, result);
    }

    @Test
    public void addingToEmptyNodeShouldCreateALeaf() throws Exception {
        Empty<Integer> empty = BinaryTree.empty();
        final Integer elem = 4;
        Leaf<Integer> result = empty.add(elem);
        Assert.assertFalse(result.isEmpty());
        Assert.assertTrue(result.nonEmpty());
        Assert.assertEquals(1, result.size());
        Assert.assertEquals(1, result.height());
        Assert.assertTrue(result.contains(elem));
        Assert.assertEquals(elem, result.elem());
    }

    @Test(expected = NoSuchElementException.class)
    public void fetchingElemShouldThrowExceptionWhenCalledOnEmptyNode() throws Exception {
        Empty<Integer> empty = BinaryTree.empty();
        empty.elem();
    }

    @Test(expected = NoSuchElementException.class)
    public void fetchingChildShouldThrowExceptionWhenCalledOnEmptyNode() throws Exception {
        Empty<Integer> empty = BinaryTree.empty();
        empty.child(2);
    }

    @Test(expected = NoSuchElementException.class)
    public void fetchingLeftChildShouldThrowExceptionWhenCalledOnEmptyNode() throws Exception {
        Empty<Integer> empty = BinaryTree.empty();
        empty.left();
    }

    @Test(expected = NoSuchElementException.class)
    public void fetchingRightChildShouldThrowExceptionWhenCalledOnEmptyNode() throws Exception {
        Empty<Integer> empty = BinaryTree.empty();
        empty.right();
    }
}
