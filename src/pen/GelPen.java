package pen;

public class GelPen extends Pen{
    private Refill refill;

    public Refill getRefill() {
        return refill;
    }

    public GelPen(String name, String brand, double price, Refill refill) {
        super(name, brand, PenType.GEL, CapState.CAPPED, price);
        this.refill = refill;
    }
}
