package org.lopina.strings.impl;

import org.lopina.strings.UniqueCharactersChecker;

public class PrimitiveUniqueCharactersChecker implements UniqueCharactersChecker {
    public boolean test(String s) {
        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < i; j++) {
                if (chars[i] == chars[j]) {
                    return false;
                }
            }
        }

        return true;
    }
}
