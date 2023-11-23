package Bank;

import Other.Person;
import java.util.ArrayList;
import java.util.List;
import Bank.Bank.Currency;

public class Account {
    private final String accountId;
    private final String ownerFullName;
    private final int ownerId;
    private final Bank bank;

    private double balance;
    private List<Card> cardList;

    public Card mainCard;
    private Currency currentCurrency;

    public Account(String ownerFullName, int ownerId, Bank bank) {
        this.accountId = bank.generateRandomNumber(10);
        this.ownerFullName = ownerFullName;
        this.ownerId = ownerId;
        this.bank = bank;
        this.cardList = new ArrayList<>();
        this.balance = 0.0;
        this.currentCurrency = Currency.UAH;
    }

    public Card createCard(Person person) {
        Card card = new Card(person.getPersonId(), person.getFullName());
        this.cardList.add(card);
        person.addCard(card);
        if (this.getMainCard() == null) {
           this.setMainCard(card);
        }
        if (person.getMainCard() == null) {
            person.setMainCard(card);
        }
        return card;
    }

    public boolean transfer (Account targetAccount, double amount) {
        return  (bank.processTransactio(this, targetAccount, amount));
    }
    public String isAccountOwner(Person person) {
        if (person.getFullName().equals(person.getAccount().getOwnerFullName())) {
            return "Owner of account";
        }
        else {
            return "Trustee( ͡❛ ͜ʖ ͡❛)";
        }
    }

    public Currency getCurrentCurrency() {
        return currentCurrency;
    }

    public Card getMainCard() {
        return mainCard;
    }

    public void setMainCard(Card mainCard) {
        this.mainCard = mainCard;
    }

    public double getBalance() {
        return balance;
    }

    public String getOwnerFullName() {
        return ownerFullName;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public class Card {

        private final String cardNumber;
        private final String cvv;
        private int ownerId;
        private String ownerFullName;

        public Card(int ownerId, String ownerFullName) {
            this.ownerId = ownerId;
            this.ownerFullName = ownerFullName;
            this.cardNumber = bank.generateRandomNumber(16);
            this.cvv = bank.generateRandomNumber(4);
        }

        public void deposit(double money) {
            balance += money;
        }

        public void withdraw(double money) {
            balance -= money;
        }

        public String getCardNumber() {
            return cardNumber;
        }

        @Override
        public String toString() {
            return "Card{" +
                "cardNumber='" + cardNumber + '\'' +
                ", cardAccountId=" + accountId + '\'' +
                ", cvv='" + cvv + '\'' +
                ", ownerID='" + ownerId + '\'' +
                ", ownerFullName='" + ownerFullName + '\'' +
                '}';
        }
    }
    @Override
    public String toString() {
        return "Account{" +
            "accountId='" + accountId + '\'' +
            ", ownerFullName=" + ownerFullName + '\'' +
            ", ownerId='" + ownerId + '\'' +
            ", balance='" + getBalance() + '\'' +
            ", bank='" + bank.getName() + '\'' +
            '}';
    }
}
