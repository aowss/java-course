package course.ch22.exercises;

import java.util.ArrayList;
import java.util.List;

/**
 * Service that dispatches notifications via email and/or SMS.
 *
 * <p>Used in Exercise 3: students test this class with a spy to verify
 * which dispatch methods are called.
 */
public class NotificationService {

    private final List<String> sentEmails = new ArrayList<>();
    private final List<String> sentSms = new ArrayList<>();

    /**
     * Sends an email notification.
     *
     * @param to      the recipient email address
     * @param message the message content
     */
    public void sendEmail(String to, String message) {
        sentEmails.add(to + ": " + message);
    }

    /**
     * Sends an SMS notification.
     *
     * @param phoneNumber the recipient phone number
     * @param message     the message content
     */
    public void sendSms(String phoneNumber, String message) {
        sentSms.add(phoneNumber + ": " + message);
    }

    /**
     * Dispatches a notification based on the user's preference.
     *
     * @param preference  "email", "sms", or "both"
     * @param contact     the email address or phone number (or both separated by "|" for "both")
     * @param message     the notification message
     * @throws IllegalArgumentException if preference is not recognized
     */
    public void dispatch(String preference, String contact, String message) {
        switch (preference) {
            case "email" -> sendEmail(contact, message);
            case "sms" -> sendSms(contact, message);
            case "both" -> {
                String[] parts = contact.split("\\|");
                if (parts.length != 2) {
                    throw new IllegalArgumentException(
                            "For 'both' preference, contact must be 'email|phone'");
                }
                sendEmail(parts[0].trim(), message);
                sendSms(parts[1].trim(), message);
            }
            default -> throw new IllegalArgumentException("Unknown preference: " + preference);
        }
    }

    /**
     * @return the list of sent email records
     */
    public List<String> getSentEmails() {
        return List.copyOf(sentEmails);
    }

    /**
     * @return the list of sent SMS records
     */
    public List<String> getSentSms() {
        return List.copyOf(sentSms);
    }
}
