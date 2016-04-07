package org.lopina.strings.impl;

import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.lopina.strings.UniqueCharacterCheckerTest;

@RunWith(BlockJUnit4ClassRunner.class)
public class FunctionalUniqueCharactersCheckerTest extends UniqueCharacterCheckerTest {

    @Override
    protected final FunctionalUniqueCharactersChecker getUniqueCharacterChecker() {
        return new FunctionalUniqueCharactersChecker();
    }
}
