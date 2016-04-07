package org.lopina.strings;

import org.junit.Assert;
import org.junit.AssumptionViolatedException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@RunWith(BlockJUnit4ClassRunner.class)
public abstract class UniqueCharacterCheckerTest {

    protected final Logger logger = Logger.getLogger(getClass().getName());

    void logInfo(Description description, String status, long nanos) {
        String testName = description.getMethodName();
        logger.info(String.format("Test %s %s, spent %d microseconds",
                testName, status, TimeUnit.NANOSECONDS.toMicros(nanos)));
    }

    @Rule
    public Stopwatch stopwatch = new Stopwatch() {
        @Override
        protected void succeeded(long nanos, Description description) {
            logInfo(description, "SUCCEEDED", nanos);
            super.succeeded(nanos, description);
        }

        @Override
        protected void failed(long nanos, Throwable e, Description description) {
            logInfo(description, "FAILED", nanos);
            super.failed(nanos, e, description);
        }

        @Override
        protected void skipped(long nanos, AssumptionViolatedException e, Description description) {
            logInfo(description, "SKIPPED", nanos);
            super.skipped(nanos, e, description);
        }

        @Override
        protected void finished(long nanos, Description description) {
            logInfo(description, "FINISHED", nanos);
            super.finished(nanos, description);
        }
    };

    protected abstract UniqueCharactersChecker getUniqueCharacterChecker();

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

    @Test
    public void reallyLargeAndTrickyStringShouldBeIdentifiedAsNotHavingUniqueCharacters() throws Exception {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 255; i++) {
            stringBuilder.append((char) i);
        }

        stringBuilder.append((char) 254);

        String string = stringBuilder.toString();
        Assert.assertFalse(getUniqueCharacterChecker().test(string));
    }
}
