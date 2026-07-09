package course.ch09.exercises;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class WordCounterTest {

    // --- countWords ---

    @Test
    void countWordsInSimpleSentence() {
        assertEquals(4, WordCounter.countWords("the cat sat down"));
    }

    @Test
    void countWordsWithMultipleSpaces() {
        assertEquals(3, WordCounter.countWords("  hello   world   java  "));
    }

    @Test
    void countWordsReturnsZeroForNull() {
        assertEquals(0, WordCounter.countWords(null));
    }

    @Test
    void countWordsReturnsZeroForEmpty() {
        assertEquals(0, WordCounter.countWords(""));
        assertEquals(0, WordCounter.countWords("   "));
    }

    // --- countUniqueWords ---

    @Test
    void countUniqueWordsCaseInsensitive() {
        assertEquals(3, WordCounter.countUniqueWords("The the THE cat Cat"));
    }

    @Test
    void countUniqueWordsAllDifferent() {
        assertEquals(4, WordCounter.countUniqueWords("one two three four"));
    }

    @Test
    void countUniqueWordsReturnsZeroForNull() {
        assertEquals(0, WordCounter.countUniqueWords(null));
    }

    // --- mostFrequentWord ---

    @Test
    void mostFrequentWordReturnsMostCommon() {
        assertEquals("the", WordCounter.mostFrequentWord("the cat sat on the mat the cat"));
    }

    @Test
    void mostFrequentWordIsCaseInsensitive() {
        assertEquals("java", WordCounter.mostFrequentWord("Java java JAVA python"));
    }

    @Test
    void mostFrequentWordReturnsNullForNull() {
        assertNull(WordCounter.mostFrequentWord(null));
    }

    @Test
    void mostFrequentWordReturnsNullForEmpty() {
        assertNull(WordCounter.mostFrequentWord(""));
    }
}
