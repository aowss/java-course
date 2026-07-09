package course.ch29.exercises;

/**
 * Exercise 1 (Guided): Implement notification delivery using the Strategy pattern.
 *
 * <p>Define a {@link NotificationStrategy} interface and concrete strategies for
 * email, SMS, and push notifications.
 *
 * <pre>{@code
 * String result = NotificationService.send("Hello", new EmailStrategy());
 * // "EMAIL: Hello"
 * }</pre>
 */
public final class NotificationStrategy {

    private NotificationStrategy() {
    }

    /**
     * Strategy for delivering a notification message.
     */
    @FunctionalInterface
    public interface DeliveryStrategy {
        /**
         * Delivers the message and returns a delivery confirmation.
         *
         * @param message the message to deliver
         * @return a confirmation string including the channel name
         */
        String deliver(String message);
    }

    /**
     * Sends a notification using the given delivery strategy.
     *
     * @param message  the message content
     * @param strategy the delivery strategy
     * @return the delivery confirmation
     */
    public static String send(String message, DeliveryStrategy strategy) {
        // TODO: delegate to strategy.deliver(message)
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Email delivery strategy. Returns {@code "EMAIL: <message>"}.
     */
    public static final class EmailStrategy implements DeliveryStrategy {
        @Override
        public String deliver(String message) {
            // TODO: return "EMAIL: " + message
            throw new UnsupportedOperationException("Not yet implemented");
        }
    }

    /**
     * SMS delivery strategy. Returns {@code "SMS: <message>"}.
     */
    public static final class SmsStrategy implements DeliveryStrategy {
        @Override
        public String deliver(String message) {
            // TODO: return "SMS: " + message
            throw new UnsupportedOperationException("Not yet implemented");
        }
    }

    /**
     * Push notification strategy. Returns {@code "PUSH: <message>"}.
     */
    public static final class PushStrategy implements DeliveryStrategy {
        @Override
        public String deliver(String message) {
            // TODO: return "PUSH: " + message
            throw new UnsupportedOperationException("Not yet implemented");
        }
    }
}
