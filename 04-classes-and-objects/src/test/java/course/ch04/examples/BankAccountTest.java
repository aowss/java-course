package course.ch04.examples;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BankAccountTest {

    @Test
    void constructorSetsFields() {
        var account = new BankAccount("Alice", 1000.0);
        assertEquals("Alice", account.getAccountHolder());
        assertEquals(1000.0, account.getBalance(), 0.001);
    }

    @Test
    void constructorRejectsNegativeBalance() {
        assertThrows(IllegalArgumentException.class,
                () -> new BankAccount("Bob", -100.0));
    }

    @Test
    void depositIncreasesBalance() {
        var account = new BankAccount("Alice", 500.0);
        account.deposit(200.0);
        assertEquals(700.0, account.getBalance(), 0.001);
    }

    @Test
    void depositRejectsNonPositiveAmount() {
        var account = new BankAccount("Alice", 500.0);
        assertThrows(IllegalArgumentException.class, () -> account.deposit(0));
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-50));
    }

    @Test
    void withdrawDecreasesBalance() {
        var account = new BankAccount("Alice", 500.0);
        account.withdraw(200.0);
        assertEquals(300.0, account.getBalance(), 0.001);
    }

    @Test
    void withdrawRejectsInsufficientFunds() {
        var account = new BankAccount("Alice", 100.0);
        assertThrows(IllegalArgumentException.class,
                () -> account.withdraw(200.0));
    }

    @Test
    void withdrawRejectsNonPositiveAmount() {
        var account = new BankAccount("Alice", 500.0);
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(0));
    }

    @Test
    void toStringContainsAccountInfo() {
        var account = new BankAccount("Alice", 1000.0);
        var str = account.toString();
        assertEquals(true, str.contains("Alice"));
        assertEquals(true, str.contains("1000"));
    }
}
