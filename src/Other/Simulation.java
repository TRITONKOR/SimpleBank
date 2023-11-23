package Other;

import Bank.Bank;
import Bank.Account.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.time.LocalDateTime;

public class Simulation {
    Bank privat = new Bank("Приват");

    Person shopOwner;
    Shop shop;

    List<Person> peoples;
    List<Product> products;

    public Simulation() {
        peoples = new ArrayList<>();
        peoples.add(new Person("Travis Scott", 32, 204147));
        peoples.add(new Person("Miles Morales", 17, 956484));
        peoples.add(new Person("Viktor Dudka", 22, 495469));
        peoples.add(new Person("Cristal Emily", 24, 854394));
        peoples.add(new Person("Henry Emily", 47, 121987));

        shopOwner = new Person("Mark Cukerberg", 10, 434465);
        privat.createNewAccount(shopOwner).createCard(shopOwner);
        shop = new Shop("Макдокнак",shopOwner);

        products = new ArrayList<>();
        products.add(new Product("Tango", 90));
        products.add(new Product("Healing Salve", 100));
        products.add(new Product("Enchanted Mango", 65));
        products.add(new Product("Iron Branch", 50));
        products.add(new Product("Circlet", 155));
        products.add(new Product("Faerie Fire", 65));
        products.add(new Product("Sentry Ward", 50));
        products.add(new Product("Dust Of Appearance", 80));
        products.add(new Product("Smoke Of Deceit", 50));

        shop.setProductList(products);

        Person travis = peoples.get(0);
        privat.createNewAccount(travis).createCard(travis).deposit(1000);
        Person miles = peoples.get(1);
        privat.createNewAccount(miles).createCard(miles).deposit(1000);
        Person viktor = peoples.get(2);
        privat.createNewAccount(viktor).createCard(viktor).deposit(1000);
        Person henry = peoples.get(4);
        privat.createNewAccount(henry).createCard(henry).deposit(1000);
        Person cristal = peoples.get(3);
        cristal.setAccount(henry.getAccount());
        cristal.getAccount().createCard(cristal);
    }

    public void Simulate() {
        Person user;
        Scanner scanner = new Scanner(System.in);
        user = choosePerson(scanner);
        System.out.println(user.toString());
        System.out.println(user.getAccount().toString());
        for(Card card : user.getCardList()) {
            System.out.println(card.toString());
        }
        System.out.println(user.getAccount().isAccountOwner(user) + '\n');

        procurement(scanner, shop, user);

        privat.showTransactionHistory();
    }

    private Person choosePerson(Scanner scanner) {
        Person wantedPerson = null;

        System.out.println("Write the fullname of person, that you want be: ");
        String input;

        while (wantedPerson == null) {
            for (Person person : peoples) {
                System.out.println(person.getFullName());
            }
            input = scanner.nextLine();

            for (Person person : peoples) {
                if (input.equals(person.getFullName())) {
                    wantedPerson = person;
                    break;
                }
            }

            if (input.isEmpty()) {
                System.out.println("nothing is not answer>-<");
            }
        }

        return wantedPerson;
    }

    private static void procurement(Scanner scanner, Shop shop, Person user) {
        String input;
        while (true) {
            int i = 0;

            System.out.println(
                "Write the name of product, what you want(if you want leave - just press Enter): ");
            for (Product product : shop.getProductList()) {
                System.out.println(++i + ") " + product.toString());
            }

            input = scanner.nextLine();

            if (input.isEmpty()) {
                break;
            }

            for (Product product : shop.getProductList()) {
                if(input.equals(product.getName())) {
                    int amount = chooseAmount(product);
                    if(user.purchase(product, amount, shop)) {
                        System.out.println(user.getFullName() + " [" + user.getAccount().getMainCard().getCardNumber() +
                            "] - купив/ла " + product.getName() + " (" + amount + ") " + product.getPrice() * amount +
                            " " + user.getAccount().getCurrentCurrency() + " " + LocalDateTime.now() + ". Залишилось на балансі - " +
                            user.getAccount().getBalance());
                    }
                    else {
                        System.out.println("Транзакція не пройшла");
                    }
                }
            }
        }
    }

    private static int chooseAmount(Product product) {
        Scanner scanner = new Scanner(System.in);
        int input;

        do {
            System.out.println("How much " + product.getName() + " you want?");
            input = scanner.nextInt();
            if (input < 1) {
                System.out.println("Wrong amount, try again");

            }        }
        while (input < 1 );
        return input;
    }
}