package org.lopina.tree.binary;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.NoSuchElementException;

@RunWith(BlockJUnit4ClassRunner.class)
public class LeafTest
{
    @Test
    public void leafTreeShouldContainSingleElement() throws Exception {
        final Integer elem = 4;
        Leaf<Integer> leaf = BinaryTree.leaf(elem);
        Assert.assertFalse(leaf.isEmpty());
        Assert.assertTrue(leaf.nonEmpty());
        Assert.assertEquals(elem, leaf.elem());
        Assert.assertEquals(1, leaf.size());
        Assert.assertEquals(1, leaf.height());
        Assert.assertTrue(leaf.contains(elem));
    }

    @Test
    public void removingContainingElementFromLeafShouldYieldEmpty() throws Exception {
        final Integer elem = 4;
        Leaf<Integer> leaf = BinaryTree.leaf(elem);
        BinaryTree<Integer> result = leaf.remove(elem);
        Assert.assertTrue(result.isEmpty());
        Assert.assertFalse(result.nonEmpty());
        Assert.assertEquals(0, result.size());
        Assert.assertEquals(0, result.height());
        Assert.assertFalse(result.contains(elem));
    }

    @Test
    public void removingNonContainingElementFromLeafShouldYieldThatSameLeaf() throws Exception {
        final Integer elem = 4;
        Leaf<Integer> leaf = BinaryTree.leaf(elem);
        BinaryTree<Integer> result = leaf.remove(elem + 1);
        Assert.assertEquals(leaf.isEmpty(), result.isEmpty());
        Assert.assertEquals(leaf.nonEmpty(), result.nonEmpty());
        Assert.assertEquals(leaf.size(), result.size());
        Assert.assertEquals(leaf.height(), result.height());
        Assert.assertEquals(leaf.contains(elem), result.contains(elem));
        Assert.assertEquals(leaf.elem(), result.elem());
    }

    @Test
    public void addingContainingElementToLeafShouldReturnSameLeaf() throws Exception {
        final Integer elem = 4;
        Leaf<Integer> leaf = BinaryTree.leaf(elem);
        BinaryTree<Integer> result = leaf.add(elem);
        Assert.assertEquals(leaf.isEmpty(), result.isEmpty());
        Assert.assertEquals(leaf.nonEmpty(), result.nonEmpty());
        Assert.assertEquals(leaf.size(), result.size());
        Assert.assertEquals(leaf.height(), result.height());
        Assert.assertEquals(leaf.contains(elem), result.contains(elem));
        Assert.assertEquals(leaf.elem(), result.elem());
    }

    @Test
    public void addingSmallerNonContainingElementToLeafShouldProduceBranch() throws Exception {
        final Integer elem = 4;
        final Integer newElem = elem - 1;
        Leaf<Integer> leaf = BinaryTree.leaf(elem);
        BinaryTree<Integer> result = leaf.add(newElem);
        Assert.assertEquals(leaf.isEmpty(), result.isEmpty());
        Assert.assertEquals(leaf.nonEmpty(), result.nonEmpty());
        Assert.assertEquals(leaf.size() + 1, result.size());
        Assert.assertEquals(leaf.height() + 1, result.height());
        Assert.assertEquals(leaf.contains(elem), result.contains(elem));
        Assert.assertEquals(leaf.elem(), result.elem());
        Assert.assertTrue(result.contains(newElem));
    }

    @Test
    public void addingBiggerNonContainingElementToLeafShouldProduceBranch() throws Exception {
        final Integer elem = 4;
        final Integer newElem = elem + 1;
        Leaf<Integer> leaf = BinaryTree.leaf(elem);
        BinaryTree<Integer> result = leaf.add(newElem);
        Assert.assertEquals(leaf.isEmpty(), result.isEmpty());
        Assert.assertEquals(leaf.nonEmpty(), result.nonEmpty());
        Assert.assertEquals(leaf.size() + 1, result.size());
        Assert.assertEquals(leaf.height() + 1, result.height());
        Assert.assertEquals(leaf.contains(elem), result.contains(elem));
        Assert.assertEquals(leaf.elem(), result.elem());
        Assert.assertTrue(result.contains(newElem));
    }

    @Test
    public void minOfLeafShouldBeLeafElement() throws Exception {
        final Integer elem = 4;
        Leaf<Integer> leaf = BinaryTree.leaf(elem);
        Assert.assertEquals(elem, leaf.min());
    }

    @Test
    public void maxOfLeafShouldBeLeafElement() throws Exception {
        final Integer elem = 4;
        Leaf<Integer> leaf = BinaryTree.leaf(elem);
        Assert.assertEquals(elem, leaf.max());
    }

    @Test(expected = NoSuchElementException.class)
    public void predecessorOfLeafShouldThrowException() throws Exception {
        final Integer elem = 4;
        Leaf<Integer> leaf = BinaryTree.leaf(elem);
        leaf.predecessor();
    }

    @Test(expected = NoSuchElementException.class)
    public void successorOfLeafShouldThrowException() throws Exception {
        final Integer elem = 4;
        Leaf<Integer> leaf = BinaryTree.leaf(elem);
        leaf.successor();
    }

    @Test
    public void applyOnLeafShouldBeLeafElementWhenZerothRequested() throws Exception {
        final Integer elem = 4;
        Leaf<Integer> leaf = BinaryTree.leaf(elem);
        Assert.assertEquals(elem, leaf.apply(0));
    }

    @Test(expected = NoSuchElementException.class)
    public void applyOnLeafShouldThrowExceptionWhenOtherThanZerothRequested() throws Exception {
        final Integer elem = 4;
        Leaf<Integer> leaf = BinaryTree.leaf(elem);
        leaf.apply(1);
    }
}
