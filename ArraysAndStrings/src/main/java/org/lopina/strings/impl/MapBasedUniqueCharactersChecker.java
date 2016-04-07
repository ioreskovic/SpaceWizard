package org.lopina.strings.impl;

import org.lopina.strings.UniqueCharactersChecker;

import java.util.HashMap;
import java.util.Map;

public class MapBasedUniqueCharactersChecker implements UniqueCharactersChecker {
    public boolean test(String s) {
        Map<Character, Integer> countMap = new HashMap<Character, Integer>();

        for (Character c : s.toCharArray()) {
            int count = countMap.getOrDefault(c, 0);

            if (count > 0) {
                return false;
            }

            countMap.put(c, count + 1);
        }

        return true;
    }
}
