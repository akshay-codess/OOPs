package designPatterns.strategy;

public class PayByCreditCard implements PayStrategy {
    private final String cardNumber;

    public PayByCreditCard(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public boolean pay(int amount) {
        System.out.println("Paying " + amount + " using Credit Card " + cardNumber);
        return true;
    }
}
