package Bank;

public class Transaction {
    private final String transactionId;
    private final Account accountFromId;
    private final Account accountToId;
    private final double amount;
    private final boolean isSuccessfull;

    public Transaction(String transactionId, Account accountFromId, Account accountToId, double amount) {
        this.transactionId = transactionId;
        this.accountFromId = accountFromId;
        this.accountToId = accountToId;
        this.amount = amount;
        this.isSuccessfull = makeTransaction(accountFromId, accountToId, amount);
    }

    public boolean isSuccessfull() {
        return isSuccessfull;
    }

    public boolean makeTransaction(Account accountFrom, Account accountTo, double amount) {
        if (accountFrom.getBalance() < amount) {
            return false;
        }
        accountFrom.mainCard.withdraw(amount);
        accountTo.mainCard.deposit(amount);
        return true;
    }

    @Override
    public String toString() {
        return "Transaction{" +
            ", accountFromId = " + accountFromId +
            ", accountToId = " + accountToId +
            ", amount = " + amount +
            ", isSuccessfull = " + isSuccessfull +
            '}';
    }
}
