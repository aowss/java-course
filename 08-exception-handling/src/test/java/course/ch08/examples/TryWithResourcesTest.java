package course.ch08.examples;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TryWithResourcesTest {

    @Test
    void useAndCloseClosesResource() {
        List<String> log = new ArrayList<>();
        TryWithResources.useAndClose(log);

        assertEquals(List.of("opened: A", "used: A", "closed: A"), log);
    }

    @Test
    void closeOnExceptionStillCloses() {
        List<String> log = new ArrayList<>();

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> TryWithResources.closeOnException(log));
        assertEquals("something went wrong", ex.getMessage());
        assertEquals(List.of("opened: B", "used: B", "closed: B"), log);
    }

    @Test
    void multipleResourcesClosedInReverseOrder() {
        List<String> log = new ArrayList<>();
        TryWithResources.multipleResources(log);

        assertEquals(List.of(
                "opened: first", "opened: second",
                "used: first", "used: second",
                "closed: second", "closed: first"), log);
    }

    @Test
    void suppressedExceptionsAreCaptured() {
        List<String> log = new ArrayList<>();

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> TryWithResources.suppressedExceptions(log));

        assertEquals("body exception", ex.getMessage());
        Throwable[] suppressed = ex.getSuppressed();
        assertEquals(1, suppressed.length);
        assertTrue(suppressed[0].getMessage().contains("Failed to close C"));
    }

    @Test
    void simpleResourceThrowsWhenUsedAfterClose() {
        List<String> log = new ArrayList<>();
        var resource = new TryWithResources.SimpleResource("test", log);
        resource.close();

        assertThrows(IllegalStateException.class, resource::use);
    }
}
