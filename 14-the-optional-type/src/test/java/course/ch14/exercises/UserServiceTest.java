package course.ch14.exercises;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserServiceTest {

    private final UserService service = new UserService(Map.of(
            "u1", new UserService.User("u1", "Alice", "alice@example.com"),
            "u2", new UserService.User("u2", "Bob", null),
            "u3", new UserService.User("u3", "Carol", "  ")
    ));

    @Test
    void findByIdExisting() {
        assertEquals("Alice", service.findById("u1").map(UserService.User::name).orElse(""));
    }

    @Test
    void findByIdMissing() {
        assertTrue(service.findById("missing").isEmpty());
    }

    @Test
    void findEmailPresent() {
        assertEquals("alice@example.com", service.findEmail("u1").orElse(""));
    }

    @Test
    void findEmailMissingOrBlank() {
        assertTrue(service.findEmail("u2").isEmpty());
        assertTrue(service.findEmail("u3").isEmpty());
        assertTrue(service.findEmail("missing").isEmpty());
    }

    @Test
    void findEmailDomain() {
        assertEquals("example.com", service.findEmailDomain("u1").orElse(""));
        assertTrue(service.findEmailDomain("u2").isEmpty());
    }

    @Test
    void greetExistingUser() {
        assertEquals("Hello, Alice!", service.greet("u1"));
    }

    @Test
    void greetMissingUser() {
        assertEquals("Hello, Guest!", service.greet("missing"));
    }
}
