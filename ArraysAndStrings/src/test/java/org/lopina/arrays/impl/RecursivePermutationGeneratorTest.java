package org.lopina.arrays.impl;

import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.lopina.arrays.PermutationGenerator;
import org.lopina.arrays.PermutationGeneratorTest;

@RunWith(BlockJUnit4ClassRunner.class)
public class RecursivePermutationGeneratorTest extends PermutationGeneratorTest
{
    @Override
    protected <T> PermutationGenerator<T> getPermutationGenerator()
    {
        return new RecursivePermutationGenerator<>();
    }
}
