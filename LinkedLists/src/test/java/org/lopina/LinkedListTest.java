package org.lopina;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.Comparator;

@RunWith(BlockJUnit4ClassRunner.class)
public class LinkedListTest
{
    @Test
    public void newListShouldHaveEmptyData() throws Exception {
        LinkedList<String> list = new LinkedList<>();
        Assert.assertEquals(0, list.size());
        Assert.assertNull(list.head());
    }

    @Test
    public void insertingIntoEmptyListShouldYieldSingletonList() throws Exception {
        LinkedList<String> list = new LinkedList<>();
        list.append("a");
        Assert.assertEquals(1, list.size());
        Assert.assertNotNull(list.head());
        Assert.assertEquals("a", list.head());
    }

    @Test
    public void deletingFromEmptyListShouldYieldEmptyList() throws Exception {
        LinkedList<String> list = new LinkedList<>();
        list.delete("a");
        Assert.assertEquals(0, list.size());
        Assert.assertNull(list.head());
    }

    @Test
    public void deletingAfterInsertionShouldYieldEmptyList() throws Exception {
        LinkedList<String> list = new LinkedList<>();
        list.append("a");
        list.delete("a");
        Assert.assertEquals(0, list.size());
    }

    @Test
    public void deletingNonExistingElementShouldNotChangeAnything() throws Exception {
        LinkedList<String> list = new LinkedList<>();
        list.append("a");
        list.append("b");
        list.append("c");
        list.delete("d");
        Assert.assertEquals(3, list.size());
    }

    @Test
    public void deletingElementFromSameListDataShouldYieldListOneElementLess() throws Exception {
        LinkedList<String> list = new LinkedList<>();
        list.append("0");
        list.append("1");
        list.append("3");
        list.append("a");
        list.append("a");
        list.append("a");
        list.append("a");
        list.append("a");
        int oldSize = list.size();
        list.delete("a");
        int newSize = list.size();
        Assert.assertEquals(oldSize - 1, newSize);
    }

    @Test
    public void distinctingEmptyListShouldYieldEmptyList() throws Exception {
        LinkedList<String> list = new LinkedList<>();
        list.distinct();
        Assert.assertEquals(0, list.size());
        Assert.assertNull(list.head());
    }

    @Test
    public void distinctingSingletonListShouldYieldSameList() throws Exception {
        LinkedList<String> list = new LinkedList<>();
        list.append("a");
        int oldSize = list.size();
        list.distinct();
        Assert.assertEquals(oldSize, list.size());
    }

    @Test
    public void distinctingNoMultipleElementListShouldYieldSameList() throws Exception {
        LinkedList<String> list = new LinkedList<>();
        list.append("a");
        list.append("b");
        list.append("c");
        list.append("d");
        int oldSize = list.size();
        list.distinct();
        Assert.assertEquals(oldSize, list.size());
    }

    @Test
    public void distinctingElementListShouldYieldDistinctList() throws Exception {
        LinkedList<String> list = new LinkedList<>();
        list.append("a");

        list.append("b");
        list.append("b");

        list.append("c");
        list.append("c");
        list.append("c");

        list.append("d");
        list.append("d");
        list.append("d");
        list.append("d");

        list.distinct();

        Assert.assertEquals(4, list.size());
    }

    @Test
    public void distinctingSlowEmptyListShouldYieldEmptyList() throws Exception {
        LinkedList<String> list = new LinkedList<>();
        list.distinctSlow();
        Assert.assertEquals(0, list.size());
        Assert.assertNull(list.head());
    }

    @Test
    public void distinctingSlowSingletonListShouldYieldSameList() throws Exception {
        LinkedList<String> list = new LinkedList<>();
        list.append("a");
        int oldSize = list.size();
        list.distinctSlow();
        Assert.assertEquals(oldSize, list.size());
    }

