package course.ch22.exercises;

import course.ch22.examples.EmailService;
import course.ch22.examples.User;
import course.ch22.examples.UserRepository;
import course.ch22.examples.UserService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Exercise 1 (Guided): Test {@link UserService} by mocking {@link UserRepository}.
 *
 * <p>Use {@code @Mock} to create a mock {@code UserRepository} and {@code EmailService},
 * and {@code @InjectMocks} to create the {@code UserService}.
 *
 * <p>Write the following tests:
 * <ul>
 *   <li>{@code findUser} returns the user when repository has it</li>
 *   <li>{@code findUser} returns empty when repository doesn't have it</li>
 *   <li>{@code registerUser} saves the user via repository</li>
 *   <li>{@code registerUser} sends a welcome email</li>
 *   <li>{@code registerUser} returns the saved user with generated ID</li>
 * </ul>
 *
 * <p>Use {@code when(...).thenReturn(...)} for stubbing and
 * {@code verify(...)} to assert interactions.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("UserService Tests")
class UserServiceTest {

    // TODO: Declare @Mock UserRepository
    // TODO: Declare @Mock EmailService
    // TODO: Declare @InjectMocks UserService

    // TODO: Test findUser returns user when found
    //       Hint: when(repository.findById(1L)).thenReturn(Optional.of(user));

    // TODO: Test findUser returns empty when not found

    // TODO: Test registerUser saves user via repository
    //       Hint: verify(repository).save(any(User.class));

    // TODO: Test registerUser sends welcome email
    //       Hint: verify(emailService).send(eq("email"), eq("Welcome!"), anyString());

    // TODO: Test registerUser returns saved user with generated ID
}
