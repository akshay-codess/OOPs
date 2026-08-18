package designPatterns.strategy;

public class Order {
    private int totalCost = 0;
    private PayStrategy payStrategy;

    public void addCost(int cost) {
        totalCost += cost;
    }

    public void setPayStrategy(PayStrategy payStrategy) {
        this.payStrategy = payStrategy;
    }

    public boolean checkout() {
        return payStrategy.pay(totalCost);
    }
}
