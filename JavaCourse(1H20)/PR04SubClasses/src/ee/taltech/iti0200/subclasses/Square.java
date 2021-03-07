package ee.taltech.iti0200.subclasses;

public class Square extends Shape {

    public Square(Category category, double size) {
        super(category, size);
    }

    @Override
    public String draw() {
        return "Drawing square! Category: "
                + getCategory().toString().charAt(0) + getCategory().toString().substring(1).toLowerCase();
    }

    @Override
    public double calculateArea() {
        return getSize() * getSize();
    }

    // TODO: Override draw() -> return "Drawing square! Category: <shape-category>"

    // TODO: Override calculateArea() -> return area (size is the length of one side)
}
