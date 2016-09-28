package org.lopina;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class BitOpsTest
{
    @Test
    public void insertingIntoNumberThatFits() throws Exception {
        int n = 1024;
        int m = 1 + 2 + 16;
        int i = 2;
        int j = 6;
        int r = BitOps.insert(m, n, i, j);
        Assert.assertEquals(4 + 8 + 64 + 1024, r);
    }

    @Test
    public void insertingIntoNumberWithExtraSpace() throws Exception {
        int n = Integer.parseInt("111110101010", 2);
        int m = Integer.parseInt("1010", 2);

        Assume.assumeTrue(n == 4010);
        Assume.assumeTrue(m == 10);

        int i = 3;
        int j = 7;

        int r = BitOps.insert(m, n, i, j);
        int e = Integer.parseInt("111111010010", 2);

        Assume.assumeTrue(e == 4050);

        Assert.assertEquals(e, r);
    }

    @Test
    public void insertingNumberIntoSameNumberShouldYieldSameNumber() throws Exception {
        int n = 15;
        int m = 15;
        int i = 0;
        int j = 3;
        int e = 15;
        int r = BitOps.insert(m, n, i, j);
        Assert.assertEquals(e, r);
    }

    @Test
    public void insertingBiggerNumberIntoSmallerNumberShouldInsertCorrectly() throws Exception {
        int n = 15;
        int m = 31;
        int i = 1;
        int j = 5;
        int e = 63;
        int r = BitOps.insert(m, n, i, j);
        Assert.assertEquals(e, r);
    }

    @Test(expected = IllegalArgumentException.class)
    public void insertingIntoTooLittleSpaceShouldThrowException() throws Exception {
        int n = 1024;
        int m = 15;
        int i = 1;
        int j = 2;
        BitOps.insert(m, n, i, j);
    }
}
