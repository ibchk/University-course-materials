package ee.taltech.iti0200.subclasses;

import java.util.Optional;

public abstract class Shape {

    private Category category;
    private double size;
    int number;

    public enum Category {
        // TODO: create enum with 3 sizes (BIG, MEDIUM, SMALL)
        BIG,
        MEDIUM,
        SMALL
    }


    public Shape(Category category, double size) {
        // TODO: Save category and size
        this.category = category;
        this.size = size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getSize() {
        return size;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public abstract String draw();

    public abstract double calculateArea();

    public Optional<Integer> getNumber() {
        if (number == 0) {
            return Optional.empty();
        }
        return Optional.of(number); // TODO return stored number if present, otherwise Optional.empty()
    }

    public void putNumber(int number) {
        // TODO: store number
        this.number = number;
    }

    public void clearNumber() {
        // TODO: clear stored number if any
        number = 0;
    }


    // TODO: Getters / Setters
    public static void main(String[] args) {
        Shape square = new Square(Shape.Category.BIG, 10);
        Shape circle = new Circle(Shape.Category.SMALL, 2);

        System.out.println(square.draw()); // Drawing square! Category: Big
        System.out.println(circle.draw()); // Drawing circle! Category: Small

        circle.setCategory(Shape.Category.MEDIUM);
        System.out.println(circle.draw()); // Drawing circle! Category: Medium

        System.out.println(circle.calculateArea()); // 12.566370614359172
        System.out.println(square.calculateArea()); // 100.0

        circle.putNumber(6);
        System.out.println(square.getNumber()); // Optional.empty()
        System.out.println(circle.getNumber().isPresent()); // true
        System.out.println(circle.getNumber().get()); // 6
        circle.clearNumber();
        System.out.println(circle.getNumber());  // Optional.empty()
    }

}
