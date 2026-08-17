package pen;

import java.util.ArrayList;
import java.util.List;

//Ink: color (String), inkTypes (List<InkType>)
public class Ink {
    private String color;

    public String getColor() {
        return color;
    }

//    public void setColor(String color) {
//        this.color = color;
//    }

    public List<InkType> getInkTypes() {
        return new ArrayList<>(inkTypes);
    }

//    public void setInkTypes(List<InkType> inkTypes) {
//        this.inkTypes = inkTypes;
//    }

    public Ink(String color, List<InkType> inkTypes) {
        this.color = color;
        this.inkTypes = new ArrayList<>(inkTypes);
    }

    private List<InkType> inkTypes;
}
