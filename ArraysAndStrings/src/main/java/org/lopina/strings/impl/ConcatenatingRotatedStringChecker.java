package org.lopina.strings.impl;

import org.lopina.strings.RotatedStringChecker;

public class ConcatenatingRotatedStringChecker implements RotatedStringChecker {

    @Override
    public boolean isRotated(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }

        return isSubstring(s1, s2 + s2);
    }

    private boolean isSubstring(String small, String big) {
        return big.contains(small);
    }
}
