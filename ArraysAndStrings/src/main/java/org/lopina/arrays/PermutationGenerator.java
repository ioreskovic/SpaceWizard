package org.lopina.arrays;

import java.util.Set;

public interface PermutationGenerator<T>
{
    Set<T[]> permutations(T[] input);
}
