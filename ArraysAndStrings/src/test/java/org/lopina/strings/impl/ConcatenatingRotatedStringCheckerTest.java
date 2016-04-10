package org.lopina.strings.impl;

import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.lopina.strings.RotatedStringChecker;
import org.lopina.strings.RotatedStringCheckerTest;

@RunWith(BlockJUnit4ClassRunner.class)
public class ConcatenatingRotatedStringCheckerTest extends RotatedStringCheckerTest {

    @Override
    protected RotatedStringChecker getRotatedStringChecker() {
        return new ConcatenatingRotatedStringChecker();
    }
}
