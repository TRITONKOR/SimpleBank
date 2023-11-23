package Other;

import Bank.Account;

import java.util.List;
import java.util.ArrayList;
public class Shop {
    private final String name;
    private Person owner;
    private List<Product> productList;

    private Account accountForPay;

    public Shop(String name, Person owner) {
        this.name = name;
        this.owner = owner;
        this.accountForPay = owner.getAccount();
        this.productList = new ArrayList<>();

    }

    public Account getAccountForPay() {
        return accountForPay;
    }

    public List<Product> getProductList() {
        return this.productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}
