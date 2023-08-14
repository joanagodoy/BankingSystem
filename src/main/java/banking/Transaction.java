package banking;

/**
 * A bank transaction implementation.
 */
public class Transaction implements TransactionInterface {
    private Long accountNumber;
    private BankInterface bank;

    /**
     * @param bank          The bank where the account is housed.
     * @param accountNumber The customer's account number.
     * @param attemptedPin  The PIN entered by the customer.
     * @throws Exception Account validation failed.
     */
    public Transaction(BankInterface bank, Long accountNumber, int attemptedPin) throws Exception {
        if(bank == null || accountNumber == null){
            throw new Exception();
        }
        this.bank = bank;
        this.accountNumber = accountNumber;
        if(!this.bank.authenticateUser(accountNumber, attemptedPin)){
            throw new Exception();
        }
    }

    public double getBalance() {
        return this.bank.getBalance(this.accountNumber);
    }

    public void credit(double amount) {
        this.bank.credit(this.accountNumber, amount);
    }

    public boolean debit(double amount) {
        return this.bank.debit(accountNumber, amount);
    }
}
