package Bank;

import Other.Person;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bank {
    private final String name;
    private List<Account> accountList;
    public TransactionHistory transactionHistory;

    public Bank(String name) {
        this.name = name;
        this.accountList = new ArrayList<>();
        transactionHistory = TransactionHistory.getInstance();
    }

    public boolean processTransactio(Account accountFrom, Account accountTo, double amount){
        Transaction transaction = new Transaction(generateRandomNumber(5),accountFrom, accountTo, amount);
        transactionHistory.saveTransaction(transaction);
        return transaction.isSuccessfull();
    }

    public void showTransactionHistory(){
        transactionHistory.getTransactions().forEach(System.out::println);
    }

    public Account createNewAccount(Person person) {
        Account account = new Account(person.getFullName(), person.getPersonId(), this);
        person.setAccount(account);
        accountList.add(account);
        return account;
    }

    public String generateRandomNumber(int countOfDigit) {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < countOfDigit; i++) {
            int digit = random.nextInt(10);
            stringBuilder.append(digit);
        }
        return stringBuilder.toString();
    }

    public String getName() {
        return name;
    }

    public enum Currency {
        USD("USA Dollar"),
        UAH("UA Hryvnia"),
        EUR("Euro");

        private final String currency;

        Currency(String currency) {
            this.currency = currency;
        }
    }
}
