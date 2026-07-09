package course.ch12.exercises;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TextAnalyzerTest {

    @Test
    void words() {
        assertEquals(List.of("the", "quick", "brown"), TextAnalyzer.words("The Quick Brown"));
    }

    @Test
    void wordCount() {
        assertEquals(9, TextAnalyzer.wordCount("The quick brown fox jumps over the lazy dog"));
    }

    @Test
    void wordCountNullOrBlank() {
        assertEquals(0, TextAnalyzer.wordCount(null));
        assertEquals(0, TextAnalyzer.wordCount("   "));
    }

    @Test
    void averageWordLength() {
        assertEquals(3.0, TextAnalyzer.averageWordLength("cat dog pig"), 0.001);
    }

    @Test
    void wordFrequencies() {
        Map<String, Long> freq = TextAnalyzer.wordFrequencies("the cat and the dog");
        assertEquals(2L, freq.get("the"));
        assertEquals(1L, freq.get("cat"));
    }

    @Test
    void longestWord() {
        assertEquals("jumps", TextAnalyzer.longestWord("The quick brown fox jumps"));
    }

    @Test
    void longestWordEmpty() {
        assertEquals("", TextAnalyzer.longestWord(""));
    }
}
