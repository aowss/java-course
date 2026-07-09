package course.ch22.examples;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Argument Matcher Demo")
class ArgumentMatcherDemoTest {

    @Mock
    private UserRepository userRepository;

    @Test
    @DisplayName("anyLong() matches any long argument")
    void anyLongMatchesAnyId() {
        var user = new User(1L, "Test", "test@example.com");
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        assertTrue(userRepository.findById(42L).isPresent());
        assertTrue(userRepository.findById(999L).isPresent());
    }

    @Test
    @DisplayName("eq() matches exact value")
    void eqMatchesExactValue() {
        var user = new User(5L, "Exact", "exact@example.com");
        when(userRepository.findById(eq(5L))).thenReturn(Optional.of(user));

        assertTrue(userRepository.findById(5L).isPresent());
        verify(userRepository).findById(eq(5L));
    }

    @Test
    @DisplayName("argThat() matches custom predicate")
    void argThatMatchesCustomPredicate() {
        var user = new User(1L, "Admin", "admin@example.com");
        when(userRepository.save(argThat(u -> u.name().equals("Admin"))))
                .thenReturn(user);

        User result = userRepository.save(new User(0L, "Admin", "admin@example.com"));

        assertTrue(result.id() > 0);
        verify(userRepository).save(argThat(u -> u.email().contains("admin")));
    }
}
