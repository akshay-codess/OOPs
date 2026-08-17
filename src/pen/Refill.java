package pen;

//Refill: ink (Ink), nib (Nib)

public class Refill {
    private Ink ink;
    private Nib nib;

    public Ink getInk() {
        return ink;
    }

//    public void setInk(Ink ink) {
//        this.ink = ink;
//    }

    public Nib getNib() {
        return nib;
    }

//    public void setNib(Nib nib) {
//        this.nib = nib;
//    }

    public Refill(Ink ink, Nib nib) {
        this.ink = ink;
        this.nib = nib;
    }
}
