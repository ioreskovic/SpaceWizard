package org.lopina.strings.impl;

import org.lopina.strings.UniqueCharactersChecker;

import java.util.HashSet;
import java.util.Set;

public class SetBasedUniqueCharactersChecker implements UniqueCharactersChecker {
    public boolean test(String s) {
        Set<Character> characterSet = new HashSet<Character>();
        for (Character c : s.toCharArray()) {
            characterSet.add(c);
        }

        return characterSet.size() == s.length();
    }
}
