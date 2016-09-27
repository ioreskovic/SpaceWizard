package org.lopina;

public class BitOps
{
    boolean getBitAt(int num, int i) {
        int mask = (1 << i);
        return ((num & mask) != 0);
    }

    int setBitAt(int num, int i) {
        int mask = (1 << i);
        return num | mask;
    }

    int clearBitAt(int num, int i) {
        int mask = ~(1 << i);
        return num & mask;
    }

    int clearBitsAbove(int num, int i) {
        int mask = (1 << i) - 1;
        return num & mask;
    }

    int clearBitsBelow(int num, int i) {
        int mask = (-1 << (i + 1));
        return num & mask;
    }

    int updateBitAt(int num, int i , boolean b) {
        int mask = ~(1 << i);
        int value = b ? 1 : 0;
        return (num & mask) | (value << i);
    }
}
