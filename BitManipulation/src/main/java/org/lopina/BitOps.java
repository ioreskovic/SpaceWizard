package org.lopina;

public class BitOps
{
    public static boolean getBitAt(int num, int i) {
        int mask = (1 << i);
        return ((num & mask) != 0);
    }

    public static int setBitAt(int num, int i) {
        int mask = (1 << i);
        return num | mask;
    }

    public static int clearBitAt(int num, int i) {
        int mask = ~(1 << i);
        return num & mask;
    }

    public static int clearBitsAbove(int num, int i) {
        int mask = (1 << i) - 1;
        return num & mask;
    }

    public static int clearBitsBelow(int num, int i) {
        int mask = (-1 << (i + 1));
        return num & mask;
    }

    public static int updateBitAt(int num, int i , boolean b) {
        int mask = ~(1 << i);
        int value = b ? 1 : 0;
        return (num & mask) | (value << i);
    }

    public static int insert(int m, int n, int i, int j) {
        int range = j - i + 1;
        int usedBits = 0;
        int c = m;

        while (c != 0) {
            c = c >> 1;
            usedBits++;
        }

        if (usedBits > range) {
            throw new IllegalArgumentException();
        }

        int jMin = Math.min(j, i + usedBits - 1);

        int clearMask = ~(((1 << (usedBits)) - 1) << i);
        int updateMask = m << i;

        return (n & clearMask) | updateMask;
    }

    public static String decimalToBinaryString(double num) {
        if (num >= 1.0 || num <= 0.0) {
            throw new IllegalArgumentException();
        }

        StringBuilder sb = new StringBuilder("0.");

        while (num > 0) {
            if (sb.length() - 2 > 32) {
                throw new IllegalArgumentException();
            }

            double r = num * 2;

            if (r >= 1.0) {
                sb.append("1");
                num = r - 1;
            } else {
                sb.append("0");
                num = r;
            }
        }

        return sb.toString();

    }
}
