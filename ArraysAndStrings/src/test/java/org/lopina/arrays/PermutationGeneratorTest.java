package org.lopina.arrays;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.Set;

@RunWith(BlockJUnit4ClassRunner.class)
public abstract class PermutationGeneratorTest
{
    protected abstract <T> PermutationGenerator<T> getPermutationGenerator();

    @Test
    public void emptyArrayPermutationsShouldBeOfSizeOne() throws Exception
    {
        String emptyString = "";
        PermutationGenerator<Character> pg = getPermutationGenerator();
        Set<Character[]> perms = pg.permutations(getCharArray(emptyString));

        Assert.assertEquals(1, perms.size());
    }

    @Test
    public void singletonArrayPermutationsShouldBeOfSizeOne() throws Exception
    {
        String emptyString = "a";
        PermutationGenerator<Character> pg = getPermutationGenerator();
        Set<Character[]> perms = pg.permutations(getCharArray(emptyString));

        Assert.assertEquals(1, perms.size());
    }

    @Test
    public void arrayPermutatiuonsShouldBeOfFactorielSize() throws Exception
    {
        String string = "abcde";
        PermutationGenerator<Character> pg = getPermutationGenerator();
        Set<Character[]> perms = pg.permutations(getCharArray(string));

        Assert.assertEquals(120, perms.size());
    }

    private Character[] getCharArray(String string)
    {
        Character[] charArray = new Character[string.length()];
        for (int i = 0; i < string.length(); i++)
        {
            charArray[i] = string.charAt(i);
        }
        return charArray;
    }
}
