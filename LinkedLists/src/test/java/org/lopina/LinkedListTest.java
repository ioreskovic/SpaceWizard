package org.lopina;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

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
}
