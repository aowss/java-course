package course.ch22.examples;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Mockito Basics")
class MockitoBasicsTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("findUser delegates to repository")
    void findUserDelegatesToRepository() {
        var alice = new User(1L, "Alice", "alice@example.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(alice));

        Optional<User> result = userService.findUser(1L);

        assertTrue(result.isPresent());
        assertEquals("Alice", result.get().name());
        verify(userRepository).findById(1L);
    }

    @Test
    @DisplayName("findUser returns empty when user not found")
    void findUserReturnsEmptyWhenNotFound() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<User> result = userService.findUser(99L);

        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("registerUser saves and sends welcome email")
    void registerUserSavesAndSendsEmail() {
        var savedUser = new User(1L, "Bob", "bob@example.com");
        when(userRepository.save(org.mockito.ArgumentMatchers.any(User.class)))
                .thenReturn(savedUser);
        when(emailService.send(anyString(), anyString(), anyString()))
                .thenReturn(true);

        User result = userService.registerUser("Bob", "bob@example.com");

        assertEquals(1L, result.id());
        verify(userRepository).save(org.mockito.ArgumentMatchers.any(User.class));
        verify(emailService).send(
                eq("bob@example.com"),
                eq("Welcome!"),
                eq("Welcome to our platform, Bob!"));
    }
}
