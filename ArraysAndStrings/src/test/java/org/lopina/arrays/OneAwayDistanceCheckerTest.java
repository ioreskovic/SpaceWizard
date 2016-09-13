package org.lopina.arrays;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public abstract class OneAwayDistanceCheckerTest
{
    protected abstract <T> OneAwayDistanceChecker<T> getChecker();

    @Test
    public void emptyArraysShouldHaveUpToOneAwayDistance() throws Exception {
        OneAwayDistanceChecker<Character> edc = getChecker();

        Character[] xs = new Character[] {};
        Character[] ys = new Character[] {};

        Assert.assertEquals(Boolean.TRUE, edc.isOneAway(xs, ys));
    }

    @Test
    public void emptyAndNonEmptyArrayShouldDependOnNonEmptyArrayLength() throws Exception {
        OneAwayDistanceChecker<Character> edc = getChecker();

        Character[] xs = new Character[] {};
        Character[] ys = new Character[] { 'a', 'b', 'c', 'd', 'c', 'b', 'a' };

        Assert.assertEquals(Boolean.FALSE, edc.isOneAway(xs, ys));
    }

    @Test
    public void sameArraysShouldBeUpToOneAwayDistance() throws Exception {
        OneAwayDistanceChecker<Character> edc = getChecker();

        Character[] xs = new Character[] { 'a', 'b', 'c', 'd', 'c', 'b', 'a' };
        Character[] ys = new Character[] { 'a', 'b', 'c', 'd', 'c', 'b', 'a' };

        Assert.assertEquals(Boolean.TRUE, edc.isOneAway(xs, ys));
    }

    @Test
    public void differentArraysWithMoreThanDistanceTwoShoudBeMoreThanOneAway() throws Exception {
        OneAwayDistanceChecker<Character> edc = getChecker();

        Character[] xs = new Character[] { 'p', 'a', 'l', 'e' };
        Character[] ys = new Character[] { 'b', 'a', 'k', 'e' };

        Assert.assertEquals(Boolean.FALSE, edc.isOneAway(xs, ys));
    }
}
