package org.lopina.arrays;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public abstract class PalindromePermutationCheckerTest
{
    protected abstract  <T> PalindromePermutationChecker<T> getPalindromePermutationChecker();

    @Test
    public void emptyArrayShouldHavePalindromePermutation() throws Exception {
        PalindromePermutationChecker<Character> pc = getPalindromePermutationChecker();

        Character[] input = new Character[] {};

        Boolean result = pc.hasPalindromePermutation(input);

        Assert.assertEquals(Boolean.TRUE, result);
    }

    @Test
    public void singletonArrayShouldHavePalindromePermutation() throws Exception {
        PalindromePermutationChecker<Character> pc = getPalindromePermutationChecker();

        Character[] input = new Character[] { 'a' };

        Boolean result = pc.hasPalindromePermutation(input);

        Assert.assertEquals(Boolean.TRUE, result);
    }

    @Test
    public void sameElemToSizeArrayShouldHavePalindromePermutation() throws Exception {
        PalindromePermutationChecker<Character> pc = getPalindromePermutationChecker();

        Character[] input = new Character[] { 'a', 'a' };

        Boolean result = pc.hasPalindromePermutation(input);

        Assert.assertEquals(Boolean.TRUE, result);
    }

    @Test
    public void diffElemToSizeArrayShouldNotHavePalindromePermutation() throws Exception {
        PalindromePermutationChecker<Character> pc = getPalindromePermutationChecker();

        Character[] input = new Character[] { 'a', 'b' };

        Boolean result = pc.hasPalindromePermutation(input);

        Assert.assertEquals(Boolean.FALSE, result);
    }

    @Test
    public void centralElemArrayShouldHavePalindromePermutation() throws Exception {
        PalindromePermutationChecker<Character> pc = getPalindromePermutationChecker();

        Character[] input = new Character[] { 'b', 'a', 'a', 'c', 'c', 'b', 'b' };

        Boolean result = pc.hasPalindromePermutation(input);

        Assert.assertEquals(Boolean.TRUE, result);
    }

    @Test
    public void mirrorElemArrayShouldHavePalindromePermutation() throws Exception {
        PalindromePermutationChecker<Character> pc = getPalindromePermutationChecker();

        Character[] input = new Character[] { 'a', 'a', 'c', 'c', 'b', 'b' };

        Boolean result = pc.hasPalindromePermutation(input);

        Assert.assertEquals(Boolean.TRUE, result);
    }

    @Test
    public void multipleOddCountElemArrayShouldNotHavePalindromePermutation() throws Exception {
        PalindromePermutationChecker<Character> pc = getPalindromePermutationChecker();

        Character[] input = new Character[] { 'b', 'a', 'a', 'c', 'c', 'b', 'b', 'c' };

        Boolean result = pc.hasPalindromePermutation(input);

        Assert.assertEquals(Boolean.FALSE, result);
    }
}
