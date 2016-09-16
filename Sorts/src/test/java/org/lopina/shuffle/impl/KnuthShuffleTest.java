package org.lopina.shuffle.impl;

import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.lopina.shuffle.Shuffle;
import org.lopina.shuffle.ShuffleTest;

@RunWith(BlockJUnit4ClassRunner.class)
public class KnuthShuffleTest extends ShuffleTest {

    @Override
    protected <T> Shuffle<T> getShuffle() {
        return new KnuthShuffle<>();
    }
}
