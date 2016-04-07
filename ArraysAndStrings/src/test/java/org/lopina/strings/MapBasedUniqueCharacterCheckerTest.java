package org.lopina.strings;

import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.lopina.strings.impl.MapBasedUniqueCharacterChecker;

@RunWith(BlockJUnit4ClassRunner.class)
public class MapBasedUniqueCharacterCheckerTest extends UniqueCharacterCheckerTest {

    @Override
    final MapBasedUniqueCharacterChecker getUniqueCharacterChecker() {
        return new MapBasedUniqueCharacterChecker();
    }
}
