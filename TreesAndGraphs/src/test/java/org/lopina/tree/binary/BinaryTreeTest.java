package org.lopina.tree.binary;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

@RunWith(BlockJUnit4ClassRunner.class)
public class BinaryTreeTest
{
    @Test(expected = NoSuchElementException.class)
    public void tryingToAccessNonExistingChildrenOnBranchShouldThrowException() throws Exception {
        Branch<Integer> branch = BinaryTree.branch(BinaryTree.leaf(3), 4, BinaryTree.leaf(5));
        branch.child(2);
    }

    @Test(expected = NoSuchElementException.class)
    public void tryingToAccessNonExistingChildrenOnLeafShouldThrowException() throws Exception {
        Leaf<Integer> leaf = BinaryTree.leaf(4);
        leaf.child(2);
    }

    @Test(expected = NoSuchElementException.class)
    public void tryingToAccessNonExistingChildrenOnEmptyShouldThrowException() throws Exception {
        Empty<Integer> empty = BinaryTree.empty();
        empty.child(2);
    }

    @Test
    public void stringifyingEmptyShouldProduceSomeString() throws Exception {
        Empty<Integer> empty = BinaryTree.empty();
        String treeString = empty.toString();
        Assert.assertNotNull(treeString);
        Assert.assertFalse(treeString.trim().isEmpty());
        System.out.println(treeString);
    }

    @Test
    public void stringifyingLeafShouldProduceSomeString() throws Exception {
        Leaf<Integer> leaf = BinaryTree.leaf(4);
        String treeString = leaf.toString();
        Assert.assertNotNull(treeString);
        Assert.assertFalse(treeString.trim().isEmpty());
        System.out.println(treeString);
    }

    @Test
    public void stringifyingBranchShouldProduceSomeString() throws Exception {
        Branch<Integer> branch = BinaryTree.branch(
                BinaryTree.branch(
                        BinaryTree.leaf(1),
                        2,
                        BinaryTree.leaf(3)
                ),
                4,
                BinaryTree.branch(
                        BinaryTree.leaf(5),
                        6,
                        BinaryTree.leaf(7)
                )
        );
        String treeString = branch.toString();
        Assert.assertNotNull(treeString);
        Assert.assertFalse(treeString.trim().isEmpty());
        System.out.println(treeString);
    }

    @Test
    public void balancedTreeShouldBeCreatedFromSortedArray() throws Exception {
        Integer[] elems = new Integer[] {
                1, 2, 3, 4, 5, 6
        };

        BinaryTree<Integer> tree = BinaryTree.make(elems);
        System.out.println(tree);
    }

    @Test
    public void treeLevelsShouldBeCorrectlyCaculated() throws Exception {
        BinaryTree<Integer> tree = BinaryTree.leaf(4).add(2).add(6).add(3).add(5);
        System.out.println(tree);
        List<Integer> levelZero = tree.level(0);
        List<Integer> levelOne = tree.level(1);
        List<Integer> levelTwo = tree.level(2);

        List<Integer> levelZeroExpected = new LinkedList<Integer>() {{add(4);}};
        List<Integer> levelOneExpected = new LinkedList<Integer>() {{add(2); add(6);}};
        List<Integer> levelTwoExpected = new LinkedList<Integer>() {{add(3); add(5);}};

        Assert.assertEquals(levelZeroExpected, levelZero);
        Assert.assertEquals(levelOneExpected, levelOne);
        Assert.assertEquals(levelTwoExpected, levelTwo);
    }

    @Test
    public void treeWithBalancedChildrenOfTwoDiffHeightShouldNotBeBalanced() throws Exception {
        BinaryTree<Integer> tree = BinaryTree.leaf(16)
                .add(8)
                    .add(4)
                        .add(2)
                            .add(1)
                            .add(3)
                        .add(6)
                            .add(5)
                            .add(7)
                    .add(12)
                        .add(10)
                            .add(9)
                            .add(11)
                        .add(14)
                            .add(13)
                            .add(15)
                .add(18)
                    .add(17)
                    .add(19);

        System.out.println(tree);

        Assert.assertTrue(tree.left().isBalanced());
        Assert.assertTrue(tree.right().isBalanced());
        Assert.assertFalse(tree.isBalanced());
    }

