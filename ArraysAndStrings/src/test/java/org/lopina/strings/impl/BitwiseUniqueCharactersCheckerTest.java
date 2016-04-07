package org.lopina.strings.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.lopina.strings.UniqueCharacterCheckerTest;

@RunWith(BlockJUnit4ClassRunner.class)
public class BitwiseUniqueCharactersCheckerTest extends UniqueCharacterCheckerTest {

    @Override
    protected final BitwiseUniqueCharactersChecker getUniqueCharacterChecker() {
        return new BitwiseUniqueCharactersChecker();
    }

    @Test
    @Override
    public void multipleDifferentElementStringShouldBeIdentifiedAsHavingUniqueCharacters() throws Exception {
        String string = "abcdefgh";
        Assert.assertTrue(getUniqueCharacterChecker().test(string));
    }

    @Test
    public void mixedElementStringShouldBeIdentifiedAsHavingUniqueCharacters() throws Exception {
        String string = "abcdedcba";
        Assert.assertFalse(getUniqueCharacterChecker().test(string));
    }

    @Test
    public void reallyLargeAndTrickyStringShouldBeIdentifiedAsNotHavingUniqueCharacters() throws Exception {
        StringBuilder stringBuilder = new StringBuilder();

        for (char c = 'a'; c <= 'z'; c++) {
            stringBuilder.append(c);
        }

        stringBuilder.append('z');

        String string = stringBuilder.toString();
        Assert.assertFalse(getUniqueCharacterChecker().test(string));
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionShouldBeThrownIfStringHasCharactersBeforeLittleA() throws Exception {
        char badChar = 'a' - 1;
        String string = "" + badChar;
        getUniqueCharacterChecker().test(string);
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionShouldBeThrownIfStringHasCharactersBeforeAfterLittleZ() throws Exception {
        char badChar = 'z' + 1;
        String string = "" + badChar;
        getUniqueCharacterChecker().test(string);
    }

}
