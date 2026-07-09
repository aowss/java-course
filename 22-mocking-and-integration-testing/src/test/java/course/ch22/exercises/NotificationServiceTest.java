package course.ch22.exercises;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Exercise 3 (Challenge): Test {@link NotificationService} using a spy.
 *
 * <p>Write tests that verify:
 * <ul>
 *   <li>{@code dispatch("email", ...)} calls {@code sendEmail} but not {@code sendSms}</li>
 *   <li>{@code dispatch("sms", ...)} calls {@code sendSms} but not {@code sendEmail}</li>
 *   <li>{@code dispatch("both", ...)} calls both methods</li>
 * </ul>
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("NotificationService Tests")
class NotificationServiceTest {

    // TODO: Create a @Spy NotificationService
    // TODO: Test email preference dispatches email only
    // TODO: Test sms preference dispatches sms only
    // TODO: Test both preference dispatches both
}
