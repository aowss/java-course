package course.ch29.exercises;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NotificationStrategyTest {

    @Test
    void sendViaEmail() {
        assertEquals("EMAIL: Hello", NotificationStrategy.send("Hello",
                new NotificationStrategy.EmailStrategy()));
    }

    @Test
    void sendViaSms() {
        assertEquals("SMS: Alert", NotificationStrategy.send("Alert",
                new NotificationStrategy.SmsStrategy()));
    }

    @Test
    void sendViaPush() {
        assertEquals("PUSH: Update", NotificationStrategy.send("Update",
                new NotificationStrategy.PushStrategy()));
    }
}
