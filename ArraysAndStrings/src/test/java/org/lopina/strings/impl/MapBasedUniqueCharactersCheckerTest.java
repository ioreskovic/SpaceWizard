package org.lopina.strings.impl;

import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.lopina.strings.UniqueCharacterCheckerTest;

@RunWith(BlockJUnit4ClassRunner.class)
public class MapBasedUniqueCharactersCheckerTest extends UniqueCharacterCheckerTest {

    @Override
    protected final MapBasedUniqueCharactersChecker getUniqueCharacterChecker() {
        return new MapBasedUniqueCharactersChecker();
    }
}
