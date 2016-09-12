package org.lopina.arrays.impl;

import org.lopina.arrays.PermutationGenerator;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class RecursivePermutationGenerator<T> implements PermutationGenerator<T>
{
    @Override
    public Set<T[]> permutations(T[] input)
    {
        if (input.length == 0)
        {
            return Collections.singleton(input);
        }
        else
        {
            Set<T[]> set = new HashSet<>();

            for (int i = 0; i < input.length; i++)
            {
                for (T[] semiPerm : permutations(excludeAt(input, i)))
                {
                    set.add(prepend(semiPerm, input[i]));
                }
            }

            return set;
        }
    }

    private T[] excludeAt(T[] input, int index)
    {
        T[] newArray = (T[]) new Object[input.length - 1];

        for (int i = 0; i < index; i++) {
            newArray[i] = input[i];
        }

        for (int i = index + 1; i < input.length; i++) {
            newArray[i - 1] = input[i];
        }

        return newArray;
    }

    private T[] prepend(T[] input, T t)
    {
        T[] newArray = (T[]) new Object[input.length + 1];
        newArray[0] = t;

        for (int i = 0; i < input.length; i++) {
            newArray[i + 1] = input[i];
        }

        return newArray;
    }
}
