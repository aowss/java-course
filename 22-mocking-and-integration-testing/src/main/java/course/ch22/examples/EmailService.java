package course.ch22.examples;

/**
 * A simple email service interface used to demonstrate mocking.
 */
public interface EmailService {

    /**
     * Sends an email.
     *
     * @param to      the recipient address
     * @param subject the email subject
     * @param body    the email body
     * @return {@code true} if the email was sent successfully
     */
    boolean send(String to, String subject, String body);
}
