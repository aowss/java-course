package course.ch27.examples;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PatternMatchingSwitchTest {

    @Test
    void classifyNull() {
        assertEquals("null", PatternMatchingSwitch.classify(null));
    }

    @Test
    void classifyNegativeInteger() {
        assertEquals("negative integer: -3", PatternMatchingSwitch.classify(-3));
    }

    @Test
    void classifyPositiveInteger() {
        assertEquals("positive integer: 42", PatternMatchingSwitch.classify(42));
    }

    @Test
    void classifyBlankString() {
        assertEquals("blank string", PatternMatchingSwitch.classify("   "));
    }

    @Test
    void classifyNonBlankString() {
        assertEquals("string: hello", PatternMatchingSwitch.classify("hello"));
    }

    @Test
    void classifyIntArray() {
        assertEquals("int array of length 2", PatternMatchingSwitch.classify(new int[]{1, 2}));
    }

    @Test
    void classifyUnknownType() {
        assertEquals("unknown: Double", PatternMatchingSwitch.classify(3.14));
    }

    @Test
    void sequenceLengthHandlesNull() {
        assertEquals(-1, PatternMatchingSwitch.sequenceLength(null));
    }

    @Test
    void sequenceLengthForString() {
        assertEquals(5, PatternMatchingSwitch.sequenceLength("hello"));
    }

    @Test
    void sequenceLengthForStringBuilder() {
        assertEquals(3, PatternMatchingSwitch.sequenceLength(new StringBuilder("abc")));
    }
}
