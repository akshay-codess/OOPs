package designPatterns.adapter;

import designPatterns.adapter.thridParty.iciciBank.ICICIBankAPI;

public class ICICIBankAPIAdapter implements  BankAPIAdapter{
    private ICICIBankAPI iciciBankAPI = new ICICIBankAPI();
    @Override
    public double getBalance(String accountNumber) {
//        String balance = iciciBankAPI.getBalance();
//        double doubleBalance =  Double.parseDouble(balance);
//        return doubleBalance;
        return 0;
    }

    @Override
    public boolean sendMoney(String fromAccount, String toAccount, double amount) {
        return false;
    }
}
