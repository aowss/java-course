package course.ch22.exercises;

import course.ch22.examples.EmailService;
import course.ch22.examples.User;
import course.ch22.examples.UserRepository;
import course.ch22.examples.UserService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserService Tests")
class UserServiceTest {

    @Mock
    private UserRepository repository;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private UserService userService;

    @Test
    void findUserReturnsUserWhenFound() {
        var alice = new User(1L, "Alice", "alice@example.com");
        when(repository.findById(1L)).thenReturn(Optional.of(alice));

        Optional<User> result = userService.findUser(1L);

        assertTrue(result.isPresent());
        assertEquals("Alice", result.get().name());
    }

    @Test
    void findUserReturnsEmptyWhenNotFound() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertTrue(userService.findUser(99L).isEmpty());
    }

    @Test
    void registerUserSavesViaRepository() {
        when(repository.save(any(User.class))).thenAnswer(inv -> inv.getArgument(0));

        userService.registerUser("Bob", "bob@example.com");

        verify(repository).save(any(User.class));
    }

    @Test
    void registerUserSendsWelcomeEmail() {
        when(repository.save(any(User.class))).thenAnswer(inv -> inv.getArgument(0));

        userService.registerUser("Bob", "bob@example.com");

        verify(emailService).send(eq("bob@example.com"), eq("Welcome!"), anyString());
    }

    @Test
    void registerUserReturnsSavedUser() {
        var saved = new User(42L, "Bob", "bob@example.com");
        when(repository.save(any(User.class))).thenReturn(saved);

        User result = userService.registerUser("Bob", "bob@example.com");

        assertEquals(42L, result.id());
        assertEquals("Bob", result.name());
    }
}
