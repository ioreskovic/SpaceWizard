package org.lopina.strings.impl;

import org.lopina.strings.UniqueCharactersChecker;

public class BitwiseUniqueCharactersChecker implements UniqueCharactersChecker {
    public boolean test(String s) {
        int checker = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int position = c - 'a';

            if (position < 0 || position > 25) {
                throw new IllegalArgumentException("Sorry, works only for simple character strings, not this one: " + s);
            }

            int mask = 1 << position;
            if ((checker & mask) > 0) {
                return false;
            }
            checker = checker | mask;
        }

        return true;
    }
}
