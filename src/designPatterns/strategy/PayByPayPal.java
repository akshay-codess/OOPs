package designPatterns.strategy;

public class PayByPayPal implements PayStrategy {
    private final String email;

    public PayByPayPal(String email) {
        this.email = email;
    }

    @Override
    public boolean pay(int amount) {
        System.out.println("Paying " + amount + " using PayPal account " + email);
        return true;
    }
}
