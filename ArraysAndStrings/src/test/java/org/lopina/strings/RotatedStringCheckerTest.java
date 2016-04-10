package org.lopina.strings;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public abstract class RotatedStringCheckerTest {
    protected abstract RotatedStringChecker getRotatedStringChecker();

    @Test(expected = NullPointerException.class)
    public void nullPointerExceptionShouldBeThrownWhenAnyArgumentsAreNull() throws Exception {
        String s1 = null;
        String s2 = null;

        RotatedStringChecker checker = getRotatedStringChecker();

        checker.isRotated(s1, s2);
    }

    @Test
    public void emptyStringsShouldBeDenotedAsRotated() throws Exception {
        String s1 = "";
        String s2 = "";

        RotatedStringChecker checker = getRotatedStringChecker();

        Assert.assertTrue(checker.isRotated(s1, s2));
    }

    @Test
    public void oneEmptyStringAndOneNonEmptyStringShouldBeDenotedAsRotated() throws Exception {
        String s1 = "";
        String s2 = "abc";

        RotatedStringChecker checker = getRotatedStringChecker();

        Assert.assertFalse(checker.isRotated(s1, s2));
    }

    @Test
    public void oneNonEmptyStringAndOneEmptyStringShouldBeDenotedAsRotated() throws Exception {
        String s1 = "abc";
        String s2 = "";

        RotatedStringChecker checker = getRotatedStringChecker();

        Assert.assertFalse(checker.isRotated(s1, s2));
    }

    @Test
    public void sameOneCharacterStringsShouldBeDenotedAsRotated() throws Exception {
        String s1 = "a";
        String s2 = "a";

        RotatedStringChecker checker = getRotatedStringChecker();

        Assert.assertTrue(checker.isRotated(s1, s2));
    }

    @Test
    public void differentOneCharacterStringsShouldBeDenotedAsNotRotated() throws Exception {
        String s1 = "a";
        String s2 = "b";

        RotatedStringChecker checker = getRotatedStringChecker();

        Assert.assertFalse(checker.isRotated(s1, s2));
    }

    @Test
    public void rotatedStringsShouldBeCorrectlyIdentified() throws Exception {
        String s1 = "345612";
        String s2 = "612345";

        RotatedStringChecker checker = getRotatedStringChecker();

        Assert.assertTrue(checker.isRotated(s1, s2));
    }

    @Test
    public void nonRotatedStringsShouldBeCorrectlyIdentified() throws Exception {
        String s1 = "1234";
        String s2 = "2413";

        RotatedStringChecker checker = getRotatedStringChecker();

        Assert.assertFalse(checker.isRotated(s1, s2));
    }
}
