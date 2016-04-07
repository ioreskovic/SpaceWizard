package org.lopina.strings;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public abstract class UniqueCharacterCheckerTest {
    abstract UniqueCharactersChecker getUniqueCharacterChecker();

    @Test(expected = NullPointerException.class)
    public void nullPointerExceptionShouldBeThrownOnNullStringArgument() throws Exception {
        String string = null;
        Assert.assertTrue(getUniqueCharacterChecker().test(string));
    }

    @Test
    public void emptyStringShouldBeIdentifiedAsHavingUniqueCharacters() throws Exception {
        String string = "";
        Assert.assertTrue(getUniqueCharacterChecker().test(string));
    }

    @Test
    public void oneElementStringShouldBeIdentifiedAsHavingUniqueCharacters() throws Exception {
        String string = "a";
        Assert.assertTrue(getUniqueCharacterChecker().test(string));
    }

    @Test
    public void multipleSameElementStringShouldBeIdentifiedAsNotHavingUniqueCharacters() throws Exception {
        String string = "aaaaaa";
        Assert.assertFalse(getUniqueCharacterChecker().test(string));
    }

    @Test
    public void multipleDifferentElementStringShouldBeIdentifiedAsHavingUniqueCharacters() throws Exception {
        String string = "abc1234";
        Assert.assertTrue(getUniqueCharacterChecker().test(string));
    }

    @Test
    public void mixedElementStringShouldBeIdentifiedAsHavingUniqueCharacters() throws Exception {
        String string = "1a111a311a";
        Assert.assertFalse(getUniqueCharacterChecker().test(string));
    }
}
