package org.lopina.strings.impl;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.lopina.strings.StringCompressor;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class TailRecursiveCharacterStringCompressor implements StringCompressor
{
    @Override
    public String compress(String input)
    {
        if (input.isEmpty()) {
            return input;
        } else {
            Deque<Pair<Character, Integer>> acc = new ArrayDeque<>();
            compressIter(input, input.charAt(0), 1, 1, acc);

            if (acc.size() == input.length()) {
                return input;
            } else {
                return stringify(acc);
            }
        }

    }

    private String stringify(Deque<Pair<Character, Integer>> acc)
    {
        StringBuilder stringBuilder = new StringBuilder();

        for (Pair<Character, Integer> countPair : acc) {
            stringBuilder.append(countPair.getLeft());
            stringBuilder.append(countPair.getRight());
        }

        return stringBuilder.toString();
    }

    private void compressIter(String input, Character lastChar, int lastCharSeen, int index, Deque<Pair<Character, Integer>> acc)
    {
        if (index < input.length() - 1) {
            Character c = input.charAt(index);

            if (c.equals(lastChar)) {
                compressIter(input, lastChar, lastCharSeen + 1, index + 1, acc);
            } else {
                acc.offerLast(new ImmutablePair<>(lastChar, lastCharSeen));
                compressIter(input, c, 1, index + 1, acc);
            }
        } else {
            Character c = input.charAt(input.length() - 1);

            if (c.equals(lastChar)) {
                acc.offerLast(new ImmutablePair<>(lastChar, lastCharSeen + 1));
            } else {
                acc.offerLast(new ImmutablePair<>(lastChar, lastCharSeen));
                acc.offerLast(new ImmutablePair<>(c, 1));
            }
        }
    }
}
