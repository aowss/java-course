package course.ch22.exercises;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("NotificationService Tests")
class NotificationServiceTest {

    @Spy
    private NotificationService notificationService = new NotificationService();

    @Test
    void emailPreferenceCallsSendEmailOnly() {
        notificationService.dispatch("email", "user@example.com", "Hello");

        verify(notificationService).sendEmail("user@example.com", "Hello");
        verify(notificationService, never()).sendSms(org.mockito.ArgumentMatchers.anyString(),
                org.mockito.ArgumentMatchers.anyString());
    }

    @Test
    void smsPreferenceCallsSendSmsOnly() {
        notificationService.dispatch("sms", "+15551234567", "Hello");

        verify(notificationService).sendSms("+15551234567", "Hello");
        verify(notificationService, never()).sendEmail(org.mockito.ArgumentMatchers.anyString(),
                org.mockito.ArgumentMatchers.anyString());
    }

    @Test
    void bothPreferenceCallsBothMethods() {
        notificationService.dispatch("both", "user@example.com|+15551234567", "Hello");

        verify(notificationService).sendEmail("user@example.com", "Hello");
        verify(notificationService).sendSms("+15551234567", "Hello");
    }
}
