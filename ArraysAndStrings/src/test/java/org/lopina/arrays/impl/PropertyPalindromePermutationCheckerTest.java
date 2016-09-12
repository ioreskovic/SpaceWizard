package org.lopina.arrays.impl;

import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.lopina.arrays.PalindromePermutationChecker;
import org.lopina.arrays.PalindromePermutationCheckerTest;

@RunWith(BlockJUnit4ClassRunner.class)
public class PropertyPalindromePermutationCheckerTest extends PalindromePermutationCheckerTest
{
    @Override
    protected <T> PalindromePermutationChecker<T> getPalindromePermutationChecker()
    {
        return new PropertyPalindromePermutationChecker<>();
    }
}
