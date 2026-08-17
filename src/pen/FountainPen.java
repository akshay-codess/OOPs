package pen;

public class FountainPen extends Pen implements RefillablePen{
    private Nib nib;

    private Ink ink;

    public Nib getNib() {
        return nib;
    }

    public Ink getInk() {
        return ink;
    }

    public FountainPen(String name, String brand, double price, Nib nib, Ink ink) {
        super(name, brand, PenType.FOUNTAIN, CapState.CAPPED, price);
        this.nib = nib;
        this.ink = ink;
    }

    @Override
    public void refill(Ink ink) {
        this.ink = ink;
        System.out.println("Refilled");
    }
}
