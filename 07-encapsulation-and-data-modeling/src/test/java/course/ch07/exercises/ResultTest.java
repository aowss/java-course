package course.ch07.exercises;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResultTest {

    @Test
    void getOrElseReturnsValueOnSuccess() {
        Result<Integer> result = Result.ok(42);
        assertEquals(42, result.getOrElse(0));
    }

    @Test
    void getOrElseReturnsDefaultOnFailure() {
        Result<Integer> result = Result.fail("error");
        assertEquals(0, result.getOrElse(0));
    }

    @Test
    void mapTransformsSuccess() {
        Result<Integer> result = Result.ok(10).map(n -> n * 2);
        assertEquals(20, result.getOrElse(0));
    }

    @Test
    void mapPropagatesFailure() {
        Result<Integer> result = Result.<Integer>fail("bad").map(n -> n * 2);
        assertEquals(0, result.getOrElse(0));
        assertEquals("Failure: bad", result.describe());
    }

    @Test
    void describeSuccess() {
        assertEquals("Success: hello", Result.ok("hello").describe());
    }

    @Test
    void describeFailure() {
        assertEquals("Failure: not found", Result.fail("not found").describe());
    }
}
