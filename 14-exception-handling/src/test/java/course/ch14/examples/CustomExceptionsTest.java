package course.ch14.examples;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomExceptionsTest {

    @Test
    void depositIncreasesBalance() throws CustomExceptions.InsufficientFundsException {
        var account = new CustomExceptions.BankAccount("A1", 100.0);
        account.deposit(50);
        assertEquals(150.0, account.getBalance());
    }

    @Test
    void withdrawDecreasesBalance() throws CustomExceptions.InsufficientFundsException {
        var account = new CustomExceptions.BankAccount("A1", 100.0);
        account.withdraw(30);
        assertEquals(70.0, account.getBalance());
    }

    @Test
    void withdrawThrowsInsufficientFunds() {
        var account = new CustomExceptions.BankAccount("A1", 50.0);
        var ex = assertThrows(CustomExceptions.InsufficientFundsException.class,
                () -> account.withdraw(100));
        assertEquals(50.0, ex.getShortfall());
        assertTrue(ex.getMessage().contains("50.0"));
    }

    @Test
    void depositNonPositiveThrows() {
        var account = new CustomExceptions.BankAccount("A1", 100.0);
        assertThrows(IllegalArgumentException.class, () -> account.deposit(0));
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-10));
    }

    @Test
    void withdrawNonPositiveThrows() {
        var account = new CustomExceptions.BankAccount("A1", 100.0);
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(0));
    }

    @Test
    void invalidAccountIdThrows() {
        assertThrows(CustomExceptions.InvalidAccountException.class,
                () -> new CustomExceptions.BankAccount("", 100));
        assertThrows(CustomExceptions.InvalidAccountException.class,
                () -> new CustomExceptions.BankAccount(null, 100));
    }

    @Test
    void negativeInitialBalanceThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new CustomExceptions.BankAccount("A1", -50));
    }

    @Test
    void transferMovesMoneyBetweenAccounts() throws CustomExceptions.InsufficientFundsException {
        var from = new CustomExceptions.BankAccount("A1", 200.0);
        var to = new CustomExceptions.BankAccount("A2", 50.0);
        from.transfer(to, 100);
        assertEquals(100.0, from.getBalance());
        assertEquals(150.0, to.getBalance());
    }

    @Test
    void transferThrowsOnInsufficientFunds() {
        var from = new CustomExceptions.BankAccount("A1", 30.0);
        var to = new CustomExceptions.BankAccount("A2", 0.0);
        assertThrows(CustomExceptions.InsufficientFundsException.class,
                () -> from.transfer(to, 50));
    }
}
