package designPatterns.decorator;

public class OrangeCone implements IceCreamConstituents{

    IceCreamConstituents iceCreamConstituents;

    public OrangeCone(){};

    public OrangeCone(IceCreamConstituents iceCreamConstituents) {
        this.iceCreamConstituents = iceCreamConstituents;
    }

    @Override
    public int getCost() {
        if(iceCreamConstituents ==  null) {
            return 10;
        } else {
            return iceCreamConstituents.getCost() + 10;
        }
    }

    @Override
    public String getDescription() {
        if(iceCreamConstituents ==  null) {
            return "Orange Cone";
        } else {
            return iceCreamConstituents.getDescription() + "Orange Cone";
        }
    }
}
