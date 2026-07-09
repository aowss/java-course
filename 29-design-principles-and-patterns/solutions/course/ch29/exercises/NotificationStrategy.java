package course.ch29.exercises;

public final class NotificationStrategy {

    private NotificationStrategy() {
    }

    @FunctionalInterface
    public interface DeliveryStrategy {
        String deliver(String message);
    }

    public static String send(String message, DeliveryStrategy strategy) {
        return strategy.deliver(message);
    }

    public static final class EmailStrategy implements DeliveryStrategy {
        @Override
        public String deliver(String message) {
            return "EMAIL: " + message;
        }
    }

    public static final class SmsStrategy implements DeliveryStrategy {
        @Override
        public String deliver(String message) {
            return "SMS: " + message;
        }
    }

    public static final class PushStrategy implements DeliveryStrategy {
        @Override
        public String deliver(String message) {
            return "PUSH: " + message;
        }
    }
}
