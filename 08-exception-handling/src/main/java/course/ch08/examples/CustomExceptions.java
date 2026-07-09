package course.ch08.examples;

/**
 * Demonstrates defining and using custom exception classes.
 *
 * <p>Custom exceptions allow you to represent domain-specific error conditions.
 * <ul>
 *   <li><strong>Checked exceptions</strong> (extend {@link Exception}) — the caller must
 *       handle or declare them. Use for recoverable conditions.</li>
 *   <li><strong>Unchecked exceptions</strong> (extend {@link RuntimeException}) — the caller
 *       is not forced to handle them. Use for programming errors.</li>
 * </ul>
 *
 * <pre>{@code
 * try {
 *     account.withdraw(1000);
 * } catch (InsufficientFundsException e) {
 *     System.out.println("Short by: " + e.getShortfall());
 * }
 * }</pre>
 */
public class CustomExceptions {

    /**
     * A checked exception indicating that a bank account has insufficient funds.
     */
    public static class InsufficientFundsException extends Exception {

        private final double shortfall;

        public InsufficientFundsException(double shortfall) {
            super("Insufficient funds, short by: " + shortfall);
            this.shortfall = shortfall;
        }

        public double getShortfall() {
            return shortfall;
        }
    }

    /**
     * An unchecked exception indicating that an account identifier is invalid.
     */
    public static class InvalidAccountException extends RuntimeException {

        public InvalidAccountException(String accountId) {
            super("Invalid account: " + accountId);
        }
    }

    /**
     * A simple bank account that uses custom exceptions.
     */
    public static class BankAccount {

        private final String id;
        private double balance;

        public BankAccount(String id, double initialBalance) {
            if (id == null || id.isBlank()) {
                throw new InvalidAccountException(String.valueOf(id));
            }
            if (initialBalance < 0) {
                throw new IllegalArgumentException("Initial balance cannot be negative");
            }
            this.id = id;
            this.balance = initialBalance;
        }

        public String getId() {
            return id;
        }

        public double getBalance() {
            return balance;
        }

        /**
         * Deposits money into the account.
         *
         * @param amount the amount to deposit
         * @throws IllegalArgumentException if {@code amount} is not positive
         */
        public void deposit(double amount) {
            if (amount <= 0) {
                throw new IllegalArgumentException("Deposit amount must be positive");
            }
            balance += amount;
        }

        /**
         * Withdraws money from the account.
         *
         * @param amount the amount to withdraw
         * @throws InsufficientFundsException if the balance is too low
         * @throws IllegalArgumentException   if {@code amount} is not positive
         */
        public void withdraw(double amount) throws InsufficientFundsException {
            if (amount <= 0) {
                throw new IllegalArgumentException("Withdrawal amount must be positive");
            }
            if (amount > balance) {
                throw new InsufficientFundsException(amount - balance);
            }
            balance -= amount;
        }

        /**
         * Transfers money from this account to another.
         *
         * @param target the account to transfer to
         * @param amount the amount to transfer
         * @throws InsufficientFundsException if this account has insufficient funds
         */
        public void transfer(BankAccount target, double amount) throws InsufficientFundsException {
            withdraw(amount);
            target.deposit(amount);
        }
    }

    public static void main(String[] args) {
        var account = new BankAccount("ACC-001", 500.0);
        System.out.println("Balance: " + account.getBalance());

        try {
            account.withdraw(200);
            System.out.println("After withdraw 200: " + account.getBalance());
            account.withdraw(400);
        } catch (InsufficientFundsException e) {
            System.out.println("Caught: " + e.getMessage());
            System.out.println("Shortfall: " + e.getShortfall());
        }

        System.out.println("\nCreating invalid account:");
        try {
            new BankAccount("", 100);
        } catch (InvalidAccountException e) {
            System.out.println("Caught: " + e.getMessage());
        }
    }
}
