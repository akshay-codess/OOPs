package designPatterns.decorator;

public class ChocoChips implements IceCreamConstituents {

    private IceCreamConstituents iceCreamConstituents;

    public ChocoChips(IceCreamConstituents iceCreamConstituents) {
        this.iceCreamConstituents = iceCreamConstituents;
    }

    @Override
    public int getCost() {
        return iceCreamConstituents.getCost() + 5;
    }

    @Override
    public String getDescription() {
        return iceCreamConstituents.getDescription() + "ChocoChips";
    }
}
