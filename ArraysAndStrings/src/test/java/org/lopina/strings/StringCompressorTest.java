package org.lopina.strings;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public abstract class StringCompressorTest {
    protected abstract StringCompressor getStringCompressor();

    @Test(expected = NullPointerException.class)
    public void compressingNullStringShouldThrowNullPointerException() throws Exception {
        String input = null;
        StringCompressor stringCompressor = getStringCompressor();
        stringCompressor.compress(input);
    }

    @Test
    public void compressingEmptyStringShouldReturnSameString() throws Exception {
        String input = "";
        StringCompressor stringCompressor = getStringCompressor();
        String output = stringCompressor.compress(input);
        String expected = "";
        Assert.assertEquals(expected, output);
    }

    @Test
    public void compressingCompressableStringShouldReturnCopressedString() throws Exception {
        String input = "AABCCCCCAAA";
        StringCompressor stringCompressor = getStringCompressor();
        String output = stringCompressor.compress(input);
        String expected = "A2B1C5A3";
        Assert.assertEquals(expected, output);
    }

    @Test
    public void compressingNonCompressableStringShouldReturnOriginalString() throws Exception {
        String input = "AABBCCDD";
        StringCompressor stringCompressor = getStringCompressor();
        String output = stringCompressor.compress(input);
        String expected = "AABBCCDD";
        Assert.assertEquals(expected, output);
    }
}
