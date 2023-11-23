package Bank;

import java.util.ArrayList;
import java.util.List;

public class TransactionHistory {
    private static TransactionHistory transactionHistory;
    private static List<Transaction> transactionList;

    private TransactionHistory() {
        transactionList = new ArrayList<>();
    }

    static TransactionHistory getInstance() {
        if(transactionHistory == null){
            transactionHistory = new TransactionHistory();
        }
        return transactionHistory;
    }
    void saveTransaction(Transaction transaction) {
        transactionList.add(transaction);
    }
    public List<Transaction> getTransactions() {
        return transactionList;
    }

}
