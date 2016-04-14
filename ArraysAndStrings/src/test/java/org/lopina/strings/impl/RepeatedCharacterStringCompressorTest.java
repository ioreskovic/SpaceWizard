package org.lopina.strings.impl;

import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.lopina.strings.StringCompressorTest;

@RunWith(BlockJUnit4ClassRunner.class)
public class RepeatedCharacterStringCompressorTest extends StringCompressorTest {
    @Override
    protected RepeatedCharacterStringCompressor getStringCompressor() {
        return new RepeatedCharacterStringCompressor();
    }
}
