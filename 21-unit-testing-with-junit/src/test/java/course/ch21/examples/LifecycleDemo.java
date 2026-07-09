package course.ch21.examples;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Lifecycle Demo")
class LifecycleDemo {

    private static final List<String> LOG = new ArrayList<>();
    private List<String> items;

    @BeforeAll
    static void beforeAll() {
        LOG.add("@BeforeAll — runs once before all tests");
    }

    @AfterAll
    static void afterAll() {
        LOG.add("@AfterAll — runs once after all tests");
        assertFalse(LOG.isEmpty(), "Log should contain lifecycle entries");
    }

    @BeforeEach
    void setUp() {
        items = new ArrayList<>();
        LOG.add("@BeforeEach — fresh list for each test");
    }

    @AfterEach
    void tearDown() {
        items = null;
        LOG.add("@AfterEach — cleanup after each test");
    }

    @Test
    @DisplayName("Test 1 — list starts empty")
    void listStartsEmpty() {
        assertTrue(items.isEmpty());
    }

    @Test
    @DisplayName("Test 2 — add an item")
    void addItem() {
        items.add("apple");
        assertEquals(1, items.size());
    }

    @Test
    @DisplayName("Test 3 — each test gets a fresh list")
    void freshList() {
        assertEquals(0, items.size(), "Each test should start with a fresh, empty list");
    }
}
