package org.lopina.strings.impl;

import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.lopina.strings.StringCompressor;
import org.lopina.strings.StringCompressorTest;

@RunWith(BlockJUnit4ClassRunner.class)
public class TailRecursiveCharacterStringCompressorTest extends StringCompressorTest
{
    @Override
    protected StringCompressor getStringCompressor()
    {
        return new TailRecursiveCharacterStringCompressor();
    }
}
