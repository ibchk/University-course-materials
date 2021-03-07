package ee.taltech.iti0200.subclasses;
import java.lang.Math;

public class Circle extends Shape {

    public Circle(Category category, double size) {
        super(category, size);
    }

    @Override
    public String draw() {
        return "Drawing circle! Category: "
                + getCategory().toString().charAt(0) + getCategory().toString().substring(1).toLowerCase();
    }

    @Override
    public double calculateArea() {
        return getSize() * getSize() * Math.PI;
    }

    // TODO: Override draw() -> return "Drawing circle! Category: <shape-category>"

    // TODO: Override calculateArea() -> return area (size would be the length of radius)
}
