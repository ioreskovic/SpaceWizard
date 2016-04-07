package org.lopina.strings.impl;

import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.lopina.strings.UniqueCharacterCheckerTest;

@RunWith(BlockJUnit4ClassRunner.class)
public class SetBasedUniqueCharactersCheckerTest extends UniqueCharacterCheckerTest {

    @Override
    protected final SetBasedUniqueCharactersChecker getUniqueCharacterChecker() {
        return new SetBasedUniqueCharactersChecker();
    }
}