    @Test
    public void distinctingSlowNoMultipleElementListShouldYieldSameList() throws Exception {
        LinkedList<String> list = new LinkedList<>();
        list.append("a");
        list.append("b");
        list.append("c");
        list.append("d");
        int oldSize = list.size();
        list.distinctSlow();
        Assert.assertEquals(oldSize, list.size());
    }

    @Test
    public void distinctingSlowElementListShouldYieldDistinctList() throws Exception {
        LinkedList<String> list = new LinkedList<>();
        list.append("a");

        list.append("b");
        list.append("b");

        list.append("c");
        list.append("c");
        list.append("c");

        list.append("d");
        list.append("d");
        list.append("d");
        list.append("d");

        list.distinctSlow();

        Assert.assertEquals(4, list.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void nthToLastOnEmptyListShouldThrowException() throws Exception {
        LinkedList<String> list = new LinkedList<>();
        list.nthToLast(0);
    }

    @Test
    public void nthToLastOnSingletonListShouldReturnHeadDataFor0thArg() throws Exception {
        LinkedList<String> list = new LinkedList<>();
        list.append("a");
        String nthToLast = list.nthToLast(0);
        Assert.assertEquals("a", nthToLast);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nthToLastOnSingletonListShouldThrowExceptionFor1stArg() throws Exception {
        LinkedList<String> list = new LinkedList<>();
        list.append("a");
        String nthToLast = list.nthToLast(1);
    }

    @Test
    public void nthToLastShouldCorrectlyTakeElements() throws Exception {
        LinkedList<String> list = new LinkedList<>();
        list.append("1");
        list.append("2");
        list.append("3");
        list.append("4");
        list.append("5");
        list.append("6");

        Assert.assertEquals("6", list.nthToLast(0));
        Assert.assertEquals("5", list.nthToLast(1));
        Assert.assertEquals("4", list.nthToLast(2));
        Assert.assertEquals("3", list.nthToLast(3));
        Assert.assertEquals("2", list.nthToLast(4));
        Assert.assertEquals("1", list.nthToLast(5));
    }

    @Test
    public void partitionAroundEmptyListShouldYieldEmptyList() throws Exception {
        LinkedList<Integer> list = new LinkedList<>();
        list.partitionAround(5, Comparator.<Integer>naturalOrder());
        Assert.assertEquals(0, list.size());
    }

    @Test
    public void paritionAroundSingletonLtListShouldYieldSameList() throws Exception {
        LinkedList<Integer> list = new LinkedList<>();
        list.append(0);
        list.partitionAround(5, Comparator.<Integer>naturalOrder());
        Assert.assertEquals(1, list.size());
        Assert.assertEquals(Integer.valueOf(0), list.head());
    }

    @Test
    public void paritionAroundSingletonGteListShouldYieldSameList() throws Exception {
        LinkedList<Integer> list = new LinkedList<>();
        list.append(9);
        list.partitionAround(5, Comparator.<Integer>naturalOrder());
        Assert.assertEquals(1, list.size());
        Assert.assertEquals(Integer.valueOf(9), list.head());
    }

    @Test
    public void partitionAroundListShouldReturnPartitionedList() throws Exception {
        LinkedList<Integer> list = new LinkedList<>();

        list.append(9);
        list.append(0);
        list.append(8);
        list.append(1);
        list.append(7);
        list.append(2);
        list.append(6);
        list.append(3);
        list.append(5);
        list.append(4);

        Integer pivot = 5;
        list.partitionAround(pivot, Comparator.<Integer>naturalOrder());

        Assert.assertEquals(10, list.size());

        System.out.println(list.toString());
    }

    @Test
    public void partitionOfEmptyListShouldYieldEmptyList() throws Exception {
        LinkedList<Integer> list = new LinkedList<>();
        LinkedList<Integer> pList = list.partition(5, Comparator.<Integer>naturalOrder());
        Assert.assertEquals(0, pList.size());
    }

    @Test
    public void paritionOfSingletonLtListShouldYieldSameList() throws Exception {
        LinkedList<Integer> list = new LinkedList<>();
        list.append(0);
        LinkedList<Integer> pList = list.partition(5, Comparator.<Integer>naturalOrder());
        Assert.assertEquals(1, pList.size());
        Assert.assertEquals(Integer.valueOf(0), pList.head());
    }

    @Test
    public void paritionOfSingletonGteListShouldYieldSameList() throws Exception {
        LinkedList<Integer> list = new LinkedList<>();
        list.append(9);
        LinkedList<Integer> pList = list.partition(5, Comparator.<Integer>naturalOrder());
        Assert.assertEquals(1, pList.size());
        Assert.assertEquals(Integer.valueOf(9), pList.head());
    }

    @Test
    public void partitionOfListShouldReturnPartitionedList() throws Exception {
        LinkedList<Integer> list = new LinkedList<>();

        list.append(9);
        list.append(0);
        list.append(8);
        list.append(1);
        list.append(7);
        list.append(2);
        list.append(6);
        list.append(3);
        list.append(5);
        list.append(4);

        Integer pivot = 5;
        LinkedList<Integer> pList = list.partition(pivot, Comparator.<Integer>naturalOrder());

        Assert.assertEquals(10, pList.size());

        System.out.println(pList.toString());
    }

    @Test
    public void sumLeastSignificantShouldSumCorrectly1() throws Exception {
        LinkedList<Integer> a = new LinkedList<>();
        a.append(7);
        a.append(1);
        a.append(6);

        LinkedList<Integer> b = new LinkedList<>();
        b.append(5);
        b.append(9);
        b.append(2);

        LinkedList<Integer> r = LinkedList.sumLeastSignificant(a, b);

        LinkedList<Integer> e = new LinkedList<>();
        e.append(2);
        e.append(1);
        e.append(9);

        Assert.assertEquals(e, r);
    }

    @Test
    public void sumLeastSignificantShouldSumCorrectly2() throws Exception {
        LinkedList<Integer> a = new LinkedList<>();
        a.append(7);
        a.append(1);
        a.append(6);

        LinkedList<Integer> b = new LinkedList<>();
        b.append(1);
        b.append(1);

        LinkedList<Integer> r = LinkedList.sumLeastSignificant(a, b);

        LinkedList<Integer> e = new LinkedList<>();
        e.append(8);
        e.append(2);
        e.append(6);

        Assert.assertEquals(e, r);
    }

    @Test
    public void sumLeastSignificantShouldSumCorrectly3() throws Exception {
        LinkedList<Integer> a = new LinkedList<>();
        a.append(7);
        a.append(1);
        a.append(6);

        LinkedList<Integer> b = new LinkedList<>();
        b.append(3);
        b.append(8);
        b.append(3);

        LinkedList<Integer> r = LinkedList.sumLeastSignificant(a, b);

        LinkedList<Integer> e = new LinkedList<>();
        e.append(0);
        e.append(0);
        e.append(0);
        e.append(1);

        Assert.assertEquals(e, r);
    }

    @Test
    public void sumLeastSignificantShouldSumCorrectly4() throws Exception {
        LinkedList<Integer> a = new LinkedList<>();
        a.append(7);
        a.append(1);
        a.append(6);

        LinkedList<Integer> b = new LinkedList<>();

        LinkedList<Integer> r = LinkedList.sumLeastSignificant(a, b);

        LinkedList<Integer> e = new LinkedList<>();
        e.append(7);
        e.append(1);
        e.append(6);

        Assert.assertEquals(e, r);
    }

    @Test
    public void sumMostSignificantShouldSumCorrectly1() throws Exception {
        LinkedList<Integer> a = new LinkedList<>();
        a.append(6);
        a.append(1);
        a.append(7);

        LinkedList<Integer> b = new LinkedList<>();
        b.append(2);
        b.append(9);
        b.append(5);

        LinkedList<Integer> r = LinkedList.sumMostSignificant(a, b);

        LinkedList<Integer> e = new LinkedList<>();
        e.append(2);
        e.append(1);
        e.append(9);

        Assert.assertEquals(e, r);
    }

    @Test
    public void sumMostSignificantShouldSumCorrectly2() throws Exception {
        LinkedList<Integer> a = new LinkedList<>();
        a.append(6);
        a.append(1);
        a.append(7);

        LinkedList<Integer> b = new LinkedList<>();
        b.append(1);
        b.append(1);

        LinkedList<Integer> r = LinkedList.sumMostSignificant(a, b);

        LinkedList<Integer> e = new LinkedList<>();
        e.append(8);
        e.append(2);
        e.append(6);

        Assert.assertEquals(e, r);
    }

    @Test
    public void sumMostSignificantShouldSumCorrectly3() throws Exception {
        LinkedList<Integer> a = new LinkedList<>();
        a.append(6);
        a.append(1);
        a.append(7);

        LinkedList<Integer> b = new LinkedList<>();
        b.append(3);
        b.append(8);
        b.append(3);

        LinkedList<Integer> r = LinkedList.sumMostSignificant(a, b);

        LinkedList<Integer> e = new LinkedList<>();
        e.append(0);
        e.append(0);
        e.append(0);
        e.append(1);

        Assert.assertEquals(e, r);
    }

    @Test
    public void sumMostSignificantShouldSumCorrectly4() throws Exception {
        LinkedList<Integer> a = new LinkedList<>();
        a.append(6);
        a.append(1);
        a.append(7);

        LinkedList<Integer> b = new LinkedList<>();

        LinkedList<Integer> r = LinkedList.sumMostSignificant(a, b);

        LinkedList<Integer> e = new LinkedList<>();
        e.append(7);
        e.append(1);
        e.append(6);

        Assert.assertEquals(e, r);
    }

    @Test
    public void reversingEmptyListShouldYieldEmptyList() throws Exception {
        LinkedList<Integer> list = new LinkedList<>();
        list.reversed();
        Assert.assertEquals(new LinkedList<Integer>(), list);
    }

    @Test
    public void reversingSingletonListShouldYieldSameSingletonList() throws Exception {
        LinkedList<Integer> list = new LinkedList<>();
        list.append(5);
        list.reversed();
        LinkedList<Integer> exp = new LinkedList<Integer>();
        exp.append(5);
        Assert.assertEquals(exp, list);
    }

    @Test
    public void reversingTwoElementListShouldYieldListOfSameElementsInReversedOrder() throws Exception {
        LinkedList<Integer> list = new LinkedList<>();
        list.append(1);
        list.append(9);
        list.reversed();
        LinkedList<Integer> exp = new LinkedList<Integer>();
        exp.append(9);
        exp.append(1);
        Assert.assertEquals(exp, list);
    }

    @Test
    public void reversingMultipleElementListShouldYieldListOfSameElementsInReversedOrder() throws Exception {
        LinkedList<Integer> list = new LinkedList<>();
        list.append(1);
        list.append(1);
        list.append(2);
        list.append(3);
        list.append(3);
        list.append(4);
        list.append(5);
        list.append(5);
        list.append(6);
        list.reversed();
        LinkedList<Integer> exp = new LinkedList<Integer>();
        exp.append(6);
        exp.append(5);
        exp.append(5);
        exp.append(4);
        exp.append(3);
        exp.append(3);
        exp.append(2);
        exp.append(1);
        exp.append(1);
        Assert.assertEquals(exp, list);
    }

    @Test
    public void reversedEmptyListShouldYieldEmptyList() throws Exception {
        LinkedList<Integer> list = new LinkedList<>();
        list = list.reverse();
        Assert.assertEquals(new LinkedList<Integer>(), list);
    }

    @Test
    public void reversedSingletonListShouldYieldSameSingletonList() throws Exception {
        LinkedList<Integer> list = new LinkedList<>();
        list.append(5);
        list = list.reverse();
        LinkedList<Integer> exp = new LinkedList<Integer>();
        exp.append(5);
        Assert.assertEquals(exp, list);
    }

    @Test
    public void reversedTwoElementListShouldYieldListOfSameElementsInReversedOrder() throws Exception {
        LinkedList<Integer> list = new LinkedList<>();
        list.append(1);
        list.append(9);
        list = list.reverse();
        LinkedList<Integer> exp = new LinkedList<Integer>();
        exp.append(9);
        exp.append(1);
        Assert.assertEquals(exp, list);
    }

    @Test
    public void reversedMultipleElementListShouldYieldListOfSameElementsInReversedOrder() throws Exception {
        LinkedList<Integer> list = new LinkedList<>();
        list.append(1);
        list.append(1);
        list.append(2);
        list.append(3);
        list.append(3);
        list.append(4);
        list.append(5);
        list.append(5);
        list.append(6);
        list = list.reverse();
        LinkedList<Integer> exp = new LinkedList<Integer>();
        exp.append(6);
        exp.append(5);
        exp.append(5);
        exp.append(4);
        exp.append(3);
        exp.append(3);
        exp.append(2);
        exp.append(1);
        exp.append(1);
        Assert.assertEquals(exp, list);
    }

    @Test
    public void emptyListShouldBeAPalindrome() throws Exception {
        LinkedList<Integer> list = new LinkedList<>();
        Assert.assertTrue(list.isPalindrome());
    }

    @Test
    public void singletonListShouldBeAPalindrome() throws Exception {
        LinkedList<Integer> list = new LinkedList<>();
        list.append(0);
        Assert.assertTrue(list.isPalindrome());
    }

    @Test
    public void twoDifferentElementListShouldNotBeAPalindrome() throws Exception {
        LinkedList<Integer> list = new LinkedList<>();
        list.append(0);
        list.append(1);
        Assert.assertFalse(list.isPalindrome());
    }

    @Test
    public void multipleDifferentElementListShouldNotBeAPalindrome() throws Exception {
        LinkedList<Integer> list = new LinkedList<>();
        list.append(0);
        list.append(1);
        list.append(1);
        list.append(2);
        list.append(1);
        list.append(1);
        list.append(3);
        Assert.assertFalse(list.isPalindrome());
    }

    @Test
    public void multipleDifferentElementListShouldBeAPalindrome() throws Exception {
        LinkedList<Integer> list = new LinkedList<>();
        list.append(0);
        list.append(1);
        list.append(1);
        list.append(2);
        list.append(1);
        list.append(1);
        list.append(0);
        Assert.assertTrue(list.isPalindrome());
    }

    @Test
    public void twoEmptyListsShouldNotHaveIntersection() throws Exception {
        LinkedList<Integer> a = new LinkedList<>();
        LinkedList<Integer> b = new LinkedList<>();
        Assert.assertTrue(LinkedList.areIntersected(a, b));
    }

    @Test
    public void emptyAndNonEmptyListsShouldNotHaveIntersection() throws Exception {
        LinkedList<Integer> a = new LinkedList<>();
        a.append(1);
        a.append(2);
        LinkedList<Integer> b = new LinkedList<>();
        Assert.assertFalse(LinkedList.areIntersected(a, b));
    }

    @Test
    public void intersectedListsShouldHaveIntersection() throws Exception {
        LinkedList<Integer> tail = new LinkedList<>();
        tail.append(4);
        tail.append(5);
        tail.append(6);

        LinkedList<Integer> a = new LinkedList<>();
        a.append(1);
        a.append(2);
        a.append(3);

        LinkedList<Integer> b = new LinkedList<>();
        b.append(7);
        b.append(8);
        b.append(9);

        a.concat(tail);
        b.concat(tail);

        Assert.assertTrue(LinkedList.areIntersected(a, b));
    }

    @Test
    public void twoEmptyListsIntersectionShouldBeNull() throws Exception {
        LinkedList<Integer> a = new LinkedList<>();
        LinkedList<Integer> b = new LinkedList<>();
        Assert.assertNull(LinkedList.intersection(a, b));
    }

    @Test
    public void twoSameSingletonListIntersectionShouldBeSingletoneElement() throws Exception {
        LinkedList<Integer> a = new LinkedList<>();
        LinkedList<Integer> b = new LinkedList<>();
        LinkedList<Integer> t = new LinkedList<>();
        t.append(3);
        a.concat(t);
        b.concat(t);
        Assert.assertEquals(Integer.valueOf(3), LinkedList.intersection(a, b));
    }

    @Test
    public void emptyAndNonEmptyListIntersectionShouldBeNull() throws Exception {
        LinkedList<Integer> a = new LinkedList<>();
        LinkedList<Integer> b = new LinkedList<>();
        b.append(2);
        Assert.assertNull(LinkedList.intersection(a, b));
    }

    @Test
    public void nonIntersectionListIntersectionShouldBeNul() throws Exception {
        LinkedList<Integer> a = new LinkedList<>();
        a.append(1);
        a.append(2);
        a.append(3);
        LinkedList<Integer> b = new LinkedList<>();
        b.append(1);
        b.append(2);
        b.append(3);
        Assert.assertNull(LinkedList.intersection(a, b));
    }

    @Test
    public void listAndItsSublistIntersectionShouldBeSublistsHead() throws Exception {
        LinkedList<Integer> a = new LinkedList<>();
        LinkedList<Integer> b = new LinkedList<>();
        LinkedList<Integer> t = new LinkedList<>();
        t.append(5);
        t.append(6);
        a.append(1);
        a.append(2);
        a.append(3);
        a.append(4);
        a.concat(t);
        b.concat(t);
        Assert.assertEquals(b.head(), LinkedList.intersection(a, b));
    }

    @Test
    public void twoIntersectingListsIntersectionShouldExist() throws Exception {
        LinkedList<Integer> a = new LinkedList<>();
        LinkedList<Integer> b = new LinkedList<>();
        LinkedList<Integer> t = new LinkedList<>();
        a.append(1);
        a.append(2);
        a.append(3);
        b.append(4);
        b.append(5);
        t.append(6);
        t.append(7);
        a.concat(t);
        b.concat(t);
        Assert.assertEquals(t.head(), LinkedList.intersection(a, b));
    }

    @Test
    public void emptyListShouldHaveNoLoops() throws Exception {
        LinkedList<Integer> a = new LinkedList<>();
        Assert.assertNull(a.loopStart());
    }

    @Test
    public void singletonListShouldHaveNoLoops() throws Exception {
        LinkedList<Integer> a = new LinkedList<>();
        a.append(1);
        Assert.assertNull(a.loopStart());
    }

    @Test
    public void nonCircularListShouldHaveNoLoops() throws Exception {
        LinkedList<Integer> a = new LinkedList<>();
        a.append(1);
        a.append(2);
        a.append(3);
        a.append(4);
        Assert.assertNull(a.loopStart());
    }

    @Test
    public void pureCircularListShouldHaveItsHeadAsLoopStart() throws Exception {
        LinkedList<Integer> a = new LinkedList<>();
        a.append(1);
        a.append(2);
        a.append(3);
        a.append(4);
        a.nThNode(3).next = a.root();
        Assert.assertSame(a.root(), a.loopStart());
    }

    @Test
    public void circularListShouldHaveElementAsLoopStart() throws Exception {
        LinkedList<Integer> a = new LinkedList<>();
        a.append(1);
        a.append(2);
        a.append(3);
        a.append(4);
        a.append(5);
        a.append(6);
        a.nThNode(5).next = a.nThNode(2);
        Assert.assertSame(a.nThNode(2), a.loopStart());
    }

}
