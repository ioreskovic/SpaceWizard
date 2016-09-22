package org.lopina.tree.binary;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class BranchTest
{
    @Test
    public void branchShouldBeCreatedCorrectly() throws Exception {
        final Integer leafElem = 2;
        final Integer branchElem = 4;
        Leaf<Integer> leaf = BinaryTree.leaf(leafElem);
        Empty<Integer> empty = BinaryTree.empty();
        Branch<Integer> branch = BinaryTree.branch(leaf, branchElem, empty);
        Assert.assertFalse(branch.isEmpty());
        Assert.assertTrue(branch.nonEmpty());
        Assert.assertEquals(branchElem, branch.elem());
        Assert.assertEquals(1 + leaf.size() + empty.size(), branch.size());
        Assert.assertEquals(1 + Math.max(leaf.height(), empty.height()), branch.height());
        Assert.assertTrue(branch.contains(branchElem));
        Assert.assertTrue(branch.contains(leafElem));
    }

    @Test
    public void addingDirectContainingElementIntoBranchShouldReturnSameBranch() throws Exception {
        final Integer elem = 4;
        Branch<Integer> branch = BinaryTree.branch(BinaryTree.leaf(2), elem, BinaryTree.leaf(6));
        Branch<Integer> result = branch.add(elem);
        Assert.assertEquals(branch.isEmpty(), result.isEmpty());
        Assert.assertEquals(branch.nonEmpty(), result.nonEmpty());
        Assert.assertEquals(branch.size(), result.size());
        Assert.assertEquals(branch.height(), result.height());
        Assert.assertEquals(branch.contains(elem), result.contains(elem));
    }

    @Test
    public void addingIndirectContainingElementIntoBranchShouldReturnSameBranch() throws Exception {
        final Integer elem = 2;
        Branch<Integer> branch = BinaryTree.branch(BinaryTree.leaf(elem), 4, BinaryTree.leaf(6));
        Branch<Integer> result = branch.add(elem);
        Assert.assertEquals(branch.isEmpty(), result.isEmpty());
        Assert.assertEquals(branch.nonEmpty(), result.nonEmpty());
        Assert.assertEquals(branch.size(), result.size());
        Assert.assertEquals(branch.height(), result.height());
        Assert.assertEquals(branch.contains(elem), result.contains(elem));
    }

    @Test
    public void addingNonContainingElementIntoBranchShouldReturnNewBranchContainingThatElement() throws Exception {
        final Integer elem = 5;
        Branch<Integer> branch = BinaryTree.branch(BinaryTree.leaf(2), 4, BinaryTree.leaf(6));
        Branch<Integer> result = branch.add(elem);
        Assert.assertEquals(branch.isEmpty(), result.isEmpty());
        Assert.assertEquals(branch.nonEmpty(), result.nonEmpty());
        Assert.assertEquals(branch.size() + 1, result.size());
        Assert.assertTrue(result.contains(elem));
    }

    @Test
    public void deletingNonContainingElementFromBranchShouldReturnThatBranch() throws Exception {
        Branch<Integer> branch = BinaryTree.branch(BinaryTree.leaf(2), 4, BinaryTree.leaf(6));
        final int elem = 5;
        BinaryTree<Integer> result = branch.remove(elem);
        Assert.assertEquals(branch.isEmpty(), result.isEmpty());
        Assert.assertEquals(branch.nonEmpty(), result.nonEmpty());
        Assert.assertEquals(branch.size(), result.size());
        Assert.assertEquals(branch.height(), result.height());
        Assert.assertEquals(branch.contains(elem), result.contains(elem));
    }

    @Test
    public void deletingElementSmallerThanRootShouldReduceLeftSubtree() throws Exception {
        Branch<Integer> branch = BinaryTree.branch(BinaryTree.leaf(2), 4, BinaryTree.leaf(6));
        final int elem = 2;
        BinaryTree<Integer> result = branch.remove(elem);
        Assert.assertEquals(branch.left().size() - 1, result.left().size());
        Assert.assertEquals(branch.right().size(), result.right().size());
        Assert.assertEquals(branch.size() - 1, result.size());
        Assert.assertFalse(result.contains(elem));
    }

    @Test
    public void deletingElementBiggerThanRootShouldReduceRightSubtree() throws Exception {
        Branch<Integer> branch = BinaryTree.branch(BinaryTree.leaf(2), 4, BinaryTree.leaf(6));
        final int elem = 6;
        BinaryTree<Integer> result = branch.remove(elem);
        Assert.assertEquals(branch.left().size(), result.left().size());
        Assert.assertEquals(branch.right().size() - 1, result.right().size());
        Assert.assertEquals(branch.size() - 1, result.size());
        Assert.assertFalse(result.contains(elem));
    }

    @Test
    public void minimumOfATreeShouldBeMinimumOfItsLeftSubtreeWhenItExists() throws Exception {
        BinaryTree<Integer> leftSubtree = BinaryTree.<Integer>empty().add(2).add(1).add(3);
        Integer elem = 4;
        BinaryTree<Integer> branch = BinaryTree.branch(leftSubtree, elem, BinaryTree.<Integer>empty());
        Assert.assertEquals(leftSubtree.min(), branch.min());
    }

    @Test
    public void minimumOfATreeShouldBeItsElementWhenLeftSubtreeDoesntExists() throws Exception {
        BinaryTree<Integer> rightSubtree = BinaryTree.<Integer>empty().add(6).add(5).add(7);
        Integer elem = 4;
        BinaryTree<Integer> branch = BinaryTree.branch(BinaryTree.<Integer>empty(), elem, rightSubtree);
        Assert.assertEquals(elem, branch.min());
    }

    @Test
    public void maximumOfATreeShouldBeMaximumOfItsRightSubtreeWhenItExists() throws Exception {
        BinaryTree<Integer> rightSubtree = BinaryTree.<Integer>empty().add(6).add(5).add(7);
        Integer elem = 4;
        BinaryTree<Integer> branch = BinaryTree.branch(BinaryTree.<Integer>empty(), elem, rightSubtree);
        Assert.assertEquals(rightSubtree.max(), branch.max());
    }

    @Test
    public void maximumOfATreeShouldBeItsElementWhenRightSubtreeDoesntExists() throws Exception {
        BinaryTree<Integer> leftSubtree = BinaryTree.<Integer>empty().add(2).add(1).add(3);
        Integer elem = 4;
        BinaryTree<Integer> branch = BinaryTree.branch(leftSubtree, elem, BinaryTree.<Integer>empty());
        Assert.assertEquals(elem, branch.max());
    }
}
