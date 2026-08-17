package pen;

public class PenFactory {
    public static Pen getPenForType(PenRequest request) {
        switch (request.getType()) {
            case BALL:
                return new BallPen(request.getName(), request.getBrand(), request.getPrice(), request.getRefill());
            case GEL:
                return new GelPen(request.getName(), request.getBrand(), request.getPrice(), request.getRefill());
            case FOUNTAIN:
                return new FountainPen(request.getName(), request.getBrand(), request.getPrice(), request.getNib(), request.getInk());
            default:
                throw new IllegalArgumentException("Unknown pen type: " + request.getType());
        }
    }
}