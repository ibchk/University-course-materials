package ee.taltech.iti0200.burgerbooth;

import ee.taltech.iti0200.burgerbooth.terminal.TerminalDigitalCheckout;
import ee.taltech.iti0200.burgerbooth.terminal.TerminalDriveIn;
import ee.taltech.iti0200.burgerbooth.terminal.TerminalRealCheckout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BurgerBoothTest {

    public static final double PRICE16 = 1.6;
    public static final double PRICE22 = 2.2;
    public static final double PRICE24 = 2.4;
    public static final double PRICE08 = 0.8;
    public static final double PRICE11 = 1.1;
    public static final double PRICE03 = 0.3;
    public static final int PRICEFIFTEEN = 15;

    BurgerBooth cafe = new BurgerBooth();
    TerminalRealCheckout termReal = new TerminalRealCheckout();
    TerminalDigitalCheckout termDig = new TerminalDigitalCheckout();
    TerminalDriveIn termDrive = new TerminalDriveIn();
    String bread = "bread";
    String sauce = "sauce";
    String cutlet = "cutlet";
    String cheese = "cheese";
    String tomatoSlice = "tomato slice";
    String syrupCola = "Cola syrup";
    String syrupSprite = "Sprite syrup";
    String water = "water";
    String potato = "potato";
    String bacon = "bacon";
    List<String> burgerOriginalIngredients = new ArrayList<>(Arrays.asList(bread, sauce, cutlet, cheese, tomatoSlice,
            sauce, bread));
    Food burgerOriginal = new Food(PRICE16, "BurgerOriginal", burgerOriginalIngredients, FoodType.BURGER);
    List<String> burgerMaxiIngredients = new ArrayList<>(Arrays.asList(bread, sauce, cutlet, cheese, cutlet, cheese,
            tomatoSlice, sauce, bread));
    Food burgerMaxi = new Food(PRICE22, "BurgerMaxi", burgerMaxiIngredients, FoodType.BURGER);
    List<String> burgerBaconIngredients = new ArrayList<>(Arrays.asList(bread, sauce, cutlet, cheese,
            bacon, tomatoSlice, sauce, bread));
    Food burgerBacon = new Food(PRICE24, "BurgerBacon", burgerBaconIngredients, FoodType.BURGER);
    List<String> potatoList = new ArrayList<>(Collections.singletonList(potato));
    Food frenchFries = new Food(1, "French fries", potatoList, FoodType.SNACK);
    List<String> waterList = new ArrayList<>(Collections.singletonList(water));
    Food stillWater = new Food(PRICE08, "Still water", waterList, FoodType.COLDDRINK);
    List<String> colaList = new ArrayList<>(Arrays.asList(water, syrupCola));
    Food cola = new Food(PRICE11, "Cola", colaList, FoodType.COLDDRINK);
    List<String> spriteList = new ArrayList<>(Arrays.asList(water, syrupSprite));
    Food sprite = new Food(PRICE11, "Sprite", spriteList, FoodType.COLDDRINK);

    @org.junit.jupiter.api.Test
    void noTerminal() {
        assertEquals("Terminal problems.", cafe.finishOrder());
    }

    @org.junit.jupiter.api.Test
    void checkFood() {
        assertEquals("Cola", cola.getName());
        assertEquals(PRICE11, cola.getPrice());
        assertEquals(PRICE03, cola.getMakeingTime());
        assertEquals(FoodType.COLDDRINK, cola.getFoodType());
        assertEquals(2, cola.getIngredients().size());
        cola.setPrice(1);
        assertEquals(1, cola.getPrice());
    }

    @org.junit.jupiter.api.Test
    void checkIngredientsInStock() {
        cafe.addIngredient(bread, 100);
        cafe.addIngredient(sauce, 100);
        cafe.addIngredient(cutlet, 100);
        cafe.addIngredient(cheese, 100);
        cafe.addIngredient(tomatoSlice, 100);
        cafe.addIngredient(syrupCola, 100);
        cafe.addIngredient(syrupSprite, 100);
        cafe.addIngredient(water, 100);
        cafe.addIngredient(potato, 100);
        cafe.addIngredient(bacon, 100);
        for (String ingredient : cafe.getIngredientsInStock().keySet()) {
            assertEquals(100, cafe.getIngredientsInStock().get(ingredient));
        }
    }

    @org.junit.jupiter.api.Test
    void tryToMakeNormalOrderNotInMenu() {
        cafe.addIngredient(bread, 100);
        cafe.addIngredient(sauce, 100);
        cafe.addIngredient(cutlet, 100);
        cafe.addIngredient(cheese, 100);
        cafe.addIngredient(tomatoSlice, 100);
        cafe.addIngredient(syrupCola, 100);
        cafe.addIngredient(syrupSprite, 100);
        cafe.addIngredient(water, 100);
        cafe.addIngredient(potato, 100);
        cafe.addIngredient(bacon, 100);
        cafe.setTerminal(termReal);
        cafe.addFoodToOrder(burgerOriginal);
        cafe.addFoodToOrder(stillWater);
        cafe.addFoodToOrder(frenchFries);
        assertEquals(0, cafe.getOrder().size());
    }

    @org.junit.jupiter.api.Test
    void tryToMakeNormalOrderNotInStock() {
        cafe.addFoodToMenu(burgerOriginal);
        cafe.addFoodToMenu(burgerBacon);
        cafe.addFoodToMenu(burgerMaxi);
        cafe.addFoodToMenu(stillWater);
        cafe.addFoodToMenu(cola);
        cafe.addFoodToMenu(sprite);
        cafe.addFoodToMenu(frenchFries);
        cafe.setTerminal(termReal);
        cafe.addFoodToOrder(burgerOriginal);
        cafe.addFoodToOrder(stillWater);
        cafe.addFoodToOrder(frenchFries);
        assertEquals(0, cafe.getOrder().size());
    }

    @org.junit.jupiter.api.Test
    void makeBigOrderRightPrice() {
        cafe.addIngredient(bread, 100);
        cafe.addIngredient(sauce, 100);
        cafe.addIngredient(cutlet, 100);
        cafe.addIngredient(cheese, 100);
        cafe.addIngredient(tomatoSlice, 100);
        cafe.addIngredient(syrupCola, 100);
        cafe.addIngredient(syrupSprite, 100);
        cafe.addIngredient(water, 100);
        cafe.addIngredient(potato, 100);
        cafe.addIngredient(bacon, 100);
        cafe.setTerminal(termReal);
        cafe.addFoodToMenu(burgerOriginal);
        cafe.addFoodToMenu(burgerBacon);
        cafe.addFoodToMenu(burgerMaxi);
        cafe.addFoodToMenu(stillWater);
        cafe.addFoodToMenu(cola);
        cafe.addFoodToMenu(sprite);
        cafe.addFoodToMenu(frenchFries);
        cafe.addFoodToOrder(burgerOriginal);
        cafe.addFoodToOrder(stillWater);
        cafe.addFoodToOrder(frenchFries);
        cafe.addFoodToOrder(frenchFries);
        cafe.addFoodToOrder(frenchFries);
        cafe.removeFoodFromOrder(frenchFries);
        cafe.addFoodToOrder(cola);
        double expectedPrice = 0.0;
        expectedPrice += burgerOriginal.getPrice();
        expectedPrice += stillWater.getPrice();
        expectedPrice += frenchFries.getPrice();
        expectedPrice += frenchFries.getPrice();
        expectedPrice += cola.getPrice();
        double sumPrice = 0.0;
        for (Food food : cafe.getOrder().keySet()) {
            sumPrice += food.getPrice() * cafe.getOrder().get(food);
        }
        assertEquals(expectedPrice, sumPrice);
    }

    @org.junit.jupiter.api.Test
    void checkOrderSavedAndRemoved() {
        cafe.addIngredient(bread, 100);
        cafe.addIngredient(sauce, 100);
        cafe.addIngredient(cutlet, 100);
        cafe.addIngredient(cheese, 100);
        cafe.addIngredient(tomatoSlice, 100);
        cafe.addIngredient(syrupCola, 100);
        cafe.addIngredient(syrupSprite, 100);
        cafe.addIngredient(water, 100);
        cafe.addIngredient(potato, 100);
        cafe.addIngredient(bacon, 100);
        cafe.setTerminal(termReal);
        cafe.addFoodToMenu(burgerOriginal);
        cafe.addFoodToMenu(burgerBacon);
        cafe.addFoodToMenu(burgerMaxi);
        cafe.addFoodToMenu(stillWater);
        cafe.addFoodToMenu(cola);
        cafe.addFoodToMenu(sprite);
        cafe.addFoodToMenu(frenchFries);
        cafe.addFoodToOrder(burgerOriginal);
        cafe.addFoodToOrder(stillWater);
        cafe.addFoodToOrder(frenchFries);
        cafe.addFoodToOrder(frenchFries);
        cafe.addFoodToOrder(frenchFries);
        cafe.removeFoodFromOrder(frenchFries);
        cafe.addFoodToOrder(cola);
        cafe.finishOrder();
        assertEquals(0, cafe.getOrder().size());
        assertEquals(1, cafe.getAllOrders().size());
    }

    @org.junit.jupiter.api.Test
    void checkOrderSavedAndRemovedDifferentOrders() {
        cafe.addIngredient(bread, 100);
        cafe.addIngredient(sauce, 100);
        cafe.addIngredient(cutlet, 100);
        cafe.addIngredient(cheese, 100);
        cafe.addIngredient(tomatoSlice, 100);
        cafe.addIngredient(syrupCola, 100);
        cafe.addIngredient(syrupSprite, 100);
        cafe.addIngredient(water, 100);
        cafe.addIngredient(potato, 100);
        cafe.addIngredient(bacon, 100);
        cafe.setTerminal(termReal);
        cafe.addFoodToMenu(burgerOriginal);
        cafe.addFoodToMenu(burgerBacon);
        cafe.addFoodToMenu(burgerMaxi);
        cafe.addFoodToMenu(stillWater);
        cafe.addFoodToMenu(cola);
        cafe.addFoodToMenu(sprite);
        cafe.addFoodToMenu(frenchFries);
        cafe.addFoodToOrder(burgerOriginal);
        cafe.addFoodToOrder(stillWater);
        cafe.addFoodToOrder(frenchFries);
        cafe.addFoodToOrder(frenchFries);
        cafe.addFoodToOrder(frenchFries);
        cafe.removeFoodFromOrder(frenchFries);
        cafe.addFoodToOrder(cola);
        cafe.finishOrder();
        cafe.addFoodToOrder(stillWater);
        cafe.addFoodToOrder(frenchFries);
        cafe.addFoodToOrder(frenchFries);
        cafe.finishOrder();
        cafe.setTerminal(termDig);
        cafe.addFoodToOrder(burgerOriginal);
        cafe.finishOrder();
        cafe.setTerminal(termDrive);
        cafe.addFoodToOrder(burgerOriginal);
        cafe.addFoodToOrder(stillWater);
        cafe.addFoodToOrder(frenchFries);
        cafe.addFoodToOrder(frenchFries);
        cafe.addFoodToOrder(frenchFries);
        cafe.removeFoodFromOrder(frenchFries);
        cafe.addFoodToOrder(cola);
        cafe.addFoodToOrder(burgerMaxi);
        cafe.finishOrder();
        assertEquals(4, cafe.getAllOrders().size());
    }

    @org.junit.jupiter.api.Test
    void checkMoneyEarned() {
        cafe.addIngredient(bread, 100);
        cafe.addIngredient(sauce, 100);
        cafe.addIngredient(cutlet, 100);
        cafe.addIngredient(cheese, 100);
        cafe.addIngredient(tomatoSlice, 100);
        cafe.addIngredient(syrupCola, 100);
        cafe.addIngredient(syrupSprite, 100);
        cafe.addIngredient(water, 100);
        cafe.addIngredient(potato, 100);
        cafe.addIngredient(bacon, 100);
        cafe.setTerminal(termReal);
        burgerOriginal.setPrice(1);
        burgerBacon.setPrice(1);
        burgerMaxi.setPrice(1);
        stillWater.setPrice(1);
        cola.setPrice(1);
        sprite.setPrice(1);
        frenchFries.setPrice(1);
        cafe.addFoodToMenu(burgerOriginal);
        cafe.addFoodToMenu(burgerBacon);
        cafe.addFoodToMenu(burgerMaxi);
        cafe.addFoodToMenu(stillWater);
        cafe.addFoodToMenu(cola);
        cafe.addFoodToMenu(sprite);
        cafe.addFoodToMenu(frenchFries);
        cafe.addFoodToOrder(burgerOriginal);
        cafe.addFoodToOrder(stillWater);
        cafe.addFoodToOrder(frenchFries);
        cafe.addFoodToOrder(frenchFries);
        cafe.addFoodToOrder(frenchFries);
        cafe.removeFoodFromOrder(frenchFries);
        cafe.addFoodToOrder(cola);
        cafe.finishOrder();
        cafe.addFoodToOrder(stillWater);
        cafe.addFoodToOrder(frenchFries);
        cafe.addFoodToOrder(frenchFries);
        cafe.finishOrder();
        cafe.setTerminal(termDig);
        cafe.addFoodToOrder(burgerOriginal);
        cafe.addFoodToOrder(frenchFries);
        cafe.removeFoodFromOrder(frenchFries);
        cafe.finishOrder();
        cafe.setTerminal(termDrive);
        cafe.addFoodToOrder(burgerOriginal);
        cafe.addFoodToOrder(stillWater);
        cafe.addFoodToOrder(frenchFries);
        cafe.addFoodToOrder(frenchFries);
        cafe.addFoodToOrder(frenchFries);
        cafe.removeFoodFromOrder(frenchFries);
        cafe.addFoodToOrder(cola);
        cafe.addFoodToOrder(burgerMaxi);
        cafe.finishOrder();
        assertEquals(PRICEFIFTEEN, cafe.getMoney());
    }

    @org.junit.jupiter.api.Test
    void test() {
        BurgerBooth cafe = new BurgerBooth();
        TerminalRealCheckout termReal = new TerminalRealCheckout();
        TerminalDigitalCheckout termDig = new TerminalDigitalCheckout();
        TerminalDriveIn termDrive = new TerminalDriveIn();
        String bread = "bread";
        String sauce = "sauce";
        String cutlet = "cutlet";
        String cheese = "cheese";
        String tomatoSlice = "tomato slice";
        String syrupCola = "Cola syrup";
        String syrupSprite = "Sprite syrup";
        String water = "water";
        String potato = "potato";
        String bacon = "bacon";
        List<String> burgerOriginalIngredients = new ArrayList<>(Arrays.asList(bread, sauce, cutlet, cheese,
                tomatoSlice, sauce, bread));
        Food burgerOriginal = new Food(PRICE16, "BurgerOriginal", burgerOriginalIngredients, FoodType.BURGER);
        List<String> burgerMaxiIngredients = new ArrayList<>(Arrays.asList(bread, sauce, cutlet, cheese, cutlet, cheese,
                tomatoSlice, sauce, bread));
        Food burgerMaxi = new Food(PRICE22, "BurgerMaxi", burgerMaxiIngredients, FoodType.BURGER);
        List<String> burgerBaconIngredients = new ArrayList<>(Arrays.asList(bread, sauce, cutlet, cheese,
                bacon, tomatoSlice, sauce, bread));
        Food burgerBacon = new Food(PRICE24, "BurgerBacon", burgerBaconIngredients, FoodType.BURGER);
        List<String> potatoList = new ArrayList<>(Collections.singletonList(potato));
        Food frenchFries = new Food(1, "French fries", potatoList, FoodType.SNACK);
        List<String> waterList = new ArrayList<>(Collections.singletonList(water));
        Food stillWater = new Food(PRICE08, "Still water", waterList, FoodType.COLDDRINK);
        List<String> colaList = new ArrayList<>(Arrays.asList(water, syrupCola));
        Food cola = new Food(PRICE11, "Cola", colaList, FoodType.COLDDRINK);
        List<String> spriteList = new ArrayList<>(Arrays.asList(water, syrupSprite));
        Food sprite = new Food(PRICE11, "Sprite", spriteList, FoodType.COLDDRINK);

        cafe.addIngredient(bread, 100);
        cafe.addIngredient(sauce, 100);
        cafe.addIngredient(cutlet, 100);
        cafe.addIngredient(cheese, 100);
        cafe.addIngredient(tomatoSlice, 100);
        cafe.addIngredient(syrupCola, 100);
        cafe.addIngredient(syrupSprite, 100);
        cafe.addIngredient(water, 100);
        cafe.addIngredient(potato, 100);
        cafe.addIngredient(bacon, 100);
        cafe.setTerminal(termReal);
        cafe.addFoodToMenu(burgerOriginal);
        cafe.addFoodToMenu(burgerBacon);
        cafe.addFoodToMenu(burgerMaxi);
        cafe.addFoodToMenu(stillWater);
        cafe.addFoodToMenu(cola);
        cafe.addFoodToMenu(sprite);
        cafe.addFoodToMenu(frenchFries);
        cafe.addFoodToOrder(burgerOriginal);
        cafe.addFoodToOrder(stillWater);
        cafe.addFoodToOrder(frenchFries);
        cafe.addFoodToOrder(frenchFries);
        cafe.addFoodToOrder(frenchFries);
        cafe.removeFoodFromOrder(frenchFries);
        cafe.addFoodToOrder(cola);
        System.out.println(cafe.finishOrder());

        cafe.addFoodToOrder(burgerOriginal);
        cafe.addFoodToOrder(burgerBacon);
        cafe.addFoodToOrder(burgerOriginal);
        cafe.addFoodToOrder(burgerMaxi);
        cafe.addFoodToOrder(burgerOriginal);
        cafe.addFoodToOrder(stillWater);
        cafe.addFoodToOrder(frenchFries);
        cafe.addFoodToOrder(frenchFries);
        cafe.addFoodToOrder(frenchFries);
        cafe.removeFoodFromOrder(frenchFries);
        cafe.addFoodToOrder(cola);
        System.out.println(cafe.finishOrder());

        System.out.println(cafe.getAllOrders());
    }
}
