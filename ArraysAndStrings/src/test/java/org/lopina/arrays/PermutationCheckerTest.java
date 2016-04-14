package org.lopina.arrays;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.lopina.arrays.PermutationChecker;

@RunWith(BlockJUnit4ClassRunner.class)
public abstract class PermutationCheckerTest {
    protected abstract <T> PermutationChecker<T> getPermutationChecker();

    @Test(expected = NullPointerException.class)
    public void nullPointerExceptionShouldBeThrownWhenBothArgumentsAreNull() throws Exception {
        Character[] array1 = null;
        Character[] array2 = null;

        PermutationChecker<Character> permutationChecker = getPermutationChecker();

        permutationChecker.arePermutations(array1, array2);
    }

    @Test(expected = NullPointerException.class)
    public void nullPointerExceptionShouldBeThrownWhenFirstArgumentIsNull() throws Exception {
        Character[] array1 = null;
        Character[] array2 = new Character[0];

        PermutationChecker<Character> permutationChecker = getPermutationChecker();

        permutationChecker.arePermutations(array1, array2);
    }

    @Test(expected = NullPointerException.class)
    public void nullPointerExceptionShouldBeThrownWhenSecondArgumentIsNull() throws Exception {
        Character[] array1 = new Character[0];
        Character[] array2 = null;

        PermutationChecker<Character> permutationChecker = getPermutationChecker();

        permutationChecker.arePermutations(array1, array2);
    }

    @Test
    public void emptyArraysShouldAlwaysBeAPermutationOfOneAnother() throws Exception {
        Character[] array1 = new Character[0];
        Character[] array2 = new Character[0];

        PermutationChecker<Character> permutationChecker = getPermutationChecker();

        Assert.assertTrue(permutationChecker.arePermutations(array1, array2));
    }

    @Test
    public void sameArraysShouldAlwaysBeAPermutationOfOneAnother() throws Exception {
        Character[] array1 = new Character[]{'1', '2', '3', '4'};
        Character[] array2 = new Character[]{'1', '2', '3', '4'};

        PermutationChecker<Character> permutationChecker = getPermutationChecker();

        Assert.assertTrue(permutationChecker.arePermutations(array1, array2));
    }

    @Test
    public void permutableArraysShouldAlwaysBeAPermutationOfOneAnother() throws Exception {
        Character[] array1 = new Character[]{'1', '2', '3', '4', '4', '3', '2', '1'};
        Character[] array2 = new Character[]{'4', '3', '2', '1', '1', '2', '3', '4'};

        PermutationChecker<Character> permutationChecker = getPermutationChecker();

        Assert.assertTrue(permutationChecker.arePermutations(array1, array2));
    }

    @Test
    public void arraysThatHaveDifferentLengthsShouldNotBeIdentifiedAsPermutations() throws Exception {
        Character[] array1 = new Character[]{'1', '2', '3', '4', '4', '3', '2', '1'};
        Character[] array2 = new Character[]{'4', '3', '2', '1', '1', '2', '3'};

        PermutationChecker<Character> permutationChecker = getPermutationChecker();

        Assert.assertFalse(permutationChecker.arePermutations(array1, array2));
    }

    @Test
    public void arraysThatHaveSameLengthButAreNotPermutationsShouldNotBeIdentifiedAsPermutations() throws Exception {
        Character[] array1 = new Character[]{'1', '2', '3', '4', '4', '3', '2', '1'};
        Character[] array2 = new Character[]{'4', '3', '2', '1', '1', '2', '3', '5'};

        PermutationChecker<Character> permutationChecker = getPermutationChecker();

        Assert.assertFalse(permutationChecker.arePermutations(array1, array2));
    }
}
