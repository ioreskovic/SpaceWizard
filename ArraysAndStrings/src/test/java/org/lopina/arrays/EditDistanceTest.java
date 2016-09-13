package org.lopina.arrays;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public abstract class EditDistanceTest
{
    protected abstract <T> EditDistance<T> getEditDistance();

    @Test
    public void editDistanceOfEmptyArraysShouldBeZero() throws Exception {
        EditDistance<Character> edc = getEditDistance();

        Character[] xs = new Character[] {};
        Character[] ys = new Character[] {};

        Assert.assertEquals(0, edc.editDistance(xs, ys));
    }

    @Test
    public void editDistanceOfEmptyAndNonEmptyArraysShouldBeEqualToLengthOfNonEmptyArray() throws Exception {
        EditDistance<Character> edc = getEditDistance();

        Character[] xs = new Character[] {};
        Character[] ys = new Character[] { 'a', 'b', 'c', 'd', 'c', 'b', 'a' };

        Assert.assertEquals(ys.length, edc.editDistance(xs, ys));
    }

    @Test
    public void editDistanceOfSameArraysShouldBeZero() throws Exception {
        EditDistance<Character> edc = getEditDistance();

        Character[] xs = new Character[] { 'a', 'b', 'c', 'd', 'c', 'b', 'a' };
        Character[] ys = new Character[] { 'a', 'b', 'c', 'd', 'c', 'b', 'a' };

        Assert.assertEquals(0, edc.editDistance(xs, ys));
    }

    @Test
    public void editDistanceOfDifferentArraysShouldBeCorrectlyCalculated() throws Exception {
        EditDistance<Character> edc = getEditDistance();

        Character[] xs = new Character[] { 'p', 'a', 'l', 'e' };
        Character[] ys = new Character[] { 'b', 'a', 'k', 'e' };

        Assert.assertEquals(2, edc.editDistance(xs, ys));
    }
}
