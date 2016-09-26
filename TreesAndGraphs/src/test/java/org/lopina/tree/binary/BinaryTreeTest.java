package org.lopina.tree.binary;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.*;

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

    @Test(expected = NoSuchElementException.class)
    public void ancestorOfElementsInEmptyTreeShouldThrowException() throws Exception {
        BinaryTree<Integer> tree = BinaryTree.<Integer>empty();
        tree.ancestor(1, 7);
    }

    @Test(expected = NoSuchElementException.class)
    public void predecessorOfNonContainingElementShouldThrowException() throws Exception {
        BinaryTree<Integer> tree = BinaryTree.<Integer>empty().add(4).add(2).add(6).add(1).add(3).add(5).add(7);
        tree.predecessor(8);
    }

    @Test(expected = NoSuchElementException.class)
    public void successorOfNonContainingElementShouldThrowException() throws Exception {
        BinaryTree<Integer> tree = BinaryTree.<Integer>empty().add(4).add(2).add(6).add(1).add(3).add(5).add(7);
        tree.successor(0);
    }

    @Test(expected = NoSuchElementException.class)
    public void predecessorOfElementWithNoPredecessorShouldThrowException() throws Exception {
        BinaryTree<Integer> tree = BinaryTree.<Integer>empty().add(4).add(2).add(6).add(1).add(3).add(5).add(7);
        tree.predecessor(1);
    }

    @Test(expected = NoSuchElementException.class)
    public void successorOfElementWithNoSuccessorShouldThrowException() throws Exception {
        BinaryTree<Integer> tree = BinaryTree.<Integer>empty().add(4).add(2).add(6).add(1).add(3).add(5).add(7);
        tree.successor(7);
    }

    @Test
    public void predecessorOfNodeWithLeftChildShouldReturnMaximumInLeftSubtree() throws Exception {
        Leaf<Integer> leaf1 = BinaryTree.leaf(1);
        Leaf<Integer> leaf3 = BinaryTree.leaf(3);

        Leaf<Integer> leaf5 = BinaryTree.leaf(5);
        Leaf<Integer> leaf7 = BinaryTree.leaf(7);

        Branch<Integer> branch123 = BinaryTree.branch(leaf1, 2, leaf3);
        Branch<Integer> branch567 = BinaryTree.branch(leaf5, 6, leaf7);

        BinaryTree<Integer> tree = BinaryTree.branch(branch123, 4, branch567);

        Assert.assertEquals(branch567.left().max(), tree.predecessor(6));
    }

    @Test
    public void successorOfNodeWithRightChildShouldReturnMinimumInRightSubtree() throws Exception {
        Leaf<Integer> leaf1 = BinaryTree.leaf(1);
        Leaf<Integer> leaf3 = BinaryTree.leaf(3);

        Leaf<Integer> leaf5 = BinaryTree.leaf(5);
        Leaf<Integer> leaf7 = BinaryTree.leaf(7);

        Branch<Integer> branch123 = BinaryTree.branch(leaf1, 2, leaf3);
        Branch<Integer> branch567 = BinaryTree.branch(leaf5, 6, leaf7);

        BinaryTree<Integer> tree = BinaryTree.branch(branch123, 4, branch567);

        Assert.assertEquals(branch123.right().min(), tree.successor(2));
    }

    @Test
    public void predecessorOfNodeWithoutLeftChildShouldReturnFirstParentWhereNodeIsInRightSubtree() throws Exception {
        BinaryTree<Integer> tree = BinaryTree.<Integer>empty()
                .add(9)
                .add(2).add(16)
                .add(1).add(4).add(14).add(17)
                .add(3).add(6).add(12).add(15)
                .add(5).add(8).add(10).add(13)
                .add(7).add(11);

        System.out.println(tree);

        Assert.assertEquals(tree.elem(), tree.predecessor(10));
    }

    @Test
    public void successorOfNodeWithoutRightChildShouldReturnFirstParentWhereNodeIsInLeftSubtree() throws Exception {
        BinaryTree<Integer> tree = BinaryTree.<Integer>empty()
                .add(9)
                .add(2).add(16)
                .add(1).add(4).add(14).add(17)
                .add(3).add(6).add(12).add(15)
                .add(5).add(8).add(10).add(13)
                .add(7).add(11);

        System.out.println(tree);

        Assert.assertEquals(tree.elem(), tree.successor(8));
    }

    @Test
    public void referencingElementsShouldReferenceThemInOrder() throws Exception {
        BinaryTree<Integer> tree = BinaryTree.<Integer>empty()
                .add(7)
                .add(1).add(8)
                .add(4).add(12)
                .add(3).add(5).add(9)
                .add(2).add(6).add(11)
                .add(10);

        for (int i = 0; i < tree.size(); i++) {
            Assert.assertEquals(Integer.valueOf(i + 1), tree.apply(i));
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void referencingLesserInvalidApplyIndexedElementShouldThrowException() throws Exception {
        BinaryTree<Integer> tree = BinaryTree.<Integer>empty()
                .add(7)
                .add(1).add(8)
                .add(4).add(12)
                .add(3).add(5).add(9)
                .add(2).add(6).add(11)
                .add(10);

        tree.apply(-1);
    }

    @Test(expected = NoSuchElementException.class)
    public void referencingGreaterInvalidApplyIndexedElementShouldThrowException() throws Exception {
        BinaryTree<Integer> tree = BinaryTree.<Integer>empty()
                .add(7)
                .add(1).add(8)
                .add(4).add(12)
                .add(3).add(5).add(9)
                .add(2).add(6).add(11)
                .add(10);

        tree.apply(tree.size());
    }

    @Test
    public void removingRootFromSingletonTreeShouldYieldEmptyTree() throws Exception {
        BinaryTree<Integer> tree = BinaryTree.branch(BinaryTree.<Integer>empty(), 4, BinaryTree.<Integer>empty());
        BinaryTree<Integer> result = tree.remove(4);
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void removingLeftLeafOfABranchShouldYieldLeafWhenRightSubtreeIsEmpty() throws Exception {
        BinaryTree<Integer> tree = BinaryTree.branch(BinaryTree.leaf(2), 4, BinaryTree.<Integer>empty());
        BinaryTree<Integer> result = tree.remove(4);
        Assert.assertEquals(1, result.size());
        Assert.assertTrue(result.contains(2));
        Assert.assertFalse(result.contains(4));
        Assert.assertEquals(Integer.valueOf(2), result.elem());
    }

    @Test
    public void removingRightLeafOfABranchShouldYieldLeafWhenLeftSubtreeIsEmpty() throws Exception {
        BinaryTree<Integer> tree = BinaryTree.branch(BinaryTree.<Integer>empty(), 4, BinaryTree.leaf(6));
        BinaryTree<Integer> result = tree.remove(4);
        Assert.assertEquals(1, result.size());
        Assert.assertTrue(result.contains(6));
        Assert.assertFalse(result.contains(4));
        Assert.assertEquals(Integer.valueOf(6), result.elem());
    }

    @Test
    public void removingRootShouldYieldTreeWithPredecessorAsRootWhenRightSubtreeIsEmpty() throws Exception {
        BinaryTree<Integer> tree = BinaryTree.leaf(4).add(2).add(3);
        BinaryTree<Integer> result = tree.remove(4);
        Assert.assertEquals(2, result.size());
        Assert.assertFalse(result.contains(4));
        Assert.assertTrue(result.contains(2));
        Assert.assertTrue(result.contains(3));
        Assert.assertEquals(Integer.valueOf(3), result.elem());
    }

    @Test
    public void removingRootShouldYieldTreeWithSuccessorAsRootWhenLeftSubtreeIsEmpty() throws Exception {
        BinaryTree<Integer> tree = BinaryTree.leaf(4).add(6).add(5);
        BinaryTree<Integer> result = tree.remove(4);
        Assert.assertEquals(2, result.size());
        Assert.assertFalse(result.contains(4));
        Assert.assertTrue(result.contains(6));
        Assert.assertTrue(result.contains(5));
        Assert.assertEquals(Integer.valueOf(5), result.elem());
    }

    @Test
    public void nthMinShouldReturnElementsInOrder() throws Exception {
        BinaryTree<Integer> tree = BinaryTree.<Integer>empty()
                .add(7)
                .add(1).add(8)
                .add(4).add(12)
                .add(3).add(5).add(9)
                .add(2).add(6).add(11)
                .add(10);

        for (int i = 0; i < tree.size(); i++) {
            Assert.assertEquals(Integer.valueOf(i + 1), tree.nthMin(i));
        }
    }

    @Test
    public void nthMaxShouldReturnElementsInReverseOrder() throws Exception {
        BinaryTree<Integer> tree = BinaryTree.<Integer>empty()
                .add(7)
                .add(1).add(8)
                .add(4).add(12)
                .add(3).add(5).add(9)
                .add(2).add(6).add(11)
                .add(10);

        for (int i = 0; i < tree.size(); i++) {
            Assert.assertEquals(Integer.valueOf(tree.size() - i), tree.nthMax(i));
        }
    }

    @Test
    public void pathBetweenNodesShouldBeginWithOneAndEndWithAnotherAndGoOverCommonAncestorWhenBothExist() throws Exception {
        BinaryTree<Integer> tree = BinaryTree.<Integer>empty()
                .add(9)
                .add(1).add(17)
                .add(8).add(10)
                .add(2).add(16)
                .add(7).add(11)
                .add(3).add(15)
                .add(6).add(12)
                .add(4).add(14)
                .add(8).add(13);

        Iterable<Integer> result = tree.pathBetween(8, 13);

        List<Integer> meaningfulResult = new ArrayList<>();

        for (Iterator<Integer> it = result.iterator(); it.hasNext();) {
            Integer n = it.next();
            if (n.equals(8) || n.equals(9) || n.equals(13)) {
                meaningfulResult.add(n);
            }
        }

        ArrayList<Integer> expected = new ArrayList<Integer>(){{
            add(8); add(9); add(13);
        }};

        Assert.assertEquals(expected, meaningfulResult);
    }

    @Test
    public void pathBetweenNodesShouldBeginWithReverseOneAndEndWithAnotherAndGoOverCommonAncestorWhenBothExist() throws Exception {
        BinaryTree<Integer> tree = BinaryTree.<Integer>empty()
                .add(9)
                .add(1).add(17)
                .add(8).add(10)
                .add(2).add(16)
                .add(7).add(11)
                .add(3).add(15)
                .add(6).add(12)
                .add(4).add(14)
                .add(8).add(13);

        Iterable<Integer> result = tree.pathBetween(13, 8);

        List<Integer> meaningfulResult = new ArrayList<>();

        for (Iterator<Integer> it = result.iterator(); it.hasNext();) {
            Integer n = it.next();
            if (n.equals(8) || n.equals(9) || n.equals(13)) {
                meaningfulResult.add(n);
            }
        }

        ArrayList<Integer> expected = new ArrayList<Integer>(){{
            add(13); add(9); add(8);
        }};

        Assert.assertEquals(expected, meaningfulResult);
    }

    @Test(expected = NoSuchElementException.class)
    public void pathOnEmptyTreeShouldThrowException() throws Exception {
        BinaryTree.<Integer>empty().pathBetween(1, 1);
    }

    @Test(expected = NoSuchElementException.class)
    public void pathOnLeftSplittingElementNotExistsShouldThrowException() throws Exception {
        BinaryTree<Integer> tree = BinaryTree.<Integer>empty()
                .add(18)
                .add(9)
                .add(1).add(17)
                .add(8).add(10)
                .add(2).add(16)
                .add(7).add(11)
                .add(3).add(15)
                .add(6).add(12)
                .add(4).add(14)
                .add(8).add(13);

        Iterable<Integer> result = tree.pathBetween(0, 13);
    }

    @Test(expected = NoSuchElementException.class)
    public void pathOnRightSplittingElementNotExistsShouldThrowException() throws Exception {
        BinaryTree<Integer> tree = BinaryTree.<Integer>empty()
                .add(0)
                .add(9)
                .add(1).add(17)
                .add(8).add(10)
                .add(2).add(16)
                .add(7).add(11)
                .add(3).add(15)
                .add(6).add(12)
                .add(4).add(14)
                .add(8).add(13);

        Iterable<Integer> result = tree.pathBetween(8, 18);
    }

    @Test
    public void testWeave() throws Exception {
        ArrayDeque<Integer> t1 = new ArrayDeque<Integer>() {{
            offerLast(1);
            offerLast(2);
        }};

        ArrayDeque<Integer> t2 = new ArrayDeque<Integer>() {{
            offerLast(3);
            offerLast(4);
        }};

        Set<Deque<Integer>> result = BinaryTree.weave(t1, t2);
        System.out.println(result);
    }

    @Test
    public void testWeave2() throws Exception {
        ArrayDeque<Integer> t1 = new ArrayDeque<Integer>() {{
            offerLast(1);
            offerLast(2);
        }};

        ArrayDeque<Integer> t2 = new ArrayDeque<Integer>() {{
            offerLast(3);
            offerLast(4);
        }};

        ArrayDeque<Integer> t3 = new ArrayDeque<Integer>() {{
            offerLast(5);
            offerLast(6);
        }};

        List<Deque<Integer>> ts = new ArrayList<>();
        ts.add(t1);
        ts.add(t2);
        ts.add(t3);

        Set<Deque<Integer>> s1 = new HashSet<>();
        s1.add(ts.get(0));

        for (int i = 1; i < ts.size(); i++) {
            Set<Deque<Integer>> s2 = new HashSet<>();
            s2.add(ts.get(i));
            Set<Deque<Integer>> resultsIter = new HashSet<>();
            for (Deque<Integer> a : s1) {
                for (Deque<Integer> b : s2) {
                    resultsIter.addAll(BinaryTree.weave(a, b));
                }
            }
            s1 = resultsIter;
        }

        System.out.println(s1);
        System.out.println(s1.size());
    }

    @Test
    public void testBstSequencesEmpty() throws Exception {
        Set<Deque<Integer>> bstSeq = BinaryTree.<Integer>empty().bstSequences();
        System.out.println(bstSeq);
        System.out.println(bstSeq.size());
    }

    @Test
    public void testBstSequencesLeaf() throws Exception {
        Set<Deque<Integer>> bstSeq = BinaryTree.leaf(4).bstSequences();
        System.out.println(bstSeq);
        System.out.println(bstSeq.size());
    }

    @Test
    public void testBstSequencesBranch() throws Exception {
        Set<Deque<Integer>> bstSeq = BinaryTree.branch(BinaryTree.leaf(1), 2, BinaryTree.leaf(3)).bstSequences();
        System.out.println(bstSeq);
        System.out.println(bstSeq.size());
    }

    @Test
    public void testBstSequencesTree() throws Exception {
        Set<Deque<Integer>> bstSeq = BinaryTree.<Integer>empty().add(4).add(2).add(6).add(1).add(3).add(5).add(7).bstSequences();
        System.out.println(bstSeq);
        System.out.println(bstSeq.size());
    }

    @Test
    public void randomShouldReturnRandomValueFromTheTree() throws Exception {
        Integer[] elems = new Integer[] {
                2, 4, 6, 8, 10, 12, 14
        };

        Set<Integer> elemsSet = new HashSet<>(Arrays.asList(elems));

        Assert.assertTrue(elemsSet.contains(BinaryTree.make(elems).random()));
    }

    @Test(expected = NoSuchElementException.class)
    public void randomOnEmptyTreeShouldThrowException() throws Exception {
        BinaryTree.<Integer>empty().random();
    }

    @Test
    public void pathSumShouldCollectAllRootToLeafPathsWithCorrectSum() throws Exception {
        BinaryTree<Integer> tree = BinaryTree.branch(BinaryTree.branch(BinaryTree.leaf(3), 2, BinaryTree.leaf(3)), 1,
                BinaryTree.branch(BinaryTree.leaf(3), 2, BinaryTree.leaf(4)));

        List<Iterable<Integer>> pathSums = BinaryTree.pathSum(tree, 6);
        System.out.println(pathSums);
        Assert.assertEquals(3, pathSums.size());
    }

}
