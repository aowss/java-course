package course.ch22.exercises;

/**
 * Payment processing interface used in Exercise 2.
 */
public interface PaymentGateway {

    /**
     * Charges a payment.
     *
     * @param amount      the amount in cents
     * @param cardToken   a tokenized card reference
     * @return {@code true} if the charge succeeded
     */
    boolean charge(int amount, String cardToken);

    /**
     * Refunds a previous charge.
     *
     * @param transactionId the original transaction ID
     * @param amount        the amount to refund in cents
     * @return {@code true} if the refund succeeded
     */
    boolean refund(String transactionId, int amount);
}
