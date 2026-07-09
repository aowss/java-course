package course.ch08.exercises;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ResourcePoolTest {

    static class TestResource implements AutoCloseable {
        boolean closed = false;

        @Override
        public void close() {
            closed = true;
        }
    }

    static class FailingCloseResource implements AutoCloseable {
        final String name;

        FailingCloseResource(String name) {
            this.name = name;
        }

        @Override
        public void close() throws Exception {
            throw new RuntimeException("close failed: " + name);
        }
    }

    @Test
    void borrowCreatesResource() throws Exception {
        try (var pool = new ResourcePool<>(TestResource::new, 3)) {
            TestResource r = pool.borrow();
            assertNotNull(r);
        }
    }

    @Test
    void releaseAndReuseResource() throws Exception {
        try (var pool = new ResourcePool<>(TestResource::new, 1)) {
            TestResource r1 = pool.borrow();
            pool.release(r1);
            TestResource r2 = pool.borrow();
            assertSame(r1, r2);
        }
    }

    @Test
    void borrowThrowsWhenPoolExhausted() throws Exception {
        try (var pool = new ResourcePool<>(TestResource::new, 1)) {
            pool.borrow();
            assertThrows(IllegalStateException.class, pool::borrow);
        }
    }

    @Test
    void closeClosesAllResources() throws Exception {
        var pool = new ResourcePool<>(TestResource::new, 3);
        TestResource r1 = pool.borrow();
        TestResource r2 = pool.borrow();
        pool.release(r1);
        pool.close();
        assertTrue(r1.closed);
        assertTrue(r2.closed);
    }

    @Test
    void closeCollectsExceptionsAsSuppressed() {
        var counter = new AtomicInteger(0);
        var pool = new ResourcePool<>(
                () -> new FailingCloseResource("r" + counter.incrementAndGet()), 3);
        pool.borrow();
        pool.borrow();
        pool.borrow();

        Exception thrown = assertThrows(Exception.class, pool::close);
        assertTrue(thrown.getSuppressed().length >= 1);
    }

    @Test
    void releaseNullThrows() throws Exception {
        try (var pool = new ResourcePool<>(TestResource::new, 1)) {
            assertThrows(IllegalArgumentException.class, () -> pool.release(null));
        }
    }

    @Test
    void constructorRejectsNonPositiveMaxSize() {
        assertThrows(IllegalArgumentException.class,
                () -> new ResourcePool<>(TestResource::new, 0));
        assertThrows(IllegalArgumentException.class,
                () -> new ResourcePool<>(TestResource::new, -1));
    }

    @Test
    void multipleBorrowsUpToMaxSize() throws Exception {
        try (var pool = new ResourcePool<>(TestResource::new, 3)) {
            assertNotNull(pool.borrow());
            assertNotNull(pool.borrow());
            assertNotNull(pool.borrow());
            assertThrows(IllegalStateException.class, pool::borrow);
        }
    }
}
