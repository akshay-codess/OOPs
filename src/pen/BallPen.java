package pen;

public class BallPen extends Pen{
    private Refill refill;

    public Refill getRefill() {
        return refill;
    }


    public BallPen(String name, String brand, double price, Refill refill) {
        super(name, brand, PenType.BALL, CapState.CAPPED, price);
        this.refill = refill;
    }
}
