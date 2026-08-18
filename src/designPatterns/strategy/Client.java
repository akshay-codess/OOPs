package designPatterns.strategy;

public class Client {
    public static void main(String[] args) {
        Order order = new Order();
        order.addCost(2200);
        order.addCost(1850);

        // Context is configured with one strategy...
        order.setPayStrategy(new PayByPayPal("amanda@ya.com"));
        order.checkout();

        // ...and can be reconfigured with a different one at runtime,
        // without changing Order's code at all.
        order.setPayStrategy(new PayByCreditCard("4111-1111-1111-1111"));
        order.checkout();
    }
}
