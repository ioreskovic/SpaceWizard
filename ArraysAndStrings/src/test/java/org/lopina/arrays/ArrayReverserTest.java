package org.lopina.arrays;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.Arrays;

@RunWith(BlockJUnit4ClassRunner.class)
public abstract class ArrayReverserTest {
    protected abstract <T> ArrayReverser<T> getArrayReverser();

    @Test(expected = NullPointerException.class)
    public void exceptionShouldBeThrownWhenTryingToReverseNullArrayInPlace() throws Exception {
        String[] array = null;
        getArrayReverser().reverse(array);
    }

    @Test(expected = NullPointerException.class)
    public void exceptionShouldBeThrownWhenTryingToReverseNullArray() throws Exception {
        String[] array = null;
        getArrayReverser().reversed(array);
    }

    @Test
    public void nothingShouldBeDoneWhenTryingToReverseEmptyArray() throws Exception {
        String[] array = new String[0];
        ArrayReverser<String> reverser = getArrayReverser();
        String[] reversed = reverser.reversed(array);

        Assert.assertNotNull(reversed);
        Assert.assertArrayEquals(array, reversed);
    }

    @Test
    public void nothingShouldBeDoneWhenTryingToReverseEmptyArrayInPlace() throws Exception {
        String[] array = new String[0];
        String[] expectedArray = Arrays.copyOf(array, array.length);
        ArrayReverser<String> reverser = getArrayReverser();
        reverser.reverse(array);

        Assert.assertArrayEquals(expectedArray, array);
    }

    @Test
    public void oneElementArrayShouldBeCorrectlyReversed() throws Exception {
        String[] array = new String[] { "1" };
        String[] expected = new String[] { "1" };
        ArrayReverser<String> reverser = getArrayReverser();
        String[] reversed = reverser.reversed(array);

        Assert.assertNotNull(reversed);
        Assert.assertArrayEquals(expected, reversed);
    }

    @Test
    public void oneElementArrayShouldBeCorrectlyReversedInPlace() throws Exception {
        String[] array = new String[] { "1" };
        String[] expected = new String[] { "1" };
        ArrayReverser<String> reverser = getArrayReverser();
        reverser.reverse(array);

        Assert.assertArrayEquals(expected, array);
    }

    @Test
    public void twoElementArrayShouldBeCorrectlyReversed() throws Exception {
        String[] array = new String[] { "1", "2" };
        String[] expected = new String[] { "2", "1" };
        ArrayReverser<String> reverser = getArrayReverser();
        String[] reversed = reverser.reversed(array);

        Assert.assertNotNull(reversed);
        Assert.assertArrayEquals(expected, reversed);
    }

    @Test
    public void twoElementArrayShouldBeCorrectlyReversedInPlace() throws Exception {
        String[] array = new String[] { "1", "2" };
        String[] expected = new String[] { "2", "1" };
        ArrayReverser<String> reverser = getArrayReverser();
        reverser.reverse(array);

        Assert.assertArrayEquals(expected, array);
    }

    @Test
    public void evenSizedArrayShouldBeCorrectlyReversed() throws Exception {
        String[] array = new String[] { "1", "2", "3", "4", "5", "6" };
        String[] expected = new String[] { "6", "5", "4", "3", "2", "1" };
        ArrayReverser<String> reverser = getArrayReverser();
        String[] reversed = reverser.reversed(array);

        Assert.assertNotNull(reversed);
        Assert.assertArrayEquals(expected, reversed);
    }

    @Test
    public void evenSizedArrayShouldBeCorrectlyReversedInPlace() throws Exception {
        String[] array = new String[] { "1", "2", "3", "4", "5", "6" };
        String[] expected = new String[] { "6", "5", "4", "3", "2", "1" };
        ArrayReverser<String> reverser = getArrayReverser();
        reverser.reverse(array);

        Assert.assertArrayEquals(expected, array);
    }

    @Test
    public void oddSizedArrayShouldBeCorrectlyReversed() throws Exception {
        String[] array = new String[] { "1", "2", "3", "4", "5" };
        String[] expected = new String[] { "5", "4", "3", "2", "1" };
        ArrayReverser<String> reverser = getArrayReverser();
        String[] reversed = reverser.reversed(array);

        Assert.assertNotNull(reversed);
        Assert.assertArrayEquals(expected, reversed);
    }

    @Test
    public void oddSizedArrayShouldBeCorrectlyReversedInPlace() throws Exception {
        String[] array = new String[] { "1", "2", "3", "4", "5" };
        String[] expected = new String[] { "5", "4", "3", "2", "1" };
        ArrayReverser<String> reverser = getArrayReverser();
        reverser.reverse(array);

        Assert.assertArrayEquals(expected, array);
    }
}
