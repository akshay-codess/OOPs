package designPatterns.decorator;

public class Client {
    public static void main(String[] args) {
        IceCreamConstituents iceCreamConstituents =
                new OrangeCone(
                        new ChocoChips(
                                new OrangeCone()
                        )
                );
        System.out.println(iceCreamConstituents.getCost());
        System.out.println(iceCreamConstituents.getDescription());
    }
}
