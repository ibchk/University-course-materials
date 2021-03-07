package ee.taltech.iti0200.burgerbooth;

import java.util.List;

public class Food {

    public static final double PRICEPERCENT = 0.15;

    private double price;
    private String name;
    private List<String> ingredients;
    private FoodType foodType;

    public Food(double price, String name, List<String> ingredients, FoodType foodType) {
        this.price = price;
        this.name = name;
        this.ingredients = ingredients;
        this.foodType = foodType;
    }

    public double getMakeingTime() {
        return PRICEPERCENT * ingredients.size();
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public FoodType getFoodType() {
        return foodType;
    }
}
