package org.lopina.strings.impl;

import org.lopina.strings.UniqueCharactersChecker;

public class FunctionalUniqueCharacterChecker implements UniqueCharactersChecker {
    public boolean test(String s) {
        return s.chars().distinct().count() == s.length();
    }
}
