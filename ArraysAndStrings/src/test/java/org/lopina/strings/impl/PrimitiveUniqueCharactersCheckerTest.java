package org.lopina.strings.impl;

import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.lopina.strings.UniqueCharacterCheckerTest;

@RunWith(BlockJUnit4ClassRunner.class)
public class PrimitiveUniqueCharactersCheckerTest extends UniqueCharacterCheckerTest {

    @Override
    protected final PrimitiveUniqueCharactersChecker getUniqueCharacterChecker() {
        return new PrimitiveUniqueCharactersChecker();
    }
}
