package banking;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

/**
 * The Bank implementation.
 */
public class Bank implements BankInterface {
    private LinkedHashMap<Long, Account> accounts;

    public Bank() {
        this.accounts = new LinkedHashMap<>();
    }

    private Account getAccount(Long accountNumber) {
        return accounts.get(accountNumber);
    }

    public Long openCommercialAccount(Company company, int pin, double startingDeposit) {

        CommercialAccount commercialAccount = new CommercialAccount(company,new Random().nextLong(), pin, startingDeposit);
        accounts.put(commercialAccount.getAccountNumber(), commercialAccount);
        return commercialAccount.getAccountNumber();

    }

    public Long openConsumerAccount(Person person, int pin, double startingDeposit) {

        ConsumerAccount consumerAccount = new ConsumerAccount(person, new Random().nextLong(), pin, startingDeposit);
        accounts.put(consumerAccount.getAccountNumber(), consumerAccount);
        return consumerAccount.getAccountNumber();

    }

    public double getBalance(Long accountNumber) {
        if(accounts.get(accountNumber) == null){
            return -1.0;
        }
        return accounts.get(accountNumber).getBalance();
    }

    public void credit(Long accountNumber, double amount) {
        accounts.get(accountNumber).creditAccount(amount);
    }

    public boolean debit(Long accountNumber, double amount) {
        return accounts.get(accountNumber).debitAccount(amount);
    }

    public boolean authenticateUser(Long accountNumber, int pin) {
        return accounts.get(accountNumber).validatePin(pin);
    }
    
    public void addAuthorizedUser(Long accountNumber, Person authorizedPerson) {
        CommercialAccount commercialAccount = (CommercialAccount) accounts.get(accountNumber);
        commercialAccount.addAuthorizedUser(authorizedPerson);
    }

    public boolean checkAuthorizedUser(Long accountNumber, Person authorizedPerson) {
        try {
            CommercialAccount commercialAccount = (CommercialAccount) accounts.get(accountNumber);
            return commercialAccount.isAuthorizedUser(authorizedPerson);
        }catch (Exception e){
            return false;
        }
    }

    public Map<String, Double> getAverageBalanceReport() {
        Map<String, Double> map = new HashMap<>();
        Double sumConsumer = new Double(0);
        int qtdConsumer = 0;
        Double sumCommercial = new Double(0);
        int qtdCommercial = 0;
        for (Long key : accounts.keySet()) {
            if (accounts.get(key).getClass() == ConsumerAccount.class) {
                sumConsumer = sumConsumer + accounts.get(key).getBalance();
                qtdConsumer = qtdConsumer + 1;

            } else {
                sumCommercial = sumCommercial + accounts.get(key).getBalance();
                qtdCommercial = qtdCommercial + 1;
            }
        }
        for (Long key : accounts.keySet()) {
            if (accounts.get(key).getClass() == ConsumerAccount.class) {
                map.put(ConsumerAccount.class.getSimpleName(), sumConsumer/qtdConsumer);
            } else {
                map.put(CommercialAccount.class.getSimpleName(), sumCommercial/qtdCommercial);
            }
        }
        return map;
    }
}
