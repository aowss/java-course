package course.ch04.examples;

/**
 * A simple bank account demonstrating fields, constructors, methods,
 * the {@code this} keyword, and encapsulation with access modifiers.
 *
 * <p>Run this to see deposits, withdrawals, and balance reporting.
 */
public class BankAccount {

    private String accountHolder;
    private double balance;

    /**
     * Creates an account with an initial balance.
     *
     * @param accountHolder the owner's name
     * @param initialBalance the starting balance (must be non-negative)
     * @throws IllegalArgumentException if initialBalance is negative
     */
    public BankAccount(String accountHolder, double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    /**
     * Deposits the given amount.
     *
     * @param amount the amount to deposit (must be positive)
     * @throws IllegalArgumentException if amount is not positive
     */
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        this.balance += amount;
    }

    /**
     * Withdraws the given amount.
     *
     * @param amount the amount to withdraw (must be positive and at most the current balance)
     * @throws IllegalArgumentException if amount is not positive or exceeds the balance
     */
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (amount > this.balance) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        this.balance -= amount;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    @Override
    public String toString() {
        return "BankAccount{accountHolder='%s', balance=%.2f}".formatted(accountHolder, balance);
    }

    public static void main(String[] args) {
        var account = new BankAccount("Alice", 1000.0);
        System.out.println(account);

        account.deposit(500.0);
        System.out.println("After deposit:  " + account);

        account.withdraw(200.0);
        System.out.println("After withdraw: " + account);
    }
}
