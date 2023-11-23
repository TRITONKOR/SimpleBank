package Other;

import Bank.Account;
import Bank.Account.Card;
import java.util.ArrayList;
import java.util.List;

public class Person {
    private final String fullName;
    private final int age;
    private final int personId;

    private Account account;
    private List<Card> cardList;
    private Card mainCard;

    public Person(String fullName, int age, int personId) {
        this.fullName = fullName;
        this.age = age;
        this.personId = personId;
        this.cardList = new ArrayList<>();
    }

    public void addCard(Card card) {
        cardList.add(card);
    }

    public boolean purchase(Saleable saleable,  int amount,  Shop shop) {
        return this.getAccount().transfer(shop.getAccountForPay(), saleable.getPrice() * amount);
    }

    public Card getMainCard() {
        return mainCard;
    }

    public void setMainCard(Card mainCard) {
        this.mainCard = mainCard;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    public String getFullName() {
        return fullName;
    }

    public int getAge() {
        return age;
    }

    public int getPersonId() {
        return personId;
    }

    @Override
    public String toString() {
        return "Person{" +
            "fullName ='" + getFullName() + '\'' +
            ", age ='" + getAge() + '\'' +
            ", personId ='" + getPersonId() +
            '}';
    }
}
