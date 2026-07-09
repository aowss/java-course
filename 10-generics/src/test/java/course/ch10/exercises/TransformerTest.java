package course.ch10.exercises;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransformerTest {

    @Test
    void transformAppliesFunction() {
        Transformer<String, Integer> length = String::length;
        assertEquals(5, length.transform("hello"));
    }

    @Test
    void andThenComposesTransformers() {
        Transformer<String, Integer> length = String::length;
        Transformer<Integer, String> format = i -> "Length: " + i;
        var composed = length.andThen(format);
        assertEquals("Length: 5", composed.transform("hello"));
    }

    @Test
    void applyToListTransformsAllElements() {
        Transformer<String, String> upper = String::toUpperCase;
        var result = Transformer.applyToList(List.of("a", "b", "c"), upper);
        assertEquals(List.of("A", "B", "C"), result);
    }

    @Test
    void applyToListWithEmptyList() {
        Transformer<Integer, Integer> doubler = i -> i * 2;
        var result = Transformer.applyToList(List.of(), doubler);
        assertEquals(List.of(), result);
    }

    @Test
    void applyToListWithTypeChange() {
        Transformer<Integer, String> toStr = Object::toString;
        var result = Transformer.applyToList(List.of(1, 2, 3), toStr);
        assertEquals(List.of("1", "2", "3"), result);
    }
}
