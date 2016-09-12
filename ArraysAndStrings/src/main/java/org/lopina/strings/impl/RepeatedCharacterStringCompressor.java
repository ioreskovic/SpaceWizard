package org.lopina.strings.impl;

import org.lopina.strings.StringCompressor;

public class RepeatedCharacterStringCompressor implements StringCompressor {

    @Override
    public String compress(String input) {
        if (input.isEmpty()) {
            return input;
        }

        char[] chars = input.toCharArray();
        StringBuilder sb = new StringBuilder();

        Character lastRead = chars[0];
        Integer lastCount = 1;

        for (int i = 1; i < chars.length; i++) {
            Character c = chars[i];
            if (c.equals(lastRead)) {
                lastCount++;
            } else {
                write(sb, lastRead, lastCount);
                lastRead = c;
                lastCount = 1;
            }
        }

        write(sb, lastRead, lastCount);

        if (sb.length() <= input.length()) {
            return sb.toString();
        }

        return input;
    }

    private static void write(StringBuilder sb, Character lastRead, Integer lastCount) {
        sb.append(lastRead).append(lastCount);
    }
}