    @Test
    public void treeShouldBeSearchableAtAllLevels() throws Exception {
        BinaryTree<Integer> fakeLeft = BinaryTree.leaf(6).add(5).add(7);
        BinaryTree<Integer> fakeRight = BinaryTree.leaf(2).add(1).add(3);
        BinaryTree<Integer> nonSearchable = BinaryTree.branch(fakeLeft, 4, fakeRight);
        System.out.println(nonSearchable);
        Assert.assertTrue(fakeLeft.isSearchable());
        Assert.assertTrue(fakeRight.isSearchable());
        Assert.assertFalse(nonSearchable.isSearchable());
    }

    @Test
    public void ancestorOfSameValuesShouldBeThatSameValue() throws Exception {
        BinaryTree<Integer> tree = BinaryTree.leaf(16)
                .add(8)
                .add(4)
                .add(2)
                .add(1)
                .add(3)
                .add(6)
                .add(5)
                .add(7)
                .add(12)
                .add(10)
                .add(9)
                .add(11)
                .add(14)
                .add(13)
                .add(15)
                .add(18)
                .add(17)
                .add(19);

        Integer elem = 9;
        Assert.assertEquals(elem, tree.ancestor(elem, elem));
    }

    @Test
    public void ancestorOfRootAndLeafShouldBeRoot() throws Exception {
        BinaryTree<Integer> tree = BinaryTree.leaf(16)
                .add(8)
                .add(4)
                .add(2)
                .add(1)
                .add(3)
                .add(6)
                .add(5)
                .add(7)
                .add(12)
                .add(10)
                .add(9)
                .add(11)
                .add(14)
                .add(13)
                .add(15)
                .add(18)
                .add(17)
                .add(19);

        Integer expectedAncestor = 16;
        Integer root = 16;
        Integer leaf = 15;

        Assert.assertEquals(expectedAncestor, tree.ancestor(leaf, root));
    }

    @Test
    public void ancestorUnderSameParentShouldHaveParentAsAncestor() throws Exception {
        BinaryTree<Integer> tree = BinaryTree.leaf(16)
                .add(8)
                .add(4)
                .add(2)
                .add(1)
                .add(3)
                .add(6)
                .add(5)
                .add(7)
                .add(12)
                .add(10)
                .add(9)
                .add(11)
                .add(14)
                .add(13)
                .add(15)
                .add(18)
                .add(17)
                .add(19);

        Integer expectedAncestor = 8;
        Integer a = 7;
        Integer b = 9;

        Assert.assertEquals(expectedAncestor, tree.ancestor(b, a));
    }

    @Test(expected = NoSuchElementException.class)
    public void ancestorOfNonContainedValueShouldThrowException() throws Exception {
        BinaryTree<Integer> tree = BinaryTree.leaf(16)
                .add(8)
                .add(4)
                .add(2)
                .add(0)
                .add(3)
                .add(6)
                .add(5)
                .add(7)
                .add(12)
                .add(10)
                .add(9)
                .add(11)
                .add(14)
                .add(13)
                .add(15)
                .add(18)
                .add(17)
                .add(19);

        Integer a = 1;
        Integer b = 7;

        tree.ancestor(b, a);
    }

    @Test(expected = NoSuchElementException.class)
    public void ancestorOfBothNonContainedValuesShouldThrowException() throws Exception {
        BinaryTree<Integer> tree = BinaryTree.leaf(16)
                .add(8)
                .add(4)
                .add(2)
                .add(0)
                .add(3)
                .add(6)
                .add(5)
                .add(7)
                .add(12)
                .add(10)
                .add(9)
                .add(11)
                .add(14)
                .add(13)
                .add(15)
                .add(18)
                .add(17)
                .add(20);

        Integer a = 1;
        Integer b = 19;

        tree.ancestor(a, b);
    }
}
