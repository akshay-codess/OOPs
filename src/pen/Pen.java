package pen;


public abstract class Pen {
    private String name;
    private String brand;
    private PenType penType;
    private CapState capState;

    private double price;


    public Pen(String name, String brand, PenType penType, CapState capState, double price) {
        this.name = name;
        this.brand = brand;
        this.penType = penType;
        this.capState = capState;
        this.price = price;
    }


    void write() {
        if(this.capState == CapState.CAPPED) {
//            System.out.println("cannot write when cap on");
            throw new RuntimeException("cannot write when cap on");
        } else {
            System.out.println("Wrote Something");
        }

    }
    void cap() {
        if(this.capState == CapState.NOCAP)  {
            throw new RuntimeException("Pen Has No Cap");
        }
        if(this.capState == CapState.CAPPED) {
            throw new RuntimeException("Already Capped");
        }
        this.capState = CapState.CAPPED;
    }
    void unCap() {
        if(this.capState == CapState.NOCAP)  {
            throw new RuntimeException("Pen Has No Cap");
        }
        if(this.capState == CapState.UNCAPPED) {
            throw new RuntimeException("Already Uncapped");
        }
        this.capState = CapState.UNCAPPED;
    }
}
