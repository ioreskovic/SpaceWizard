package org.lopina.strings;

import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.lopina.strings.impl.FunctionalUniqueCharacterChecker;

@RunWith(BlockJUnit4ClassRunner.class)
public class FunctionalUniqueCharacterCheckerTest extends UniqueCharacterCheckerTest {

    @Override
    final FunctionalUniqueCharacterChecker getUniqueCharacterChecker() {
        return new FunctionalUniqueCharacterChecker();
    }
}
