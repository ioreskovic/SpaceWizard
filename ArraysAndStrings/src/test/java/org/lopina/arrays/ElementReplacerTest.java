package org.lopina.arrays;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public abstract class ElementReplacerTest {
    protected abstract <T> ElementReplacer<T> getElementReplacer();

    @Test
    public void emptyStringShouldNotBeReplacedAtAll() throws Exception {
        Character[] input = new Character[0];
        Character replacee = ' ';
        Character[] replacement = new Character[] {'%', '2', '0'};
        ElementReplacer<Character> elementReplacer = getElementReplacer();
        elementReplacer.replace(input, replacee, replacement);
        Character[] expected = new Character[0];
        Assert.assertArrayEquals(expected, input);
    }

    @Test
    public void existingElementsBeingReplacedShouldAlterTheString() throws Exception {
        Character[] input = new Character[] {'M','R',' ','J','O','H','N',' ','S','M','I','T','H', null, null, null, null};
        Character replacee = ' ';
        Character[] replacement = new Character[] {'%', '2', '0'};
        ElementReplacer<Character> elementReplacer = getElementReplacer();
        elementReplacer.replace(input, replacee, replacement);
        Character[] expected = new Character[] {'M','R','%','2','0','J','O','H','N','%','2','0','S', 'M', 'I', 'T', 'H'};
        Assert.assertArrayEquals(expected, input);
    }

    @Test
    public void nonExistingElementsBeingReplacedShouldNotAlterTheString() throws Exception {
        Character[] input = new Character[] {'M','R',' ','J','O','H','N',' ','S','M','I','T','H', null, null, null, null};
        Character replacee = 'X';
        Character[] replacement = new Character[] {'%', '2', '0'};
        ElementReplacer<Character> elementReplacer = getElementReplacer();
        elementReplacer.replace(input, replacee, replacement);
        Character[] expected = new Character[] {'M','R',' ','J','O','H','N',' ','S','M','I','T','H', null, null, null, null};
        Assert.assertArrayEquals(expected, input);
    }
}
