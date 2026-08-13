package designPatterns.adapter;

public class PhonePe {
    private BankAPIAdapter bankAPIAdapter;

    public PhonePe(BankAPIAdapter bankAPIAdapter) {
        this.bankAPIAdapter = bankAPIAdapter;
    }

    double doSomething() throws InterruptedException {
//        double currentBalance = bankAPIAdapter.getBalance();
        return 0.0;
    }
}
